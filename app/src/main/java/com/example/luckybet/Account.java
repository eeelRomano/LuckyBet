package com.example.luckybet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Account extends Activity {
    User userl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        //tbx dane uzytkownika
        TextView login = (TextView) findViewById(R.id.lbl_alogin);
        TextView email = (TextView) findViewById(R.id.lbl_aemail);
        TextView fname = (TextView) findViewById(R.id.lbl_afname);
        TextView lname = (TextView) findViewById(R.id.lbl_aalname);
        TextView phone = (TextView) findViewById(R.id.lbl_aphone);

        //navi dolne
        ImageButton btn_addbalance = (ImageButton) findViewById(R.id.btn_addbalance);
        ImageButton btn_account = (ImageButton) findViewById(R.id.btn_account);
        ImageButton btn_withdraw = (ImageButton) findViewById(R.id.btn_withdraw);
        ImageButton btn_matches = (ImageButton) findViewById(R.id.btn_matches);
        ImageButton btn_bet = (ImageButton) findViewById(R.id.btn_bet);

        TextView balance = (TextView) findViewById(R.id.tbx_balance);

        //Intent
        userl = GetUser();

        //saldo
        balance.setText(userl.getBalance() +" zł");

        //dane uzytkownika
        login.setText(userl.getLogin());
        email.setText(userl.getEmail());
        fname.setText(userl.getFname());
        lname.setText(userl.getLname());
        phone.setText(userl.getPhone());

        //testy
        finish();
        Intent intent = new Intent(Account.this, Matches.class);
        intent.putExtra("loggeduser", userl);
        startActivity(intent);

        //btn dodaj saldo
        btn_addbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Account.this, R.string.lbl_invalidDeposit, Toast.LENGTH_LONG).show();
            }
        });

        //btn wyplac
        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Account.this, Withdraw.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

        //btn mecze
        btn_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Account.this, Matches.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

        //btn kupon
        btn_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Account.this, Bet.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

    }

    public User GetUser()
    {
        try{
            User user;
            Intent i = getIntent();
            return user = (User) getIntent().getSerializableExtra("loggeduser");
        }
        catch (Exception e)
        {
            Log.d("STATE", e.toString());
            User user = User.UserError();
            return user;
        }
    }
}
