package com.example.tpdatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonnesDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME="PERSONNES.DB";
    public static final String TEXT_TYPE=" TEXT";


    private static final  String SQL_CREATE_TABLE_PERSONNE="CREATE TABLE "+PersonnesContract.PersonneTable.TABLE_NAME+"( "
            + PersonnesContract.PersonneTable._ID + " INTEGER PRIMARY KEY, "
            + PersonnesContract.PersonneTable.COLUMN_NOM + TEXT_TYPE + ", "
            + PersonnesContract.PersonneTable.COLUMN_PENOM + TEXT_TYPE + ", "
            + PersonnesContract.PersonneTable.COLUMN_NUM_TEL+ TEXT_TYPE + ")";

    private static final  String SQL_DELETE_TABLE_PERSONNE="DROP TABLE IF EXISTS "+PersonnesContract.PersonneTable.TABLE_NAME;

    public PersonnesDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PERSONNE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_PERSONNE);
        onCreate(db);
    }

    public void insertData(String nom, String prenom, String numTel){
        ContentValues contentValues=new ContentValues();
        contentValues.put(PersonnesContract.PersonneTable.COLUMN_NOM,nom);
        contentValues.put(PersonnesContract.PersonneTable.COLUMN_PENOM,prenom);
        contentValues.put(PersonnesContract.PersonneTable.COLUMN_NUM_TEL,numTel);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(PersonnesContract.PersonneTable.TABLE_NAME,null,contentValues);
        db.close();
    }

    public void deletePersonne(Personne p) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PersonnesContract.PersonneTable.TABLE_NAME, "_ID" + " = ?",
                new String[] { String.valueOf(p.getId()) });
        db.close();
    }

    public List<Personne> getPersonByName(String nom){
        List<Personne> personnes=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection={PersonnesContract.PersonneTable._ID,PersonnesContract.PersonneTable.COLUMN_NOM, PersonnesContract.PersonneTable.COLUMN_PENOM, PersonnesContract.PersonneTable.COLUMN_NUM_TEL};
        Cursor c=db.query(PersonnesContract.PersonneTable.TABLE_NAME,
                projection,
                PersonnesContract.PersonneTable.COLUMN_NOM+"=?",
                new String[]{nom},
                null,
                null,
                null);
        if(c.moveToFirst()){
            do{
                personnes.add(new Personne(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return personnes;
    }



}
