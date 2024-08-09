package com.example.appbansach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.appbansach.R;
import com.example.appbansach.model.User;
import com.google.gson.Gson;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {
    private EditText edUserNameC;

    private EditText edPasswordC;

    private EditText edConfirmPasswordC;

    private EditText edEmailC;

    private EditText edPhoneNumberC;

    private EditText edAddress;

    private Button btnRegister;

    private ImageButton imBack;

    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    private final Gson gson= new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedPreferences=getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //
        anhxadulieu();
        taosukien();
    }

    void anhxadulieu()
    {
        edUserNameC = findViewById(R.id.edUserName);
        edPasswordC= findViewById(R.id.edPasswordRe);
        edConfirmPasswordC=findViewById(R.id.edt_confirm_password);
        edEmailC=findViewById(R.id.edEmail);
        edPhoneNumberC= findViewById(R.id.edPhone);
        edAddress=findViewById(R.id.eddiachi);
        btnRegister=findViewById(R.id.btRegister);
        imBack= findViewById(R.id.imbBack);
    }



    void taosukien()
    {
        btnRegister.setOnClickListener(view -> sukienRegister());
        imBack.setOnClickListener(view -> finish());
    }

    void sukienRegister()
    {
        String userName = edUserNameC.getText().toString().trim();
        String password = edPasswordC.getText().toString().trim();
        String confirmPassword= edConfirmPasswordC.getText().toString().trim();
        String email= edEmailC.getText().toString().trim();
        String phone = edPhoneNumberC.getText().toString().trim();
        String address = edAddress.getText().toString().trim();


        boolean isValid = checkUserName(userName) && checkPassword(password, confirmPassword);
        if(isValid)
        {
            User userNew = new User();
            userNew.setUserName(userName);
            userNew.setPassword(password);
            userNew.setEmail(email);
            userNew.setPhoneNumber(phone);
            userNew.setAddress(address);
            //

            String userStr = gson.toJson(userNew);
            editor.putString(Utils.KEY_USER, userStr);
            editor.commit();
            //
            Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private boolean checkUserName(String userName)
    {
        if (userName.isEmpty())
        {
            edUserNameC.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }
        if (userName.length()<= 5)
        {
            edUserNameC.setError("Tên đăng nhập phải ít nhất 6 kí tự");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password, String confirmPassword)
    {
        if(password.isEmpty()) {
            edPasswordC.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        if (password.length()<= 5)
        {
            edPasswordC.setError("Mật khẩu phải lớn hơn 5 kí tự");
            return false;
        }
        if(!password.equals(confirmPassword))
        {
            edConfirmPasswordC.setError("Xác nhận mật khẩu không khớp");
            return false;
        }
        return true;
    }
}


