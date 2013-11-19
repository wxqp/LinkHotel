package com.esoftmovil.notification;

import com.esoftmovil.hotellink.MainActivity;
import com.esoftmovil.hotellink.R;
import com.esoftmovil.utilidades.Utilidades;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

public class GCMIntentService extends IntentService 
{
	private static final int NOTIF_ALERTA_ID = 1;

	public GCMIntentService() 
	{
        super("GCMIntentService");
    }
	
	@Override
    protected void onHandleIntent(Intent intent) 
	{
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        
        String messageType = gcm.getMessageType(intent);
        Bundle extras = intent.getExtras();

        if (!extras.isEmpty()) 
        {  
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) 
            {
            	mostrarNotification(extras);
            }
        }
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }
	
	private void mostrarNotification(Bundle prExtras) 
	{
		
		String sMensaje = prExtras.getString("msg");
		String sTitulo = prExtras.getString("tittle");
		int nId = Integer.parseInt(prExtras.getString("id"));
		String sTipo = prExtras.getString("tipo");
		
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
		
		NotificationCompat.Builder mBuilder = 
			new NotificationCompat.Builder(this)  
				.setSmallIcon(R.drawable.ic_launcher)  
				.setContentTitle(sTitulo)
				.setAutoCancel(true)
				.setLights(Color.BLUE, 500, 500)
				.setVibrate(new long[] {500,0,500,0,500,0,500,0,500})
				.setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
				.setContentText(sMensaje);
		
		Intent notIntent = new Intent(this, MainActivity.class);
		PendingIntent contIntent = PendingIntent.getActivity(this, 0, notIntent, PendingIntent.FLAG_CANCEL_CURRENT);   
		mBuilder.setContentIntent(contIntent);
		if(Utilidades.isForeground(this) != true)
		{
			mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());
		}
    }
}
