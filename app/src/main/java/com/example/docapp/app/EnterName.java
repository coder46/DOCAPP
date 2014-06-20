package com.example.docapp.app;

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

import java.net.FileNameMap;


public class EnterName extends ActionBarActivity {
    String EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        EMAIL = intent.getStringExtra(MainActivity.EXTRA_EMAIL);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_name);
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
        getMenuInflater().inflate(R.menu.enter_name, menu);
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

    public void moveToPassword(View view)
    {
        Intent intent = new Intent(this, EnterPassword.class);
        intent.putExtra(MainActivity.EXTRA_EMAIL, EMAIL);

        Log.e("ERROR", EMAIL);

        EditText Fname = (EditText)findViewById(R.id.editText);
        intent.putExtra(MainActivity.EXTRA_FNAME, Fname.getText().toString());

        EditText Lname = (EditText)findViewById(R.id.editText2);
        intent.putExtra(MainActivity.EXTRA_LNAME, Lname.getText().toString());


        startActivity(intent);
    }


}
