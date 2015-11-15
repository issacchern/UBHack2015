package cse.buffalo.edu.ibetterme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HistActivity extends AppCompatActivity {
    private ArrayList<String> arrayListReminder;
    public static ArrayAdapter<String> arrayAdapterReminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayListReminder = new ArrayList<String>();

        arrayAdapterReminder = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListReminder);
        ListView listViewReminder = (ListView) findViewById(R.id.listViewReminders);
        listViewReminder.setAdapter(arrayAdapterReminder);

        String
            str = getIntent().getExtras().getString("ABC");



        registerForContextMenu(listViewReminder);
        try{
            Log.i("ON CREATE", "Hi, the on create has occurred");

            if(str != ""){
                arrayAdapterReminder.add("Set " + str + " reminder on 11/15");
            }


            arrayAdapterReminder.add("Missed medicine time on 11/3");
            arrayAdapterReminder.add("Paid medical bill on time on 10/30");
            arrayAdapterReminder.add("Took medicine on time on 10/23");

        }catch(Exception e){
            Log.i("ON CREATE", e.getMessage());
        }
    }



    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectedIndex = info.position;

        if(item.getTitle().equals("Delete task")){
            arrayListReminder.remove(selectedIndex);
            arrayAdapterReminder.notifyDataSetChanged();
        }

        return true;
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
