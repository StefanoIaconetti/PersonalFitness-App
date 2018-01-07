package com.stefanoiaconetti.personalfitness_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.EditText;

import java.util.ArrayList;

//Implementing the onfragment interaction listeners
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BMIFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        WaterIntakeFragment.OnFragmentInteractionListener,
        WorkoutFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener,
        CalorieLogFragment.OnFragmentInteractionListener{

    FragmentManager fm = getSupportFragmentManager();
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();

        if (savedInstanceState == null){
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content, new HomeFragment());
            transaction.commit();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalorieLogFragment.calorieArray.add(new CalorieLog("Hi", "Hi"));


                AlertDialog.Builder alertBox = new AlertDialog.Builder(context);

                alertBox.setTitle("Add your food and calories!");
                EditText foodEdit = new EditText(context);
                EditText calorieEdit = new EditText(context);
                alertBox.setView(foodEdit);

                alertBox.setView(calorieEdit);


                alertBox.show();
               // FragmentTransaction tran = fm.beginTransaction();


                   // tran.replace(R.id.content, new CalorieLogFragment(), "Calorie Log");
                  //  tran.addToBackStack(null);
                   // tran.commit();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
        FragmentTransaction tran = fm.beginTransaction();

        if (id == R.id.nav_home) {
            tran.replace(R.id.content, new HomeFragment(), "Home");
            tran.addToBackStack(null);
            tran.commit();
        } else if (id == R.id.nav_BMI) {
            tran.replace(R.id.content, new BMIFragment(), "BMI");
            tran.addToBackStack(null);
            tran.commit();
        } else if (id == R.id.nav_Water) {
            tran.replace(R.id.content, new WaterIntakeFragment(), "WaterIntake");
            tran.addToBackStack(null);
            tran.commit();
        } else if (id == R.id.nav_Workouts) {
            tran.replace(R.id.content, new WorkoutFragment(), "Workouts");
            tran.addToBackStack(null);
            tran.commit();
        } else if (id == R.id.nav_schedule) {
            tran.replace(R.id.content, new ScheduleFragment(), "Schedule");
            tran.addToBackStack(null);
            tran.commit();
        }else if (id == R.id.nav_contact) {
            tran.replace(R.id.content, new ContactFragment(), "Contact");
            tran.addToBackStack(null);
            tran.commit();
        }
        else if (id == R.id.nav_calorie) {
            tran.replace(R.id.content, new CalorieLogFragment(), "Calorie");
            tran.addToBackStack(null);
            tran.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onFragmentInteraction (Uri uri){

    }
}
