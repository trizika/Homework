package com.example.homework4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    EditText editZipcode2;
    Button buttonSearch, buttonGotoReport;
    TextView textBirdname, textPersonname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        editZipcode2 = findViewById(R.id.editZipcode2);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonGotoReport = findViewById(R.id.buttonGotoReport);
        textBirdname = findViewById(R.id.textBirdname);
        textPersonname = findViewById(R.id.textPersonname);

        buttonSearch.setOnClickListener(this);

        buttonGotoReport.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Bird");

        if (view == buttonGotoReport) {
            Intent reportintent = new Intent(this, MainActivity.class);
            startActivity(reportintent);

        } else if (view == buttonSearch) {

            int findZipcode = Integer.parseInt(editZipcode2.getText().toString());
            myRef.orderByChild("zipcode").equalTo(findZipcode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String foundBirdname = foundBird.birdname;
                    String foundPersonname = foundBird.personname;

                    textPersonname.setText(foundPersonname);
                    textBirdname.setText(foundBirdname);
                }


                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
}
