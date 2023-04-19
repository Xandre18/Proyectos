package com.example.pfc.BaseDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pfc.Objetos.Cliente;
import com.example.pfc.Objetos.Producto;
import com.example.pfc.Objetos.Venta;
import com.example.pfc.Objetos.VentaProducto;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "dbShopScan.sqlite";

    //Tabla Cliente;
    private static final String TABLA_CLIENTE = "clientes";
    private static final String IDCLIENTE_COL = "id";
    private static final String DNI_COL = "dni";
    private static final String TLF_COL = "tlf";
    private static final String NOMBRE_COL = "nombre";
    private static final String EMAIL_COL = "email";
    private static final String DIRECCION_COL = "dir";
    private static final String USUARIO_COL = "user";
    private static final String CONTRASENHA_COL = "pwd";
    private static final String ADMIN_COL = "admin";
    private static ArrayList<Cliente> listaClientes;

    //Tabla Venta
    private static final String TABLA_VENTA = "ventas";
    private static final String CODIGO_COL = "codV";
    private static final String FECHA_COL = "fecha";
    private static final String CLIENTE_COL = "id_cliente";
    private static ArrayList<Venta> listaVentas;

    //Tabla Producto;
    private static final String TABLA_PRODUCTO = "productos";
    private static final String REF_COL = "refNum";
    private static final String IDPRODUCTO_COL = "idProd";
    private static final String STOCk_COL = "stock";
    private static final String PRECIO_COL = "precioUnidad";
    private static ArrayList<Producto> listaProductos;

    //Tabla Productos-Venta
    private static final String TABLA_PROD_VENT = "ventasProductos";
    private static final String COD_VENTA_COL = "codV";
    private static final String ID_PROD = "idProd";
    private static final String CANTIDAD_COL = "cantidad";
    private static ArrayList<VentaProducto> listaVentaProducto;

    //Constructor
    public DBHandler(Context context){super(context, DB_NAME, null, DB_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_Cliente = "CREATE TABLE " + TABLA_CLIENTE + "(" +
                IDCLIENTE_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DNI_COL + " TEXT NOT NULL," +
                TLF_COL + " TEXT NOT NULL," +
                NOMBRE_COL + " TEXT NOT NULL," +
                EMAIL_COL + " TEXT NOT NULL," +
                DIRECCION_COL  + " TEXT NOT NULL," +
                USUARIO_COL  + " TEXT NOT NULL," +
                CONTRASENHA_COL + " TEXT NOT NULL," +
                ADMIN_COL + " INTEGER " +
                ")";
        db.execSQL(query_Cliente);

        String query_Venta = "CREATE TABLE " + TABLA_VENTA + "(" +
                CODIGO_COL  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FECHA_COL + " TEXT NOT NULL," +
                CLIENTE_COL +" INTEGER NOT NULL REFERENCES "+ TABLA_CLIENTE +
                ")";
        db.execSQL(query_Venta);

        String query_Producto = "CREATE TABLE " + TABLA_PRODUCTO + "(" +
                REF_COL + " INTEGER NOT NULL," +
                IDPRODUCTO_COL  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STOCk_COL + " INTEGER NOT NULL," +
                PRECIO_COL + " INTEGER NOT NULL" +
                ")";
        db.execSQL(query_Producto);

        String query_ProductoVenta = "CREATE TABLE " + TABLA_PROD_VENT + "(" +
                COD_VENTA_COL +" INTEGER NOT NULL REFERENCES "+ TABLA_CLIENTE +", "+
                ID_PROD +" INTEGER NOT NULL REFERENCES "+ TABLA_PRODUCTO +", "+
                CANTIDAD_COL  +" INTEGER NOT NULL "+
                ")";
        db.execSQL(query_ProductoVenta);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CLIENTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_VENTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PROD_VENT);
        onCreate(db);
    }

    public ArrayList<Cliente> getClientes(){
        listaClientes = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_CLIENTE, null);
        if(c.moveToNext()){
            do{
                boolean admin;
                if(c.getInt(9) == 1) admin = true;
                else admin = false;
                listaClientes.add(new Cliente(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),admin));
            }while(c.moveToNext());
        }
        return listaClientes;
    }
}
