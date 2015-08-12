package com.example.sand_r0.co_workerregform;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

public class UserDataReg extends AppCompatActivity {

    Bitmap thumbnail;
    ParseFile profileImage;

    EditText fName, phNumber, mail;
    ImageView picView;
    Button uploadBtn, uRegBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_reg);

        fName = (EditText) findViewById(R.id.fullNameTxt);
        phNumber = (EditText) findViewById(R.id.telTxt);
        mail = (EditText) findViewById(R.id.mailTxt);

        picView = (ImageView) findViewById(R.id.picView);

        uploadBtn = (Button) findViewById(R.id.uploadBtn);
        uRegBtn = (Button) findViewById(R.id.regDataBtn);


        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        uRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //compressAndSave();

                ParseObject userInfo = new ParseObject("GeoWorkers");
                userInfo.put("Full_Name", fName.getText().toString());
                userInfo.put("Email", mail.getText().toString());
                userInfo.put("Tel", phNumber.getText().toString());
                userInfo.put("Profile_pic", profileImage);
                userInfo.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(),
                                    "Saved", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), UserDataReg.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }

    public void selectImage() {
        Intent getIMG = new Intent(Intent.ACTION_PICK);
        getIMG.setType("image/*");
        //   if (getIMG.resolveActivity(getPackageManager()) != null) {
        startActivityForResult(getIMG, 1);
        // }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {

            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();

            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();


            Bitmap prfPic = (BitmapFactory.decodeFile(picturePath));
            thumbnail = Bitmap.createScaledBitmap(prfPic, 200, 200
                    * prfPic.getHeight() / prfPic.getWidth(), false);

            saveAndSet();


        }
    }

    private void saveAndSet() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] scaledData = bos.toByteArray();

        picView.setImageBitmap(thumbnail);

        profileImage = new ParseFile("Profile_Pic.jpg", scaledData);
        profileImage.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(),
                            "Photo Saved", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Error Saving"
                            + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_data_reg, menu);
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
