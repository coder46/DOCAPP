package com.example.docapp.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;


public class EnterPassword extends ActionBarActivity {

    String EMAIL;
    String FNAME;
    String LNAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        EMAIL = intent.getStringExtra(MainActivity.EXTRA_EMAIL);

        FNAME = intent.getStringExtra(MainActivity.EXTRA_FNAME);

        LNAME = intent.getStringExtra(MainActivity.EXTRA_LNAME);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_password);
        overridePendingTransition(R.anim.activity_in,R.anim.old_activity_out);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enter_password, menu);
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

    public void moveToUserType(View view)
    {
        Intent intent = new Intent(this, EnterUserType.class);
        EditText pass = (EditText) findViewById(R.id.editText);
        intent.putExtra( MainActivity.EXTRA_PASS, pass.getText().toString());
        intent.putExtra(MainActivity.EXTRA_EMAIL, EMAIL);
        intent.putExtra(MainActivity.EXTRA_FNAME, FNAME);
        intent.putExtra(MainActivity.EXTRA_LNAME, LNAME);

        Log.e("ERROR", "email "+EMAIL);
        Log.e("ERROR", "fname "+FNAME);
        Log.e("ERROR", "lname "+LNAME);

        startActivity(intent);
    }
}
