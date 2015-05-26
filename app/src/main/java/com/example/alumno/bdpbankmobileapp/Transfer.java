

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

import org.apache.http.NameValuePair;


public class Transfer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        final Button but = (Button)findViewById(R.id.button2);

        but.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(Transfer.this, MainActivity.class);

                startActivity(intent);
            }
        });

        final String[] categoriasFrom = new String[]{"checking", "savings"};

        ArrayAdapter<String> adaptadorFrom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriasFrom);
        adaptadorFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner comboFrom = (Spinner) findViewById(R.id.spinner);
        comboFrom.setAdapter(adaptadorFrom);

        comboFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                Toast.makeText(Transfer.this, "Select: " + categoriasFrom[position], Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Transfer.this, "Nothing has been selected", Toast.LENGTH_LONG).show();
            }
        });

        final String[] categoriasTo = new String[]{"checking", "savings"};

        ArrayAdapter<String> adaptadorTo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriasTo);
        adaptadorTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner comboTo = (Spinner) findViewById(R.id.spinner2);
        comboTo.setAdapter(adaptadorTo);

        comboTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                Toast.makeText(Transfer.this, "Select: " + categoriasTo[position], Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Transfer.this, "Nothing has been selected", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transfer, menu);
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


}
