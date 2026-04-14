package com.kluniversity.auth.model;

public class AuthDTOs {

    public static class RegisterRequest {
        private String username;
        private String password;
        private String email;
        private String fullName;
        private String phone;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        private String message;
        private Long userId;
        private String username;

        public LoginResponse(String message, Long userId, String username) {
            this.message = message;
            this.userId = userId;
            this.username = username;
        }

        public String getMessage() { return message; }
        public Long getUserId() { return userId; }
        public String getUsername() { return username; }
    }

    public static class ApiResponse {
        private String message;
        private boolean success;

        public ApiResponse(String message, boolean success) {
            this.message = message;
            this.success = success;
        }

        public String getMessage() { return message; }
        public boolean isSuccess() { return success; }
    }
}
