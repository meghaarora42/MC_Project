package com.example.mc_project;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class Story extends Activity
{
	EditText story;
	Button submit;
	LocationManager manager;
	Criteria criteria;
	Double lat,lng;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_story);
		Parse.initialize(this, "cIlG71ZlahKyRJv8kaJ0L2y6hDbdvixZyimny8tH", "QhqzYsrDG8GwvzTqvX2LcV6ZgCAhhy2pPW4Corg7");
		story=(EditText)findViewById(R.id.editText1);
		submit=(Button)findViewById(R.id.button1);
		submit.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String text=story.getText().toString();
				Bundle extras = getIntent().getExtras();
				String useremail = extras.getString("email");
				String username = extras.getString("name");
				Double lat =extras.getDouble("Latitude");
				Double lng =extras.getDouble("Longitude");
				String address = extras.getString("Address");
				ParseObject testObject = new ParseObject("user");
				testObject.put("Email",useremail);	
				testObject.put("Name",username);
				testObject.put("Text",text);
				testObject.put("Latitude",lat);
				testObject.put("Longitude",lng);
				testObject.put("Address",address);
				testObject.saveInBackground();
				Intent i=new Intent("android.action.HOMEPAGE");
				startActivity(i);
			}
		});

	}


}
