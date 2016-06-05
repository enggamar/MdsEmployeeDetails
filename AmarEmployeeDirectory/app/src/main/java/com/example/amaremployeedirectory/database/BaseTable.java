package com.example.amaremployeedirectory.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.amaremployeedirectory.application.BaseApplication;
import com.example.amaremployeedirectory.util.MdsLog;


/**
 * 
 */
public abstract class BaseTable
{
	protected SQLiteDatabase mWritableDatabase;
	protected String		 mTableName;

	public BaseTable(String pTableName )  {
		BaseApplication applicationInstance=BaseApplication.getInstance();
		if(applicationInstance==null)
			throw new Error("Application class must be set in the manifest file or else provided as argument.");
		mWritableDatabase = applicationInstance.getWritableDbInstance();
		mTableName=pTableName;
	}

	
	
	public BaseTable( BaseApplication pBaseApplication, String pTableName ) {
		mWritableDatabase = pBaseApplication.getWritableDbInstance();
		mTableName=pTableName;
	}

	/**
	 * @return the pPrimaryKey of newly inserted row
	 */
//	public abstract Object insertData( IModel pModel );
	
	/**
	 * @return true if row with pPrimaryKey is found and deleted
	 */
	public abstract boolean deleteData( Object pPrimaryKey );
	
	/**
	 * @return array list of all data in table
	 */
//	public abstract ArrayList<IModel> getAllData();
	
	/**
	 * @return the number of rows deleted
	 */
	public int deleteAll() {
		try { return mWritableDatabase.delete(mTableName, "1", null); }
		catch( Exception e ) { MdsLog.e(mTableName, "" + e); return 0; }
	}
	
	/**
	 * @return count of total rows in table, -1 in case of any exception.
	 */
	public int getCount() {
		String columnName = "rowsCount";
		String query =  "select count(*) as " + columnName + "  from " + mTableName;
		int rowsCount = -1;
		Cursor cursor = null;
		try { 
			cursor = mWritableDatabase.rawQuery( query, null);
			if( cursor.moveToNext() ) {
				rowsCount = cursor.getInt(cursor.getColumnIndex(columnName));
			}
		} catch( Exception e ) { MdsLog.e( mTableName, "" + e ); }
		finally { closeCursor(cursor); }
		return rowsCount;
	}
	
	/**
	 * Closes the pCursor.
	 * @param pCursor
	 */
	protected final void closeCursor( Cursor pCursor ) {
		if( pCursor != null && ! pCursor.isClosed() ) pCursor.close();
	}
	public boolean checkIsDataAlreadyInDBorNot(String TableName,
													  String dbfield, String fieldValue) {
		String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
		try {
			Cursor cursor = mWritableDatabase.rawQuery(Query, null);
			if (cursor.getCount() <= 0) {
				cursor.close();
				return false;
			}
			cursor.close();
		} catch (SQLiteException e){
			return false;
		}
		return true;
	}
}