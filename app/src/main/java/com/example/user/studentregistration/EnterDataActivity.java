package com.example.user.studentregistration;

/**
 * Created by user on 6/10/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class EnterDataActivity extends Activity{
    EditText etnm, editTextEmail, editTextPhone, editTextAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Button btnRegistration;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnm = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);

        //btnRegistration = (Button) findViewById(R.id.btnReg);
    }
    public void onClickAdd (View btnAdd) {

        String personName = etnm.getText().toString();
        String personEmail = editTextEmail.getText().toString();
        String personPhone = editTextPhone.getText().toString();
        String personAddress = editTextAddress.getText().toString();


        if ( personName.length() != 0 && personEmail.length() != 0 && personPhone.length() != 0 && personAddress.length() != 0) {

            Intent newIntent = getIntent();
            newIntent.putExtra("tag_person_name", personName);
            newIntent.putExtra("tag_person_Email", personEmail);
            newIntent.putExtra("tag_person_Phone", personPhone);
            newIntent.putExtra("tag_person_Address", personAddress);

            this.setResult(RESULT_OK, newIntent);

            finish();
        }
    }
}
