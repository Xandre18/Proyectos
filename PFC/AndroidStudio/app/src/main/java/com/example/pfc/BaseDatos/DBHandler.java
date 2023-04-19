package com.example.pfc.BaseDatos;

import android.content.Context;
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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
