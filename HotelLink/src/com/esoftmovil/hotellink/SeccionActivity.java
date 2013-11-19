package com.esoftmovil.hotellink;

import java.util.ArrayList;  

import com.esoftmovil.com.esoftmovil.db.DBManager;

import android.graphics.Bitmap; 
import android.graphics.BitmapFactory; 
import android.view.View; 
import android.widget.AdapterView; 
import android.widget.AdapterView.OnItemClickListener; 
import android.widget.GridView;
import android.widget.Toast;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class SeccionActivity extends Activity {
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>(); 
	GridViewAdapter GridAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seccion);
		
		gridArray.add(new Item(ACTIVITY_SERVICE)); 
		gridArray.add(new Item(ACTIVITY_SERVICE)); 
		gridArray.add(new Item(ACTIVITY_SERVICE)); 
		gridArray.add(new Item(ACTIVITY_SERVICE));
		
		gridView = (GridView) findViewById(R.id.grd_seccion); 
		GridAdapter = new GridViewAdapter(this, R.layout.row_grid, gridArray); 
		gridView.setAdapter(GridAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() 
		{ 
			@Override 
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) 
			{ 
				Toast.makeText(getApplicationContext(),gridArray.get(position).getText(), Toast.LENGTH_SHORT).show(); 
			} 
		}); 
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seccion, menu);
		return true;
	}

}
