package edu.nu.corporate_portal.DTO.Auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AzureLoginRequest {

    private Auth auth;
    private String expires;

    @Setter
    @Getter
    public static class Auth {
        private User user;

    }

    @Setter
    @Getter
    public static class User {
        private String name;
        private String email;
        private String id;
        private String accessToken;

    }
}
