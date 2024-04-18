package com.example.luckybet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    List<User> users;
    int kod = 1010;
    int userbalance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);
        EditText tbx_login = (EditText) findViewById(R.id.tbx_login);
        EditText tbx_password = (EditText) findViewById(R.id.pwd_password);

        //User user = new User(login, pass, email, fname, lname, phone);
        //User user = new User("admin", "admin", "admin@luckybet.pl", "Admin", "Admin", "000111222", "100000");
        //RegisterUser(user);

        Deserialize();
        Coupon coupon = new Coupon("admin","Psg","Barcelona","Psg","2.10","2");
        CreateCoupon(coupon);

        //testy
        tbx_login.setText("johnny");
        tbx_password.setText("johnny");
        String login = tbx_login.getText().toString();
        String password = tbx_password.getText().toString();
        Authorize(login, password);

        //login enterem
        tbx_password.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    String login = tbx_login.getText().toString();
                    String password = tbx_password.getText().toString();
                    Authorize(login, password);
                    return true;
                }
                return false;
            }
        });

        //btn login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = tbx_login.getText().toString();
                String password = tbx_password.getText().toString();
                Authorize(login, password);
            }
        });

        //btn register
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registerform.class);
                startActivity(intent);
            }
        });


    }

    public void Deserialize()
    {
        try
        {
            File directory = this.getFilesDir();
            File file = new File(directory, "users.xml");
            users = UserXmlParser.parse(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void Authorize(String log, String pwd)
    {
        for(User userl : users)
        {
            if(userl.getLogin(
            ).equals(log))
            {
                if(userl.getPassword().equals(pwd))
                {
                    //userbalance = Integer.parseInt(userl.getBalance());
                    Intent intent = new Intent(MainActivity.this, Account.class);
                    intent.putExtra("loggeduser", userl);
                    startActivityForResult(intent, kod);
                    break;
                }
                else
                {
                    Toast.makeText(MainActivity.this, R.string.lbl_invalidpwd, Toast.LENGTH_LONG).show();
                    break;
                }
            }
            else if(users.iterator().equals(users.size()))
            {
                Toast.makeText(MainActivity.this, R.string.lbl_invalidlogin, Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    public void CreateCoupon(Coupon coupon)
    {
        try {
            File directory = this.getFilesDir();
            File file = new File(directory, "coupons.xml");
            String text = "<Coupons>\n" +
                    "<Coupon>\n" +
                    "<Userid>admin</Userid>\n" +
                    "<Home>Psg</Home>\n" +
                    "<Away>Barcelona</Away>\n" +
                    "<SelectedTeam>Psg</SelectedTeam>\n" +
                    "<Multiplier>2.10</Multiplier>\n" +
                    "<Stake>2</Stake>\n" +
                    "</Coupon>\n" +
                    "</Coupons>";
            writeToFile(file, text);

            //deserializacja
            List<User> users = UserXmlParser.parse(file);

            //walidacja nowego uzytkownika

            //Nowy uzytkownik
            //users.add(user);

            //serializacja
            //UserXmlParser.serializeUsers(users, file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void RegisterUser(User user)
    {
        try {
            File directory = this.getFilesDir();
            File file = new File(directory, "users.xml");
            /*String text = "<Users>\n" +
                    "<User>\n" +
                    "<Login>admin</Login>\n" +
                    "<Password>admin</Password>\n" +
                    "<Email>admin@luckybet.pl</Email>\n" +
                    "<Fname>Admin</Fname>\n" +
                    "<Lname>Admin</Lname>\n" +
                    "<Phone>000111222</Phone>\n" +
                    "<Balance>100000</Balance>\n" +
                    "</User>\n" +
                    "</Users>";
            writeToFile(file, text);*/

            //deserializacja
            List<User> users = UserXmlParser.parse(file);

            //walidacja nowego uzytkownika

            //Nowy uzytkownik
            //users.add(user);

            //serializacja
            //UserXmlParser.serializeUsers(users, file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(File file, String content)
    {
        try{
        FileOutputStream writer = new FileOutputStream(file);
        writer.write(content.getBytes());
        writer.close();
    }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}