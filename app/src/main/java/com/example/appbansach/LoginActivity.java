package com.example.appbansach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appbansach.MainActivity;
import com.example.appbansach.R;
import com.example.appbansach.model.User;
import com.google.gson.Gson;


public class LoginActivity extends AppCompatActivity {
    Button btLogin, btRegister;
    EditText edUserNameC, edPasswordC;
    SharedPreferences.Editor editor;

    private final Gson gson = new Gson();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().setTitle("Login");
        //
        anhxa();
        //
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        //
        taosukien();
        //
        //
    }
    private void taosukien() {
        btLogin.setOnClickListener(view -> checkUserLogin ());
        btRegister.setOnClickListener(funRegister());
    }

    private void anhxa() {
        edUserNameC = findViewById(R.id.eduser);
        edPasswordC = findViewById(R.id.edpassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);

    }
    @NonNull
    private View.OnClickListener funRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }

    private void checkUserLogin() {
        String userPref = sharedPreferences.getString(Utils.KEY_USER, null);
        User user = gson.fromJson(userPref, User.class);
        if (user == null) {
            return;
        }

        boolean isValid = edUserNameC.getText().toString().trim().equals(user.getUserName()) && edPasswordC.getText().toString().trim().equals(user.getPassword());
        if (isValid) {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Utils.KEY_USER_PROFILE, user);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

        }
    }


    boolean checkUserName(String username) {
        if (username.isEmpty()) {
            edUserNameC.setError("Vui lòng nhập");
            return false;
        }
        return true;
    }



    boolean checkPassword(String password) {
        if (password.isEmpty()) {
            edPasswordC.setError("Vui lòng nhập");
            return false;
        }
        return true;
    }

};



