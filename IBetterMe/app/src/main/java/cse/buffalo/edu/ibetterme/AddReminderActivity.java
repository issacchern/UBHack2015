package cse.buffalo.edu.ibetterme;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class AddReminderActivity extends AppCompatActivity {
    CustomDateTimePicker custom;
    CustomDateTimePicker custom2;
    private Button fromTimeButton;
    private Button toTimeButton;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fromTimeButton = (Button) findViewById(R.id.fromTimeButton);
        toTimeButton = (Button) findViewById(R.id.toTimeButton);

        submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddReminderActivity.this, HistActivity.class);

                Toast.makeText(getApplicationContext(),"Reminder has been submitted!", Toast.LENGTH_LONG).show();



                EditText editTextReminders = (EditText)findViewById(R.id.textEditString);
                String reminders = editTextReminders.getText().toString().trim();
                intent.putExtra("ABC", reminders);
                startActivity(intent);


            }
        });

        fromTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom.showDialog();

            }
        });

        toTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom2.showDialog();

            }
        });



        custom = new CustomDateTimePicker(this,
                new CustomDateTimePicker.ICustomDateTimeListener() {

                    @Override
                    public void onSet(Dialog dialog, Calendar calendarSelected,
                                      Date dateSelected, int year, String monthFullName,
                                      String monthShortName, int monthNumber, int date,
                                      String weekDayFullName, String weekDayShortName,
                                      int hour24, int hour12, int min, int sec,
                                      String AM_PM) {


                        String hourStr = "";
                        if(hour12 < 10){
                            hourStr = "0"+hour12;
                        } else{
                            hourStr = hour12 +"";
                        }
                        String minStr = "";
                        if(min < 10){
                            minStr = "0" + min;

                        }else{
                            minStr = min + "";
                        }


                        ((Button) findViewById(R.id.fromTimeButton))
                                .setText((monthNumber+1)
                                        + "/" + (calendarSelected
                                        .get(Calendar.DAY_OF_MONTH)) + "/" + year
                                        + ", " + hourStr + ":" + minStr
                                        + " " + AM_PM);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
        /**
         * Pass Directly current time format it will return AM and PM if you set
         * false
         */
        custom.set24HourFormat(false);
        /**
         * Pass Directly current data and time to show when it pop up
         */
        custom.setDate(Calendar.getInstance());



        custom2 = new CustomDateTimePicker(this,
                new CustomDateTimePicker.ICustomDateTimeListener() {

                    @Override
                    public void onSet(Dialog dialog, Calendar calendarSelected,
                                      Date dateSelected, int year, String monthFullName,
                                      String monthShortName, int monthNumber, int date,
                                      String weekDayFullName, String weekDayShortName,
                                      int hour24, int hour12, int min, int sec,
                                      String AM_PM) {




                        String hourStr = "";
                        if(hour12 < 10){
                            hourStr = "0"+hour12;
                        } else{
                            hourStr = hour12 +"";
                        }
                        String minStr = "";
                        if(min < 10){
                            minStr = "0" + min;

                        }else{
                            minStr = min + "";
                        }


                        ((Button) findViewById(R.id.toTimeButton))
                                .setText((monthNumber+1)
                                        + "/" + (calendarSelected
                                        .get(Calendar.DAY_OF_MONTH)) + "/" + year
                                        + ", " + hourStr + ":" + minStr
                                        + " " + AM_PM);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
        /**
         * Pass Directly current time format it will return AM and PM if you set
         * false
         */
        custom2.set24HourFormat(false);
        /**
         * Pass Directly current data and time to show when it pop up
         */
        custom2.setDate(Calendar.getInstance());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch(i){
            case android.R.id.home:
                this.finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
