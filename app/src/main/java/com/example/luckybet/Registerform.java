package com.example.luckybet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class Registerform extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerform);
        Button btn_rregister = (Button) findViewById(R.id.btn_rregister);
        Button btn_back = (Button) findViewById(R.id.btn_back);

        //Powrot
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Zarejestruj
        btn_rregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //walidacja wprowadzonych danych
                EditText tbx_rlogin = (EditText) findViewById(R.id.tbx_rlogin);
                EditText tbx_rpassword = (EditText) findViewById(R.id.tbx_rpassword);
                EditText tbx_remail = (EditText) findViewById(R.id.tbx_remail);
                EditText tbx_rfname = (EditText) findViewById(R.id.tbx_rfname);
                EditText tbx_rlname = (EditText) findViewById(R.id.tbx_rlname);
                EditText tbx_phone = (EditText) findViewById(R.id.tbx_rphone);

                if(tbx_rlogin.equals("") || tbx_rlogin.equals(" ") || tbx_rpassword.equals("") || tbx_rpassword.equals(" ")
                        || tbx_remail.equals("") || tbx_remail.equals(" ") || tbx_rfname.equals("") || tbx_rfname.equals(" ")
                        || tbx_rlname.equals("") || tbx_rlname.equals(" ") || tbx_phone.equals("") || tbx_phone.equals(" "))
                {
                    Toast.makeText(Registerform.this, R.string.lbl_emptyinput, Toast.LENGTH_LONG).show();
                }
                else{
                    String login = tbx_rlogin.getText().toString();
                    String pass = tbx_rpassword.getText().toString();
                    String email = tbx_remail.getText().toString();
                    String fname = tbx_rfname.getText().toString();
                    String lname = tbx_rlname.getText().toString();
                    String phone = tbx_phone.getText().toString();
                    if(login.equals("") || login.equals(" ") || pass.equals("") || pass.equals(" ")
                            || email.equals("") || email.equals(" ") || fname.equals("") || fname.equals(" ")
                            || lname.equals("") || lname.equals(" ") || phone.equals("") || phone.equals(" "))
                    {
                        Toast.makeText(Registerform.this, R.string.lbl_emptyinput, Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        User user = new User(login, pass, email, fname, lname, phone, "10");
                        RegisterUser(user);
                        Intent intent = new Intent(Registerform.this, Account.class);
                        intent.putExtra("loggeduser", user);
                        startActivity(intent);
                        Toast.makeText(Registerform.this, R.string.lbl_registersuccess, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void RegisterUser(User user)
    {
        try {
            File directory = this.getFilesDir();
            File file = new File(directory, "users.xml");
            //deserializacja
            List<User> users = UserXmlParser.parse(file);

            //walidacja nowego uzytkownika

            //Nowy uzytkownik
            users.add(user);

            //serializacja
            UserXmlParser.serializeUsers(users, file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

