package com.example.mc_project;

import android.app.Activity;
import android.os.Bundle;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
				String email = extras.getString("email");
				Log.d("email",email);
				ParseObject testObject = new ParseObject("user");
				testObject.put("email",email);	
				testObject.put("text",text);
				testObject.saveInBackground();
				
			}
		});
	
	}

}
