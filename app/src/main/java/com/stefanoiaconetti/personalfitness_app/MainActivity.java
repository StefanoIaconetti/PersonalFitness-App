package com.stefanoiaconetti.personalfitness_app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

//Implementing the onfragment interaction listeners
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BMIFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        WaterIntakeFragment.OnFragmentInteractionListener,
        WorkoutFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener,
        CalorieLogFragment.OnFragmentInteractionListener,
        WorkoutViewPager.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener{

    public FragmentManager fm = getSupportFragmentManager();
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
                //Layout so i can have multiple texts/ editBoxes
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                //AlertDialog being built
                final AlertDialog.Builder alertBox = new AlertDialog.Builder(context);
                alertBox.setTitle(R.string.what_ate);

                //Edittexts and textiews
                final EditText foodEdit = new EditText(context);
                final EditText calorieEdit = new EditText(context);
                TextView foodText = new TextView(context);
                TextView calorieText = new TextView(context);

                //Padding and adding texts to the textviews
                calorieText.setPadding(15, 0, 0, 0);
                foodText.setPadding(15, 0, 0, 0);
               // calorieEdit.setRawInputType(InputType.TYPE_CLASS_NUMBER, InputType.TYPE_NUMBER_FLAG_DECIMAL);
               calorieEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
                foodText.setText(R.string.add_food);
                calorieText.setText(R.string.calorie_many);

                //Adding them into my linear layout
                layout.addView(foodText);
                layout.addView(foodEdit);
                layout.addView(calorieText);
                layout.addView(calorieEdit);

                //Populating the alertbox
                alertBox.setView(layout);

                //What happens when the add button is pressed
            alertBox.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Users input is now placed into the recycler array
                        String food = foodEdit.getText() + "";
                        String calories = calorieEdit.getText() + "";
                        int totalList = 0;

                        if(food.equals("") || calories.equals("")){

                        }else {
                            CalorieLogFragment.calorieArray.add(new CalorieLog( " " + food, calories + " calories"));
                            //You are either sent to the fragment or it refreshed
                            FragmentTransaction tran = fm.beginTransaction();
                            tran.replace(R.id.content, new CalorieLogFragment(), "Calorie Log");
                            tran.addToBackStack(null);
                            tran.commit();
                            CalorieLogFragment.totalCalorie = CalorieLogFragment.totalCalorie + Integer.parseInt(calories);
                        }

                    }
                });
            //alertbox is dismissed if button is pressed
                alertBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alertBox.show();
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
        FragmentTransaction tran = fm.beginTransaction();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            tran.replace(R.id.content, new SettingsFragment(), "Settings");
            tran.addToBackStack(null);
            tran.commit();
        }else if (id == R.id.action_credits) {
            tran.replace(R.id.content, new CreditsFragment(), "Credits");
            tran.addToBackStack(null);
            tran.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction tran = fm.beginTransaction();
        tran.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);

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
            tran.replace(R.id.content, new WorkoutViewPager(), "Workouts");
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
