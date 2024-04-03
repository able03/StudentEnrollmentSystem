package com.example.studentenrollmentsystem;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.w3c.dom.Text;

public class ValidationClass {
    Context context;




    public boolean LetterLengthValid(String word){
        if(word.length() > 4 && word.length() < 10){
            return true;
        }else{
            return false;
        }
    }
    public boolean ContainsSpecialChar(String word){
        if(LetterLengthValid(word)){
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(c == '?' || c == '!'){
                    return true;
                }else{
                }
            }
        }
        return false;
    }

    public boolean ContainsCapitalLetter(String word){
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);

            if(Character.isUpperCase(c)){

                return true;
            }else{

            }

        }

        return false;
    }

    public boolean ContainsDigit(String word){
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            } else {

            }
        }

        return false;
    }

    public boolean UsernameValidation(String word){
        if(LetterLengthValid(word)){
            return true;
        }else{
            return false;
        }
    }


    public boolean PasswordValidation(String word){
        if(LetterLengthValid(word) && ContainsCapitalLetter(word) && ContainsDigit(word) && ContainsSpecialChar(word)){
            return true;
        }else{
            return false;
        }
    }

    public boolean IsStringEmpty(String word){
        if(word.isEmpty()){
            return true;
        }else{

            return false;
        }
    }

    public boolean ContactNumberValidation(String word){
        if(word.substring(0,4).contains("0920")){
            return true;
        }else{
            return false;
        }
    }

    public boolean EmailValidation(String word){
        if(word.contains("@")){
            if(word.contains("@yahoo.com") || word.contains("@google.com") || word.contains("sti.edu") ){
                String substr = word.substring(Math.max(0,word.length() - 3));
                if(substr.contains("com") || substr.contains("edu")){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }

        }else{
            return false;
        }
    }



}


