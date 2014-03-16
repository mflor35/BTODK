package com.example.sampleapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.Menu;


public class MainActivity extends Activity {
	
	private CheckBox checkFalse, checkTrue;
	int clicksCount;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupMessageButton();
	}
	
	private void setupMessageButton(){
		Button messageButton = (Button) findViewById(R.id.button1);
		checkFalse = (CheckBox) findViewById(R.id.checkBox1);
		checkTrue = (CheckBox) findViewById(R.id.checkBox2);
		
		messageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clicksCount++;
				Handler handler =  new Handler();
				Runnable run = new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (clicksCount == 1)
						{
							xmlFormater(1);
							checkTrue.setChecked(!checkTrue.isChecked());
							clicksCount = 0;
						}
					}
					
				};
				if(clicksCount == 1)
				{
					handler.postDelayed(run,250);
				}
				else if(clicksCount == 2)
				{
					xmlFormater(2);
					checkFalse.setChecked(!checkFalse.isChecked());
					clicksCount = 0;
				}
			}
		});
		
	}
	public boolean isExternalStorageWritable()
	{
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state))
		{
			return true;
		}
		return false;
	}
	
	void writeTofile(String instance){
		Log.d("WriteToFile: ","WriteToFile");
		String fileName;
		
		fileName = "SomeFile.txt";
		String string = "Some XML data \n";
		FileOutputStream fileOut = null;
		
		try {
			fileOut = openFileOutput(fileName, Context.MODE_WORLD_READABLE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOut.write(string.getBytes());
			Log.d("WriteToFile", "Success: " + getFilesDir());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void xmlFormater(int input)
	{
		String xmlTest = "<?xml version ='1.0'?><testform id='testfom'> ";
		
		String response;
		
		if(input == 1)
		{
			response = String.format(xmlTest, "1");
		}
		else
		{
			response = String.format(xmlTest,"2");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
