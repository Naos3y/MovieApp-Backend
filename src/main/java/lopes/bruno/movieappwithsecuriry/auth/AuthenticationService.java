package lopes.bruno.movieappwithsecuriry.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lopes.bruno.movieappwithsecuriry.config.JwtService;
import lopes.bruno.movieappwithsecuriry.entity.Role;
import lopes.bruno.movieappwithsecuriry.entity.User;
import lopes.bruno.movieappwithsecuriry.exception.AuthenticationFailedException;
import lopes.bruno.movieappwithsecuriry.exception.UserAlreadyExistsException;
import lopes.bruno.movieappwithsecuriry.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        if (repository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already taken");
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .userMovies(new HashSet<>())
                .build();

        repository.save(user);
        var token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            log.info("Starting authentication for email: {}", request.getEmail());

            // Attempt authentication
            log.debug("Attempting authentication with provided credentials.");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Retrieve user details
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> {
                        log.error("User with email {} not found", request.getEmail());
                        return new IllegalArgumentException("User not found");
                    });
            log.info("User {} authenticated successfully", user.getEmail());

            // Generate token for the authenticated user
            var token = jwtService.generateToken(user);
            log.info("JWT token generated for user: {}", user.getUsername());

            // Return authentication response
            return AuthenticationResponse.builder()
                    .token(token)
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();

        } catch (AuthenticationException e) {
            log.error("Authentication failed for email {}: Invalid email or password", request.getEmail());
            throw new AuthenticationFailedException("Invalid email or password");
        } catch (Exception e) {
            log.error("An unexpected error occurred during authentication for email {}: {}", request.getEmail(), e.getMessage());
            throw e;
        }
    }

}
