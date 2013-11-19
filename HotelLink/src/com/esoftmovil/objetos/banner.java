package com.esoftmovil.objetos;

import java.sql.Date;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.esoftmovil.com.esoftmovil.db.DBManager;

public class banner {
	
	public int bannerID;
	public String imagen;
	public byte estaActivo = 1;
	public Date fechaModificacion;
	public int hotelID;

	public banner(){

	}
	public int Insertar(Context prContext)
	{
		int CodigoResultado=0;
		String SQL;
		try{
		DBManager Manejador=new DBManager(prContext);
		SQLiteDatabase db=Manejador.getWritableDatabase();
		SQL=String.format("INSERT INTO banner values (%d,'%s',%d,'%s',%d)", this.bannerID,this.imagen,this.estaActivo,this.fechaModificacion,this.hotelID);
		
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
		try
		{
			DBManager Manejador=new DBManager(prContext);
			SQLiteDatabase db=Manejador.getWritableDatabase();
			
			SQL=String.format("DELETE FROM banner WHERE bannerID=%d", this.bannerID);
			
			Manejador.ExecuteNonUpdateQuery(SQL);
			
			db.close();
			CodigoResultado=1;
			
		}
		catch(Exception ex)
		{
			return 0;
		}
		return CodigoResultado;
	}
	
	public static ArrayList<banner> Consultar(Context prContext)
	{
		ArrayList<banner> ListaBanners=new ArrayList<banner>();
		try
		{
			
			String SQL;
			banner obanner;
			DBManager Manejador=new DBManager(prContext);
			SQLiteDatabase db=Manejador.getWritableDatabase();
			
			SQL=String.format("SELECT * FROM banner");
			
			Cursor cur=Manejador.ExecuteReader(SQL);
			
			cur.moveToFirst();
			
			while(cur.isLast()==false)
			{
				obanner=new banner();
				
				obanner.bannerID=cur.getInt(0);
				obanner.imagen=cur.getString(1);
				obanner.estaActivo=(byte)cur.getInt(2);
				obanner.fechaModificacion=Date.valueOf(cur.getString(3));
				obanner.hotelID=cur.getInt(4);
				
				ListaBanners.add(obanner);
				
				cur.moveToNext();
				
			}
			db.close();
			
		}
		catch(Exception ex)
		{
			return new ArrayList<banner>();
		}
		return ListaBanners;
	}
	
	public static banner Consultar(Context prContext, int prbannerID)
	{
		banner obanner;
		try
		{
			String SQL;
			obanner=new banner();
			DBManager Manejador=new DBManager(prContext);
			SQLiteDatabase db=Manejador.getWritableDatabase();
			
			SQL=String.format("SELECT * FROM banner WHERE bannerID=%d",prbannerID);
			
			Cursor cur=Manejador.ExecuteReader(SQL);
			
			cur.moveToFirst();
			
			if(cur.isAfterLast())
			{
				obanner.bannerID=cur.getInt(0);
				obanner.imagen=cur.getString(1);
				obanner.estaActivo=(byte)cur.getInt(2);
				obanner.fechaModificacion=Date.valueOf(cur.getString(3));
				obanner.hotelID=cur.getInt(4);
				
			}
			db.close();
		}
		catch(Exception ex)
		{
			return new banner();
		}
		return obanner;
		
	}

}
