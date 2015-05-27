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


public class Withdrawal extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        final Button but = (Button)findViewById(R.id.button);
        final Button with = (Button)findViewById(R.id.complete);

        but.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(Withdrawal.this, MainActivity.class);

                startActivity(intent);
            }
        });

        with.setOnClickListener(new View.OnClickListener()
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

            Log.i("Withdraw", "Withdraw is working in the back");

            TextView txtAccount = (TextView) findViewById(R.id.account);
            TextView txtAmount = (TextView) findViewById(R.id.amount);

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://192.168.19.22:8080/BestBankServerApp/rest/transaction/");
                post.setHeader("content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                nameValuePairs.add(new BasicNameValuePair("transType", "Withdraw"));
                nameValuePairs.add(new BasicNameValuePair("transAmount", txtAmount.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("Account", txtAccount.getText().toString()));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));


                HttpResponse resp = httpClient.execute(post);
                String jsontext = EntityUtils.toString(resp.getEntity());
                JSONObject objeto = new JSONObject(jsontext);
                String estado = objeto.getString("estado");
                Log.i("Withdrawal State", "------>" + estado);

                if ("CORRECTO".equals(estado)) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Withdrawal.this, "Se registró correctamente", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Withdrawal.this, "Hubo un error", Toast.LENGTH_LONG).show();
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
        if (id == R.id.action_home) {
            Intent intent = new Intent(Withdrawal.this, MainActivity.class);

            startActivity(intent);
        }
        if (id == R.id.action_deposit) {
            Intent intent = new Intent(Withdrawal.this, Deposit.class);

            startActivity(intent);
        }
        if (id == R.id.action_withdraw) {
            Intent intent = new Intent(Withdrawal.this, Withdrawal.class);

            startActivity(intent);
        }
        if (id == R.id.action_transfer) {
            Intent intent = new Intent(Withdrawal.this, Transfer.class);

            startActivity(intent);
        }
        if (id == R.id.action_transactions) {
            Intent intent = new Intent(Withdrawal.this, Transactions.class);

            startActivity(intent);
        }
        if (id == R.id.action_logout) {
            Intent intent = new Intent(Withdrawal.this, LoginScreen.class);

            startActivity(intent);
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
