package com.esoftmovil.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Utilidades 
{
	public static Date DateFromDotNetJSONString(String prFecha)
	{
		String ValorFecha = prFecha;
		ValorFecha = ValorFecha.substring(ValorFecha.indexOf("(") + 1, ValorFecha.indexOf(")"));
		long ValorMilisegundos = Long.parseLong(ValorFecha);
		Date Fecha = new Date(ValorMilisegundos);
		return Fecha;
	}
	
	public static String getFormatDate(Date prFecha)
    { 
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(prFecha);
    }

    public static Date stringToDate(String date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try
        {
            return df.parse(date);
        } 
        catch (ParseException ex)
        {
        }
        return null;
    }
    
    
  //-----------------------Interfaz-----------------------
  	public static void showAlert(String prText, Context prContext)
  	{
  		final Toast oToast = Toast.makeText(prContext, prText, Toast.LENGTH_LONG);
  		oToast.show();
  	}
  	
  	public static boolean isForeground(Context prContext) 
	{
		ActivityManager activityManager = (ActivityManager) prContext.getSystemService( Context.ACTIVITY_SERVICE );
	    List<RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
	    for(int i = 0; i < procInfos.size(); i++)
	    {
	        if(procInfos.get(i).processName.equals(AppConstants.AppName) && procInfos.get(i).importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) 
	        {
	            return true;
	        }
	    }
	    return false;
	}
  	
  	public boolean checkPlayServices(Context prContext) 
	{
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(prContext);
	    if (resultCode != ConnectionResult.SUCCESS) 
	    {
	        Log.i("PushNotification", "Dispositivo no soportado.");
	        return false;
	    }
	    return true;
	}
}
