package cse.buffalo.edu.ibetterme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Winnie on 11/15/15.
 */
public class HistoryActivity extends Activity {
    private ArrayList<String> arrayListReminder;
    public static ArrayAdapter<String> arrayAdapterReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayListReminder = new ArrayList<String>();
        arrayAdapterReminder = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListReminder);
        ListView listViewReminder = (ListView) findViewById(R.id.listViewReminders);
        listViewReminder.setAdapter(arrayAdapterReminder);

        registerForContextMenu(listViewReminder);
        try{
            Log.i("ON CREATE", "Hi, the on create has occurred");
            Scanner scanner = new Scanner(openFileInput("Reminder.txt"));
            while(scanner.hasNextLine()){
                String toDo = scanner.nextLine();
                arrayAdapterReminder.add(toDo);
            }
            scanner.close();
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



}
