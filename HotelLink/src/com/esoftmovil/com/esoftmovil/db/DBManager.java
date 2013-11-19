package com.esoftmovil.com.esoftmovil.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper
{
	private SQLiteDatabase  mDb;
	Context ctx;
	String DATABASE_PATH = "/data/data/com.esoftmovil.hotellink/databases/";
	static String DATABASE_NAME="BDHotelLinkAndroid";

	//private static final int DATABASE_VERSION = 1;
	public DBManager(Context ctx)
	{
	    super(ctx,"BDHotelLinkAndroid",null,1);
	}
	
    @Override
    public void onCreate(SQLiteDatabase db) 
    {
    	db.execSQL("CREATE TABLE banner (bannerID int NOT NULL primary key,	imagen varchar(300) NOT NULL,estaActivo bit DEFAULT 1 NOT NULL,	fechaModificacion datetime NOT NULL,hotelID uniqueidentifier NOT NULL)");
    	db.execSQL("CREATE TABLE habitacion (habitacionID uniqueidentifier NOT NULL primary key,habitacionTipoID uniqueidentifier,habitacionEstatusID tinyint NOT NULL,pisoID uniqueidentifier NOT NULL,nombre varchar(100) NOT NULL,	estaActivo bit DEFAULT 1 NOT NULL)");
    	db.execSQL("CREATE TABLE huesped (huespedID uniqueidentifier NOT NULL primary key,hotelID uniqueidentifier NOT NULL,nombre varchar(300) NOT NULL,sexo bit,correo varchar(100),	telefono varchar(50),	domicilio varchar(300),	ciudad varchar(30),	estado varchar(30),	pais varchar(30),	observaciones varchar(4000),	fechaNacimiento datetime,fechaCreacion datetime)");
    	db.execSQL("CREATE TABLE incidente (incidenteID uniqueidentifier NOT NULL primary key,habitacionID uniqueidentifier NOT NULL,incidenteTipo int,imagen varchar(300),	observaciones varchar(3000),empleadoCreadorID int NOT NULL)");
    	db.execSQL("CREATE TABLE incidenteTipo (incidenteTipoID int NOT NULL primary key,nombre varchar(200) NOT NULL,estaActivo bit DEFAULT 1 NOT NULL)");
    	db.execSQL("CREATE TABLE piso (pisoID uniqueidentifier NOT NULL primary key,seccionID uniqueidentifier NOT NULL,hotelID uniqueidentifier NOT NULL,nombre varchar(100) NOT NULL,	abreviatura varchar(5) NOT NULL,estaActivo bit NOT NULL)");
    	db.execSQL("CREATE TABLE producto (	productoID bigint NOT NULL primary key,	nombre varchar(200) NOT NULL,descripcion varchar(500) NULL,	precio float DEFAULT 0.0 NOT NULL,nombreCorto varchar(5) NOT NULL,estaActivo bit DEFAULT 1 NOT NULL,imagen varchar(300) NULL,productoCategoriaID int NOT NULL)");
    	db.execSQL("CREATE TABLE productoCategoria (productoCategoriaID int NOT NULL primary key,nombre varchar(200) NOT NULL,estaActivo bit DEFAULT 1 NOT NULL,descripcion varchar(500),imagen varchar(300),hotelID uniqueidentifier NOT NULL)");
    	db.execSQL("CREATE TABLE seccion (seccionID uniqueidentifier NOT NULL primary key,	hotelID uniqueidentifier NOT NULL,	nombre varchar(100) NOT NULL,	descripcion varchar(2000),	estaActivo bit DEFAULT 1 NOT NULL)");
    	db.execSQL("CREATE TABLE solicitud (solicitudID bigint NOT NULL primary key,total money NOT NULL,fecha datetime,empleadoCreador int,huespedID uniqueidentifier,	hotelID uniqueidentifier)");
    	db.execSQL("CREATE TABLE solicitudDetalle (	solicitudDetalleID bigint NOT NULL primary key,	productoID bigint NOT NULL,	cantidad int,	solicitudID bigint NOT NULL,	precio bigint DEFAULT 0)");
    	db.execSQL("CREATE TABLE tarea (tareaID int NOT NULL primary key,habitacionID uniqueidentifier NOT NULL,prioridad int DEFAULT 1 NOT NULL,estatus int DEFAULT 0 NOT NULL,fechaInicio datetime,fechaFin datetime,duracionReal timestamp,empleadoID int NOT NULL,tipoTareaID int,empleadoCreadorID int,descripcion varchar(3000))");
    	db.execSQL("CREATE TABLE tipoTarea (tipoTareaID int NOT NULL primary key,nombre varchar(200) NOT NULL,descripcion varchar(3000),estaActivo bit DEFAULT 1 NOT NULL,duracionEstimada int DEFAULT 0,hotelID uniqueidentifier)");
    	
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub

        Log.w("DBHelper", "Upgrading database from version "
                        + oldVersion + " to " + newVersion
                        + ", which will destroy all old data");

        onCreate(db);
    }
    
   	public boolean checkDataBase() 
   	{
   		String myPath = DATABASE_PATH + DATABASE_NAME;
        File f = new File(myPath);
        return f.exists();
   	}

    public void createDataBase() 
    {
        openDB();            
        try 
        {
            InputStream myInput = ctx.getAssets().open(DATABASE_NAME + ".sqlite");
            OutputStream myOutput = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) 
            {
                myOutput.write(buffer, 0, length);
            }

            if (mDb.isOpen())
            {
                mDb.close();
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } 
        catch (FileNotFoundException e) 
        {
            throw new Error("file not found --  " + e.getMessage());
        } 
        catch (IOException e) 
        {
            throw new Error("io exception " + e.getMessage());
        } 
        catch (Exception e)
        {
            throw new Error(" exception " + e.getMessage());
        }
    }

    public DBManager openDB() throws SQLException 
    {
        mDb = this.getReadableDatabase();
        return this;
    }

    public void close() 
    {
        try 
        {
            mDb.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public Cursor ExecuteReader(String prSQL)
    {
        mDb = this.getReadableDatabase();
        Cursor cur = mDb.rawQuery(prSQL, null);
        return cur;
    }

    public void ExecuteNonUpdateQuery(String prSQL)
    {
        mDb = this.getWritableDatabase();
        mDb.execSQL(prSQL);
        mDb.close();
    }
}
