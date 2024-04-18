package com.example.luckybet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Withdraw extends Activity {
    User userl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdraw);

        //navi dolne
        ImageButton btn_addbalance = (ImageButton) findViewById(R.id.btn_waddbalance);
        ImageButton btn_account = (ImageButton) findViewById(R.id.btn_waccount);
        ImageButton btn_withdraw = (ImageButton) findViewById(R.id.btn_wwithdraw);
        ImageButton btn_matches = (ImageButton) findViewById(R.id.btn_wmatches);
        ImageButton btn_bet = (ImageButton) findViewById(R.id.btn_wbet);

        TextView balance = (TextView) findViewById(R.id.tbx_wbalance);

        //Intent
        userl = GetUser();

        //saldo
        balance.setText(userl.getBalance() +" zł");

        //btn dodaj saldo
        btn_addbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Withdraw.this, R.string.lbl_invalidDeposit, Toast.LENGTH_LONG).show();
            }
        });

        //btn konto
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Withdraw.this, Account.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

        //btn mecze
        btn_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Withdraw.this, Matches.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

        //btn kupon
        btn_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Withdraw.this, Bet.class);
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
