package com.example.mc_project;

import java.util.List;
import java.util.Locale;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
import android.widget.TextView;

public class Homepage extends Activity
{
	String text;
	TextView comment;
	Button newstory;
	LocationManager manager;
	Criteria criteria;
	Double lat,lng;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_homepage);
		Parse.initialize(this, "cIlG71ZlahKyRJv8kaJ0L2y6hDbdvixZyimny8tH", "QhqzYsrDG8GwvzTqvX2LcV6ZgCAhhy2pPW4Corg7");
		
		comment=(TextView)findViewById(R.id.comment);
		newstory=(Button)findViewById(R.id.newstorybutton);
	 	manager = (LocationManager) this.getSystemService( Context.LOCATION_SERVICE );
        criteria=new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
		criteria.setVerticalAccuracy(Criteria.NO_REQUIREMENT);
//		Location lastKnownLocationGPS = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		startLocationFetching();
		newstory.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Bundle extras = getIntent().getExtras();
				String useremail = extras.getString("email");
				String username = extras.getString("name");
				String address=getAddress(lat, lng);
				Intent i=new Intent("android.action.STORY");
				i.putExtra("email",useremail);
				i.putExtra("name",username);
				i.putExtra("Latitude",lat);
				i.putExtra("Longitude",lng);
				i.putExtra("Address",address);
				startActivity(i);
				
			}
		});
		ParseQuery<ParseObject> query = ParseQuery.getQuery("user");
//		query.whereEqualTo("latitude", "dstemkoski@example.com");
		query.whereEqualTo("Name","Danish Goel");
		query.findInBackground(new FindCallback<ParseObject>() 
		{
		    public void done(List<ParseObject> scoreList, ParseException e) 
		    {
		        if (e == null) 
		        {
		            for (ParseObject totem :scoreList) 
		            {
		                comment.append(totem.getString("Text"));
		                comment.append("\n");
		            } 
		        } else 
		        {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }
		});
//		query.getFirstInBackground(new GetCallback<ParseObject>() 
//		{
//		  public void done(ParseObject object, ParseException e) 
//		  {
//			  text=object.getString("Text");	
//			  comment.append(text);
//		  }
//		});
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
//			return(address);
			}
			catch(Exception e)
			{
				return("");	
			}
		}
}