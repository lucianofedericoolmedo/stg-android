package com.ingsoft.magic_assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.DialogInterface;
import android.widget.TextView;

import com.ingsoft.magic_assistant.data.Life;
import com.ingsoft.magic_assistant.data.RivalLife;
import com.ingsoft.magic_assistant.data.UserLife;


public class HomeActivity extends AppCompatActivity implements DialogInterface.OnClickListener
    , NavigationView.OnNavigationItemSelectedListener {

    UserLife userLife;
    RivalLife rivalLife;
    TextView userLifeText, rivalLifeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userLife = new UserLife();
        rivalLife = new RivalLife();
        userLifeText = (TextView) findViewById(R.id.p1life);
        rivalLifeText = (TextView) findViewById(R.id.p2life);
        userLifeText.setText(userLife.toString());
        rivalLifeText.setText(rivalLife.toString());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
        } else {
        super.onBackPressed();
        }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
        }


    public void incP1(View view){
        userLife.increment();
        userLifeText.setText(userLife.toString());
    }

    public void decP1(View view){
        userLife.decrement();
        this.decrement_and_lose(view, userLifeText, userLife);
    }

    private void decrement_and_lose(View view, TextView userlife , Life lifesleft){
        if (lifesleft.isEnding()){
            AlertDialog ad = new AlertDialog.Builder(this)
                    .setMessage(lifesleft.lose_message())
                    .setTitle(lifesleft.lose_title())
                    .setPositiveButton("Jugar de nuevo", this)
                    .create();
            ad.show();
            /*
            Deberia recargar la activity de otra forma (?)...
             */
            this.reiniciar(view);
        } else {
            userlife.setText(lifesleft.toString());
        }
    }

    public void incP2(View view){
        rivalLife.increment();
        rivalLifeText.setText(rivalLife.toString());
    }

    public void decP2(View view){
        rivalLife.decrement();
        this.decrement_and_lose(view, rivalLifeText, rivalLife);
    }

    public void incVenonP1(View view){
        userLife.incrementVenon();
        this.decrement_and_lose(view, userLifeText, userLife);
    }

    public void decVenonP1(View view){
        userLife.decrementVenon();
        this.decrement_and_lose(view, userLifeText, userLife);
    }

    public void incVenonP2(View view){
        rivalLife.incrementVenon();
        this.decrement_and_lose(view, rivalLifeText, rivalLife);
    }

    public void decVenonP2(View view){
        rivalLife.decrementVenon();
        this.decrement_and_lose(view, rivalLifeText, rivalLife);
    }

    public void reiniciar(View view){
        userLife = new UserLife();
        rivalLife = new RivalLife();
        userLifeText.setText(userLife.toString());
        rivalLifeText.setText(rivalLife.toString());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        switch(which){
            case DialogInterface.BUTTON_POSITIVE: // yes
                break;
            default:
                // nothing
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
        }

        return super.onOptionsItemSelected(item);
        }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
        Intent myIntent = new Intent(HomeActivity.this,SearchMainActivity.class);
        HomeActivity.this.startActivity(myIntent);
        }

        /*
        Pueden venir otras ....
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }
}
