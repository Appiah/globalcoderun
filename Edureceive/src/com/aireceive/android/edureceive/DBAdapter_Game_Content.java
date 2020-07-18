package com.aireceive.android.edureceive;
	

import com.aireceive.android.A;
import com.aireceive.android.Get;

/*
	`id` int(10) NOT NULL AUTO_INCREMENT,
  `sim_tbl_id` int(10) NOT NULL COMMENT 'game author or creator sim tbl id',
  `game_code` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `question_level` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'easy, medium, intermediate, hard, expert, geek, professor : 0, 1, 2, 3, 4, 5, 6 ... buffered by "{" and "}"',
  `question_content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `question_image` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-',
  `opt_a` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `opt_a_image` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-',
  `opt_a_comment` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `opt_a_pts` varchar(3) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '1 , 2, 9 or -1 , -3, -21 , hence the max lnf as 3\n',
  `opt_b` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `opt_b_image` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-',
  `opt_b_comment` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `opt_b_pts` varchar(3) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `opt_c` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `opt_c_image` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-',
  `opt_c_comment` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `opt_c_pts` varchar(3) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `opt_d` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `opt_d_image` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-',
  `opt_d_comment` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `opt_d_pts` varchar(3) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `correct_answer` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL,
  `correct_answer_explanation` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `total_time_per_question_min` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
*/		

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter_Game_Content {

	public static final String TAG = "DBAdapter_Game_Content";
	
	public static final String KEY_ROWID = "id";
	
	public static final String KEY_GAME_CONTENT_ID = "game_content_id";
	
	public static final String KEY_GAME_HEAD_ID = "game_head_tbl_id";
	
	public static final String KEY_GAME_CODE = "game_code";
	
	public static final String KEY_QUESTION_LEVEL = "question_level";
	public static final String KEY_QUESTION_CONTENT = "question_content";
	public static final String KEY_QUESTION_IMAGE = "question_image";
	public static final String KEY_OPT_A = "opt_a";
	public static final String KEY_OPT_A_IMAGE = "opt_a_image";
	public static final String KEY_OPT_A_COMMENT = "opt_a_comment";
	public static final String KEY_OPT_A_PTS = "opt_a_pts";
	public static final String KEY_OPT_B = "opt_b";
	public static final String KEY_OPT_B_IMAGE = "opt_b_image";
	public static final String KEY_OPT_B_COMMENT = "opt_b_comment";
	public static final String KEY_OPT_B_PTS = "opt_b_pts";
	public static final String KEY_OPT_C = "opt_c";
	public static final String KEY_OPT_C_IMAGE = "opt_c_image";
	public static final String KEY_OPT_C_COMMENT = "opt_c_comment";
	public static final String KEY_OPT_C_PTS = "opt_c_pts";
	public static final String KEY_OPT_D = "opt_d";
	public static final String KEY_OPT_D_IMAGE = "opt_d_image";
	public static final String KEY_OPT_D_COMMENT = "opt_d_comment";
	public static final String KEY_OPT_D_PTS = "opt_d_pts";
	public static final String KEY_CORRECT_ANSWER = "correct_answer";
	public static final String KEY_CORRECT_ANSWER_EXPLANATION = "correct_answer_explanation";
	public static final String KEY_TOTAL_TIME_PER_QUESTION_MIN = "total_time_per_question_min";
	public static final String KEY_TIMESTAMP = "time_stamp";
	
	
	public static final int KEY_ROWID_pos = 0;
	
	public static final int KEY_GAME_CONTENT_ID_pos = 1;
	
	public static final int KEY_GAME_HEAD_ID_pos = 2;
	
	public static final int KEY_GAME_CODE_pos = 3;
	
	public static final int KEY_QUESTION_LEVEL_pos = 4;
	public static final int KEY_QUESTION_CONTENT_pos = 5;
	public static final int KEY_QUESTION_IMAGE_pos = 6;
	public static final int KEY_OPT_A_pos = 7;
	public static final int KEY_OPT_A_IMAGE_pos = 8;
	public static final int KEY_OPT_A_COMMENT_pos = 9;
	public static final int KEY_OPT_A_PTS_pos = 10;
	public static final int KEY_OPT_B_pos = 11;
	public static final int KEY_OPT_B_IMAGE_pos = 12;
	public static final int KEY_OPT_B_COMMENT_pos = 13;
	public static final int KEY_OPT_B_PTS_pos = 14;
	public static final int KEY_OPT_C_pos = 15;
	public static final int KEY_OPT_C_IMAGE_pos = 16;
	public static final int KEY_OPT_C_COMMENT_pos = 17;
	public static final int KEY_OPT_C_PTS_pos = 18;
	public static final int KEY_OPT_D_pos = 19;
	public static final int KEY_OPT_D_IMAGE_pos = 20;
	public static final int KEY_OPT_D_COMMENT_pos = 21;
	public static final int KEY_OPT_D_PTS_pos = 22;
	public static final int KEY_CORRECT_ANSWER_pos = 23;
	public static final int KEY_CORRECT_ANSWER_EXPLANATION_pos = 24;
	public static final int KEY_TOTAL_TIME_PER_QUESTION_MIN_pos = 25;
	public static final int KEY_TIMESTAMP_pos = 26;
	
	
	public static final String START_TBL_CREATION = "CREATE TABLE IF NOT EXISTS "+A.DATABASE_TABLE_game_content_tbl+" ("+KEY_ROWID+" integer primary key autoincrement, ";
	
	public static final String [] TABLE_COLUMNS_TO_BE_CREATED = new String [] {"",
		
		KEY_GAME_CONTENT_ID+" text not null,",
		
		KEY_GAME_HEAD_ID+" text not null,",
		
		KEY_GAME_CODE+" text not null,",
		
		KEY_QUESTION_LEVEL+" text not null,",
		KEY_QUESTION_CONTENT+" text not null,",
		KEY_QUESTION_IMAGE+" text not null,",
		KEY_OPT_A+" text not null,",
		KEY_OPT_A_IMAGE+" text not null,",
		KEY_OPT_A_COMMENT+" text not null,",
		KEY_OPT_A_PTS+" text not null,",
		KEY_OPT_B+" text not null,",
		KEY_OPT_B_IMAGE+" text not null,",
		KEY_OPT_B_COMMENT+" text not null,",
		KEY_OPT_B_PTS+" text not null,",
		KEY_OPT_C+" text not null,",
		KEY_OPT_C_IMAGE+" text not null,",
		KEY_OPT_C_COMMENT+" text not null,",
		KEY_OPT_C_PTS+" text not null,",
		KEY_OPT_D+" text not null,",
		KEY_OPT_D_IMAGE+" text not null,",
		KEY_OPT_D_COMMENT+" text not null,",
		KEY_OPT_D_PTS+" text not null,",
		KEY_CORRECT_ANSWER+" text not null,",
		KEY_CORRECT_ANSWER_EXPLANATION+" text not null,",
		KEY_TOTAL_TIME_PER_QUESTION_MIN+" text not null,",
		KEY_TIMESTAMP+" text not null"
			
	};
	
	public static final String END_TBL_CREATION = ");";
	
	private static final String DATABASE_CREATE_game_content_tbl = START_TBL_CREATION
		
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_CONTENT_ID_pos]+" "
		
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_HEAD_ID_pos]+" "
		
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_CODE_pos]+" "
		
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTION_LEVEL_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTION_CONTENT_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_QUESTION_IMAGE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_A_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_A_IMAGE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_A_COMMENT_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_A_PTS_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_B_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_B_IMAGE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_B_COMMENT_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_B_PTS_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_C_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_C_IMAGE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_C_COMMENT_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_C_PTS_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_D_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_D_IMAGE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_D_COMMENT_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_OPT_D_PTS_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_CORRECT_ANSWER_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_CORRECT_ANSWER_EXPLANATION_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_TOTAL_TIME_PER_QUESTION_MIN_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_TIMESTAMP_pos]
				
	+ END_TBL_CREATION;
	
	private final Context context;
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	private static boolean DEV_MODE;
	public DBAdapter_Game_Content (Context ctx){
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);//there would be an error initially but just keep going...
		DEV_MODE = Get.DEV_MODE(ctx);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper{//after importing for "SQLiteOpenHelper", Add unimplemented methods
		
		DatabaseHelper(Context context){
			super (context, A.DATABASE_NAME_game_content_db, null, Get.db_version_current_game_content(context));
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			try{
				if(DEV_MODE){
					Log.d("Game content database creation script : ",DATABASE_CREATE_game_content_tbl);
				}
				db.execSQL(DATABASE_CREATE_game_content_tbl);
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
				db.execSQL("DROP TABLE IF EXISTS "+A.DATABASE_TABLE_game_content_tbl);
				onCreate(db);
			}else{
				if(DEV_MODE){
					Log.w("DB Upgrade of "+A.DATABASE_TABLE_game_content_tbl,"from "+Integer.toString(oldVersion)+" to "+Integer.toString(newVersion)+": NOT ALLOWED");
				}
			}
		}
	}
	
	//updgrade the database
		public void upgrade(int oldVersion, int newVersion){
			DBHelper.onUpgrade(db, oldVersion, newVersion);
		}
	
	//opens the database
	public DBAdapter_Game_Content open() throws SQLiteException{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	//closes the database
	public void close(){
		DBHelper.close();
	}
	
	//insert a Message_Item into the database
	public long insertGameContent_Item(
			
			String KEY_GAME_CONTENT_ID_str,
			
			String KEY_GAME_HEAD_ID_str,
			
			String KEY_GAME_CODE_str,
			
			String KEY_QUESTION_LEVEL_str,
			String KEY_QUESTION_CONTENT_str,
			String KEY_QUESTION_IMAGE_str,
			String KEY_OPT_A_str,
			String KEY_OPT_A_IMAGE_str,
			String KEY_OPT_A_COMMENT_str,
			String KEY_OPT_A_PTS_str,
			String KEY_OPT_B_str,
			String KEY_OPT_B_IMAGE_str,
			String KEY_OPT_B_COMMENT_str,
			String KEY_OPT_B_PTS_str,
			String KEY_OPT_C_str,
			String KEY_OPT_C_IMAGE_str,
			String KEY_OPT_C_COMMENT_str,
			String KEY_OPT_C_PTS_str,
			String KEY_OPT_D_str,
			String KEY_OPT_D_IMAGE_str,
			String KEY_OPT_D_COMMENT_str,
			String KEY_OPT_D_PTS_str,
			String KEY_CORRECT_ANSWER_str,
			String KEY_CORRECT_ANSWER_EXPLANATION_str,
			String KEY_TOTAL_TIME_PER_QUESTION_MIN_str,
			String KEY_TIMESTAMP_str
			){
		ContentValues initialValues = new ContentValues();
		
		initialValues.put(KEY_GAME_CONTENT_ID, KEY_GAME_CONTENT_ID_str);
		
		initialValues.put(KEY_GAME_HEAD_ID, KEY_GAME_HEAD_ID_str);
		
		initialValues.put(KEY_GAME_CODE,KEY_GAME_CODE_str);
		
		initialValues.put(KEY_QUESTION_LEVEL,KEY_QUESTION_LEVEL_str);
		initialValues.put(KEY_QUESTION_CONTENT,KEY_QUESTION_CONTENT_str);
		initialValues.put(KEY_QUESTION_IMAGE,KEY_QUESTION_IMAGE_str);
		
		initialValues.put(KEY_OPT_A,KEY_OPT_A_str);
		initialValues.put(KEY_OPT_A_IMAGE,KEY_OPT_A_IMAGE_str);
		initialValues.put(KEY_OPT_A_COMMENT,KEY_OPT_A_COMMENT_str);
		initialValues.put(KEY_OPT_A_PTS,KEY_OPT_A_PTS_str);
		
		initialValues.put(KEY_OPT_B,KEY_OPT_B_str);
		initialValues.put(KEY_OPT_B_IMAGE,KEY_OPT_B_IMAGE_str);
		initialValues.put(KEY_OPT_B_COMMENT,KEY_OPT_B_COMMENT_str);
		initialValues.put(KEY_OPT_B_PTS,KEY_OPT_B_PTS_str);
		
		initialValues.put(KEY_OPT_C,KEY_OPT_C_str);
		initialValues.put(KEY_OPT_C_IMAGE,KEY_OPT_C_IMAGE_str);
		initialValues.put(KEY_OPT_C_COMMENT,KEY_OPT_C_COMMENT_str);
		initialValues.put(KEY_OPT_C_PTS,KEY_OPT_C_PTS_str);
		
		initialValues.put(KEY_OPT_D,KEY_OPT_D_str);
		initialValues.put(KEY_OPT_D_IMAGE,KEY_OPT_D_IMAGE_str);
		initialValues.put(KEY_OPT_D_COMMENT,KEY_OPT_D_COMMENT_str);
		initialValues.put(KEY_OPT_D_PTS,KEY_OPT_D_PTS_str);
		
		initialValues.put(KEY_CORRECT_ANSWER,KEY_CORRECT_ANSWER_str);
		initialValues.put(KEY_CORRECT_ANSWER_EXPLANATION,KEY_CORRECT_ANSWER_EXPLANATION_str);
		initialValues.put(KEY_TOTAL_TIME_PER_QUESTION_MIN,KEY_TOTAL_TIME_PER_QUESTION_MIN_str);
		initialValues.put(KEY_TIMESTAMP,KEY_TIMESTAMP_str);
		
		return db.insert(A.DATABASE_TABLE_game_content_tbl, null, initialValues);
		
	}
	
	//deletes a particular Message_Item
	public boolean deleteGameContent_Item(long rowId){
		String whereClause = KEY_ROWID + "=" + rowId;
		String[] whereArgs = null;
		return db.delete(A.DATABASE_TABLE_game_content_tbl, whereClause, whereArgs) > 0;
	}
	
	
	//retrieves all the Message_Items
	public Cursor getAllGameContent_Items(){
		String[] columns = new String[]{KEY_ROWID,
				
				KEY_GAME_CONTENT_ID,
				
				KEY_GAME_HEAD_ID,
				
				KEY_GAME_CODE,
				
				KEY_QUESTION_LEVEL,
				KEY_QUESTION_CONTENT,
				KEY_QUESTION_IMAGE,
				KEY_OPT_A,
				KEY_OPT_A_IMAGE,
				KEY_OPT_A_COMMENT,
				KEY_OPT_A_PTS,
				KEY_OPT_B,
				KEY_OPT_B_IMAGE,
				KEY_OPT_B_COMMENT,
				KEY_OPT_B_PTS,
				KEY_OPT_C,
				KEY_OPT_C_IMAGE,
				KEY_OPT_C_COMMENT,
				KEY_OPT_C_PTS,
				KEY_OPT_D,
				KEY_OPT_D_IMAGE,
				KEY_OPT_D_COMMENT,
				KEY_OPT_D_PTS,
				KEY_CORRECT_ANSWER,
				KEY_CORRECT_ANSWER_EXPLANATION,
				KEY_TOTAL_TIME_PER_QUESTION_MIN,
				KEY_TIMESTAMP};
	
		String selection = null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		return db.query(A.DATABASE_TABLE_game_content_tbl, columns, selection, selectionArgs, groupBy, having, orderBy);
	}
	
	//retrieve a particular Message_Item with ID as input
	public Cursor getGameContent_Item_with_ID(long rowId) throws SQLException {
		boolean distinct = false;
		String table = A.DATABASE_TABLE_game_content_tbl;
		String [] columns = new String []{KEY_ROWID,
				
				KEY_GAME_CONTENT_ID,
				
				KEY_GAME_HEAD_ID,
				
				KEY_GAME_CODE,
				
				KEY_QUESTION_LEVEL,
				KEY_QUESTION_CONTENT,
				KEY_QUESTION_IMAGE,
				KEY_OPT_A,
				KEY_OPT_A_IMAGE,
				KEY_OPT_A_COMMENT,
				KEY_OPT_A_PTS,
				KEY_OPT_B,
				KEY_OPT_B_IMAGE,
				KEY_OPT_B_COMMENT,
				KEY_OPT_B_PTS,
				KEY_OPT_C,
				KEY_OPT_C_IMAGE,
				KEY_OPT_C_COMMENT,
				KEY_OPT_C_PTS,
				KEY_OPT_D,
				KEY_OPT_D_IMAGE,
				KEY_OPT_D_COMMENT,
				KEY_OPT_D_PTS,
				KEY_CORRECT_ANSWER,
				KEY_CORRECT_ANSWER_EXPLANATION,
				KEY_TOTAL_TIME_PER_QUESTION_MIN,
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
	
	public Cursor getGameContent_Item_with_GAME_CODE(String game_code_str) throws SQLException {
		
		String table = A.DATABASE_TABLE_game_content_tbl;
		String [] columns = new String []{KEY_ROWID,
				
				KEY_GAME_CONTENT_ID,
				
				KEY_GAME_HEAD_ID,
				
				KEY_GAME_CODE,
				
				KEY_QUESTION_LEVEL,
				KEY_QUESTION_CONTENT,
				KEY_QUESTION_IMAGE,
				KEY_OPT_A,
				KEY_OPT_A_IMAGE,
				KEY_OPT_A_COMMENT,
				KEY_OPT_A_PTS,
				KEY_OPT_B,
				KEY_OPT_B_IMAGE,
				KEY_OPT_B_COMMENT,
				KEY_OPT_B_PTS,
				KEY_OPT_C,
				KEY_OPT_C_IMAGE,
				KEY_OPT_C_COMMENT,
				KEY_OPT_C_PTS,
				KEY_OPT_D,
				KEY_OPT_D_IMAGE,
				KEY_OPT_D_COMMENT,
				KEY_OPT_D_PTS,
				KEY_CORRECT_ANSWER,
				KEY_CORRECT_ANSWER_EXPLANATION,
				KEY_TOTAL_TIME_PER_QUESTION_MIN,
				KEY_TIMESTAMP};
		String selection = KEY_GAME_CODE + " LIKE " +"\'"+ game_code_str+"\'";
		String [] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		String limit = null;
		Cursor mCursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		
		return mCursor;
		
	}
	
public Cursor getGameContent_Item_with_GAME_CONTENT_ID(String game_content_id_str) throws SQLException {
		
		String table = A.DATABASE_TABLE_game_content_tbl;
		String [] columns = new String []{
				KEY_ROWID,
				
				KEY_GAME_CONTENT_ID,
				
				KEY_GAME_HEAD_ID,
				
				KEY_GAME_CODE,
				
				KEY_QUESTION_LEVEL,
				KEY_QUESTION_CONTENT,
				KEY_QUESTION_IMAGE,
				KEY_OPT_A,
				KEY_OPT_A_IMAGE,
				KEY_OPT_A_COMMENT,
				KEY_OPT_A_PTS,
				KEY_OPT_B,
				KEY_OPT_B_IMAGE,
				KEY_OPT_B_COMMENT,
				KEY_OPT_B_PTS,
				KEY_OPT_C,
				KEY_OPT_C_IMAGE,
				KEY_OPT_C_COMMENT,
				KEY_OPT_C_PTS,
				KEY_OPT_D,
				KEY_OPT_D_IMAGE,
				KEY_OPT_D_COMMENT,
				KEY_OPT_D_PTS,
				KEY_CORRECT_ANSWER,
				KEY_CORRECT_ANSWER_EXPLANATION,
				KEY_TOTAL_TIME_PER_QUESTION_MIN,
				KEY_TIMESTAMP
		};
		
		String selection = KEY_GAME_CONTENT_ID + " = "+ game_content_id_str;
		String [] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		String limit = null;
		
		Cursor mCursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		
		return mCursor;
		
	}
	
	
	//update a Message_Item
	public boolean updateGameContent_Item(long rowId,
			
			String KEY_GAME_CONTENT_ID_str,
			
			String KEY_GAME_HEAD_ID_str,
			
			String KEY_GAME_CODE_str,
			
			String KEY_QUESTION_LEVEL_str,
			String KEY_QUESTION_CONTENT_str,
			String KEY_QUESTION_IMAGE_str,
			String KEY_OPT_A_str,
			String KEY_OPT_A_IMAGE_str,
			String KEY_OPT_A_COMMENT_str,
			String KEY_OPT_A_PTS_str,
			String KEY_OPT_B_str,
			String KEY_OPT_B_IMAGE_str,
			String KEY_OPT_B_COMMENT_str,
			String KEY_OPT_B_PTS_str,
			String KEY_OPT_C_str,
			String KEY_OPT_C_IMAGE_str,
			String KEY_OPT_C_COMMENT_str,
			String KEY_OPT_C_PTS_str,
			String KEY_OPT_D_str,
			String KEY_OPT_D_IMAGE_str,
			String KEY_OPT_D_COMMENT_str,
			String KEY_OPT_D_PTS_str,
			String KEY_CORRECT_ANSWER_str,
			String KEY_CORRECT_ANSWER_EXPLANATION_str,
			String KEY_TOTAL_TIME_PER_QUESTION_MIN_str,
			String KEY_TIMESTAMP_str
			){
		ContentValues args = new ContentValues();
		
		args.put(KEY_GAME_CONTENT_ID, KEY_GAME_CONTENT_ID_str);
		
		args.put(KEY_GAME_HEAD_ID, KEY_GAME_HEAD_ID_str);
		
		args.put(KEY_GAME_CODE, KEY_GAME_CODE_str);
		
		args.put(KEY_QUESTION_LEVEL, KEY_QUESTION_LEVEL_str);
		args.put(KEY_QUESTION_CONTENT, KEY_QUESTION_CONTENT_str);
		args.put(KEY_QUESTION_IMAGE, KEY_QUESTION_IMAGE_str);
		
		args.put(KEY_OPT_A, KEY_OPT_A_str);
		args.put(KEY_OPT_A_IMAGE, KEY_OPT_A_IMAGE_str);
		args.put(KEY_OPT_A_COMMENT, KEY_OPT_A_COMMENT_str);
		args.put(KEY_OPT_A_PTS, KEY_OPT_A_PTS_str);
		
		args.put(KEY_OPT_B, KEY_OPT_B_str);
		args.put(KEY_OPT_B_IMAGE, KEY_OPT_B_IMAGE_str);
		args.put(KEY_OPT_B_COMMENT, KEY_OPT_B_COMMENT_str);
		args.put(KEY_OPT_B_PTS, KEY_OPT_B_PTS_str);
		
		args.put(KEY_OPT_C, KEY_OPT_C_str);
		args.put(KEY_OPT_C_IMAGE, KEY_OPT_C_IMAGE_str);
		args.put(KEY_OPT_C_COMMENT, KEY_OPT_C_COMMENT_str);
		args.put(KEY_OPT_C_PTS, KEY_OPT_C_PTS_str);
		
		args.put(KEY_OPT_D, KEY_OPT_D_str);
		args.put(KEY_OPT_D_IMAGE, KEY_OPT_D_IMAGE_str);
		args.put(KEY_OPT_D_COMMENT, KEY_OPT_D_COMMENT_str);
		args.put(KEY_OPT_D_PTS, KEY_OPT_D_PTS_str);
		
		args.put(KEY_CORRECT_ANSWER, KEY_CORRECT_ANSWER_str);
		args.put(KEY_CORRECT_ANSWER_EXPLANATION, KEY_CORRECT_ANSWER_EXPLANATION_str);
		args.put(KEY_TOTAL_TIME_PER_QUESTION_MIN, KEY_TOTAL_TIME_PER_QUESTION_MIN_str);
		args.put(KEY_TIMESTAMP, KEY_TIMESTAMP_str);
		
		String table = A.DATABASE_TABLE_game_content_tbl;
		ContentValues values = args;
		String whereClause = KEY_ROWID + "=" + rowId;
		String []whereArgs = null;
		
		return db.update(table, values, whereClause, whereArgs) > 0;
	}
	
	public boolean updateGameContent_with_local_id_forSpecificColumn(long rowId, String key_col_nm_str, String value_for_col_str){
		ContentValues args = new ContentValues();
		
		args.put(key_col_nm_str, value_for_col_str);
		
		String table = A.DATABASE_TABLE_game_content_tbl;
		ContentValues values = args;
		String whereClause = KEY_ROWID + "=" + rowId;
		String []whereArgs = null;
		
		return db.update(table, values, whereClause, whereArgs) > 0;
	}
	
	
}
