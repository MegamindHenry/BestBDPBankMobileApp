package com.example.alumno.bdpbankmobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Deposit extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

       final Button but = (Button)findViewById(R.id.cancelBttn);
       final Button dep = (Button)findViewById(R.id.complete);

       but.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(Deposit.this, MainActivity.class);

                startActivity(intent);
            }
        });

        dep.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                new ClientREST().execute();
            }
        });
    }

    private class ClientREST extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            Log.i("Deposit", "Deposit is working in the back");

            TextView txtAccount = (TextView) findViewById(R.id.account);
            TextView txtAmount = (TextView) findViewById(R.id.amount);

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://192.168.19.164:8089/rest/transaction/deposit/");
                post.setHeader("content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                //nameValuePairs.add(new BasicNameValuePair("transType", "Withdraw"));
                //String name = ((LoginApplication)getApplication()).getUsername();
                nameValuePairs.add(new BasicNameValuePair("account", "1"));//txtAccount.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("amount",txtAmount.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("type", "1"));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));




                HttpResponse resp = httpClient.execute(post);
                String jsontext = EntityUtils.toString(resp.getEntity());
                JSONObject objeto = new JSONObject(jsontext);
                String state = objeto.getString("response");
                Log.i("Deposit State", "------>" + state);

                if ("success".equals(state)) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Deposit.this, "Deposit Occured", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Deposit.this, "Lack Funds  ", Toast.LENGTH_LONG).show();
                        }
                    });
                }


            } catch (Exception ex) {
                Log.e("Withdrawal", "Error: " + ex);
            }
            return null;
        }
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
        if (id == R.id.action_home) {
            Intent intent = new Intent(Deposit.this, MainActivity.class);

            startActivity(intent);
        }
        if (id == R.id.action_deposit) {
            Intent intent = new Intent(Deposit.this, Deposit.class);

            startActivity(intent);
        }
        if (id == R.id.action_withdraw) {
            Intent intent = new Intent(Deposit.this, Withdrawal.class);

            startActivity(intent);
        }
        if (id == R.id.action_transfer) {
            Intent intent = new Intent(Deposit.this, Transfer.class);

            startActivity(intent);
        }
        if (id == R.id.action_transactions) {
            Intent intent = new Intent(Deposit.this, Transactions.class);

            startActivity(intent);
        }
        if (id == R.id.action_logout) {
            Intent intent = new Intent(Deposit.this, LoginScreen.class);

            startActivity(intent);
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

