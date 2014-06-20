package com.example.docapp.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class DisplayDoc extends ActionBarActivity {

    public String docId;
    public String docName;
    public String fname2;
    public String lname2;
    public String email;
    public String Spec;

    public final static String EXTRA_docId = "com.example.myfirstapp.DOCID";
    public final static String EXTRA_curId = "com.example.myfirstapp.CURID";

    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_doc);

        Intent intent = getIntent();
        docName = intent.getStringExtra(PatientHome.EXTRA_selectedDoc);

        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.whereEqualTo("userType", "Doctor");
        userQuery.whereEqualTo("Fname",docName.split(" ")[0].trim());
        userQuery.whereEqualTo("Lname",docName.split(" ")[1].trim());
        //userQuery.whereEqualTo("Speciality", sInput.trim());
        //userQuery.whereEqualTo("Speciality", "Orthopedic");
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);

        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> results, ParseException e) {
                // results has the list of users with a hometown team with a winning record
                int i = 0;
                Log.d("post", "inside parse " + String.valueOf(results.size()));
                for (ParseObject result : results) {
                    Log.d("post", "found");
                    // This does not require a network access.
                    //ParseObject user = result.getParseObject("userType");
                    docId = result.getObjectId();
                    fname2 = result.getString("Fname");
                    lname2 = result.getString("Lname");
                    email = result.getString("email");
                    Spec = result.getString("Speciality");

                    t1.setText(docId);
                    t2.setText(fname2+" "+lname2);
                    t3.setText(email);
                    t4.setText(Spec);
                    //where.add(fname+" "+lname);
                    //values[i]=fname+" "+lname;
                    //i++;
                    //adapter.setNotifyOnChange(true);
                    //adapter.add(fname2 + " " + lname2);

                    Log.d("post", "retrieved a related post " + docId + " " + fname2+" "+lname2+" "+email+" "+Spec);
                }
            }
        });





    }

    public void makeAppointment(View view)
    {

        ParseUser cuser = ParseUser.getCurrentUser();
        String cid=cuser.getObjectId();
        Intent intent = new Intent(this, MakeAppointment.class);
        intent.putExtra(DisplayDoc.EXTRA_docId, docId);
        intent.putExtra(DisplayDoc.EXTRA_curId, cid);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_doc, menu);
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
