package com.example.mc_project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import com.facebook.Session;

public class LoginActivity extends FragmentActivity {

	TextView tv;
	private LoginFragment loginFragment;
	private static final String TAG = "FB";
	
	@Override
	protected void onResumeFragments() {
		// TODO Auto-generated method stub
		if(loginFragment.FBOnce==true)
			loginFragment.execbg();
		super.onResumeFragments();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    /* Generating keyhash
	    try {
	        PackageInfo info = getPackageManager().getPackageInfo(
	                "com.example.mc_project", 
	                PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	            MessageDigest md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	            }
	    } catch (NameNotFoundException e) {

	    } catch (NoSuchAlgorithmException e) {

	    }*/
	    if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        loginFragment = new LoginFragment();
	        loginFragment.FBOnce=false;
	        getSupportFragmentManager()
	        .beginTransaction()
	        .add(android.R.id.content, loginFragment)
	        .commit();
	        
	        
	    } else {
	        // Or set the fragment from restored state info
	        loginFragment = (LoginFragment) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
	    
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	
}
