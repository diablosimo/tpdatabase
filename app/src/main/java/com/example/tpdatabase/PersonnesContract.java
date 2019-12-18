package com.example.tpdatabase;

import android.provider.BaseColumns;

import java.util.Date;

public final class PersonnesContract {
    private PersonnesContract() {}
    public static class PersonneTable implements BaseColumns {
        public static final String TABLE_NAME="PERSONNE";
        public static final String COLUMN_NOM="nom_personne";
        public static final String COLUMN_PENOM="prenom_personne";
        public static final String COLUMN_NUM_TEL="num_tel_personne";
    }
}
