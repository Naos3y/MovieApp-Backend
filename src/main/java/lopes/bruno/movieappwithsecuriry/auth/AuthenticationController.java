package lopes.bruno.movieappwithsecuriry.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        log.info("=== START Registration Request ===");
        log.info("Incoming registration request for username: {}", request.getUsername());
        log.info("Incoming registration request for email: {}", request.getEmail());

        try {
            var response = service.register(request);
            log.info("=== Successfully registered user: {} ===", request.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("=== Registration failed ===", e);
            log.error("Error message: {}", e.getMessage());
            throw e;
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
