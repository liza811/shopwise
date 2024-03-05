package com.example.myapplicationliza;
import android.util.Patterns;
public class EmailValidator {




        public static boolean isValidEmail(CharSequence email) {
            return (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        }


}
