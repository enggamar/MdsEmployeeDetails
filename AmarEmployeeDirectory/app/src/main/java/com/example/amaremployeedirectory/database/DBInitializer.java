package com.example.amaremployeedirectory.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author harsh.jain
 *
 */
public interface DBInitializer {
	
	/**
	 * @return database name
	 */
	String getDBName();
	/**
	 * @return database version
	 */
	int getDBVersion();
	
	/**
	 * Implementing class must do the initialization part(creating/populating tables) in this method
	 * @param db newly created database instance
	 */
	void initalizeDB(SQLiteDatabase db);


}
