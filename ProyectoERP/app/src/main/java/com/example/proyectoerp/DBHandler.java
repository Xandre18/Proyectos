package com.example.proyectoerp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bdERP.sqlite";

    private static final String TABLE_USERS = "users";
    private static final String USER_COL = "userName";
    private static final String PASWD_COL = "paswd";
    private static final String ADMIN_COL = "admin";

    private static ArrayList<User> userList;

   public DBHandler(Context context){super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                USER_COL + " TEXT NOT NULL, " +
                PASWD_COL + " TEXT NOT NULL," +
                ADMIN_COL + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<User> leerUsers(){
       userList = new ArrayList<>();
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        if(c.moveToFirst()){
            do {
                boolean admin;
                if(c.getInt(2)==1)admin = true;
                else admin = false;

                userList.add(new User(c.getString(0),c.getString(1),admin));
            }while(c.moveToNext());
        }

       return userList;
    }
    public void addUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COL, u.getName());
        values.put(PASWD_COL, u.getPaswd());
        values.put(ADMIN_COL , u.isAdmin());
//        if(u.isAdmin()){
//            values.put(ADMIN_COL, 1);
//        }else values.put(ADMIN_COL, 0);

        db.insert(TABLE_USERS, null, values);
        db.close();
    }
}
