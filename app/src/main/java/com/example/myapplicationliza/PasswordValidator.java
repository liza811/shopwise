package com.example.myapplicationliza;

public class PasswordValidator {


        public static boolean isValidPassword(String password) {
            // Check if password length is between 8 and 20 characters
            if (password.length() < 8 || password.length() > 20) {
                return false;
            }

            // Check if password contains at least one uppercase letter, one lowercase letter, one digit, and one special character
            String pattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";
            return password.matches(pattern);

    }

}
