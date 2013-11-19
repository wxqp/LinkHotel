package com.esoftmovil.objetos;

import java.sql.Date;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.esoftmovil.com.esoftmovil.db.DBManager;
import com.esoftmovil.utilidades.Utilidades;

public class huesped {
	public int huespedID;
	public int hotelID;
	public String nombre;
	public byte sexo;
	public String correo;
	public String telefono;
	public String domicilio;
	public String ciudad;
	public String estado;
	public String pais;
	public String observaciones;
	public Date fechaNacimiento;
	public Date fechaCreacion;

	public huesped(){

	}
	 public int Insertar(Context prContext)
	 {
	  int CodigoResultado=0;
	  String SQL;
	  try{
	      DBManager Manejador=new DBManager(prContext);
	      SQLiteDatabase db=Manejador.getWritableDatabase();
	      SQL=String.format("INSERT INTO huesped values (%d,%d,'%s',%d,'%s','%s','%s','%s','%s','%s','%s','%s','%s')",
	    		  this.huespedID,this.hotelID,this.nombre,this.sexo,this.correo,this.telefono,
	    		  this.domicilio,this.ciudad,this.estado,this.pais,this.observaciones,this.fechaNacimiento,this.fechaCreacion);
	  
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
	    		  SQL=String.format("DELETE FROM huesped WHERE huespedID=%d",this.huespedID);
	    		  
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
	 public static ArrayList<huesped> Consultar(Context prContext) throws Exception
	 {
		 
		 	DBManager Manejador = new DBManager(prContext);
	        ArrayList<huesped> ListaHuespedes = new ArrayList<huesped>();
	        try
	        {
	           String SQL;
	           huesped ohuesped;
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM huesped");
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	ohuesped=new huesped();
	        	    
	            	ohuesped.huespedID=cur.getInt(0);
	            	ohuesped.hotelID=cur.getInt(1);
	            	ohuesped.nombre=cur.getString(2);
	            	ohuesped.sexo=(byte)cur.getInt(3);
	            	ohuesped.correo=cur.getString(4);
	            	ohuesped.telefono=cur.getString(5);
	            	ohuesped.domicilio=cur.getString(6);
	            	ohuesped.ciudad=cur.getString(7);
	            	ohuesped.estado=cur.getString(8);
	            	ohuesped.pais=cur.getString(9);
	            	ohuesped.observaciones=cur.getString(10);
	            	ohuesped.fechaNacimiento=Date.valueOf(cur.getString(11));
	            	ohuesped.fechaCreacion=Date.valueOf(cur.getString(12));
	 	    
	            	ListaHuespedes.add(ohuesped);
	 	    
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
	        return ListaHuespedes;
	 }
	 
	 
	 public static huesped Consultar(Context prContext, int prhuespedID)throws Exception
	 {
		    DBManager Manejador = new DBManager(prContext);
		    huesped ohuesped;
	        try
	        {
	           Manejador.openDB();
	           String SQL;
	           ohuesped=new huesped();
	 	       SQLiteDatabase db=Manejador.getWritableDatabase();
	 	   
	 	       SQL=String.format("SELECT * FROM huesped WHERE huespedID=%d",prhuespedID);
	 	   
	 	       Cursor cur=Manejador.ExecuteReader(SQL);
	 	   
	 	       cur.moveToFirst();
	            while (cur.isAfterLast() == false)
	            {
	            	ohuesped.huespedID=cur.getInt(0);
	            	ohuesped.hotelID=cur.getInt(1);
	            	ohuesped.nombre=cur.getString(2);
	            	ohuesped.sexo=(byte)cur.getInt(3);
	            	ohuesped.correo=cur.getString(4);
	            	ohuesped.telefono=cur.getString(5);
	            	ohuesped.domicilio=cur.getString(6);
	            	ohuesped.ciudad=cur.getString(7);
	            	ohuesped.estado=cur.getString(8);
	            	ohuesped.pais=cur.getString(9);
	            	ohuesped.observaciones=cur.getString(10);
	            	ohuesped.fechaNacimiento=Date.valueOf(cur.getString(11));
	            	ohuesped.fechaCreacion=Date.valueOf(cur.getString(12));
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
	        return ohuesped;
		 
	 }

}
