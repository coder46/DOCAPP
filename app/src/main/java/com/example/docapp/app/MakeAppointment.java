package com.example.docapp.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;


public class MakeAppointment extends ActionBarActivity {

    public String docId;
    public String curId;

    EditText t1,t2,t3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        Intent intent = getIntent();
        docId = intent.getStringExtra(DisplayDoc.EXTRA_docId);
        curId = intent.getStringExtra(DisplayDoc.EXTRA_curId);

        t1 = (EditText) findViewById(R.id.editText);
        t2 = (EditText) findViewById(R.id.editText2);
        t3 = (EditText) findViewById(R.id.editText3);





    }

    public void confirmAppointment(View view)
    {
        ParseObject appointment = new ParseObject("Appointments");
        appointment.put("docId", docId);
        appointment.put("patientId", curId);
        appointment.put("reason",t1.getText().toString());
        appointment.put("date", t2.getText().toString());
        appointment.put("time", t3.getText().toString());
        appointment.saveEventually();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast2 = Toast.makeText(context, "BOOKED!!", duration);
        toast2.show();
        Intent intent = new Intent(this, PatientHome.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.make_appointment, menu);
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
