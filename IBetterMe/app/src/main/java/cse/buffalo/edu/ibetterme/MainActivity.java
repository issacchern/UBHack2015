package cse.buffalo.edu.ibetterme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private String globalstr ="";
    protected static final int RESULT_SPEECH = 1;
    private Button emailButton;
    private EditText editText;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        editText = (EditText) findViewById(R.id.editText);

        ImageView imgFavorite = (ImageView) findViewById(R.id.recorder);
        imgFavorite.setClickable(true);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    editText.setText("", TextView.BufferType.EDITABLE);


                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });

        emailButton = (Button) findViewById(R.id.email_to_provider);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().trim().isEmpty()) {
                    Toast noEmail = Toast.makeText(getApplicationContext(), "Please type or speak your email first. ", Toast.LENGTH_LONG);
                    noEmail.getView().setBackgroundColor(Color.RED);
                    noEmail.show();

                }
                else {

                   // Toast emailSent = Toast.makeText(getApplicationContext(), "Email sent to bcbs.gmail.com!", Toast.LENGTH_LONG);
                   // emailSent.getView().setBackgroundColor(Color.GREEN);
                   // emailSent.show();

//                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                    emailIntent.setType("text/plain");
//                    startActivity(emailIntent);

                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "bcbs@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Need Help from Donna Trump");
                    intent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
                    startActivity(intent);


                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {


            if(doubleBackToExitPressedOnce) {
                // this is to close the app entirely, but it will still be in the stack
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
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


        if (id == R.id.add_reminder) {

            Intent intent = new Intent(MainActivity.this, AddReminderActivity.class);
            startActivity(intent);



        } else if (id == R.id.add_health) {


            Intent intent = new Intent(MainActivity.this, AddHealthCareProviderActivity.class);
            startActivity(intent);




        } else if (id == R.id.account_details) {

            //set up dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.maindialog);
            dialog.setTitle("Account details");
            dialog.setCancelable(true);
            //there are a lot of settings, for dialog, check them all out!

            //set up text
            TextView text = (TextView) dialog.findViewById(R.id.TextView01);

            text.setText("Name: Donna Trump\nPhone number: 123456789\nDate of birth: 01/01/1991\n" +
                "Healthcare provider: BlueCrossBlueShield\nProvider phone number:3334441111\n" +
                    "Provider email: bcbs.gmail.com\nEMT ID: 3456 \nEmergency no 1: 1234567890\n" +
            "Emergency no 2: 09876543211");
            text.setPadding(10, 10, 10, 10);


            //set up image view
            ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
            img.setImageResource(R.drawable.small_trump);

            //set up button
            Button button = (Button) dialog.findViewById(R.id.Button01);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });
            //now that the dialog is set up, it's time to show it
            dialog.show();


        } else if (id == R.id.visit_website) {

            String url = "http://vcheng3.github.io/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);


        } else if (id == R.id.share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.logout) {

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        } else if(id == R.id.history){


            Intent intent = new Intent(MainActivity.this, HistActivity.class);
            intent.putExtra("ABC", "");
            startActivity(intent);


        } else if(id == R.id.home_button){



        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


                    editText.setText(text.get(0),TextView.BufferType.EDITABLE);

                    globalstr = text.get(0);

                    if(text.get(0).equals("help") || text.get(0).equals("help me")){
                        String posted_by = "111-333-2222";
                        String uri = "tel:" + posted_by.trim() ;
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);

                    }
                }
                break;
            }

        }
    }
}
