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


public class incidente {
	public int incidenteID;
	public int habitacionID;
	public int incidenteTipo;
	public String imagen;
	public String observaciones;
	public int empleadoCreadorID;

	public incidente(){

	}

	
	
	 public int Insertar(Context prContext)
	 {
	  int CodigoResultado=0;
	  String SQL;
	  try{
	      DBManager Manejador=new DBManager(prContext);
	      SQLiteDatabase db=Manejador.getWritableDatabase();
	      SQL=String.format("INSERT INTO incidente values (%d,%d,%d,'%s','%s',%d)", this.incidenteID,this.habitacionID,this.incidenteTipo,this.imagen,this.observaciones,this.empleadoCreadorID);
	  
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
	    	//this.setStatus(4);
	    	//this.setSincronizado(false);
	    	try{
	    		  DBManager Manejador=new DBManager(prContext);
	    		  SQLiteDatabase db=Manejador.getWritableDatabase();
	    		  SQL=String.format("DELETE FROM incidente WHERE incidenteID=%d",this.incidenteID);
	    		  
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
	 public static ArrayList<incidente> Consultar(Context prContext) throws Exception
	 {
		 
		 	DBManager Manejador = new DBManager(prContext);
	        ArrayList<incidente> ListaIncidentes = new ArrayList<incidente>();
	        try
	        {
	           String SQL;
	 	       incidente oincidente;
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM incidente");
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oincidente=new incidente();
	        	    
	            	oincidente.incidenteID=cur.getInt(0);
	            	oincidente.habitacionID=cur.getInt(1);
	            	oincidente.incidenteTipo=cur.getInt(2);
	            	oincidente.imagen=cur.getString(3);
	            	oincidente.observaciones=cur.getString(4);
	            	oincidente.empleadoCreadorID=cur.getInt(5);
	 	    
	            	ListaIncidentes.add(oincidente);
	 	    
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
	        return ListaIncidentes;
	 }
	 
	 
	 public static incidente Consultar(Context prContext, int princidenteID)throws Exception
	 {
		    DBManager Manejador = new DBManager(prContext);
		    incidente oincidente;
	        try
	        {
	           Manejador.openDB();
	           String SQL;
	           oincidente=new incidente();
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM incidente WHERE incidenteID=%d",princidenteID);
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oincidente.incidenteID=cur.getInt(0);
	            	oincidente.habitacionID=cur.getInt(1);
	            	oincidente.incidenteTipo=cur.getInt(2);
	            	oincidente.imagen=cur.getString(3);
	            	oincidente.observaciones=cur.getString(4);
	            	oincidente.empleadoCreadorID=cur.getInt(5);
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
	        return oincidente;
		 
	 }



}
