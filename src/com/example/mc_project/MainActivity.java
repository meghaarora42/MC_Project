package com.example.mc_project;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends FragmentActivity 
{
	private Location mCurrentLocation;
	private LocationClient mLocationClient;
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
//	@Override
//	protected void onStart() 
//	{
//	        super.onStart();
//	        // Connect the client.
//	        mLocationClient.connect();
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*--checking for internet connection
		ConnectivityManager conMgr  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo info = conMgr.getActiveNetworkInfo(); 
		if(info != null && info.isConnected()) 
		{
		   
		}
		else
		{
			Toast.makeText(this,"No Internet connection",1).show();
		}
		 */
		//mLocationClient.connect();
//		mLocationClient = new LocationClient(this, this, this);
//		mCurrentLocation = mLocationClient.getLastLocation();
//		double lat;
//		double lng;
//		if(mCurrentLocation==null)
//		{
//			lat=0;
//			lng=0;
//		}
//		else
//		{
//			lat=mCurrentLocation.getLatitude();
//			lng=mCurrentLocation.getLongitude();
//			Log.d("Long",Double.toString(lat));
//			Log.d("Lat",Double.toString(lat));
//		}
		Intent i=new Intent("android.action.LOGIN");
		startActivity(i);
		finish();
	}
}
