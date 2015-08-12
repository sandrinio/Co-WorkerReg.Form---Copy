package com.example.sand_r0.co_workerregform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText userInput, passInput;
    Button loginBtn;
    //    Intent iUserReg = new Intent(this, UserReg.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        userInput = (EditText) findViewById(R.id.usernameTxt);
        passInput = (EditText) findViewById(R.id.passTxt);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String us = userInput.getText().toString();
                String pw = passInput.getText().toString();

                switch (v.getId()) {
                    case (R.id.loginBtn):
                        if (us == "" && pw == "") {
                            Toast.makeText(AdminLogin.this,
                                    "Fill Fields", Toast.LENGTH_LONG).show();
                        } else if (!us.equals("admin") || !pw.equals("kukuruku")) {
                            Toast.makeText(AdminLogin.this,
                                    "Wrong ID or Password", Toast.LENGTH_LONG).show();
                        } else if (us.equals("admin") || pw.equals("kukuruku")) {
                            Intent i = new Intent(getApplicationContext(), UserReg.class);
                            startActivity(i);
                        }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_login, menu);
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
