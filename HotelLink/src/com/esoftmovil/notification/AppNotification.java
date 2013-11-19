package com.esoftmovil.notification;

import java.io.IOException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;

import com.esoftmovil.com.esoftmovil.db.AsyncTaskWebRequestSinDialog;
import com.esoftmovil.utilidades.AppConstants;
import com.esoftmovil.utilidades.Configuracion;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class AppNotification 
{
	//Tiempo de expiracion de token, una semana por default
    public static final long EXPIRATION_TIME_MS = 1000 * 3600 * 24 * 7;
    //Id del proyecto en google api console, IMPORTANTE CAMBIAR PARA CADA PROYECTO
    String SENDER_ID = "88650129494";

    private Context context;
    private String gcm_Token;
    private GoogleCloudMessaging gcm;
    
    public AppNotification(Context prContext)
    {
    	context = prContext;
    }

	public void CheckGCMTokenID() 
	{
		//Chequemos si esta instalado Google Play Services
		if(checkPlayServices())
		{
	        //Obtenemos el Registration ID guardado
	        gcm_Token = getGCMToken(context);
	
	        //Si no disponemos de Registration ID comenzamos el registro
	        if (gcm_Token.equals("")) 
	        {
	        	//El registro debe de ser necesariamente en segundo hilo
	        	TareaRegistroGCM tarea = new TareaRegistroGCM();
	    		tarea.execute();
	        }
		}
		else 
		{
            Log.i("PushNotification", "No se ha encontrado Google Play Services.");
        }
	}
	
	public void UnRegister() 
	{
		//El registro debe de ser necesariamente en segundo hilo
    	TareaBajaGCM tarea = new TareaBajaGCM();
		tarea.execute();
	}
	
	private boolean checkPlayServices() 
	{
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
	    if (resultCode != ConnectionResult.SUCCESS) 
	    {
	        Log.i("PushNotification", "Dispositivo no soportado.");
	        return false;
	    }
	    return true;
	}
	
	private String getGCMToken(Context context) 
	{	    
		//Si no esta registrado no devuelve nada 
	    String tokenID = AppSharedPreferences.getRegistrationID(context);
	    if (tokenID == null) 
	    {
	        Log.d("PushNotification", "Registro GCM no encontrado.");
	        return "";
	    }
	    
	    int registeredAppVersion = AppSharedPreferences.getAppVersion(context);
	    int currentAppVersion = getAppVersion(context);
	    long expirationTime = AppSharedPreferences.getExperationTime(context);
	   
	    if (registeredAppVersion != currentAppVersion) 
	    {
	        Log.d("PushNotification", "Nueva version de la aplicacion");
	        return "";
	    }
	    else if (System.currentTimeMillis() > expirationTime)
	    {
	    	Log.d("PushNotification", "Registro GCM expirado.");
	        return "";
	    }
	    Log.d("PushNotification", "Token: " + tokenID);
	    return tokenID;
	}
	
	private static int getAppVersion(Context context) 
	{
	    try
	    {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        
	        return packageInfo.versionCode;
	    } 
	    catch (NameNotFoundException e) 
	    {
	        throw new RuntimeException("Error al obtener versión: " + e);
	    }
	}
	
	private void setGCMToken(Context context, String regId) 
	{	    
	    int appVersion = getAppVersion(context);
	    
	    //Se registra en las sharedPreferences
	    AppSharedPreferences.setAppVersion(appVersion, context);
	    AppSharedPreferences.setRegistrationID(regId, context);
	    AppSharedPreferences.setExperationTime(System.currentTimeMillis() + EXPIRATION_TIME_MS, context);
	}
	
	private class TareaRegistroGCM extends AsyncTask<String,Integer,String>
	{
		@Override
	    protected String doInBackground(String... params) 
		{
	        String msg = "";
	        
	        try 
	        {
	            if (gcm == null) 
	            {
	                gcm = GoogleCloudMessaging.getInstance(context);
	            }
	            
	            //Nos registramos en los servidores de GCM
	            gcm_Token = gcm.register(SENDER_ID);
	            Log.d("PushNotification", "Token: " + gcm_Token);
	            
	            //Nos registramos en nuestro servidor
	            registerServer();

	            //Guardamos los datos en preferencias
	            setGCMToken(context, gcm_Token);
	        } 
	        catch (IOException ex) 
	        {
	        	Log.d("PushNotification", "Error registro en GCM:" + ex.getMessage());
	        }
	       
	        return msg;
	    }

		private void registerServer() {
			String URL = String.format("%s/Dispositivo.aspx?r=1", AppConstants.HostUrl);
			AsyncTaskWebRequestSinDialog oAsyncTask = new AsyncTaskWebRequestSinDialog(context);
			oAsyncTask.setPostExecuteListener(new AsyncTaskWebRequestSinDialog.onPostExecuteListener() 
			{
				@Override
				public void onPostExecute(String content, Boolean prError) 
				{
					try 
					{
						JSONObject oJson = new JSONObject(content);
						String CodigoRespues = oJson.getString("codigoRespuesta");
						if (CodigoRespues.equalsIgnoreCase("200")) 
						{
							Log.d("PushNotification", "Se registro token en servidor");
						}
					}
					catch (Exception ex) {}
				}
			});
			String sss = Configuracion.getMatricula(context);
			NameValuePair oNameValuePair = new BasicNameValuePair("prMatricula", sss);
			oAsyncTask.postParameters.add(oNameValuePair);
			oAsyncTask.postParameters.add(new BasicNameValuePair("prToken", gcm_Token));
			oAsyncTask.execute(URL);
		}
	}
	
	private class TareaBajaGCM extends AsyncTask<String,Integer,String>
	{
		@Override
	    protected String doInBackground(String... params) 
		{
	        String msg = "";
	        
	        //Borramos los datos en las preferencias
			AppSharedPreferences.setRegistrationID(null, context);
	        return msg;
	    }
	}
}

class AppSharedPreferences
{
	//Nombre del archivo de preferencias
	private static final String SHARED_PREFS_FILE = "NotificationPreferences";
	//Token notificacion
	private static final String REG_ID = "registration_id";
	//Version de la app
    private static final String APP_VERSION = "appVersion";
    //Tiempo de expiracion del token de notificacion
    private static final String EXPIRATION_TIME = "onServerExpirationTimeMs";

	private static SharedPreferences getSettings(Context prContext)
	{
		return prContext.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
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

