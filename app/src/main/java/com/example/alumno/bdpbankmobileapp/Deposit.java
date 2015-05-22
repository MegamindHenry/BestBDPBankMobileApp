package com.example.alumno.bdpbankmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Deposit extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        final Button but = (Button)findViewById(R.id.homeButton);

        but.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(Deposit.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deposit, menu);

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

    public void Deposit()
    {
        EditText input = (EditText) findViewById(R.id.account);
        int accNum = Integer.parseInt(input.getText().toString());
        input = (EditText) findViewById(R.id.amount);
        int withAmount = Integer.parseInt(input.getText().toString());
        String message = "";

        //if(true)
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
