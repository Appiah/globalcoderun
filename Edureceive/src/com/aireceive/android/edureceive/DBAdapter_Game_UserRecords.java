package com.aireceive.android.edureceive;
	

import com.aireceive.android.A;
import com.aireceive.android.Get;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter_Game_UserRecords {

	public static final String TAG = "DBAdapter_Game_UserRcords";
	
	public static final String KEY_ROWID = "id";
	
	public static final String KEY_GAME_USER_RECORDS_ID = "game_user_records_id";
	
	public static final String KEY_GAME_HEAD_ROW_ID = "game_head_row_id";
	public static final String KEY_GAME_HEAD_ID = "game_head_row_id";
	
	public static final String KEY_GAME_CONTENT_ROW_ID = "game_content_row_id";
	public static final String KEY_GAME_CONTENT_ID = "game_content_id";
	
	public static final String KEY_QUESTION_PLAYED = "question_played";
	public static final String KEY_QUESTION_SCORE = "question_score";
	public static final String KEY_QUESTION_RECORD_TIME_START = "question_record_time_start";
	public static final String KEY_QUESTION_RECORD_TIME_END = "question_record_time_end";
	public static final String KEY_QUESTIONS_COMPLETED = "question_completed";
	public static final String KEY_QUESTIONS_RECORD_TIME_COMPLETED = "questions_record_time_completed";
	
	public static final String KEY_TIMESTAMP = "time_stamp";
	
	
	
	public static final int KEY_ROWID_pos = 0;
	
	public static final int KEY_GAME_USER_RECORDS_ID_pos = 1;
	
	public static final int KEY_GAME_HEAD_ROW_ID_pos = 2;
	public static final int KEY_GAME_HEAD_ID_pos = 3;
	
	public static final int KEY_GAME_CONTENT_ROW_ID_pos = 4;
	public static final int KEY_GAME_CONTENT_ID_pos = 5;
	
	public static final int KEY_QUESTION_PLAYED_pos = 6;
	public static final int KEY_QUESTION_SCORE_pos = 7;
	public static final int KEY_QUESTION_RECORD_TIME_START_pos = 8;
	public static final int KEY_QUESTION_RECORD_TIME_END_pos = 9;
	public static final int KEY_QUESTIONS_COMPLETED_pos = 10;
	public static final int KEY_QUESTIONS_RECORD_TIME_COMPLETED_pos = 11;
	
	public static final int KEY_TIMESTAMP_pos = 12;
	
	
	
	public static final String START_TBL_CREATION = "CREATE TABLE IF NOT EXISTS "+A.DATABASE_TABLE_game_user_records_tbl+" ("+KEY_ROWID+" integer primary key autoincrement, ";
	
	public static final String [] TABLE_COLUMNS_TO_BE_CREATED = new String [] {"",
		KEY_GAME_USER_RECORDS_ID+" text not null,",
		
		KEY_GAME_HEAD_ROW_ID+" text not null,",
		KEY_GAME_HEAD_ID+" text not null,",
		
		KEY_GAME_CONTENT_ROW_ID+" text not null,",
		KEY_GAME_CONTENT_ID+" text not null,",
		
		KEY_QUESTION_PLAYED+" text not null,",
		KEY_QUESTION_SCORE+" text not null,",
		KEY_QUESTION_RECORD_TIME_START+" text not null,",
		KEY_QUESTION_RECORD_TIME_END+" text not null,",
		KEY_QUESTIONS_COMPLETED+" text not null,",
		KEY_QUESTIONS_RECORD_TIME_COMPLETED+" text not null,",
		KEY_TIMESTAMP+" text not null"
			
	};
	
	public static final String END_TBL_CREATION = ");";
	
	private static final String DATABASE_CREATE_game_user_records_tbl = START_TBL_CREATION
			
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_USER_RECORDS_ID_pos]+" "
				
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_HEAD_ROW_ID_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_HEAD_ID_pos]+" "
		                              
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_CONTENT_ROW_ID_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_CONTENT_ID_pos]+" "
				
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTION_PLAYED_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTION_SCORE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTION_RECORD_TIME_START_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTION_RECORD_TIME_END_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTIONS_COMPLETED_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTIONS_RECORD_TIME_COMPLETED_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_TIMESTAMP_pos]
		
	+ END_TBL_CREATION;
	
	private final Context context;
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	private static boolean DEV_MODE;
	public DBAdapter_Game_UserRecords (Context ctx){
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);//there would be an error initially but just keep going...
		DEV_MODE = Get.DEV_MODE(ctx);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper{//after importing for "SQLiteOpenHelper", Add unimplemented methods
		
		DatabaseHelper(Context context){
			super (context, A.DATABASE_NAME_game_user_records_db, null, Get.db_version_current_game_user_records(context));
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			try{
				if(DEV_MODE){
					Log.d("Game User records database creation script : ",DATABASE_CREATE_game_user_records_tbl);
				}
				db.execSQL(DATABASE_CREATE_game_user_records_tbl);
			}catch(SQLException e){
				if(DEV_MODE){
				e.printStackTrace();}
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if(DEV_MODE){
			Log.w(TAG, "Upgrading database from version "+ oldVersion + " to " + newVersion + ", which will destroy all old data");}
			if(newVersion>oldVersion){
				db.execSQL("DROP TABLE IF EXISTS "+A.DATABASE_TABLE_game_user_records_tbl);
				onCreate(db);
			}else{
				if(DEV_MODE){
					Log.w("DB Upgrade of "+A.DATABASE_TABLE_game_user_records_tbl,"from "+Integer.toString(oldVersion)+" to "+Integer.toString(newVersion)+": NOT ALLOWED");
				}
			}
		}
	}
	
	//updgrade the database
		public void upgrade(int oldVersion, int newVersion){
			DBHelper.onUpgrade(db, oldVersion, newVersion);
		}
	
	//opens the database
	public DBAdapter_Game_UserRecords open() throws SQLiteException{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	//closes the database
	public void close(){
		DBHelper.close();
	}
	
	//insert a Message_Item into the database
	public long insertGameUserRecords_Item(
			String KEY_ROWID_str,
			String KEY_GAME_USER_RECORDS_ID_str,
			
			String KEY_GAME_HEAD_ROW_ID_str,
			String KEY_GAME_HEAD_ID_str,
			
			String KEY_GAME_CONTENT_ROW_ID_str,
			String KEY_GAME_CONTENT_ID_str,
			
			String KEY_QUESTION_PLAYED_str,
			String KEY_QUESTION_SCORE_str,
			String KEY_QUESTION_RECORD_TIME_START_str,
			String KEY_QUESTION_RECORD_TIME_END_str,
			String KEY_QUESTIONS_COMPLETED_str,
			String KEY_QUESTIONS_RECORD_TIME_COMPLETED_str,
			String KEY_TIMESTAMP_str
			){
		ContentValues initialValues = new ContentValues();
		
		initialValues.put(KEY_GAME_USER_RECORDS_ID, KEY_GAME_USER_RECORDS_ID_str);
				
		initialValues.put(KEY_GAME_HEAD_ROW_ID,KEY_GAME_HEAD_ROW_ID_str);
		initialValues.put(KEY_GAME_HEAD_ID,KEY_GAME_HEAD_ID_str);
		
		initialValues.put(KEY_GAME_CONTENT_ROW_ID,KEY_GAME_CONTENT_ROW_ID_str);
		initialValues.put(KEY_GAME_CONTENT_ID,KEY_GAME_CONTENT_ID_str);
		
		initialValues.put(KEY_QUESTION_PLAYED, KEY_QUESTION_PLAYED_str);
		initialValues.put(KEY_QUESTION_SCORE, KEY_QUESTION_SCORE_str);
		initialValues.put(KEY_QUESTION_RECORD_TIME_START, KEY_QUESTION_RECORD_TIME_START_str);
		initialValues.put(KEY_QUESTION_RECORD_TIME_END, KEY_QUESTION_RECORD_TIME_END_str);
		initialValues.put(KEY_QUESTIONS_COMPLETED, KEY_QUESTIONS_COMPLETED_str);
		initialValues.put(KEY_QUESTIONS_RECORD_TIME_COMPLETED, KEY_QUESTIONS_RECORD_TIME_COMPLETED_str);
		initialValues.put(KEY_TIMESTAMP,KEY_TIMESTAMP_str);
		
		return db.insert(A.DATABASE_TABLE_game_user_records_tbl, null, initialValues);
		
	}
	
	//deletes a particular Message_Item
	public boolean deleteGameUserRcords_Item(long rowId){
		String whereClause = KEY_ROWID + "=" + rowId;
		String[] whereArgs = null;
		return db.delete(A.DATABASE_TABLE_game_user_records_tbl, whereClause, whereArgs) > 0;
	}
	
	
	//retrieves all the Message_Items
	public Cursor getAllGameUserRcords_Items(){
		
		String[] columns = new String[]{KEY_ROWID,
				KEY_GAME_USER_RECORDS_ID,
				
				KEY_GAME_HEAD_ROW_ID,
				KEY_GAME_HEAD_ID,
				
				KEY_GAME_CONTENT_ROW_ID,
				KEY_GAME_CONTENT_ID,
				
				KEY_QUESTION_PLAYED,
				KEY_QUESTION_SCORE,
				KEY_QUESTION_RECORD_TIME_START,
				KEY_QUESTION_RECORD_TIME_END,
				KEY_QUESTIONS_COMPLETED,
				KEY_QUESTIONS_RECORD_TIME_COMPLETED,
				KEY_TIMESTAMP};
	
		String selection = null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		return db.query(A.DATABASE_TABLE_game_user_records_tbl, columns, selection, selectionArgs, groupBy, having, orderBy);
	}
	
	//retrieve a particular Message_Item with ID as input
	public Cursor getGameUserRecords_Item_with_ID(long rowId) throws SQLException {
		boolean distinct = true;
		String table = A.DATABASE_TABLE_game_user_records_tbl;
		
		String [] columns = new String []{KEY_ROWID,
				KEY_GAME_USER_RECORDS_ID,

				KEY_GAME_HEAD_ROW_ID,
				KEY_GAME_HEAD_ID,
				
				KEY_GAME_CONTENT_ROW_ID,
				KEY_GAME_CONTENT_ID,
				
				KEY_QUESTION_PLAYED,
				KEY_QUESTION_SCORE,
				KEY_QUESTION_RECORD_TIME_START,
				KEY_QUESTION_RECORD_TIME_END,
				KEY_QUESTIONS_COMPLETED,
				KEY_QUESTIONS_RECORD_TIME_COMPLETED,
				KEY_TIMESTAMP};
		
		String selection = KEY_ROWID + "=" + rowId;
		String [] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		String limit = null;
		Cursor mCursor = db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		
		return mCursor;
		
	}
	
	
	//update a Message_Item
	public boolean updateGameUserRcords_Item(long rowId,
			
			String KEY_GAME_USER_RECORDS_ID_str,
			
			String KEY_GAME_HEAD_ROW_ID_str,
			String KEY_GAME_HEAD_ID_str,
			
			String KEY_GAME_CONTENT_ROW_ID_str,
			String KEY_GAME_CONTENT_ID_str,
			
			String KEY_QUESTION_PLAYED_str,
			String KEY_QUESTION_SCORE_str,
			String KEY_QUESTION_RECORD_TIME_START_str,
			String KEY_QUESTION_RECORD_TIME_END_str,
			String KEY_QUESTIONS_COMPLETED_str,
			String KEY_QUESTIONS_RECORD_TIME_COMPLETED_str,
			String KEY_TIMESTAMP_str
			){
		ContentValues args = new ContentValues();
		
		args.put(KEY_GAME_USER_RECORDS_ID, KEY_GAME_USER_RECORDS_ID_str);
		
		args.put(KEY_GAME_HEAD_ROW_ID,KEY_GAME_HEAD_ROW_ID_str);
		args.put(KEY_GAME_HEAD_ID,KEY_GAME_HEAD_ID_str);
		
		args.put(KEY_GAME_CONTENT_ROW_ID,KEY_GAME_CONTENT_ROW_ID_str);
		args.put(KEY_GAME_CONTENT_ID,KEY_GAME_CONTENT_ID_str);
		
		args.put(KEY_QUESTION_PLAYED, KEY_QUESTION_PLAYED_str);
		args.put(KEY_QUESTION_SCORE, KEY_QUESTION_SCORE_str);
		args.put(KEY_QUESTION_RECORD_TIME_START, KEY_QUESTION_RECORD_TIME_START_str);
		args.put(KEY_QUESTION_RECORD_TIME_END, KEY_QUESTION_RECORD_TIME_END_str);
		args.put(KEY_QUESTIONS_COMPLETED, KEY_QUESTIONS_COMPLETED_str);
		args.put(KEY_QUESTIONS_RECORD_TIME_COMPLETED, KEY_QUESTIONS_RECORD_TIME_COMPLETED_str);
		args.put(KEY_TIMESTAMP,KEY_TIMESTAMP_str);
		
		String table = A.DATABASE_TABLE_game_user_records_tbl;
		ContentValues values = args;
		String whereClause = KEY_ROWID + "=" + rowId;
		String []whereArgs = null;
		
		return db.update(table, values, whereClause, whereArgs) > 0;
	}
	
	public boolean updateGameUserRcords_with_local_id_forSpecificColumn(long rowId, String key_col_nm_str, String value_for_col_str){
		ContentValues args = new ContentValues();
		
		args.put(key_col_nm_str, value_for_col_str);
		
		String table = A.DATABASE_TABLE_game_user_records_tbl;
		ContentValues values = args;
		String whereClause = KEY_ROWID + "=" + rowId;
		String []whereArgs = null;
		
		return db.update(table, values, whereClause, whereArgs) > 0;
	}
	
	
}