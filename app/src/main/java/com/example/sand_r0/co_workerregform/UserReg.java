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

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class UserReg extends AppCompatActivity {

    EditText wUser, wPass, wPass2;
    Button signBtn, tempBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        Parse.enableLocalDatastore(this);



        Parse.initialize(this, "8m6aynpMS2egMvnDD048vcrwyZoiDSFNCTzZtbLp",
                "zivve5EsuniwaXgRj26s0TwSP5DtaUZeLn5SRuC7");

        wUser = (EditText) findViewById(R.id.workerUserTxt);
        wPass = (EditText) findViewById(R.id.workerPassTxt);
        wPass2 = (EditText) findViewById(R.id.workerPass2Txt);

        signBtn = (Button) findViewById(R.id.signBtn);
        tempBtn = (Button) findViewById(R.id.tempBtn);





        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String psw = wPass.getText().toString();
                String psw2 = wPass2.getText().toString();

                switch (v.getId()){
                    case (R.id.signBtn):
                        if(!psw.equals(psw2)){
                            Toast.makeText(UserReg.this,
                                    "Password Doesnt Match", Toast.LENGTH_LONG).show();
                        }else{
                            ParseUser user = new ParseUser();
                            user.setUsername(wUser.getText().toString());
                            user.setPassword(psw);
                            user.signUpInBackground(new SignUpCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e == null){
                                        Toast.makeText(UserReg.this,
                                                "Registered", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(getApplicationContext(), UserDataReg.class);
                                        startActivity(i);
                                    }else {
                                        Toast.makeText(UserReg.this,
                                                "Something Went Wrong", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                        }

                }

            }
        });
                 //i have to delete this button later
        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tI = new Intent(getApplicationContext(), UserDataReg.class);
                startActivity(tI);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_reg, menu);
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
