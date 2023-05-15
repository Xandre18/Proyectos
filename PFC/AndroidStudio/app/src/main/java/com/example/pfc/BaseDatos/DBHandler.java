package com.example.pfc.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pfc.Objetos.Cliente;
import com.example.pfc.Objetos.Producto;
import com.example.pfc.Objetos.ProductoCantidad;
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
    private static final String APELLIDO_COL = "apellido";
    private static final String EMAIL_COL = "email";
    private static final String DIRECCION_COL = "dir";
    private static final String USUARIO_COL = "user";
    private static final String CONTRASENHA_COL = "pwd";
    private static final String ADMIN_COL = "admin";
    private static final String SESION_COL = "sesion";
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
    private static final String STOCK_COL = "stock";
    private static final String PRECIO_COL = "precioUnidad";
    private static final String IMG_COL = "img";
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
                APELLIDO_COL + " TEXT NOT NULL," +
                EMAIL_COL + " TEXT NOT NULL," +
                DIRECCION_COL  + " TEXT NOT NULL," +
                USUARIO_COL  + " TEXT NOT NULL," +
                CONTRASENHA_COL + " TEXT NOT NULL," +
                ADMIN_COL + " INTEGER, " +
                SESION_COL + " INTEGER " +
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
                STOCK_COL + " INTEGER NOT NULL," +
                PRECIO_COL + " INTEGER NOT NULL," +
                IMG_COL + " INTEGER NOT NULL," +
                NOMBRE_COL + " TEXT NOT NULL" +
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
                boolean admin , sesion;
                if(c.getInt(9) == 1) admin = true;
                else admin = false;

                if(c.getInt(10) == 1) sesion = true;
                else sesion = false;

                Cliente cliente = new Cliente(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8), admin, sesion);
                listaClientes.add(cliente);
            }while(c.moveToNext());
        }
        return listaClientes;
    }

    public void setSesionCol(int idCliente, boolean sesion ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int variable;
        if(sesion){
            variable = 1;
        }else{
            variable = 0;
        }
        values.put(SESION_COL, variable);
        db.update(TABLA_CLIENTE, values, "id=?",new String[]{String.valueOf(idCliente)});
    }

    public void  updateStock(int idProducto ,int cantidad){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STOCK_COL, cantidad);
        db.update(TABLA_PRODUCTO, values, "idProd=?", new String[]{String.valueOf(idProducto)});
    }

    public void addCliente(Cliente c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DNI_COL, c.getDni());
        values.put(TLF_COL,c.getTlf());
        values.put(NOMBRE_COL, c.getNombre());
        values.put(APELLIDO_COL, c.getApellido());
        values.put(EMAIL_COL, c.getEmail());
        values.put(DIRECCION_COL, c.getDireccion());
        values.put(USUARIO_COL, c.getUsuario());
        values.put(CONTRASENHA_COL, c.getContraseña());
        values.put(ADMIN_COL, c.isAdmin());
        values.put(SESION_COL, c.isSesion());

        db.insert(TABLA_CLIENTE, null, values);
        db.close();
    }
    public ArrayList<Producto> getProductos(){
        listaProductos = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTO, null);
        if(c.moveToNext()){
            do{
                Producto producto = new Producto(c.getInt(0),c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4),c.getString(5));
                listaProductos.add(producto);
            }while (c.moveToNext());
        }
        db.close();
        return listaProductos;
    }


    public void addProducto(Producto p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REF_COL, p.getRefNu());
        values.put(STOCK_COL,  p.getStock());
        values.put(PRECIO_COL, p.getPrecio());
        values.put(IMG_COL, p.getImg());
        values.put(NOMBRE_COL, p.getNombre());
        db.insert(TABLA_PRODUCTO, null, values);
        db.close();
    }

    public int getIDuserConectado(){
        SQLiteDatabase db = this.getWritableDatabase();
        int id = 0;
        Cursor c = db.rawQuery("SELECT id FROM " + TABLA_CLIENTE + " WHERE sesion = 1", null);
        if(c.moveToNext()){
            do{
                id = c.getInt(0);
            }while (c.moveToNext());
        }

        return id;

    }

    public Cliente getClienteConectado(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cliente user = new Cliente();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_CLIENTE + " WHERE sesion = 1", null);
        if(c.moveToNext()){
            do{
                boolean admin , sesion;
                if(c.getInt(9) == 1) admin = true;
                else admin = false;

                if(c.getInt(10) == 1) sesion = true;
                else sesion = false;

                user = new Cliente(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8),admin,sesion);
            }while (c.moveToNext());
        }
        return user;
    }

    public String getNombreByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String nombreProducto = "";
        Cursor c = db.rawQuery("SELECT nombre FROM " + TABLA_PRODUCTO + " WHERE idProd = " + id, null);
        if(c.moveToNext()){
            do{
                nombreProducto = c.getString(0);
            }while (c.moveToNext());
        }
        return nombreProducto;
    }

    public void addVenta(Venta v, ArrayList<ProductoCantidad> productoCantidads){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CLIENTE_COL, v.getCliente());
        values.put(FECHA_COL, v.getFecha());
        db.insert(TABLA_VENTA, null, values);
        db.close();
        getVentas();
        int ultimoIdVenta = 0;
        for(Venta vAux: listaVentas){
            ultimoIdVenta = vAux.getCodigo();
        }
        for(ProductoCantidad pc :productoCantidads){
            addVentaProducto(ultimoIdVenta, pc.getProducto(), pc.getCantidad());
        }
    }

    public ArrayList<Venta> getVentas(){
        listaVentas = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_VENTA, null);
        if(c.moveToNext()){
            do{
              Venta v = new Venta(c.getInt(0),c.getInt(2),c.getString(1));
              listaVentas.add(v);
            }while (c.moveToNext());
        }
        return listaVentas;
    }

    public ArrayList<Venta> getVentasByCliente(int id){
        listaVentas = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_VENTA + " WHERE id_cliente = " + id, null);
        if(c.moveToNext()){
            do{
                Venta v = new Venta(c.getInt(0),c.getInt(2),c.getString(1));
                listaVentas.add(v);
            }while (c.moveToNext());
        }
        return listaVentas;

    }

    public void addVentaProducto(int codV,int idProd, int cantidad ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COD_VENTA_COL, codV);
        values.put(ID_PROD, idProd);
        values.put(CANTIDAD_COL, cantidad);
        db.insert(TABLA_PROD_VENT, null, values);
        db.close();
    }

}
