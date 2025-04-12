package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.Auth.AzureLoginRequest;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.services.AzureTokenValidator;
import edu.nu.corporate_portal.services.JwtTokenService;
import edu.nu.corporate_portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AzureTokenValidator azureTokenValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/azure-login")
    public ResponseEntity<?> azureLogin(@RequestBody AzureLoginRequest request) {
        String azureToken = request.getAuth().getUser().getAccessToken();

        System.out.println("Azure Token: " + azureToken);

        Jwt jwt;
        try {
            jwt = azureTokenValidator.validateToken(azureToken);
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid Azure token: " + e.getMessage()));
        }

        String oid = jwt.getClaimAsString("oid");
        String role = jwt.getClaimAsString("role");
        String school = jwt.getClaimAsString("school");
        String phoneNumber = jwt.getClaimAsString("phone_number");
        String major = jwt.getClaimAsString("major");
        String birthday = jwt.getClaimAsString("birthday");
        String email = jwt.getClaimAsString("upn");
        String name = jwt.getClaimAsString("name");

        Optional<User> userOpt = userService.findByOid(oid);
        User user = userOpt.orElseGet(() -> userService.createAzureUser(
                oid, email, name, role, school, phoneNumber, major, birthday
        ));

        String accessJwt = jwtTokenService.generateAccessToken(user.getAzureSsoId());
        String refreshJwt = jwtTokenService.generateRefreshToken(user.getAzureSsoId());

        return ResponseEntity.ok(Map.of(
                "accessToken", accessJwt,
                "refreshToken", refreshJwt
        ));
    }
}
