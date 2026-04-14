package com.kluniversity.jwt.model;

public class AuthDTOs {

    public static class AuthRequest {
        private String username;
        private String password;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String token;
        private String username;
        private String role;
        private String message;

        public AuthResponse(String token, String username, String role, String message) {
            this.token = token;
            this.username = username;
            this.role = role;
            this.message = message;
        }

        public String getToken()    { return token; }
        public String getUsername() { return username; }
        public String getRole()     { return role; }
        public String getMessage()  { return message; }
    }
}
