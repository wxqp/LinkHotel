package com.esoftmovil.objetos;

import java.util.ArrayList;

import com.esoftmovil.com.esoftmovil.db.DBManager;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class producto {
	public int productoID;
	public String nombre;
	public String descripcion;
	public double precio = 0.0;
	public String nombreCorto;
	public byte estaActivo = 1;
	public String imagen;
	public int productoCategoriaID;

	public producto(){

	}
	
	 public int Insertar(Context prContext)
	 {
	  int CodigoResultado=0;
	  String SQL;
	  try{
	  DBManager Manejador=new DBManager(prContext);
	  SQLiteDatabase db=Manejador.getWritableDatabase();
	  SQL=String.format("INSERT INTO producto values (%d,'%s',%s,'%d',%s,'%d',%s,'%d')", this.productoID,this.nombre,this.descripcion,this.precio,this.nombreCorto,this.estaActivo,this.imagen,this.productoCategoriaID);
	  
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
	    		  SQL=String.format("DELETE FROM producto WHERE productoID=%d",this.productoID);
	    		  
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
	 public static ArrayList<producto> Consultar(Context prContext) throws Exception
	 {
		 
		 	DBManager Manejador = new DBManager(prContext);
	        ArrayList<producto> ListaProductos = new ArrayList<producto>();
	        try
	        {
	           String SQL;
	 	       producto oproducto;
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM producto");
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	               oproducto=new producto();
	        	    
	 		       oproducto.productoID=cur.getInt(0);
	 		       oproducto.nombre=cur.getString(1);
	 		       oproducto.descripcion=cur.getString(2);
	 		       oproducto.precio=cur.getInt(3);
	 		       oproducto.nombreCorto=cur.getString(4);
	 		       oproducto.estaActivo=(byte)cur.getInt(5);
	 		       oproducto.imagen=cur.getString(6);
	 		       oproducto.productoCategoriaID=cur.getInt(7);
	 	    
	 		       ListaProductos.add(oproducto);
	 	    
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
	        return ListaProductos;
	 }
	 
	 
	 public static producto Consultar(Context prContext, int prproductoID)throws Exception
	 {
		    DBManager Manejador = new DBManager(prContext);
	        //producto oproducto = new producto();
		    producto oproducto;
	        try
	        {
	           Manejador.openDB();
	           String SQL;
	 	       oproducto=new producto();
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM producto WHERE productoID=%d",prproductoID);
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oproducto.productoID=cur.getInt(0);
				    oproducto.nombre=cur.getString(1);
				    oproducto.descripcion=cur.getString(2);
				    oproducto.precio=cur.getInt(3);
				    oproducto.nombreCorto=cur.getString(4);
				    oproducto.estaActivo=(byte)cur.getInt(5);
				    oproducto.imagen=cur.getString(6);
				    oproducto.productoCategoriaID=cur.getInt(7);
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
	        return oproducto;
		 
	 }

}
