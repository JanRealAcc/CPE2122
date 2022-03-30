package com.example.amillionyearslater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class Status extends AppCompatActivity {

    private TextView user_info, user_id;
    private String userId;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        user_info = findViewById(R.id.info_here);
        userId = getIntent().getExtras().getString("Value");
        user_id = findViewById(R.id.id_here);
        user_id.setText("RESULT: " + userId);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance
                ("https://amillionyearslater-7935e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(userId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String firstNameU = snapshot.child("firstName").getValue(String.class);
                String middleNameU = snapshot.child("middleName").getValue(String.class);
                String lastNameU = snapshot.child("lastName").getValue(String.class);
                String phoneNumberU = snapshot.child("phoneNumber").getValue(String.class);
                String ageU = snapshot.child("age").getValue(String.class);
                String addressU = snapshot.child("address").getValue(String.class);
                String cityU = snapshot.child("city").getValue(String.class);
                String genderU = snapshot.child("gender").getValue(String.class);
                String emailU = snapshot.child("email").getValue(String.class);
                String courseU = snapshot.child("course").getValue(String.class);
                String schoolIdU = snapshot.child("schoolId").getValue(String.class);
                String vaccineU = snapshot.child("vaccine").getValue(String.class);
                String vaccineDosageU = snapshot.child("vaccineDosage").getValue(String.class);
                String birthDateU = snapshot.child("birthDate").getValue(String.class);
                String yearLevelU = snapshot.child("yearLevel").getValue(String.class);

                String info = firstNameU + " " + middleNameU + " " + lastNameU + "\n" +" \n" +
                                    addressU + "\n" + "\n" +
                                    cityU + "\n" + "\n" +
                                    courseU + "\n" + "\n" +
                                    yearLevelU + "\n" + "\n" +
                                    schoolIdU + "\n" + "\n" +
                                    vaccineU + "\n" +"\n" +
                                    vaccineDosageU + "\n" + "\n" +
                                    ageU + "y/o" + "\n" + "\n" +
                                    genderU + "\n" + "\n" +
                                    birthDateU +"\n" + "\n" +
                                    phoneNumberU + "\n" + "\n" +
                                    emailU;

                user_info.setText(info);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //SCAN AGAIN BUTTON
        Button scanCam = (Button) findViewById(R.id.scanCam);
        scanCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Status.this, QRCodeScanner.class));
            }
        });

        //FINISH BUTTON
        Button exitBtn = (Button) findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });




    }
}