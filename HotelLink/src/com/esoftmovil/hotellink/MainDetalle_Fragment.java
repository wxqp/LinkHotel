package com.esoftmovil.hotellink;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainDetalle_Fragment extends Fragment 
{
	static final  ArrayList<String> numbers = new  ArrayList<String>();

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View oView = inflater.inflate(R.layout.fragment_main_detalle, container, false);		  
				
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		numbers.add("a");
 		
 		
        //GridView gridView = (GridView) oView.findViewById(R.id.gridView);
        //GridAdapter adapter = new GridAdapter( getActivity(), numbers);
     	//gridView.setAdapter(adapter);
     	
     	return oView;
    }
	class GridAdapter extends ArrayAdapter<String> 
	{
		 Context context;
		 ArrayList<String> data = new ArrayList<String>();

		 public GridAdapter(Context context, ArrayList<String> data) 
		 {
			  super(context,android.R.layout.simple_list_item_1, data);
			  this.context = context;
			  this.data = data;
		 }

		 @Override
		 public View getView(int position, View convertView, ViewGroup parent) 
		 {
			 View row = convertView;

			 if (row == null) 
			 {
				 LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				 row = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
				// TextView txtTitle = (TextView) row.findViewById(R.id.item_text);
				 //txtTitle.setText("hjhhj");
			 } 
			 return row;

		 }
	}
}
