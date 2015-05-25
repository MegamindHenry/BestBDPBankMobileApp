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


    public class LoginScreen extends ActionBarActivity {


        int loginCount = 0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            final Button but = (Button)findViewById(R.id.button1);

            but.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {

                    int lockStatus;
                    final String[] username = new String[] {"Cameron"};

                    EditText edit1 = (EditText) findViewById(R.id.editText);
                    String text = edit1.getText().toString();

                    if (text.matches(""))

                    {Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_LONG).show();
                        return;}

                    if (text.equals("Cameron") && loginCount < 3)
                    {
                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                        startActivity(intent);
                    }
                    if (text.equals("Cameron") == false) {
                        Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_LONG).show();
                        loginCount++;
                    }
                    {

                        if (loginCount > 2)
                        {
                            lockStatus = 1;

                            if (lockStatus == 1)
                            {
                                Toast.makeText(getApplicationContext(), "Too many failed attempts, contact an administrator", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }

                }
            });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
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
