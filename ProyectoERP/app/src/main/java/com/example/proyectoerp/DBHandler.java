package com.example.proyectoerp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.proyectoerp.objects.ContControler;
import com.example.proyectoerp.objects.User;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bdERP.sqlite";

        //Tabla usuarios
    private static final String TABLE_USERS = "users";
    private static final String USER_COL = "userName";
    private static final String PASWD_COL = "paswd";
    private static final String ADMIN_COL = "admin";
    private static ArrayList<User> userList;

        //Tabla contabilidad

    private static final String TABLE_CONT = "contability";
    private static final String ID_COL = "id";
    private static final String AMOUNT_COL  = "amount";
    private static final String BALANCE_COL = "balance";
    private static ArrayList<ContControler> contList;

   public DBHandler(Context context){super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_Users = "CREATE TABLE " + TABLE_USERS + "(" +
                USER_COL + " TEXT NOT NULL, " +
                PASWD_COL + " TEXT NOT NULL," +
                ADMIN_COL + " INTEGER)";
        db.execSQL(query_Users);

        String query_Cont = "CREATE TABLE " + TABLE_CONT + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AMOUNT_COL + " INTEGER NOT NULL," +
                BALANCE_COL + " INTEGER NOT NULL)";

        db.execSQL(query_Cont);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONT);
    }

    public ArrayList<ContControler> readCont(){
       contList = new ArrayList<>();
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CONT, null);
        if(c.moveToFirst()){
            do {
                contList.add(new ContControler(c.getInt(0),c.getInt(1),c.getInt(2)));
            }while(c.moveToNext());
        }
        return contList;
    }

    public void addCont(ContControler cc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AMOUNT_COL, cc.getMount());
        ArrayList<ContControler> aux = readCont();
        int auxBalance = 0;
        for (int i = aux.size() - 1; i < aux.size(); i++) {
            auxBalance = aux.get(i).getBalance();
        }
        values.put(BALANCE_COL, auxBalance - (cc.getMount()));
        db.insert(TABLE_CONT, null, values);
        db.close();
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
        c.close();
       return userList;
    }

    public void addUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COL, u.getName());
        values.put(PASWD_COL, u.getPaswd());
        values.put(ADMIN_COL , u.isAdmin());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

}
