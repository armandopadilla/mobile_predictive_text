package com.example.predictivetext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper 
{

	private static String DB_PATH = "/data/data/com.example.predictivetext/databases/";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "predictive_text_db";
	private static final String TABLE_WORD_PREDICTOR = "dictionary";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	
	
	public DatabaseHandler(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.myContext = context;
	}
	
	public void createDataBase() throws IOException{
		
		boolean dbExists = checkDataBase();
		
		if(dbExists)
		{
			//do nothing!
		}
		else
		{
			this.getReadableDatabase();
			
			try {
				
				copyDataBase();
			}
			catch(IOException e){
				throw new Error("Error copying database");
			}
		}
		
	}
	
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		
		try
		{
			String myPath = DB_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e)
		{
			//some error here.
		}
		
		if(checkDB != null)
		{
			checkDB.close();
		}
		
		return checkDB != null ? true:false;
	}
	
	private void copyDataBase() throws IOException{
		
		InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
		String outFileName = DB_PATH+DATABASE_NAME;
		
		OutputStream myOutput = new FileOutputStream(outFileName);
		
		byte[] buffer = new byte[1024];
		int length;
		
		while((length = myInput.read(buffer)) > 0)
		{
			myOutput.write(buffer, 0, length);
		}
		
		myOutput.flush();
		myOutput.close();
		myInput.close();
		
	}
	
	public void openDataBase() throws SQLException {
		
		String myPath = DB_PATH + DATABASE_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	}
	
	@Override
	public synchronized void close(){
		if(myDataBase != null)
			myDataBase.close();
		
		super.close();
	}
	
	
	@Override 
	public void onCreate(SQLiteDatabase db)
	{
		//String CREATE_WORD_PREDICTOR_TABLE = "CREATE TABLE "+TABLE_WORD_PREDICTOR+" (id INTEGER PRIMARY KEY, word TEXT, count INTEGER, probability REAL)";
		//db.execSQL(CREATE_WORD_PREDICTOR_TABLE);
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//db.execSQL("DROP TABLE IF EXISTS "+TABLE_WORD_PREDICTOR);
		//onCreate(db);
	}
	
	public void deleteWord(Word dic)
	{
		
	}
	
	
	
	/**
	 * Get a specific word.
	 * @param id
	 * @return
	 */
	public Word getWord(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		
		//Cursor cursor = db.query(TABLE_WORD_PREDICTOR, new String[] );
		
		Word word = new Word();
		
		return word;
		
	}
	
	
	/**
	 * Add a word into the table.
	 * @param w
	 */
	public void addWord(Word w)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("word", w.getWord());
		values.put("count", w.getCount());
		values.put("probability", w.getProbability());
		
		
		db.insert(TABLE_WORD_PREDICTOR, null, values);
		db.close();
		
	}
	
	public String[] getPredictiveWord(String pattern)
	{
		String sql = "select field1 from dictionary WHERE field1 LIKE '"+pattern+"' ORDER BY field3 DESC LIMIT 5";
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor c = db.rawQuery(sql, null);
		List<String> words = new ArrayList<String>();
		
		if(c.moveToFirst())
		{
			do {
				
				words.add(c.getString(0));
				
			} while(c.moveToNext());
		}
		
		
		String[] ws = words.toArray(new String[words.size()]);
		
		return ws;
			
	}
	
}
