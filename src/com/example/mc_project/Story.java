package com.example.mc_project;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
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
				Log.d("email",useremail);
				String address=getAddress(lat, lng);
				ParseObject testObject = new ParseObject("user");
				testObject.put("Email",useremail);	
				testObject.put("Name",username);
				testObject.put("Text",text);
				testObject.put("Latitude",lat);
				testObject.put("Longitude",lng);
				testObject.put("Address",address);
				testObject.saveInBackground();
				
			}
		});

		 	manager = (LocationManager) this.getSystemService( Context.LOCATION_SERVICE );
	        criteria=new Criteria();
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			criteria.setAltitudeRequired(false);
			criteria.setBearingRequired(false);
			criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
			criteria.setVerticalAccuracy(Criteria.NO_REQUIREMENT);
//			Location lastKnownLocationGPS = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			startLocationFetching();
	}
	public String getAddress(Double latitude,Double longi)
	{
		Geocoder geocoder;
		List<Address> addresses;
		geocoder = new Geocoder(this, Locale.getDefault());
		try
		{
		addresses = geocoder.getFromLocation(latitude, longi, 1);
		String address = addresses.get(0).getAddressLine(0);
		String city = addresses.get(0).getAddressLine(1);
		String country = addresses.get(0).getAddressLine(2);
		Log.d("location",(address+","+city+","+country));
		return(address+","+city+","+country);
//		return(address);
		}
		catch(Exception e)
		{
			return("");	
		}
	}
	 private void startLocationFetching() 
	 {
			// TODO Auto-generated method stub
			new Handler().postDelayed(new Runnable() 
			{
				
				@Override
				public void run() 
				{
					// TODO Auto-generated method stub
					manager.requestSingleUpdate( criteria, new LocationListener() 
					{
						
						@Override
						public void onStatusChanged(String provider, int status, Bundle extras) {}
						
						@Override
						public void onProviderEnabled(String provider) {}
						
						@Override
						public void onProviderDisabled(String provider) {}
						
						@Override
						public void onLocationChanged(Location location) 
						{
							lat=location.getLatitude();
							String lt=lat.toString();
//							Log.d("lat",lt);
							lng=location.getLongitude();
							String lg=lng.toString();
//							Log.d("long",lg);
						}
					}, null);
				}
			}, 300);

	 }
}
