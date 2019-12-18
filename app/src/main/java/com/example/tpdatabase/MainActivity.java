package com.example.tpdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nom,prenom,numTel;
    Button save,recherche;
    PersonnesDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new PersonnesDBHelper(getApplicationContext());
        nom=findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        numTel=findViewById(R.id.numtel);
        save=findViewById(R.id.save);
        recherche=findViewById(R.id.recherche);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nom.getText().toString();
                String surname=prenom.getText().toString();
                String tel=numTel.getText().toString();
                if(!name.isEmpty() && !surname.isEmpty() && !tel.isEmpty()){
                    db.insertData(name,surname,tel);
                }

            }
        });
        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,search.class);
                startActivity(intent);
            }
        });
    }
}
