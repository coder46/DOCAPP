package com.example.docapp.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.docapp.app.DoctorInfo.Doctor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class DisplayPatient extends ActionBarActivity {

    public final static String EXTRA_pid = "com.example.docapp.pid";
    String pid;
    TextView t4,t5,t6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_patient);
        Intent intent = getIntent();
        pid = intent.getStringExtra(DoctorHome.EXTRA_patientid2);
        if (pid == null)
        {
            Log.d("posts","caught you !!");
        }
        t4 = (TextView) findViewById(R.id.textView4);
        t5 = (TextView) findViewById(R.id.textView5);
        t6 = (TextView) findViewById(R.id.textView6);

        //Log.e("posts",pid);
       ParseQuery<ParseUser> userQ = ParseUser.getQuery();
        userQ.whereEqualTo("objectId",pid);
        final String[] response = new String[1];
        userQ.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> results, ParseException e) {
                // results has the list of users with a hometown team with a winning record
                Log.d("posts","results are "+String.valueOf(results.size()));
                for (ParseObject result : results) {

                    t4.setText(pid);
                    t5.setText(result.getString("Fname"));
                    t6.setText(result.getString("Lname"));
                }


            }
        });


    }

    public void goToPresciption(View view)
    {
        Intent intent = new Intent(this, WritePresciption.class);
        intent.putExtra(DisplayPatient.EXTRA_pid, pid);
        //intent.putExtra(DisplayDoc.EXTRA_curId, cid);

        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_patient, menu);
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

}
