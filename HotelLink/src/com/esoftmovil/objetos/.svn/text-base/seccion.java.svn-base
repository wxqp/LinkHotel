package com.esoftmovil.objetos;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.esoftmovil.com.esoftmovil.db.DBManager;

public class seccion {
	public int seccionID;
	public int hotelID;
	public String nombre;
	public String descripcion;
	public byte estaActivo = 1;

	public seccion(){

	}
	public int Insertar(Context prContext)
	 {
	  int CodigoResultado=0;
	  String SQL;
	  try{
	      DBManager Manejador=new DBManager(prContext);
	      SQLiteDatabase db=Manejador.getWritableDatabase();
	      SQL=String.format("INSERT INTO seccion values (%d,%d,'%s','%s',%d)", this.seccionID,this.hotelID,this.nombre,this.descripcion,this.estaActivo);
	  
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
	    		  SQL=String.format("DELETE FROM seccion WHERE seccionID=%d",this.seccionID);
	    		  
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
	 public static ArrayList<seccion> Consultar(Context prContext) throws Exception
	 {
		 
		 	DBManager Manejador = new DBManager(prContext);
	        ArrayList<seccion> ListaSecciones = new ArrayList<seccion>();
	        try
	        {
	           String SQL;
	           seccion oseccion;
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM seccion");
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oseccion=new seccion();
	        	    
	            	oseccion.seccionID=cur.getInt(0);
	            	oseccion.hotelID=cur.getInt(1);
	            	oseccion.nombre=cur.getString(2);
	            	oseccion.descripcion=cur.getString(3);
	            	oseccion.estaActivo=(byte)cur.getInt(4);
	 	    
	            	ListaSecciones.add(oseccion);
	 	    
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
	        return ListaSecciones;
	 }
	 
	 
	 public static seccion Consultar(Context prContext, int prseccionID)throws Exception
	 {
		    DBManager Manejador = new DBManager(prContext);
		    seccion oseccion;
	        try
	        {
	           Manejador.openDB();
	           String SQL;
	           oseccion=new seccion();
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM seccion WHERE seccionID=%d",prseccionID);
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oseccion.seccionID=cur.getInt(0);
	            	oseccion.hotelID=cur.getInt(1);
	            	oseccion.nombre=cur.getString(2);
	            	oseccion.descripcion=cur.getString(3);
	            	oseccion.estaActivo=(byte)cur.getInt(4);
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
	        return oseccion;
		 
	 }

}
