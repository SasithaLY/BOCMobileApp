package com.example.sasitha.bocmobileapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button signIn;
    EditText username, password;

    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        users.add(new User("sasitha", "sasitha123"));
        users.add(new User("pasan", "pasan123"));
        users.add(new User("upeksha", "upeksha123"));
        users.add(new User("kavini", "kavini123"));


        // Here, thisActivity is the current activity

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editTextUserName);
        password = (EditText) findViewById(R.id.editTextPassword);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarWhite);
        setSupportActionBar(toolbar);

//       ImageView logo = toolbar.findViewById(R.id.imageViewLogo);
//
//         toolbar.setLogo(R.drawable.boc_logo);
//       toolbar.setLogo(R.drawable.boc_logo);

        // getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        signIn = (Button) findViewById(R.id.btnSignIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString();
                String pass = password.getText().toString();

                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);

//                if(name.isEmpty()){
//                    username.setError("Enter Username!");
//                }else if(pass.isEmpty()){
//                    password.setError("Enter Password!");
//                }else {
//                    for(User u : users){
//                        if((u.getUsername().equals(name)) && (u.getPassword().equals(pass))){
//                            Intent intent = new Intent(MainActivity.this, Home.class);
//                            startActivity(intent);
//                            Toast.makeText(MainActivity.this, "Welcome "+name, Toast.LENGTH_SHORT).show();
//                            break;
//                        }else if((u.getUsername().equals(name)) && (!u.getPassword().equals(pass))){
//                            Toast.makeText(MainActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
//                            password.setError("Incorrect Password!");
//                        }else {
//                            Toast.makeText(MainActivity.this, "There is no such User!", Toast.LENGTH_SHORT).show();
//                            username.setError("Incorrect Username!");
//                        }
//                    }
//                }




            }
        });
    }
}
