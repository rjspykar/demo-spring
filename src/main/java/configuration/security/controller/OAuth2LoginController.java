package configuration.security.controller;

import configuration.security.OAuthImpl.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Map;

    @RestController
public class OAuth2LoginController {
    private final JwtDecoder jwtDecoder;
    public OAuth2LoginController(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }
    @PostMapping("/oauth2/login")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        Jwt decodedToken = jwtDecoder.decode(token);
        String email = decodedToken.getClaim("email");
        String jwtToken = generateJwtTokenForUser(email); // Implement this method
        return ResponseEntity.ok(jwtToken);
    }
    private String generateJwtTokenForUser(String email) {
        return JwtUtil.generateToken(email);
    }
}
