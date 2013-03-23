package com.example.predictivetext;

import java.io.IOException;
import java.sql.SQLException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//We need tor remove this and replace with db.
	String[] words = {};
	DatabaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView lv  = (ListView)findViewById(R.id.mylist);
		ArrayAdapter aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
		lv.setAdapter(aa);
		
		EditText et = (EditText)findViewById(R.id.editText1);
		et.addTextChangedListener(new TextWatcher(){

			public void afterTextChanged(Editable s){}
			
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			
			public void onTextChanged(CharSequence s, int start, int before, int count){
				
				//System.out.println("S:"+s+" start: "+start+" before: "+before+" count: "+count);
				
				//Get the current text
				String text = s.toString();
				int length = text.length();
				
				//fetch the words.
				EditText et = (EditText)findViewById(R.id.editText1);
				
				//Before the cursor
				int cursorPosition = et.getSelectionStart();
				CharSequence enteredText = et.getText().toString();
				CharSequence cursorToStart = enteredText.subSequence(0, cursorPosition);
				System.out.println("====Start::"+cursorToStart);
				
				
				//After the cursor
				CharSequence cursorToEnd = enteredText.subSequence(cursorPosition, enteredText.length());
				
				//Get the current word.
				String[] startWords = cursorToStart.toString().split(" ");
				String[] endWords = cursorToEnd.toString().split(" ");
				
				
				//Given the sentence. "how are you today sir" and our 
				//cursor is after "are" we want to grab "how are"
				String node1 = startWords[startWords.length-1];
				//System.out.println("===start word before:::"+node1);
				//get the remaining words or letters
				//if there is a space right infront the break up.
				//if there is NO space then its part of th previous word.
				String node2 = endWords[0];
		
				//System.out.println("===start word after:::"+node1);
				
				//Clean up. Remove ?,.$,#,@)( etc
				String newNode1 = node1.replace("?","");
				newNode1 = newNode1.replace("!","");
				String newNode2 = node2.replace("?","");
				newNode2 = newNode2.replace("!","");
				//pattern
				String pattern = newNode1+"%"+newNode2;
				System.out.println(pattern);
				
			
				//Query the db
				DatabaseHandler db = new DatabaseHandler(MainActivity.this);
				try
				{
					db.createDataBase();
				}
				catch(IOException ioe)
				{
					throw new Error("Unable to create database: "+ioe.getMessage());
				}
				
				try
				{
					db.openDataBase();

				}catch(SQLException sqle)
				{
					throw new Error("Some error with opening the DB: "+sqle.getMessage());
				}
				
				words = db.getPredictiveWord(pattern);
				
				//Display the data in the list view.
				ListView ll = (ListView)findViewById(R.id.mylist);
				ArrayAdapter aa = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, words);
				ll.setAdapter(aa);
				
				/*
				ll.setOnItemClickListener(new OnItemClickListener(){
					
					public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
					{
						AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
						adb.setTitle("ListView OnClick");
						adb.setMessage("Selected Item is = "+ ll.getItemAtPosition(position));
						adb.setPositiveButton("Ok", null);
						adb.show();                 
					}
					
				});
				*/

			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	
	
}
