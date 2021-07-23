package com.example.userdetailsviewingapp.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userdetailsviewingapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText userName,password;
    Button loginButton;
    TextView usernameMsg,passwordMsg;
    TextInputLayout passwordParent,usernameParent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_layout);

        userName=findViewById(R.id.username_id);
        password=findViewById(R.id.password_id);
        loginButton=findViewById(R.id.login_button_id);
        usernameMsg=findViewById(R.id.username_msg_id);
        passwordMsg=findViewById(R.id.password_msg_id);
        passwordParent=findViewById(R.id.password_parent_id);
        usernameParent=findViewById(R.id.username_parent_id);


        loginButton.setOnClickListener(v -> checkCredentials(userName.getText().toString(),password.getText().toString()));




    }

   public void checkCredentials(String username,String password){

            boolean result1=emailValidator(username);
            boolean result2=passwordValidator(password);

            if(result1 && result2){
            passwordParent.setHelperText("Valid");
            usernameParent.setHelperText("Valid");
            Intent intent=new Intent(LoginActivity.this, FirstActivity.class);
            startActivity(intent);
            }
            else if(!result1 && result2){
                    usernameMsg.setVisibility(View.VISIBLE);
                    usernameParent.setHelperText("*username should be in email format");
            }
            else if(!result1 && !result2){
                passwordMsg.setVisibility(View.VISIBLE);
                passwordParent.setHelperText("*should contain 1 uppercase,1 special character,min 8 characters");
                usernameParent.setHelperText("*username should be in email format");
            }
            else{
                passwordParent.setHelperText("*should contain 1 uppercase,1 special character,min 8 characters");
            }


    }

    public boolean emailValidator(String username){
         final String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }
    public boolean passwordValidator(String password){
        final String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}