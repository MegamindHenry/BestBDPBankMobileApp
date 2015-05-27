package com.example.alumno.bdpbankmobileapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class Transactions extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        final Button but = (Button)findViewById(R.id.homeButton);

        but.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(Transactions.this, MainActivity.class);

                startActivity(intent);
            }
        });

        final String[] accountTypes = new String[]{"Saving", "Checking"};

        ArrayAdapter<String> adaptadorAT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountTypes);
        adaptadorAT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner combo = (Spinner)findViewById(R.id.spinnerAT);
        combo.setAdapter(adaptadorAT);

        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                Toast.makeText(Transactions.this, "Selected: " + accountTypes[position], Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Transactions.this, "Nothing Selected", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transactions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent = new Intent(Transactions.this, MainActivity.class);

            startActivity(intent);
        }
        if (id == R.id.action_deposit) {
            Intent intent = new Intent(Transactions.this, Deposit.class);

            startActivity(intent);
        }
        if (id == R.id.action_withdraw) {
            Intent intent = new Intent(Transactions.this, Withdrawal.class);

            startActivity(intent);
        }
        if (id == R.id.action_transfer) {
            Intent intent = new Intent(Transactions.this, Transfer.class);

            startActivity(intent);
        }
        if (id == R.id.action_transactions) {
            Intent intent = new Intent(Transactions.this, Transactions.class);

            startActivity(intent);
        }
        if (id == R.id.action_logout) {
            Intent intent = new Intent(Transactions.this, LoginScreen.class);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
