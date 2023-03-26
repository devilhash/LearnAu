package com.app.learnau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class BodyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> branch;
    TextView website;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);
        recyclerView = findViewById(R.id.recview);
        website = findViewById(R.id.website);

        branch = new ArrayList<>();
        branch.add("CSE");
        branch.add("ECE");
        branch.add("EEE");
        branch.add("MECH");
        branch.add("CIVIL");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        BranchAdapter branchAdapter = new BranchAdapter(branch,BodyActivity.this);
        recyclerView.setAdapter(branchAdapter);

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BodyActivity.this,ViewWeb.class));
            }
        });



    }
}