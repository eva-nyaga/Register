package com.example.register;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DBAdapter extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	// url to create new product
	private static String url_create_user = "http://10.0.2.2/DAI/create_user.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	EditText fname, lname, email, phone;
	Button submit, about;
	final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	String returnString;
	int success;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dai);

		fname = (EditText) findViewById(R.id.editText1);
		lname = (EditText) findViewById(R.id.editText2);
		email = (EditText) findViewById(R.id.editText3);
		phone = (EditText) findViewById(R.id.editText4);

		
	}

	public void onClick(View v) {

		// new async().execute("");
		new CreateNewUser().execute();
		// define the parameter
		postParameters.add(new BasicNameValuePair("fname", fname.getText()
				.toString()));
		postParameters.add(new BasicNameValuePair("lname", lname.getText()
				.toString()));
		postParameters.add(new BasicNameValuePair("email", email.getText()
				.toString()));
		postParameters.add(new BasicNameValuePair("phone", phone.getText()
				.toString()));
	}
		
	class CreateNewUser extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DBAdapter.this);
			pDialog.setMessage("Creating User..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating user
		 * */
		protected String doInBackground(String... args) {
			String first = fname.getText().toString();
			String last = lname.getText().toString();
			String mail = email.getText().toString();
			String number = phone.getText().toString();
			String android_version = "1.0.0";
			

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("first", first));
			params.add(new BasicNameValuePair("last", last));
			params.add(new BasicNameValuePair("mail", mail));
			params.add(new BasicNameValuePair("number", number));
			params.add(new BasicNameValuePair("android_version", android_version));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_user,
					"POST", params);

			// check log cat from response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				success = json.getInt(TAG_SUCCESS);

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			if (success == 1) {
				Toast.makeText(DBAdapter.this, "User Successfully Created",
						Toast.LENGTH_LONG).show();
				// successfully created product
				Intent i = new Intent(getApplicationContext(), DBAdapter.class);
				startActivity(i);

				// closing this screen
				finish();
			} else {
				Toast.makeText(DBAdapter.this, "User Not Successfully Created",
						Toast.LENGTH_LONG).show();
			}
			pDialog.dismiss();
		}
	}
	public void about(View v) {
		
		Intent i = new Intent(this, About.class);
		startActivity(i);
	}
	
public void contact(View v) {
		
		Intent i = new Intent(this, Contacts.class);
		startActivity(i);
	}
}
