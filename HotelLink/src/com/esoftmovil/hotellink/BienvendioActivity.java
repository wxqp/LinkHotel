package com.esoftmovil.hotellink;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Intent;


public class BienvendioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bienvenido);
		
		final LinearLayout oLayout = (LinearLayout) findViewById(R.id.lytBienvenidoParent);
		oLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent oIntent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(oIntent);
			}
		});
	}
}
