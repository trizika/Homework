package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editBirdname, editZipcode, editPersonname;
    Button buttonSubmit, buttonGotosearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editBirdname = findViewById(R.id.editBirdName);
        editZipcode = findViewById(R.id.editZipcode);
        editPersonname = findViewById(R.id.editPersonname);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonGotosearch = findViewById(R.id.buttonGotosearch);

        buttonSubmit.setOnClickListener(this);
        buttonGotosearch.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Bird");


        if (view == buttonGotosearch) {
            Intent searchintent = new Intent(this, MainActivity2.class);
            startActivity(searchintent);

        } else if (view == buttonSubmit) {

            String birdname = editBirdname.getText().toString();
            int zipcode = Integer.parseInt(editZipcode.getText().toString());
            String personname = editPersonname.getText().toString();


            Bird myBird = new Bird(birdname, zipcode, personname);

            myRef.push().setValue(myBird);


        }

    }
}
