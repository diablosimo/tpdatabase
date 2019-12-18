package com.example.tpdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Person;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    EditText nom;
    Button search,back;
    ListView listView;
    PersonnesDBHelper db;
    List<Personne> personnes;
    ArrayAdapter <Personne> personneArrayAdapter;
    Personne p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db=new PersonnesDBHelper(getApplicationContext());
        listView=findViewById(R.id.list);
        nom=findViewById(R.id.nom_search);
        search=findViewById(R.id.search);
        back=findViewById(R.id.back);
        personnes=new ArrayList<>();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nom.getText()!=null){
                    personnes=db.getPersonByName(nom.getText().toString());
                }
                personneArrayAdapter =new ArrayAdapter<Personne>(search.getContext(),R.layout.support_simple_spinner_dropdown_item,personnes);
                listView.setAdapter(personneArrayAdapter);
                registerForContextMenu(listView);
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                p= (Personne) parent.getItemAtPosition(position);
                new AlertDialog.Builder(search.getContext())
                        .setMessage("voulez vous supprimer "+p.getNom())
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.deletePersonne(p);
                                personnes.remove(p);
                                personneArrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.this.finish();
            }
        });
    }
}
