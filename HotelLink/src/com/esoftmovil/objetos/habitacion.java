package com.esoftmovil.objetos;


import java.sql.Date;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.esoftmovil.com.esoftmovil.db.DBManager;


import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.esoftmovil.com.esoftmovil.db.DBManager;


public class habitacion {
	
	public int habitacionID;
	public int habitacionTipoID;
	public int habitacionEstatusID;
	public int pisoID;
	public String nombre;
	public byte estaActivo = 1;

	public habitacion(){

	}
	
	 public int Insertar(Context prContext)
	 {
	  int CodigoResultado=0;
	  String SQL;
	  try{
	      DBManager Manejador=new DBManager(prContext);
	      SQLiteDatabase db=Manejador.getWritableDatabase();
	      SQL=String.format("INSERT INTO habitacion values (%d,%d,%d,%d,'%s','%d')", this.habitacionID,this.habitacionTipoID,this.habitacionEstatusID,this.pisoID,this.nombre,this.estaActivo);
	  
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
	    		  SQL=String.format("DELETE FROM habitacion WHERE habitacionID=%d",this.habitacionID);
	    		  
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
	 public static ArrayList<habitacion> Consultar(Context prContext) throws Exception
	 {
		 
		 	DBManager Manejador = new DBManager(prContext);
	        ArrayList<habitacion> ListaHabitaciones = new ArrayList<habitacion>();
	        try
	        {
	           String SQL;
	 	       habitacion ohabitacion;
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM habitacion");
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	ohabitacion=new habitacion();
	        	    
	            	ohabitacion.habitacionID=cur.getInt(0);
	            	ohabitacion.habitacionTipoID=cur.getInt(1);
	            	ohabitacion.habitacionEstatusID=cur.getInt(2);
	            	ohabitacion.pisoID=cur.getInt(3);
	            	ohabitacion.nombre=cur.getString(4);
	            	ohabitacion.estaActivo=(byte)cur.getInt(5);
	 	    
	            	ListaHabitaciones.add(ohabitacion);
	 	    
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
	        return ListaHabitaciones;
	 }
	 
	 
	 public static habitacion Consultar(Context prContext, int prhabitacionID)throws Exception
	 {
		    DBManager Manejador = new DBManager(prContext);
		    habitacion ohabitacion;
	        try
	        {
	           Manejador.openDB();
	           String SQL;
	           ohabitacion=new habitacion();
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM habitacion WHERE habitacionID=%d",prhabitacionID);
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	ohabitacion.habitacionID=cur.getInt(0);
	            	ohabitacion.habitacionTipoID=cur.getInt(1);
	            	ohabitacion.habitacionEstatusID=cur.getInt(2);
	            	ohabitacion.pisoID=cur.getInt(3);
	            	ohabitacion.nombre=cur.getString(4);
	            	ohabitacion.estaActivo=(byte)cur.getInt(5);
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
	        return ohabitacion;
		 
	 }
}
