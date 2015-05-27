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
import android.widget.Spinner;
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

public class Transactions extends ActionBarActivity {

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        ((LoginApplication)getApplication().getUsername());

        final Button but = (Button)findViewById(R.id.homeButton);

        new ClienteREST().execute();

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

    private class ClienteREST extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            Log.i("ProductosBuscarREST", "Dentro de doInBackground()");

            final ListView lstProductos = (ListView)findViewById(R.id.listView);
            TextView txtTest1 = (TextView)findViewById(R.id.editText3);
            //TextView txtTest2 = (TextView)findViewById(R.id.editText4);

            try {
                HttpClient httpClient = new DefaultHttpClient();
                //HttpGet get = new HttpGet("http://192.168.115.86:8080/trastienda2015_ws/rest/productos/"+txtProducto.getText().toString());
                HttpGet get = new HttpGet("http://192.168.19.18:8080/BestBankServerApp/rest/transaction/Saving/2");

                HttpResponse resp = httpClient.execute(get);
                String jsontext = EntityUtils.toString(resp.getEntity());

                JSONArray entries = new JSONArray(jsontext);
                //JSONObject testObj = new JSONObject(jsontext);


                final String[] listaClientes = new String[entries.length()];
                String listtype = "";
                String listdate = "";
                String listamount = "";
                String liststatus = "";

                for (int i=0;i<entries.length();i++) {
                    JSONObject objeto = entries.getJSONObject(i);
                    listtype += objeto.getString("transType") + "\r\n";
                    listdate += objeto.getString("transDateTime") + "\r\n";
                    listamount += objeto.getString("transAmount") + "\r\n";
                    liststatus += objeto.getString("transStatus") + "\r\n";
                    //listaClientes[i] = padRight((objeto.getString("transType")), 11) + padRight(((objeto.getString("transDateTime")).substring(0,9)), 11) + objeto.getString("transAmount") + objeto.getString("transStatus");

                }

                EditText listtype = (EditText)findViewById(R.id.listType);
                EditText listdate = (EditText)findViewById(R.id.listDate);
                EditText listamount = (EditText)findViewById(R.id.listType);
                EditText liststatus = (EditText)findViewById(R.id.listType);



                /*
                String dateTime = testObj.getString("DateTime");
                String amount = testObj.getString("Amount");

                txtTest1.setText(dateTime);
                txtTest2.setText(amount);
*/

/*                runOnUiThread(new Runnable() {
                    public void run() {
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                                Transactions.this,
                                android.R.layout.listType,
                                listaClientes);
                        lstProductos.setAdapter(adaptador);
                    }
                });*/


            } catch (Exception ex) {
                Log.e("ProductosBuscarREST", "Error: " + ex);
            }
            return null;
        }
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
