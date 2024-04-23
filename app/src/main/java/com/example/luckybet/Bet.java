package com.example.luckybet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import java.io.File;
import java.util.List;

public class Bet extends Activity {
    User userl;
    List<Coupon> coupons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bet);

        //navi dolne
        ImageButton btn_addbalance = (ImageButton) findViewById(R.id.btn_baddbalance);
        ImageButton btn_account = (ImageButton) findViewById(R.id.btn_baccount);
        ImageButton btn_withdraw = (ImageButton) findViewById(R.id.btn_bwithdraw);
        ImageButton btn_matches = (ImageButton) findViewById(R.id.btn_bmatches);
        ImageButton btn_bet = (ImageButton) findViewById(R.id.btn_bbet);

        TextView balance = (TextView) findViewById(R.id.tbx_bbalance);

        //Intent
        userl = GetUser();

        //saldo
        balance.setText(userl.getBalance() +" zł");

        //Wczytanie i wyswietlenie kuponow z pliku
        coupons = DeserializeCoupons();

        if(coupons != null){
            AddCoupons();
        }

        //btn dodaj saldo
        btn_addbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Bet.this, R.string.lbl_invalidDeposit, Toast.LENGTH_LONG).show();
            }
        });

        //btn konto
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Bet.this, Account.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

        //btn wyplac
        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Bet.this, Withdraw.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

        //btn mecze
        btn_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Bet.this, Matches.class);
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

    public List<Coupon> DeserializeCoupons()
    {
        try
        {
            File directory = this.getFilesDir();
            File file = new File(directory, "coupons.xml");
            List<Coupon> coupons;
            return coupons = CouponXmlParser.parse(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            List<Coupon> coupons;
            return coupons = null;
        }
    }

    public void AddCoupons(){
        try{
        ConstraintLayout bet = (ConstraintLayout) findViewById(R.id.bg_bbet);

        //legenda
        TextView txt_status = new TextView(this);
        TextView txt_game = new TextView(this);
        TextView txt_selected = new TextView(this);
        TextView txt_multistake = new TextView(this);
        TextView txt_towin = new TextView(this);

            txt_status.setPadding(50,0,0,0);
            txt_status.setText(R.string.txt_statususer);
            bet.addView(txt_status);

            txt_game.setPadding(500,0,0,0);
            txt_game.setText(R.string.txt_game);
            bet.addView(txt_game);

            txt_selected.setPadding(800,0,0,0);
            txt_selected.setText(R.string.txt_selected);
            bet.addView(txt_selected);

            txt_multistake.setPadding(1000,0,0,0);
            txt_multistake.setText(R.string.txt_multistake);
            bet.addView(txt_multistake);

            txt_towin.setPadding(1150,0,0,0);
            txt_towin.setText(R.string.txt_towin);
            bet.addView(txt_towin);

        //kupony
        TextView[] lbl_status = new TextView[coupons.size()];
        TextView[] lbl_userid = new TextView[coupons.size()];
        TextView[] lbl_home = new TextView[coupons.size()];
        TextView[] lbl_away = new TextView[coupons.size()];
        TextView[] lbl_selected = new TextView[coupons.size()];
        TextView[] lbl_multi = new TextView[coupons.size()];
        TextView[] lbl_stake = new TextView[coupons.size()];
        TextView[] lbl_towin = new TextView[coupons.size()];

        for(int i = 0; i < coupons.size();i++)
        {
            lbl_status[i] = new TextView(this);
            lbl_status[i].setPadding(50,i*400+100,0,0);
            lbl_status[i].setText(R.string.txt_status);
            bet.addView(lbl_status[i]);

            lbl_userid[i] = new TextView(this);
            lbl_userid[i].setPadding(50,i*400+200,0,0);
            lbl_userid[i].setText(coupons.get(i).getUserid());
            bet.addView(lbl_userid[i]);

            lbl_home[i] = new TextView(this);
            lbl_home[i].setPadding(500,i*400+100,0,0);
            lbl_home[i].setText(coupons.get(i).getHome());
            bet.addView(lbl_home[i]);

            lbl_away[i] = new TextView(this);
            lbl_away[i].setPadding(500,i*400+200,0,0);
            lbl_away[i].setText(coupons.get(i).getAway());
            bet.addView(lbl_away[i]);

            lbl_selected[i] = new TextView(this);
            lbl_selected[i].setPadding(800,i*400+100,0,0);
            lbl_selected[i].setText(coupons.get(i).getSelectedteam());
            bet.addView(lbl_selected[i]);

            lbl_multi[i] = new TextView(this);
            lbl_multi[i].setPadding(1000,i*400+100,0,0);
            lbl_multi[i].setText(coupons.get(i).getMultiplier());
            bet.addView(lbl_multi[i]);

            lbl_stake[i] = new TextView(this);
            lbl_stake[i].setPadding(1000,i*400+200,0,0);
            lbl_stake[i].setText(coupons.get(i).getStake());
            bet.addView(lbl_stake[i]);

            lbl_towin[i] = new TextView(this);
            lbl_towin[i].setPadding(1200,i*400+100,0,0);
            //int staketowin = Integer.valueOf(coupons.get(i).getStake())*Integer.valueOf(coupons.get(i).getMultiplier());
            float staketowin = Float.valueOf(lbl_multi[i].getText().toString()) * Float.valueOf(lbl_stake[i].getText().toString());
            lbl_towin[i].setText(String.valueOf(staketowin));
            //lbl_towin[i].setText("997");
            bet.addView(lbl_towin[i]);}}

        catch (Exception e)
        {
            Log.d("STATE",e.toString());
        }
        }



    }
