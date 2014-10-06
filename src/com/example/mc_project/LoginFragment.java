package com.example.mc_project;

import java.util.Arrays;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class LoginFragment extends Fragment {

	private UiLifecycleHelper uiHelper;
	private static final String TAG = "LoginFragment";
	TextView tv;
	Button b;
	String[] x;
	String useremail="";
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_main, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("email"));
	    return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	 // For scenarios where the main activity is launched and user
	    // session is not null, the session state change notification
	    // may not be triggered. Trigger it if it's open/closed.
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	
	
	static boolean FBnext=false;
	static boolean FBOnce=false;
	String[] backUp=new String[]{};
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        Request.newMeRequest(session, new Request.GraphUserCallback() {

				@Override
				public void onCompleted(GraphUser user, Response response) {
					if (user != null) {
						
						x=buildUserInfoDisplay(user);
						
						new BgTask().execute(null,null,null);
	                }
				}
	        }).executeAsync();
	    } else if (state.isClosed()) {
	    }
	}
	
	class BgTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			if(FBOnce==true)
				return null;
			while(!isAdded())
			{
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
				});
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if (FBOnce==true)
				return;
			FBOnce=true;
			
			Intent i=new Intent("android.action.STORY");
			i.putExtra("email",useremail);
			startActivity(i);
			getActivity().finish();
			super.onPostExecute(result);
		}
	}
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	private String[] buildUserInfoDisplay(GraphUser user) {
	    StringBuilder userInfo = new StringBuilder("");
	    
	    String email="";
	    try{
	    	email=user.getProperty("email").toString();
	    	useremail=email;
	    	Toast.makeText(getActivity(),email,Toast.LENGTH_SHORT).show();
	    }
	    catch(Exception e)
	    {}
	    return new String[]{user.getId(),user.getName(),email};
	}

	public void execbg() {
		new BgTask().execute(null,null,null);
		
	}
	
}
