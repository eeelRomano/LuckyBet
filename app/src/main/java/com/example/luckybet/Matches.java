package com.example.luckybet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Matches extends Activity {
    User userl;
    Coupon coupon;
    List<Game> games;
    List<Coupon> coupons;
    List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches);

        //navi dolne
        ImageButton btn_addbalance = (ImageButton) findViewById(R.id.btn_maddbalance);
        ImageButton btn_account = (ImageButton) findViewById(R.id.btn_maccount);
        ImageButton btn_withdraw = (ImageButton) findViewById(R.id.btn_mwithdraw);
        ImageButton btn_matches = (ImageButton) findViewById(R.id.btn_mmatches);
        ImageButton btn_bet = (ImageButton) findViewById(R.id.btn_mbet);

        TextView balance = (TextView) findViewById(R.id.tbx_mbalance);

        //Intent
        userl = GetUser();

        //pusty kupon
        coupon = new Coupon("user","home","away","selectedteam","multiplier","stake");
        coupons = new List<Coupon>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Coupon> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(Coupon coupon) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Coupon> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Coupon> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Coupon get(int index) {
                return null;
            }

            @Override
            public Coupon set(int index, Coupon element) {
                return null;
            }

            @Override
            public void add(int index, Coupon element) {

            }

            @Override
            public Coupon remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Coupon> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Coupon> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Coupon> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        //saldo
        balance.setText(userl.getBalance() +" zł");

        //Wczytanie i wyswietlenie meczow z pliku
        LoadMatches();

        if(games != null){
            AddMatches();
        }

        //btn dodaj saldo
        btn_addbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Matches.this, R.string.lbl_invalidDeposit, Toast.LENGTH_LONG).show();
                //CreateMatches();
            }
        });

        //btn konto
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Matches.this, Account.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

        //btn wyplac
        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Matches.this, Withdraw.class);
                intent.putExtra("loggeduser", userl);
                startActivity(intent);
            }
        });

        //btn kupon
        btn_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Matches.this, Bet.class);
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

    public void LoadMatches(){
            try
            {
                File directory = this.getFilesDir();
                File file = new File(directory, "games.xml");
                games = GameXmlParser.parse(file);
            }
            catch (Exception e)
            {
                games = null;
                e.printStackTrace();
            }
        }
    public void CreateMatches(){
        try
        {
            File directory = this.getFilesDir();
            File file = new File(directory, "games.xml");
            String text = "<Games>\n" +
                    "<Game>\n" +
                    "<Home>Real Madryt</Home>\n" +
                    "<Away>Man. City</Away>\n" +
                    "<WinHome>3.00</WinHome>\n" +
                    "<Tie>3.80</Tie>\n" +
                    "<WinAway>2.40</WinAway>\n" +
                    "</Game>\n" +
                    "<Game>\n" +
                    "<Home>Atl. Madryt</Home>\n" +
                    "<Away>Borussia</Away>\n" +
                    "<WinHome>1.90</WinHome>\n" +
                    "<Tie>3.95</Tie>\n" +
                    "<WinAway>4.70</WinAway>\n" +
                    "</Game>\n" +
                    "<Game>\n" +
                    "<Home>Psg</Home>\n" +
                    "<Away>Barcelona</Away>\n" +
                    "<WinHome>2.10</WinHome>\n" +
                    "<Tie>4.00</Tie>\n" +
                    "<WinAway>3.60</WinAway>\n" +
                    "</Game>\n" +
                    "</Games>";
            writeToFile(file, text);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    public void SetCoupon(int index, int select){
        try{
        coupon.setHome(games.get(index).getHome());
        coupon.setAway(games.get(index).getAway());

        if(select == 0){
        coupon.setMultiplier(games.get(index).getWinHome());
        coupon.setSelectedteam(games.get(index).getHome());}
        else if(select == 1)
        {
            coupon.setMultiplier(games.get(index).getWinAway());
            coupon.setSelectedteam(games.get(index).getAway());
        }
        else {coupon.setMultiplier(games.get(index).getTie());
            coupon.setSelectedteam(getString(R.string.txt_tie));}}
        catch (Exception e)
        {
            Log.d("STATE", e.toString());
        }
    }

    public void AddMatches()
    {

        ConstraintLayout matches = (ConstraintLayout) findViewById(R.id.bg_matches);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams
                ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT,(int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.adumu); // Load the font from resources

        TextView[] home = new TextView[games.size()];
        TextView[] away = new TextView[games.size()];
        TextView[] ties = new TextView[games.size()];
        Button[] winHome = new Button[games.size()];
        Button[] tie = new Button[games.size()];
        Button[] winAway = new Button[games.size()];
        Boolean[] winHomeActive = new Boolean[games.size()];
        for(int j =0; j < winHomeActive.length; j++)
        {
            winHomeActive[j] = false;
        }
        Boolean[] tieActive = new Boolean[games.size()];
        for(int j =0; j < winHomeActive.length; j++)
        {
            tieActive[j] = false;
        }
        Boolean[] winAwayActive = new Boolean[games.size()];
        for(int j =0; j < winHomeActive.length; j++)
        {
            winAwayActive[j] = false;
        }

        for(int i = 0; i < games.size();i++)
        {
            home[i] = new TextView(this);
            home[i].setPadding(50,i*400,0,0);
            home[i].setLayoutParams(params);
            home[i].setId(i);
            home[i].setText(games.get(i).getHome());
            matches.addView(home[i]);

            winHome[i] = new Button(this);
            winHome[i].setId(i);
            winHome[i].setText(games.get(i).getWinHome());
            winHome[i].setBackgroundTintList(getResources().getColorStateList(R.color.green));
            winHome[i].setTextColor(getResources().getColor(R.color.yellow));
            winHome[i].setTypeface(typeface);
            //pozycjonowanie przycisku
            ConstraintLayout.LayoutParams paramss = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            int rightMargin = 950;
            int topMargin = i == 1 ? 300 : i * 800;
            int bottomMargin = i == 1 ? 0 : 500;
            paramss.setMargins(0, topMargin, rightMargin, bottomMargin);
            winHome[i].setLayoutParams(paramss);
            paramss.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            paramss.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            paramss.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            paramss.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            final int index = i;
            winHomeActive[i] = false;
            winHome[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if(winHomeActive[index]){
                            winHome[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                            winHome[index].setTextColor(getResources().getColor(R.color.yellow));
                            winHomeActive[index] = false;
                            return;
                        }
                        switch (index) {
                            case 0:
                                if (!winHomeActive[index] && !winHomeActive[index+1] && !winHomeActive[index+2] &&
                                        !tieActive[index] && !tieActive[index+1] && !tieActive[index+2] &&
                                        !winAwayActive[index] && !winAwayActive[index+1] && !winAwayActive[index+2]) {
                                    winHome[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                    winHome[index].setTextColor(getResources().getColor(R.color.yellow));

                                    //kupon 0-home 1-away 2-tie
                                    SetCoupon(index, 0);

                                    winHomeActive[index] = true;
                                }
                                break;
                            case 1:
                                if (!winHomeActive[index] && !winHomeActive[index - 1] && !winHomeActive[index + 1] &&
                                        !tieActive[index] && !tieActive[index - 1] && !tieActive[index + 1] &&
                                        !winAwayActive[index] && !winAwayActive[index - 1] && !winAwayActive[index + 1]) {
                                    winHome[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                    winHome[index].setTextColor(getResources().getColor(R.color.yellow));

                                    //kupon 0-home 1-away 2-tie
                                    SetCoupon(index, 0);

                                    winHomeActive[index] = true;
                                }
                                break;
                            case 2:
                                if (!winHomeActive[index] && !winHomeActive[index - 1] && !winHomeActive[index - 2] &&
                                        !tieActive[index] && !tieActive[index - 1] && !tieActive[index - 2] &&
                                        !winAwayActive[index] && !winAwayActive[index - 1] && !winAwayActive[index - 2]) {
                                    winHome[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                    winHome[index].setTextColor(getResources().getColor(R.color.yellow));

                                    //kupon 0-home 1-away 2-tie
                                    SetCoupon(index, 0);

                                    winHomeActive[index] = true;
                                }
                                break;
                            default:
                                winHome[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                                winHome[index].setTextColor(getResources().getColor(R.color.yellow));
                                winHomeActive[index] = false;
                                break;
                        }
                    }
                    catch (Exception e)
                    {
                        Log.d("STATE",e.toString());
                    }
                }
            });
            matches.addView(winHome[i], paramss);

            ties[i] = new TextView(this);
            ties[i].setPadding(650,i*400,0,0);
            ties[i].setLayoutParams(params);
            ties[i].setId(i);
            ties[i].setText("0:0");
            matches.addView(ties[i]);

            tie[i] = new Button(this);
            tie[i].setId(View.generateViewId());
            tie[i].setText(games.get(i).getTie()); // Assuming getTie() retrieves text for the tie button
            tie[i].setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green));
            tie[i].setTextColor(ContextCompat.getColor(this, R.color.yellow));
            tie[i].setTypeface(typeface);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            int leftMargin = 50;
            topMargin = i == 1 ? 300 : i * 800;
            bottomMargin = i == 1 ? 0 : 500;
            layoutParams.setMargins(leftMargin, topMargin, 0, bottomMargin);
            tie[i].setLayoutParams(layoutParams);
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            tie[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        if(tieActive[index]){
                            tie[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                            tie[index].setTextColor(getResources().getColor(R.color.yellow));
                            tieActive[index] = false;
                            return;
                        }
                        switch (index) {
                            case 0:
                                if (!winHomeActive[index] && !winHomeActive[index+1] && !winHomeActive[index+2] &&
                                        !tieActive[index] && !tieActive[index+1] && !tieActive[index+2] &&
                                        !winAwayActive[index] && !winAwayActive[index+1] && !winAwayActive[index+2]) {
                                    tie[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                    tie[index].setTextColor(getResources().getColor(R.color.yellow));

                                    //kupon 0-home 1-away 2-tie
                                    SetCoupon(index, 2);

                                    tieActive[index] = true;
                                }
                                break;
                            case 1:
                                if (!winHomeActive[index] && !winHomeActive[index - 1] && !winHomeActive[index + 1] &&
                                        !tieActive[index] && !tieActive[index - 1] && !tieActive[index + 1] &&
                                        !winAwayActive[index] && !winAwayActive[index - 1] && !winAwayActive[index + 1]) {
                                    tie[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                    tie[index].setTextColor(getResources().getColor(R.color.yellow));

                                    //kupon 0-home 1-away 2-tie
                                    SetCoupon(index, 2);

                                    tieActive[index] = true;
                                }
                                break;
                            case 2:
                                if (!winHomeActive[index] && !winHomeActive[index - 1] && !winHomeActive[index - 2] &&
                                        !tieActive[index] && !tieActive[index - 1] && !tieActive[index - 2] &&
                                        !winAwayActive[index] && !winAwayActive[index - 1] && !winAwayActive[index - 2]) {
                                    tie[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                    tie[index].setTextColor(getResources().getColor(R.color.yellow));

                                    //kupon 0-home 1-away 2-tie
                                    SetCoupon(index, 2);

                                    tieActive[index] = true;
                                }
                                break;
                            default:
                                tie[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                                tie[index].setTextColor(getResources().getColor(R.color.yellow));
                                tieActive[index] = false;
                                break;
                            }
                        }
                    catch (Exception e)
                    {
                        Log.d("STATE", e.toString());
                    }
                }
            });
            matches.addView(tie[i], layoutParams);

            away[i] = new TextView(this);
            away[i].setPadding(1100,i*400,0,0);
            away[i].setLayoutParams(params);
            away[i].setId(i);
            away[i].setText(games.get(i).getAway());
            matches.addView(away[i]);

            winAway[i] = new Button(this);
            winAway[i].setId(View.generateViewId());
            winAway[i].setText(games.get(i).getWinAway());
            winAway[i].setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green));
            winAway[i].setTextColor(ContextCompat.getColor(this, R.color.yellow));
            winAway[i].setTypeface(typeface);

            ConstraintLayout.LayoutParams layoutParamss = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            leftMargin = 1100;
            topMargin = i == 1 ? 300 : i * 800;
            bottomMargin = i == 1 ? 0 : 500;
            layoutParamss.setMargins(leftMargin, topMargin, 0, bottomMargin);
            winAway[i].setLayoutParams(layoutParams);
            layoutParamss.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParamss.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParamss.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParamss.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            winAway[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(winAwayActive[index]){
                        winAway[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                        winAway[index].setTextColor(getResources().getColor(R.color.yellow));
                        winAwayActive[index] = false;
                        return;
                    }
                    switch (index) {
                        case 0:
                            if (!winHomeActive[index] && !winHomeActive[index+1] && !winHomeActive[index+2] &&
                                    !tieActive[index] && !tieActive[index+1] && !tieActive[index+2] &&
                                    !winAwayActive[index] && !winAwayActive[index+1] && !winAwayActive[index+2]) {
                                winAway[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                winAway[index].setTextColor(getResources().getColor(R.color.yellow));

                                //kupon 0-home 1-away 2-tie
                                SetCoupon(index, 1);

                                winAwayActive[index] = true;
                            }
                            break;
                        case 1:
                            if (!winHomeActive[index] && !winHomeActive[index - 1] && !winHomeActive[index + 1] &&
                                    !tieActive[index] && !tieActive[index - 1] && !tieActive[index + 1] &&
                                    !winAwayActive[index] && !winAwayActive[index - 1] && !winAwayActive[index + 1]) {
                                winAway[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                winAway[index].setTextColor(getResources().getColor(R.color.yellow));

                                //kupon 0-home 1-away 2-tie
                                SetCoupon(index, 1);

                                winAwayActive[index] = true;
                            }
                            break;
                        case 2:
                            if (!winHomeActive[index] && !winHomeActive[index - 1] && !winHomeActive[index - 2] &&
                                    !tieActive[index] && !tieActive[index - 1] && !tieActive[index - 2] &&
                                    !winAwayActive[index] && !winAwayActive[index - 1] && !winAwayActive[index - 2]) {
                                winAway[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                                winAway[index].setTextColor(getResources().getColor(R.color.yellow));

                                //kupon 0-home 1-away 2-tie
                                SetCoupon(index, 1);

                                winAwayActive[index] = true;
                            }
                            break;
                        default:
                            winAway[index].setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                            winAway[index].setTextColor(getResources().getColor(R.color.yellow));
                            winAwayActive[index] = false;
                            break;
                    }
                }
            });
            matches.addView(winAway[i], layoutParamss);
        }

        TextView lbl_stake = new TextView(this);
        lbl_stake.setText(R.string.txt_lblstake);
        lbl_stake.setPadding(600,1500,0,0);
        matches.addView(lbl_stake);

        EditText tbx_stake = new EditText(this);
        tbx_stake.setText("");
        tbx_stake.setWidth(300);
        tbx_stake.setMaxLines(1);
        tbx_stake.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7)});
        tbx_stake.setId(View.generateViewId());
        ConstraintLayout.LayoutParams stakelayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        stakelayout.setMargins(50, 2400, 0, 0);
        tbx_stake.setLayoutParams(stakelayout);
        stakelayout.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        stakelayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        stakelayout.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        stakelayout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        matches.addView(tbx_stake);

        TextView lbl_staketag = new TextView(this);
        lbl_staketag.setText(R.string.txt_lblstaketag);
        lbl_staketag.setPadding(850,1600,0,0);
        matches.addView(lbl_staketag);

        Button betbtn = new Button(this);
        betbtn.setId(View.generateViewId());
        betbtn.setText(R.string.txt_bet);
        betbtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green));
        betbtn.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        betbtn.setTypeface(typeface);
        ConstraintLayout.LayoutParams betlayoutParamss = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        betlayoutParamss.setMargins(50, 2800, 0, 0);
        betbtn.setLayoutParams(betlayoutParamss);
        betlayoutParamss.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        betlayoutParamss.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        betlayoutParamss.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        betlayoutParamss.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        betbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nie podano stawki.
                if(tbx_stake.getText().equals(null) || tbx_stake.getText().equals("") || tbx_stake.getText().equals(""))
                {
                    Toast.makeText(Matches.this, R.string.txt_emptystake, Toast.LENGTH_LONG).show();
                }

                //Podana stawka jest wieksza niz aktualne saldo konta.
                if(!(Integer.valueOf(userl.getBalance()) < Integer.valueOf(tbx_stake.getText().toString()))) {
                    try{
                    betbtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.black));
                    betbtn.setTextColor(getResources().getColor(R.color.yellow));
                    coupon.setStake(tbx_stake.getText().toString());
                    coupon.setUserid(userl.getLogin());

                    //Zmiana salda konta i serializacja do pliku
                    int newbalance = Integer.valueOf(userl.getBalance()) - Integer.valueOf(tbx_stake.getText().toString());
                    userl.setBalance(String.valueOf(newbalance));
                    users = DeserializeUsers();
                    for (int i = 0; i < users.size(); i++) {
                        User currentUser = users.get(i);
                        if (currentUser.getLogin().equals(userl.getLogin())) {
                            users.set(i, userl);
                            break;
                        }
                    }
                    SerializeUsers(users);

                    //Dodanie kuponu
                    SerializeCoupon(coupon);

                    //Potwierdzenie postawienia zakładu
                        tbx_stake.setText("");
                        tbx_stake.clearFocus();
                    Toast.makeText(Matches.this, R.string.txt_couponsuceeded, Toast.LENGTH_LONG).show();

                    //przejscie do historii zakladow
                    finish();
                        Intent intent = new Intent(Matches.this, Bet.class);
                        intent.putExtra("loggeduser", userl);
                        startActivity(intent);
                    }
                    catch (Exception e){
                        Log.d("STATE",e.toString());
                    }
                }
                else{
                    Toast.makeText(Matches.this, R.string.txt_toohigstake, Toast.LENGTH_LONG).show();
                }
            }
        });
        matches.addView(betbtn, betlayoutParamss);
    }

    public List<User> DeserializeUsers()
    {
        try
        {
            File directory = this.getFilesDir();
            File file = new File(directory, "users.xml");
            List<User> users;
            return users = UserXmlParser.parse(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            List<User> users;
            return users = null;
        }
    }

    public void SerializeUsers(List<User> userss)
    {
        File directory = this.getFilesDir();
        File file = new File(directory, "users.xml");
        UserXmlParser.serializeUsers(userss, file);
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

    public void SerializeCoupon(Coupon coupon)
    {
        File directory = this.getFilesDir();
        File file = new File(directory, "coupons.xml");
        //deserializacja
        List<Coupon> couponlist = DeserializeCoupons();

        //Nowy kupon
        couponlist.add(coupon);

        //serializacja
        CouponXmlParser.serializeCoupons(couponlist, file);
    }
}
