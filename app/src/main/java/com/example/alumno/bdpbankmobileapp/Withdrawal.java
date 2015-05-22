package com.example.alumno.bdpbankmobileapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Withdrawal extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        final Button but = (Button)findViewById(R.id.button);

        but.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(Withdrawal.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_withdrawal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void withdraw()
    {
        EditText input = (EditText) findViewById(R.id.account);
        int accNum = Integer.parseInt(input.getText().toString());
        input = (EditText) findViewById(R.id.amount);
        int withAmount = Integer.parseInt(input.getText().toString());
        String message = "";

        //if(withAmount > ******accountBalance***)
        // {
        //*****accountBalance***** - withAmount;
        message = "success";
        //}

        //else
        //{
        message = "lack funds";
        // }

        //Toast.makeText(MainActivity.this, message,Toast.LENGTH_LONG).show();


    }


}
