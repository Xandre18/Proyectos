package com.example.proyectoerp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.proyectoerp.objects.Cliente;
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

        //Tabla clientes
    private static final String TABLE_CUSTOMER = "customer";
    private static final String NAME_COL = "name";
    private static final String LAST_NAME_COL = "lastName";
    private static final String AGE_COL = "age";
    private static final String EMAIL_COL = "email";
    private static final String PHONE_COL = "phone";
    private static ArrayList<Cliente> customerList;

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

        String query_customer = "CREATE TABLE " + TABLE_CUSTOMER + "(" +
                NAME_COL + " TEXT NOT NULL, " +
                LAST_NAME_COL + " TEXT NOT NULL, " +
                AGE_COL + " TEXT NOT NULL, " +
                EMAIL_COL + " TEXT NOT NULL, " +
                PHONE_COL + " INTEGER  PRIMARY KEY)";
        db.execSQL(query_customer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONT);
    }

    public ArrayList<Cliente> readCustomer(){
       customerList = new ArrayList<>();
       SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER, null);
        if(c.moveToFirst()){
            do {
                customerList.add(new Cliente(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4)));
            }while (c.moveToNext());
        }

       return customerList;
    }

    public void addCustomer(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, cliente.getNombre());
        values.put(LAST_NAME_COL, cliente.getApellido());
        values.put(AGE_COL, cliente.getEdad());
        values.put(EMAIL_COL, cliente.getEmail());
        values.put(PHONE_COL, cliente.getTel());
        db.insert(TABLE_CUSTOMER,null,values);
        db.close();
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
        values.put(BALANCE_COL, auxBalance + (cc.getMount()));
        db.insert(TABLE_CONT, null, values);
        db.close();
    }

    public void addFistCont(ContControler contControler){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AMOUNT_COL, 0);
        values.put(BALANCE_COL, contControler.getBalance());
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

    public void updateCustomer(int tlfOriginal, Cliente c ){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();

       values.put(NAME_COL, c.getNombre());
       values.put(LAST_NAME_COL, c.getApellido());
       values.put(AGE_COL, c.getEdad());
       values.put(EMAIL_COL, c.getEmail());
       values.put(PHONE_COL, c.getTel());

       db.update(TABLE_CUSTOMER, values, PHONE_COL+ "=" +tlfOriginal, null);
    }
}
