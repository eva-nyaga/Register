package com.example.register;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DaiActivity extends Activity {
	Button submit, about, contacts;
	EditText fname, lname, email, phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dai);

		submit = (Button) findViewById(R.id.button2);
		about = (Button) findViewById(R.id.babout);
		contacts = (Button) findViewById(R.id.button1);
		

	}

	

}
