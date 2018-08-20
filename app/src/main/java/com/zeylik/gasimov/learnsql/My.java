package com.zeylik.gasimov.learnsql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class My extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "mydb3";
    private static final int DATABASE_VERSION = 1;

    public My(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

    public Cursor getEmployees(String s) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"esl", "_id"};
        String sqlTables = "sozler";

        qb.setTables(sqlTables);
        String Q = "soz like '%"+s+"%'";
        Cursor c = qb.query(db, sqlSelect, Q, null,
                null, null, "esl");

        c.moveToFirst();
        return c;

    }

}
