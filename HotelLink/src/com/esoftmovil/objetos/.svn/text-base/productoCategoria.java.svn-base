package com.esoftmovil.objetos;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.esoftmovil.com.esoftmovil.db.DBManager;

public class productoCategoria {
	public int productoCategoriaID;
	public String nombre;
	public byte estaActivo = 1;
	public String descripcion;
	public String imagen;
	public int hotelID;

	public productoCategoria(){

	}
	public int Insertar(Context prContext)
	 {
	  int CodigoResultado=0;
	  String SQL;
	  try{
	      DBManager Manejador=new DBManager(prContext);
	      SQLiteDatabase db=Manejador.getWritableDatabase();
	      SQL=String.format("INSERT INTO productoCategoria values (%d,'%s',%d,'%s','%s',%d)", this.productoCategoriaID,this.nombre,this.estaActivo,this.descripcion,this.imagen,this.hotelID);
	  
	      Manejador.ExecuteNonUpdateQuery(SQL);
	      CodigoResultado=1;
	      db.close();
	  }
	  catch(Exception ex)
	    {
	      return 0;
	    }
	     return CodigoResultado;
	 }
	 public int Eliminar(Context prContext)
	    {
	    	int CodigoResultado=0;
	    	String SQL;
	    	try{
	    		  DBManager Manejador=new DBManager(prContext);
	    		  SQLiteDatabase db=Manejador.getWritableDatabase();
	    		  SQL=String.format("DELETE FROM productoCategoria WHERE productoCategoriaID=%d",this.productoCategoriaID);
	    		  
	    		  Manejador.ExecuteNonUpdateQuery(SQL);
	    		  CodigoResultado=1;
	    		  db.close();
	    		  }
	    		  catch(Exception ex)
	    		  {
	    		   return 0;
	    		  }
	    		  return CodigoResultado;
	    	
	    }
	 public static ArrayList<productoCategoria> Consultar(Context prContext) throws Exception
	 {
		 
		 	DBManager Manejador = new DBManager(prContext);
	        ArrayList<productoCategoria> ListaproductosCategoria = new ArrayList<productoCategoria>();
	        try
	        {
	           String SQL;
	           productoCategoria oproductoCategoria;
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM productoCategoria");
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oproductoCategoria=new productoCategoria();
	        	    
	            	oproductoCategoria.productoCategoriaID=cur.getInt(0);
	            	oproductoCategoria.nombre=cur.getString(1);
	            	oproductoCategoria.estaActivo=(byte)cur.getInt(2);
	            	oproductoCategoria.descripcion=cur.getString(3);
	            	oproductoCategoria.imagen=cur.getString(4);
	            	oproductoCategoria.hotelID=cur.getInt(5);
	 	    
	            	ListaproductosCategoria.add(oproductoCategoria);
	 	    
	 	           cur.moveToNext();
	        }
	       }
	        catch(SQLException e)
	        {
	            throw new Error("Error SQL: " + e.getMessage());
	        }
	        catch(Exception e)
	        {
	            throw new Error("Error al consultar: " + e.getMessage());
	        }
	        finally
	        {
	        	Manejador.close();
	        }
	        return ListaproductosCategoria;
	 }
	 
	 
	 public static productoCategoria Consultar(Context prContext, int prproductoCategoriaID)throws Exception
	 {
		    DBManager Manejador = new DBManager(prContext);
		    productoCategoria oproductoCategoria;
	        try
	        {
	           Manejador.openDB();
	           String SQL;
	           oproductoCategoria=new productoCategoria();
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM productoCategoria WHERE productoCategoriaID=%d",prproductoCategoriaID);
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oproductoCategoria.productoCategoriaID=cur.getInt(0);
	            	oproductoCategoria.nombre=cur.getString(1);
	            	oproductoCategoria.estaActivo=(byte)cur.getInt(2);
	            	oproductoCategoria.descripcion=cur.getString(3);
	            	oproductoCategoria.imagen=cur.getString(4);
	            	oproductoCategoria.hotelID=cur.getInt(5);
	                cur.moveToNext();
	            }
	        }
	        catch(SQLException e)
	        {
	            throw new Error("Error SQL: " + e.getMessage());
	        }
	        catch(Exception e)
	        {
	            throw new Error("Error al consultar: " + e.getMessage());
	        }
	        finally
	        {
	        	Manejador.close();
	        }
	        return oproductoCategoria;
		 
	 }
}
