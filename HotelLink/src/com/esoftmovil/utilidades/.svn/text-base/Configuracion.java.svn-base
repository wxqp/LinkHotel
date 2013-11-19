package com.esoftmovil.utilidades;

import android.content.Context;
import android.content.SharedPreferences;

public class Configuracion 
{
	//Nombre del archivo de preferencias
	private static final String SHARED_PREFS_FILE = "MisPreferencias";
	//Matricula del usuario
	private static final String MATRICULA = "matricula";
	//Token notificacion
	private static final String REG_ID = "registration_id";
	//Fecha de actualizacion tramite
	private static final String TRAMITEUPDATE= "tramitesUpdate";
	//Version de la app
    private static final String APP_VERSION = "appVersion";
    //Tiempo de expiracion del token de notificacion
    private static final String EXPIRATION_TIME = "onServerExpirationTimeMs";

	private static SharedPreferences getSettings(Context prContext)
	{
		return prContext.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
	}
	
	public static String getMatricula(Context prContext)
	{
		return getSettings(prContext).getString(MATRICULA, null);  
	}

	public static void setMatricula(String prMatricula, Context prContext)
	{
	    SharedPreferences.Editor editor = getSettings(prContext).edit();
	    editor.putString(MATRICULA, prMatricula);
	    editor.commit();
	}
	
	public static String getTramitesUpdate(Context prContext)
	{
		return getSettings(prContext).getString(TRAMITEUPDATE, "19000101000000");  
	}

	public static void setTramitesUpdate(String prTramiteUpdate, Context prContext)
	{
	    SharedPreferences.Editor editor = getSettings(prContext).edit();
	    editor.putString(TRAMITEUPDATE, prTramiteUpdate);
	    editor.commit();
	}
	
	public static String getRegistrationID(Context prContext)
	{
		return getSettings(prContext).getString(REG_ID, null);  
	}

	public static void setRegistrationID(String prRegistrationID, Context prContext)
	{
	    SharedPreferences.Editor editor = getSettings(prContext).edit();
	    editor.putString(REG_ID, prRegistrationID);
	    editor.commit();
	}
	
	public static int getAppVersion(Context prContext)
	{
		return getSettings(prContext).getInt(APP_VERSION, -1);  
	}

	public static void setAppVersion(int prAppVersion, Context prContext)
	{
	    SharedPreferences.Editor editor = getSettings(prContext).edit();
	    editor.putInt(APP_VERSION, prAppVersion);
	    editor.commit();
	}
	
	public static long getExperationTime(Context prContext)
	{
		return getSettings(prContext).getLong(EXPIRATION_TIME, -1);  
	}

	public static void setExperationTime(Long prExperationTime, Context prContext)
	{
	    SharedPreferences.Editor editor = getSettings(prContext).edit();
	    editor.putLong(EXPIRATION_TIME, prExperationTime);
	    editor.commit();
	}
}

