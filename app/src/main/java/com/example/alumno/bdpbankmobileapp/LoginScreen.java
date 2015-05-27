package com.example.alumno.bdpbankmobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LoginScreen extends ActionBarActivity {


        int loginCount;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            final Button but = (Button)findViewById(R.id.button1);

            but.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);

                    startActivity(intent);
                }
            });

            but.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new ClientREST().execute();
                }
            });

        }

    private class ClientREST extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            Log.i("Login", "Login is working in the back");

            final TextView username = (TextView) findViewById(R.id.editText);
            final TextView password = (TextView) findViewById(R.id.editText2);


            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://192.168.19.164:8089/rest/Customer/login/");
                post.setHeader("content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);

                nameValuePairs.add(new BasicNameValuePair("username", username.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("password", password.getText().toString()));

                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse resp = httpClient.execute(post);

                String jsontext = EntityUtils.toString(resp.getEntity());
                Log.i("Login", jsontext);
                JSONObject objeto = new JSONObject(jsontext);

                String response = objeto.getString("response");

                if (response.equals("success") ) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(LoginScreen.this, "Correct Login", Toast.LENGTH_LONG).show();

                            LoginApplication.setUsername(username.getText().toString());
                            String currentUserName=LoginApplication.getUsername();
                            LoginApplication.setPassword(password.getText().toString());
                            String currentPassword=LoginApplication.getPassword();

                            Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                            startActivity(intent);


                        }
                    });
                }


                else if (response.equals("error") ) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(LoginScreen.this, "Error Login", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                else if (response.equals("lock") ) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(LoginScreen.this, "Too many attempts, contact an administrator", Toast.LENGTH_LONG).show();
                        }
                    });
                }



                else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(LoginScreen.this, "Incorrect Login", Toast.LENGTH_LONG).show();
                        }
                    }

                    );
                }

            } catch (Exception ex) {
                Log.e("Failed Login", "Error: " + ex);
            }

            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
        return true;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}

