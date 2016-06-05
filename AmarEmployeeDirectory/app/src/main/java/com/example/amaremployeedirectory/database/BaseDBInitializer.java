package com.example.amaremployeedirectory.database;

import android.database.sqlite.SQLiteDatabase;

public class BaseDBInitializer implements DBInitializer{
	private   String DB_NAME="";
	private  int DB_VERSION=1;
	protected String[] TABLE_NAMES;


	protected String[] CREATE_QUERIES ;

	public BaseDBInitializer(String dB_NAME, int dB_VERSION,String[] create_queries,String[] table_names) {
		DB_NAME=dB_NAME;
		DB_VERSION=dB_VERSION;
		TABLE_NAMES=table_names;
		CREATE_QUERIES=create_queries;
	}

	@Override
	public String getDBName() {
		// TODO Auto-generated method stub
		return DB_NAME;
	}

	@Override
	public int getDBVersion() {
		// TODO Auto-generated method stub
		return DB_VERSION;
	}

	@Override
	public void initalizeDB(SQLiteDatabase db) {
		int size = CREATE_QUERIES.length;
		for (int i = 0; i < size; i++ ) {
			db.execSQL(CREATE_QUERIES[i]);
		}
	}


}
