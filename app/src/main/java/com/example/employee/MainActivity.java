package com.example.employee;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ename,eemail;
    Button btnadd,btnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ename=findViewById(R.id.ename);
        eemail=findViewById(R.id.eemail);
        btnadd=findViewById(R.id.btnadd);
        btnview=findViewById(R.id.btnview);
    }

    public void Add(View view) {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT=ename.getText().toString();
                String emailTXT=eemail.getText().toString();

                if (nameTXT.length() <=0 || emailTXT.length() <=0){
                    Toast.makeText(MainActivity.this, "please enter the details", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseHelperClass databaseHelperClass=new DatabaseHelperClass(MainActivity.this);
                    EmployeeModelClass employeeModelClass=new EmployeeModelClass(nameTXT,emailTXT);
                    databaseHelperClass.addEmployee(employeeModelClass);

                    Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());

                }

            }
        });
    }

    public void View(View view) {
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewEmployeeActivity.class);
                startActivity(intent);

            }
        });
    }
}