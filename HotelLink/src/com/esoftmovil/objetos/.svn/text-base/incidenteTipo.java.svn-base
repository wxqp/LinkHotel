package com.esoftmovil.objetos;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.esoftmovil.com.esoftmovil.db.DBManager;

public class incidenteTipo {
	public int incidenteTipoID;
	public String nombre;
	public byte estaActivo = 1;

	public incidenteTipo(){

	}
	 public int Insertar(Context prContext)
	 {
	  int CodigoResultado=0;
	  String SQL;
	  try{
	      DBManager Manejador=new DBManager(prContext);
	      SQLiteDatabase db=Manejador.getWritableDatabase();
	      SQL=String.format("INSERT INTO incidenteTipo values (%d,'%s',%d)", this.incidenteTipoID,this.nombre,this.estaActivo);
	  
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
	    		  SQL=String.format("DELETE FROM incidenteTipo WHERE incidenteTipoID=%d",this.incidenteTipoID);
	    		  
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
	 public static ArrayList<incidenteTipo> Consultar(Context prContext) throws Exception
	 {
		 
		 	DBManager Manejador = new DBManager(prContext);
	        ArrayList<incidenteTipo> ListaIncidentes = new ArrayList<incidenteTipo>();
	        try
	        {
	           String SQL;
	           incidenteTipo oincidenteTipo;
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM incidenteTipo");
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oincidenteTipo=new incidenteTipo();
	        	    
	            	oincidenteTipo.incidenteTipoID=cur.getInt(0);
	            	oincidenteTipo.nombre=cur.getString(1);
	            	oincidenteTipo.estaActivo=(byte)cur.getInt(2);
	            	ListaIncidentes.add(oincidenteTipo);
	 	    
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
	 
	 
	 public static incidenteTipo Consultar(Context prContext, int princidenteTipoID)throws Exception
	 {
		    DBManager Manejador = new DBManager(prContext);
		    incidenteTipo oincidenteTipo;
	        try
	        {
	           Manejador.openDB();
	           String SQL;
	           oincidenteTipo=new incidenteTipo();
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM incidenteTipo WHERE incidenteTipoID=%d",princidenteTipoID);
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	oincidenteTipo.incidenteTipoID=cur.getInt(0);
	            	oincidenteTipo.nombre=cur.getString(1);
	            	oincidenteTipo.estaActivo=(byte)cur.getInt(2);
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
	        return oincidenteTipo;
		 
	 }

}
