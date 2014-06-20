package com.example.docapp.app;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PatientHome extends ListActivity implements
        ActionBar.OnNavigationListener, OnItemClickListener {


    public final static String EXTRA_selectedDoc = "com.example.myfirstapp.DOCNAME";
    ListView mlistview;
    EditText mSearchInput;
    ArrayAdapter<String> adapter;
    ArrayList<String> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        mlistview = (ListView)findViewById(android.R.id.list);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        mSearchInput = (EditText)findViewById(R.id.search_input);

        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add("Choose");
        itemList.add("Home");
        itemList.add("My Appointments");
        itemList.add("My Prescriptions");


        ArrayAdapter<String> aAdpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);

        actionBar.setListNavigationCallbacks(aAdpt, this);


        final String[] values = new String[] { "", "", "",
                "", "", "", "", "",
                "", "" ,"", "", ""};

        final List<String> where = new ArrayList<String>();

        /*ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.whereEqualTo("userType", "Doctor");
        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> results, ParseException e) {
                // results has the list of users with a hometown team with a winning record
                int i=0;
                for (ParseObject result : results) {
                    // This does not require a network access.
                    //ParseObject user = result.getParseObject("userType");
                    String fname = result.getString("Fname");
                    String lname = result.getString("Lname");

                    where.add(fname+" "+lname);
                    values[i]=fname+" "+lname;
                    i++;


                    Log.d("post", "retrieved a related post "+fname+" "+lname);
                }
            }
        });*/

        //String[] simpleArray = new String[ where.size() ];
        //where.toArray( simpleArray );

        for(String str : where)
        {
            Log.d("post", str);
        }



        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          //      android.R.layout.simple_list_item_1, values);

        //entries = new ArrayList<String>(Arrays.asList("List Item A", "List Item B"));

        entries = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, entries);


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          //      android.R.layout.simple_list_item_1, simpleArray);



        mlistview.setAdapter(adapter);
        mlistview.setOnItemClickListener(this);
        //updateData();

    }


    @Override
    public boolean onNavigationItemSelected(int position, long id)
    {

        if(position == 2)
        {
            Intent intent = new Intent(this, DisplayPatientAppointments.class);
            startActivity(intent);
        }
        else if (position == 1)
        {
            Intent intent = new Intent(this, PatientHome.class);
            startActivity(intent);
        }
        else if(position ==3)
        {
            Intent intent = new Intent(this, PatientPrescriptions.class);
            startActivity(intent);
        }

        return false;
    }
    public void updateData(){

        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.whereEqualTo("userType", "Doctor");
        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> results, ParseException e) {
                // results has the list of users with a hometown team with a winning record
                int i=0;
                for (ParseObject result : results) {
                    // This does not require a network access.
                    //ParseObject user = result.getParseObject("userType");
                    String fname = result.getString("Fname");
                    String lname = result.getString("Lname");

                    //where.add(fname+" "+lname);
                    //values[i]=fname+" "+lname;
                    //i++;
                    adapter.setNotifyOnChange(true);
                    adapter.add(fname+" "+lname);

                    Log.d("post", "retrieved a related post "+fname+" "+lname);
                }
            }
        });


    }

    public void searchFunc(View view){
        adapter.clear();
        TextView searchInput = (TextView) findViewById(R.id.search_input);
        String sInput = searchInput.getText().toString();
        String [] names = new String[2];
        names= sInput.split(" ");
        //names[0] = sInput.split("\\s+")[0];
        //String lname = sInput.split("\\s+")[1];
        //Log.d("post",names[0].trim());
        //Log.d("post",names[1].trim());
        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.whereEqualTo("userType", "Doctor");
        //userQuery.whereEqualTo("Fname",names[0].trim());
        //userQuery.whereEqualTo("Lname",names[1].trim());
        userQuery.whereEqualTo("Speciality", sInput.trim());
        //userQuery.whereEqualTo("Speciality", "Orthopedic");

        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> results, ParseException e) {
                // results has the list of users with a hometown team with a winning record
                int i = 0;
                Log.d("post", "inside parse " + String.valueOf(results.size()));
                for (ParseObject result : results) {
                    Log.d("post", "found");
                    // This does not require a network access.
                    //ParseObject user = result.getParseObject("userType");
                    String fname2 = result.getString("Fname");
                    String lname2 = result.getString("Lname");

                    //where.add(fname+" "+lname);
                    //values[i]=fname+" "+lname;
                    //i++;
                    adapter.setNotifyOnChange(true);
                    adapter.add(fname2 + " " + lname2);

                    Log.d("post", "retrieved a related post " + fname2 + " " + lname2);
                }
            }
        });

    }

    /*@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String res;
        String res2 = adapter.getItem(i);
        TextView taskDescription = (TextView) view.findViewById(android.R.id.list);
        //res = taskDescription.getText().toString();
        Toast.makeText(this, res2 + " selected", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, DisplayDoc.class);
        intent.putExtra(PatientHome.EXTRA_selectedDoc, res2);
        startActivity(intent);

    }
}
