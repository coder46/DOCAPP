package com.example.docapp.app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class EnterUserType extends ActionBarActivity {

    String userType;

    String EMAIL;
    String FNAME;
    String LNAME;
    String PASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        EMAIL = intent.getStringExtra(MainActivity.EXTRA_EMAIL);

        PASS = intent.getStringExtra(MainActivity.EXTRA_PASS);
        FNAME = intent.getStringExtra(MainActivity.EXTRA_FNAME);

        LNAME = intent.getStringExtra(MainActivity.EXTRA_LNAME);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_user_type);
        overridePendingTransition(R.anim.activity_in,R.anim.old_activity_out);
    }

    /*@Override
    protected void onPause()
    {
        super.onPause();
        overridePendingTransition(R.anim.old_activity_in,R.anim.activity_out);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enter_user_type, menu);
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
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*public void moveToPatientHome(View view)
    {


        Intent intent = new Intent(this, EnterUserType.class);


    }*/

    public void userSignUp(View view)
    {

        /*
        uname = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);
        pass = (EditText) findViewById(R.id.editText);
        */

        String tag = "PARSE";
        Log.e(tag,EMAIL);
        Log.e(tag, FNAME);
        Log.e(tag, LNAME);
        Log.e(tag, userType);
        Parse.initialize(this, "h8up9mUy8RC8uubGJJKzPDbXANhqMlZ8jt7diWUH", "CxYmqiAgyaHArADvGZUSn9w2peO9wfzkbgYYdtwe");

        ParseUser user = new ParseUser();
        user.setUsername(EMAIL);
        user.setEmail(EMAIL);
        user.setPassword(PASS);
        user.put("userType", userType);
        user.put("Fname",FNAME);
        user.put("Lname",LNAME);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){

                    Context context = getApplicationContext();
                    CharSequence text = "Signed Up!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    if(userType.equals("Doctor"))
                    {
                        Intent intent = new Intent(EnterUserType.this, EnterSpeciality.class);
                        startActivity(intent);
                    }
                    else {
                        //Intent intent = new Intent(EnterUserType.this, MainActivity.class);
                        //startActivity(intent);
                    }

                }else{

                    String tag = "PARSE";
                    Log.e(tag, e.toString());
                    Log.e(tag,EMAIL.toString());
                    Log.e(tag, FNAME.toString());
                    Log.e(tag, LNAME.toString());
                    Log.e(tag, userType.toString());

                    Context context = getApplicationContext();
                    //CharSequence text = "Error! Could not sign up !!";

                    CharSequence text = e.toString();
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton:
                if (checked)
                    userType = "Doctor";
                break;
            case R.id.radioButton2:
                if (checked)
                    userType = "Patient";
                break;
        }

    }

}
