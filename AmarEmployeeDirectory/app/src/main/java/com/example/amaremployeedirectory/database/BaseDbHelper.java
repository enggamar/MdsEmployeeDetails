package com.example.amaremployeedirectory.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amaremployeedirectory.util.MdsLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class BaseDbHelper extends SQLiteOpenHelper implements DBManifest
{
	String LOG_TAG = "BaseDbHelper";
	protected DBInitializer mInitializer;
	public BaseDbHelper( Context pContext )
	{
		this(pContext, DATABASE_NAME, DATABASE_VERSION);

	}
	public BaseDbHelper( Context pContext,DBInitializer pInitializer )
	{
		this(pContext, pInitializer.getDBName(),pInitializer.getDBVersion());
		mInitializer=pInitializer;
	}
	public BaseDbHelper(Context pContext,String pDBName,int pDBVersion)
	{
		super(pContext,pDBName, null, pDBVersion);
		// if( ! isDataExisting() ) copyDefaultDataBase(pContext);

	}
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		MdsLog.i( LOG_TAG, "Tables creation start.");
		if(mInitializer==null)
		{
			int size = CREATE_QUERIES.length;
			for (int i = 0; i < size; i++ ) {
				db.execSQL(CREATE_QUERIES[i]);
			}
		}
		else
		{
			mInitializer.initalizeDB(db);
		}
		MdsLog.i(LOG_TAG, "Tables creation end.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MdsLog.i( LOG_TAG, "DB upgrade.");
	}

	/**
	 * @param pContext
	 */
	public void copyDefaultDataBase( Context pContext )
	{
		InputStream defaultDbInputStream = null;
		OutputStream outputStreamOnActualDb = null;

		try
		{	
			/**
			 * Default DB file is kept in assets/defaultDatabase directory with same name as DATABASE_NAME.
			 * It's name can be different.
			 */
			defaultDbInputStream = pContext.getAssets().open( "defaultDatabase/" + DATABASE_NAME);
			MdsLog.i( LOG_TAG, "Default DB File read.");

			String actualDbFilePath = getWritableDatabase().getPath();
			outputStreamOnActualDb = new FileOutputStream( actualDbFilePath );

			/**
			 * Following will copy default-db-file over actual-db-file.
			 */
			byte[] tempBuffer = new byte[1024];
			int readLength = 0;
			MdsLog.i( LOG_TAG, "Copy start.");
			while( ( readLength = defaultDbInputStream.read(tempBuffer) ) > 0 )
			{
				outputStreamOnActualDb.write(tempBuffer, 0, readLength);
			}
			defaultDbInputStream.close();
			outputStreamOnActualDb.flush(); outputStreamOnActualDb.close();
			MdsLog.i( LOG_TAG, "Copy end.");

			if( ! isDataExisting() )
			{
				File actualDbFileObj = new File( actualDbFilePath );
				MdsLog.i( LOG_TAG, "Removed: " + actualDbFileObj.delete() );
			}
		}
		catch( Exception e ) { MdsLog.e( LOG_TAG, "" + e ); }
		finally
		{
			try
			{
				if( defaultDbInputStream != null ) defaultDbInputStream.close();
				if( outputStreamOnActualDb != null )
				{
					outputStreamOnActualDb.flush(); outputStreamOnActualDb.close();
				}
			}
			catch(Exception e) {}
		}
	}

	/**
	 *
	 */
	private boolean isDataExisting() {
		SQLiteDatabase database = null;
		Cursor cursor = null;
		boolean dataExists = false;
		try
		{
			database = getReadableDatabase();
			String columnName = "rowCount";
			for( String tabelName : TABLE_NAMES )
			{
				cursor = database.rawQuery( "select count(*) as " + columnName + "  from " + tabelName, null);
				if( cursor.moveToNext() ) {
					dataExists = cursor.getInt(cursor.getColumnIndex(columnName)) > 0;
				}
				cursor.close();
				if( dataExists ) break;
			}
		}
		catch (Exception e) { MdsLog.e( LOG_TAG, "" + e ); }
		finally
		{
			if( database != null ) database.close();
			if( cursor != null && ! cursor.isClosed() ) cursor.close();
		}
		return dataExists;
	}
	
}