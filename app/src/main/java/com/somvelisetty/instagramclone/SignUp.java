package com.somvelisetty.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private TextView edtusername, edtpassword, edtemail;
    private Button btnlogin, btnsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign Up");

        edtemail = findViewById(R.id.edtEnterEmail);
        edtusername = findViewById(R.id.edtUserName);
        edtpassword = findViewById(R.id.edtPassword);

        edtpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    onClick(btnsignin);
                }
                return false;
            }
        });

        btnlogin = findViewById(R.id.btnLogin);
        btnsignin = findViewById(R.id.btnSignUp);

        btnlogin.setOnClickListener(this);
        btnsignin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null)
        {
            transitionToSocialMediaActivity();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnLogin:

                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);

                break;
            case R.id.btnSignUp:

                if (edtemail.getText().toString().equals("") || edtusername.getText().equals("") || edtpassword.getText().toString().equals(""))
                {
                    FancyToast.makeText(SignUp.this, "Email, username and password are required for sign up.", Toast.LENGTH_LONG, FancyToast.INFO, true).show();
                } else
                {
                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtemail.getText().toString());
                appUser.setUsername(edtusername.getText().toString());
                appUser.setPassword(edtpassword.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up" + edtusername.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)
                        {
                            FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up.", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            transitionToSocialMediaActivity();
                        } else
                        {
                            FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                        progressDialog.dismiss();
                    }
                });

                break;
                }
        }

    }

    public void rootLayoutTapped(View v)
    {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e)
        {

        }

    }

    private void transitionToSocialMediaActivity()
    {
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
