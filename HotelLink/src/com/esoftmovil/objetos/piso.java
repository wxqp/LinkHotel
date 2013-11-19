package com.esoftmovil.objetos;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.esoftmovil.com.esoftmovil.db.DBManager;

public class piso {
	public int pisoID;
	public int seccionID;
	public int hotelID;
	public String nombre;
	public String abreviatura;
	public byte estaActivo;

	public piso(){

	}
	 public int Insertar(Context prContext)
	 {
	  int CodigoResultado=0;
	  String SQL;
	  try{
	      DBManager Manejador=new DBManager(prContext);
	      SQLiteDatabase db=Manejador.getWritableDatabase();
	      SQL=String.format("INSERT INTO piso values (%d,%d,%d,'%s','%s',%d)", this.pisoID,this.seccionID,this.hotelID,this.nombre,this.abreviatura,this.estaActivo);
	  
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
	    		  SQL=String.format("DELETE FROM piso WHERE pisoID=%d",this.pisoID);
	    		  
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
	 public static ArrayList<piso> Consultar(Context prContext) throws Exception
	 {
		 
		 	DBManager Manejador = new DBManager(prContext);
	        ArrayList<piso> ListaPisos = new ArrayList<piso>();
	        try
	        {
	           String SQL;
	 	       piso opiso;
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM piso");
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	opiso=new piso();
	        	    
	            	opiso.pisoID=cur.getInt(0);
	            	opiso.seccionID=cur.getInt(1);
	            	opiso.hotelID=cur.getInt(2);
	            	opiso.nombre=cur.getString(3);
	            	opiso.abreviatura=cur.getString(4);
	            	opiso.estaActivo=(byte)cur.getInt(5);
	 	    
	            	ListaPisos.add(opiso);
	 	    
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
	        return ListaPisos;
	 }
	 
	 
	 public static piso Consultar(Context prContext, int prpisoID)throws Exception
	 {
		    DBManager Manejador = new DBManager(prContext);
		    //piso opiso = new piso();
		    piso opiso;
	        try
	        {
	           Manejador.openDB();
	           String SQL;
	           opiso=new piso();
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM piso WHERE pisoID=%d",prpisoID);
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	opiso.pisoID=cur.getInt(0);
	            	opiso.seccionID=cur.getInt(1);
	            	opiso.hotelID=cur.getInt(2);
	            	opiso.nombre=cur.getString(3);
	            	opiso.abreviatura=cur.getString(4);
	            	opiso.estaActivo=(byte)cur.getInt(5);
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
	        return opiso;
		 
	 }

}
