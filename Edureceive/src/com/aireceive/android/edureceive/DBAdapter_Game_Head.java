package com.aireceive.android.edureceive;
	

import com.aireceive.android.A;
import com.aireceive.android.Get;
/*
	CREATE TABLE "game_head_tbl" (
			  "id" int(10) NOT NULL AUTO_INCREMENT COMMENT 'this table is created as the initial landing point of a user creating a new game and the name of the game must be unique.',
			  "sim_tbl_id" int(10) NOT NULL COMMENT 'Game creator or author registered sim tbl id',
			  "game_nm" varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'the name must be unique and is not case sensitive there "maths game" == "Maths Game" == "Maths GAme"',
			  "game_description" varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
			  "game_rules" varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
			  "game_interests" text COLLATE utf8mb4_unicode_ci COMMENT 'So here, the author can help make his game come as the first option or be more visible by being more specific on which sorts of category or interests he or she is targeting. And the more sincere he or she is, the better.',
			  "game_countries" text COLLATE utf8mb4_unicode_ci COMMENT 'so here, the game author can set the countries he or she wants the game to be available',
			  "game_age_range" varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-' COMMENT 'the game author can specify what age range to make the accessible to',
			  "game_gender" tinyint(1) NOT NULL DEFAULT '0' COMMENT 'The author of the game can decide whether the game should only be played by males or females, i.e. . that is if the gender of a gamer is known, then and then only under [  ] Respect profile -> checked would this game be shown to the gamer, if [  ] Respect profile is changed to NULL, then when the gamer taps on the game, it would ask the gamer to identify him or herself by gender, because the gamer requires that.',
			  "game_playability_dependencies" text COLLATE utf8mb4_unicode_ci COMMENT 'So, here , the author of the game can specify and say that, a player would have had to have played some other specific games before been allowed to play this very game.',
			  "game_playability_score_dependencies" text COLLATE utf8mb4_unicode_ci COMMENT 'So the author of the game can decide that, a play would have had to have scored a certain amount of points or total score for a particular game before being allowed to play this very game.\n',
			  "game_playability_rank_dependency" tinyint(2) NOT NULL DEFAULT '0' COMMENT 'Here, the author of the game can decide on whether a player needs to have reached a certain rank within the game before being allowed to play this very game.',
			  "game_availability" tinyint(2) NOT NULL DEFAULT '0' COMMENT 'The author of the game can decide that, this game should FREE or PAID or SUBSCRIBED (mo / yr / per game round ) ie. 0, 1, 2, 3, 4 respectively. Default : free',
			  "game_price" varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT 'if the game is not free, the price would be setted here.',
			  "game_active_status" tinyint(1) NOT NULL DEFAULT '0' COMMENT 'So the active status would define if the game author still wants this game to available to users. So during publishing of the game, this can only be changed from 0 to 1 by pressing the "publish" button and confirming to make the game live and active. And the author can deactivate is anytime and all players currently would be alerted and the game would cease to be playable.',
			  "game_code" varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
			  "game_img" varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
			  "game_auto_explain" tinyint(1) NOT NULL DEFAULT '1',
			  "game_timed" tinyint(1) NOT NULL DEFAULT '0',
			  "game_timed_min" varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
			  "game_pausable" tinyint(1) NOT NULL DEFAULT '1',
			  "game_replayable" tinyint(1) NOT NULL DEFAULT '1',
			  "game_inclusivity_by_mobile" text COLLATE utf8mb4_unicode_ci COMMENT 'the game author can create a circle of eg. only his students or friends or certain specific people only who can play his game by entering their mobile numbers. And if a potential player''s fon number is not added he or she cannot play or even see the game first of all to play select it and play it.',
			  "game_exclusivity_by_mobile" text COLLATE utf8mb4_unicode_ci COMMENT 'the game author can add a player''s mobile number ie. country code + sim number to prevent that player from ever playing this game.',
			  "time_stamp" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
			  PRIMARY KEY ("id"),
			  UNIQUE KEY "game_nm_UNIQUE" ("game_nm"),
			  UNIQUE KEY "game_code_UNIQUE" ("game_code"),
			  KEY "fk_game_head_tbl_sim_tbl1_idx" ("sim_tbl_id")
	)
*/		

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter_Game_Head {

	public static final String TAG = "DBAdapter_Game_Head";
	
	public static final String KEY_ROWID = "id";
	public static final String KEY_GAME_HEAD_ID = "game_head_id";
	public static final String KEY_SIM_NUM = "sim_num";//or author registered sim tbl id',
	/*GAME CODE*/public static final String KEY_GAME_CODE = "game_code";// varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
	public static final String KEY_GAME_NAME =  "game_name";// varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'the name must be unique and is not case sensitive there "maths game" == "Maths Game" == "Maths GAme"',
	public static final String KEY_GAME_DESCRIPTION = "game_description";// varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
	public static final String KEY_GAME_RULES = "game_rules";// varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
	public static final String KEY_GAME_INTERESTS = "game_interests";// text COLLATE utf8mb4_unicode_ci COMMENT 'So here, the author can help make his game come as the first option or be more visible by being more specific on which sorts of category or interests he or she is targeting. And the more sincere he or she is, the better.',
	public static final String KEY_GAME_COUNTRIES = "game_countries";// text COLLATE utf8mb4_unicode_ci COMMENT 'so here, the game author can set the countries he or she wants the game to be available',
	public static final String KEY_GAME_AGE_RANGE = "game_age_range";// varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-' COMMENT 'the game author can specify what age range to make the accessible to',
	public static final String KEY_GAME_GENDER = "game_gender";// tinyint(1) NOT NULL DEFAULT '0' COMMENT 'The author of the game can decide whether the game should only be played by males or females, i.e. . that is if the gender of a gamer is known, then and then only under [  ] Respect profile -> checked would this game be shown to the gamer, if [  ] Respect profile is changed to NULL, then when the gamer taps on the game, it would ask the gamer to identify him or herself by gender, because the gamer requires that.',
	public static final String KEY_GAME_PLAYABILITY_DEPENDENCIES = "game_playability_dependencies";// text COLLATE utf8mb4_unicode_ci COMMENT 'So, here , the author of the game can specify and say that, a player would have had to have played some other specific games before been allowed to play this very game.',
	public static final String KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES = "game_playability_score_dependencies";// text COLLATE utf8mb4_unicode_ci COMMENT 'So the author of the game can decide that, a play would have had to have scored a certain amount of points or total score for a particular game before being allowed to play this very game.\n',
	public static final String KEY_GAME_PLAYABILITY_RANK_DEPENDANCY = "game_playability_rank_dependency";// tinyint(2) NOT NULL DEFAULT '0' COMMENT 'Here, the author of the game can decide on whether a player needs to have reached a certain rank within the game before being allowed to play this very game.',
	public static final String KEY_GAME_AVAILABILITY = "game_availability";// tinyint(2) NOT NULL DEFAULT '0' COMMENT 'The author of the game can decide that, this game should FREE or PAID or SUBSCRIBED (mo / yr / per game round ) ie. 0, 1, 2, 3, 4 respectively. Default : free',
	public static final String KEY_GAME_PRICE_CURRENCY = "game_price_currency";
	public static final String KEY_GAME_PRICE = "game_price";// varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT 'if the game is not free, the price would be setted here.',
	public static final String KEY_GAME_ACTIVE_STATUS = "game_active_status";// tinyint(1) NOT NULL DEFAULT '0' COMMENT 'So the active status would define if the game author still wants this game to available to users. So during publishing of the game, this can only be changed from 0 to 1 by pressing the "publish" button and confirming to make the game live and active. And the author can deactivate is anytime and all players currently would be alerted and the game would cease to be playable.',
	public static final String KEY_GAME_IMG = "game_img";// varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
	public static final String KEY_GAME_AUTO_EXPLAIN = "game_auto_explain";// tinyint(1) NOT NULL DEFAULT '1',
	public static final String KEY_GAME_TIMED = "game_timed";// tinyint(1) NOT NULL DEFAULT '0',
	public static final String KEY_GAME_TIMED_MIN = "game_timed_min";// varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
	public static final String KEY_GAME_PAUSABLE = "game_pausable";// tinyint(1) NOT NULL DEFAULT '1',
	public static final String KEY_GAME_REPLAYABLE = "game_replayable";//tinyint(1) NOT NULL DEFAULT '1',
	public static final String KEY_GAME_INCLUSIVITY_BY_MOBILE = "game_inclusivity_by_mobile";// text COLLATE utf8mb4_unicode_ci COMMENT  to play select it and play it.',
	public static final String KEY_GAME_EXCLUSIVITY_BY_MOBILE = "game_exclusivity_by_mobile";// text COLLATE utf8mb4_unicode_ci COMMENT 'the game author can add a player''s mobile number ie. country code + sim number to prevent that player from ever playing this game.',
	public static final String KEY_TIMESTAMP = "time_stamp";// timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	
	
	public static final int KEY_ROWID_pos = 0;
	public static final int KEY_GAME_HEAD_ID_pos = 1;
	public static final int KEY_SIM_NUM_pos = 2;
	public static final int KEY_GAME_CODE_pos = 3;
	public static final int KEY_GAME_NAME_pos =  4;
	public static final int KEY_GAME_DESCRIPTION_pos = 5;
	public static final int KEY_GAME_RULES_pos = 6;
	public static final int KEY_GAME_INTERESTS_pos = 7;
	public static final int KEY_GAME_COUNTRIES_pos = 8;
	public static final int KEY_GAME_AGE_RANGE_pos = 9;
	public static final int KEY_GAME_GENDER_pos = 10;
	public static final int KEY_GAME_PLAYABILITY_DEPENDENCIES_pos = 11;
	public static final int KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_pos = 12;
	public static final int KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_pos = 13;
	public static final int KEY_GAME_AVAILABILITY_pos = 14;
	public static final int KEY_GAME_PRICE_CURRENCY_pos = 15;
	public static final int KEY_GAME_PRICE_pos = 16;
	public static final int KEY_GAME_ACTIVE_STATUS_pos = 17;
	public static final int KEY_GAME_IMG_pos = 18;
	public static final int KEY_GAME_AUTO_EXPLAIN_pos = 19;
	public static final int KEY_GAME_TIMED_pos = 20;
	public static final int KEY_GAME_TIMED_MIN_pos = 21;
	public static final int KEY_GAME_PAUSABLE_pos =  22;
	public static final int KEY_GAME_REPLAYABLE_pos = 23;
	public static final int KEY_GAME_INCLUSIVITY_BY_MOBILE_pos = 24;
	public static final int KEY_GAME_EXCLUSIVITY_BY_MOBILE_pos = 25;
	public static final int KEY_TIMESTAMP_pos = 26;
	
	public static final String START_TBL_CREATION = "CREATE TABLE IF NOT EXISTS "+A.DATABASE_TABLE_game_head_tbl+" ("+KEY_ROWID+" integer primary key autoincrement, ";
	
	public static final String [] TABLE_COLUMNS_TO_BE_CREATED = new String [] {"",
		KEY_GAME_HEAD_ID+" text not null,",
		KEY_SIM_NUM+" text not null,",
		KEY_GAME_CODE+" text not null,",
		KEY_GAME_NAME+" text not null,",
		KEY_GAME_DESCRIPTION+" text not null,",
		KEY_GAME_RULES+" text not null,",
		KEY_GAME_INTERESTS+" text not null,",
		KEY_GAME_COUNTRIES+" text not null,",
		KEY_GAME_AGE_RANGE+" text not null,",
		KEY_GAME_GENDER+" text not null,",
		KEY_GAME_PLAYABILITY_DEPENDENCIES+" text not null,",
		KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES+" text not null,",
		KEY_GAME_PLAYABILITY_RANK_DEPENDANCY+" text not null,",
		KEY_GAME_AVAILABILITY+" text not null,",
		KEY_GAME_PRICE_CURRENCY+" text not null,",
		KEY_GAME_PRICE+" text not null,",
		KEY_GAME_ACTIVE_STATUS+" text not null,",
		KEY_GAME_IMG+" text not null,",
		KEY_GAME_AUTO_EXPLAIN+" text not null,",
		KEY_GAME_TIMED+" text not null,",
		KEY_GAME_TIMED_MIN+" text not null,",
		KEY_GAME_PAUSABLE+" text not null,",
		KEY_GAME_REPLAYABLE+" text not null,",
		KEY_GAME_INCLUSIVITY_BY_MOBILE+" text not null,",
		KEY_GAME_EXCLUSIVITY_BY_MOBILE+" text not null,",
		KEY_TIMESTAMP+" text not null"
	};
	
	public static final String END_TBL_CREATION = ");";
	
	private static final String DATABASE_CREATE_game_head_tbl = START_TBL_CREATION
			
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_HEAD_ID_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_SIM_NUM_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_CODE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_NAME_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_DESCRIPTION_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_RULES_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_INTERESTS_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_COUNTRIES_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_AGE_RANGE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_GENDER_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_PLAYABILITY_DEPENDENCIES_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_AVAILABILITY_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_PRICE_CURRENCY_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_PRICE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_ACTIVE_STATUS_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_IMG_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_AUTO_EXPLAIN_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_TIMED_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_TIMED_MIN_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_PAUSABLE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_REPLAYABLE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_INCLUSIVITY_BY_MOBILE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_GAME_EXCLUSIVITY_BY_MOBILE_pos]+" "
		+ TABLE_COLUMNS_TO_BE_CREATED[KEY_TIMESTAMP_pos]
			
	+ END_TBL_CREATION;
	
	private final Context context;
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	private static boolean DEV_MODE;
	public DBAdapter_Game_Head (Context ctx){
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);//there would be an error initially but just keep going...
		DEV_MODE = Get.DEV_MODE(ctx);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper{//after importing for "SQLiteOpenHelper", Add unimplemented methods
		
		DatabaseHelper(Context context){
			super (context, A.DATABASE_NAME_game_head_db, null, Get.db_version_current_game_head(context));
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			try{
				if(DEV_MODE){
					Log.d("Game Head database creation script : ",DATABASE_CREATE_game_head_tbl);
				}
				db.execSQL(DATABASE_CREATE_game_head_tbl);
			}catch(SQLException e){
				if(DEV_MODE){e.printStackTrace();}
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if(DEV_MODE){
			Log.w(TAG, "Upgrading database from version "+ oldVersion + " to " + newVersion + ", which will destroy all old data");}
			if(newVersion>oldVersion){
				db.execSQL("DROP TABLE IF EXISTS "+A.DATABASE_TABLE_game_head_tbl);
				onCreate(db);
			}else{
				if(DEV_MODE){
					Log.w("DB Upgrade of "+A.DATABASE_TABLE_game_head_tbl,"from "+Integer.toString(oldVersion)+" to "+Integer.toString(newVersion)+": NOT ALLOWED");
				}
			}
		}
	}
	
	//updgrade the database
		public void upgrade(int oldVersion, int newVersion){
			DBHelper.onUpgrade(db, oldVersion, newVersion);
		}
	
	//opens the database
	public DBAdapter_Game_Head open() throws SQLiteException{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	//closes the database
	public void close(){
		DBHelper.close();
	}
	
	//insert a Message_Item into the database
	public long insertGameHead_Item(
			String KEY_GAME_HEAD_ID_str,
			String KEY_SIM_NUM_str,
			String KEY_GAME_CODE_str,
			String KEY_GAME_NAME_str,
			String KEY_GAME_DESCRIPTION_str,
			String KEY_GAME_RULES_str,
			String KEY_GAME_INTERESTS_str,
			String KEY_GAME_COUNTRIES_str,
			String KEY_GAME_AGE_RANGE_str,
			String KEY_GAME_GENDER_str,
			String KEY_GAME_PLAYABILITY_DEPENDENCIES_str,
			String KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str,
			String KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str,
			String KEY_GAME_AVAILABILITY_str,
			String KEY_GAME_PRICE_CURRENCY_str,
			String KEY_GAME_PRICE_str,
			String KEY_GAME_ACTIVE_STATUS_str,
			String KEY_GAME_IMG_str,
			String KEY_GAME_AUTO_EXPLAIN_str,
			String KEY_GAME_TIMED_str,
			String KEY_GAME_TIMED_MIN_str,
			String KEY_GAME_PAUSABLE_str,
			String KEY_GAME_REPLAYABLE_str,
			String KEY_GAME_INCLUSIVITY_BY_MOBILE_str,
			String KEY_GAME_EXCLUSIVITY_BY_MOBILE_str,
			String KEY_TIMESTAMP_str
			){
		ContentValues initialValues = new ContentValues();
		
		initialValues.put(KEY_GAME_HEAD_ID, KEY_GAME_HEAD_ID_str);
		initialValues.put(KEY_SIM_NUM, KEY_SIM_NUM_str);
		initialValues.put(KEY_GAME_CODE, KEY_GAME_CODE_str);
		initialValues.put(KEY_GAME_NAME, KEY_GAME_NAME_str);
		initialValues.put(KEY_GAME_DESCRIPTION, KEY_GAME_DESCRIPTION_str);
		initialValues.put(KEY_GAME_RULES, KEY_GAME_RULES_str);
		initialValues.put(KEY_GAME_INTERESTS, KEY_GAME_INTERESTS_str);
		initialValues.put(KEY_GAME_COUNTRIES, KEY_GAME_COUNTRIES_str);
		initialValues.put(KEY_GAME_AGE_RANGE, KEY_GAME_AGE_RANGE_str);
		initialValues.put(KEY_GAME_GENDER, KEY_GAME_GENDER_str);
		initialValues.put(KEY_GAME_PLAYABILITY_DEPENDENCIES, KEY_GAME_PLAYABILITY_DEPENDENCIES_str);
		initialValues.put(KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES, KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str);
		initialValues.put(KEY_GAME_PLAYABILITY_RANK_DEPENDANCY, KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str);
		initialValues.put(KEY_GAME_AVAILABILITY, KEY_GAME_AVAILABILITY_str);
		initialValues.put(KEY_GAME_PRICE_CURRENCY, KEY_GAME_PRICE_CURRENCY_str);
		initialValues.put(KEY_GAME_PRICE, KEY_GAME_PRICE_str);
		initialValues.put(KEY_GAME_ACTIVE_STATUS, KEY_GAME_ACTIVE_STATUS_str);
		initialValues.put(KEY_GAME_IMG, KEY_GAME_IMG_str);
		initialValues.put(KEY_GAME_AUTO_EXPLAIN, KEY_GAME_AUTO_EXPLAIN_str);
		initialValues.put(KEY_GAME_TIMED, KEY_GAME_TIMED_str);
		initialValues.put(KEY_GAME_TIMED_MIN, KEY_GAME_TIMED_MIN_str);
		initialValues.put(KEY_GAME_PAUSABLE, KEY_GAME_PAUSABLE_str);
		initialValues.put(KEY_GAME_REPLAYABLE, KEY_GAME_REPLAYABLE_str);
		initialValues.put(KEY_GAME_INCLUSIVITY_BY_MOBILE, KEY_GAME_INCLUSIVITY_BY_MOBILE_str);
		initialValues.put(KEY_GAME_EXCLUSIVITY_BY_MOBILE, KEY_GAME_EXCLUSIVITY_BY_MOBILE_str);
		initialValues.put(KEY_TIMESTAMP, KEY_TIMESTAMP_str);
		
		return db.insert(A.DATABASE_TABLE_game_head_tbl, null, initialValues);
		
	}
	
	//deletes a particular Message_Item
	public boolean deleteGameHeads_Item(long rowId){
		String whereClause = KEY_ROWID + "=" + rowId;
		String[] whereArgs = null;
		return db.delete(A.DATABASE_TABLE_game_head_tbl, whereClause, whereArgs) > 0;
	}
	
	
	//retrieves all the Message_Items
	public Cursor getAllGameHeads_Items(){
		String[] columns = new String[]{KEY_ROWID,
				KEY_GAME_HEAD_ID,
				KEY_SIM_NUM,
				KEY_GAME_CODE,
				KEY_GAME_NAME,
				KEY_GAME_DESCRIPTION,
				KEY_GAME_RULES,
				KEY_GAME_INTERESTS,
				KEY_GAME_COUNTRIES,
				KEY_GAME_AGE_RANGE,
				KEY_GAME_GENDER,
				KEY_GAME_PLAYABILITY_DEPENDENCIES,
				KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES,
				KEY_GAME_PLAYABILITY_RANK_DEPENDANCY,
				KEY_GAME_AVAILABILITY,
				KEY_GAME_PRICE_CURRENCY,
				KEY_GAME_PRICE,
				KEY_GAME_ACTIVE_STATUS,
				
				KEY_GAME_IMG,
				KEY_GAME_AUTO_EXPLAIN,
				KEY_GAME_TIMED,
				KEY_GAME_TIMED_MIN,
				KEY_GAME_PAUSABLE,
				KEY_GAME_REPLAYABLE,
				KEY_GAME_INCLUSIVITY_BY_MOBILE,
				KEY_GAME_EXCLUSIVITY_BY_MOBILE,
				KEY_TIMESTAMP};
	
		String selection = null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		return db.query(A.DATABASE_TABLE_game_head_tbl, columns, selection, selectionArgs, groupBy, having, orderBy);
	}
	
	//retrieve a particular Message_Item with ID as input
	public Cursor getGameHead_Item_with_ID(long rowId) throws SQLException {
		boolean distinct = true;
		String table = A.DATABASE_TABLE_game_head_tbl;
		String [] columns = new String []{KEY_ROWID,
				KEY_GAME_HEAD_ID,
				KEY_SIM_NUM,
				KEY_GAME_CODE,
				KEY_GAME_NAME,
				KEY_GAME_DESCRIPTION,
				KEY_GAME_RULES,
				KEY_GAME_INTERESTS,
				KEY_GAME_COUNTRIES,
				KEY_GAME_AGE_RANGE,
				KEY_GAME_GENDER,
				KEY_GAME_PLAYABILITY_DEPENDENCIES,
				KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES,
				KEY_GAME_PLAYABILITY_RANK_DEPENDANCY,
				KEY_GAME_AVAILABILITY,
				KEY_GAME_PRICE_CURRENCY,
				KEY_GAME_PRICE,
				KEY_GAME_ACTIVE_STATUS,
				KEY_GAME_IMG,
				KEY_GAME_AUTO_EXPLAIN,
				KEY_GAME_TIMED,
				KEY_GAME_TIMED_MIN,
				KEY_GAME_PAUSABLE,
				KEY_GAME_REPLAYABLE,
				KEY_GAME_INCLUSIVITY_BY_MOBILE,
				KEY_GAME_EXCLUSIVITY_BY_MOBILE,
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
	
	
	//retrieve a particular Message_Item with ID as input
		public Cursor getGameHead_Item_with_GAME_CODE(String game_code_str) throws SQLException {
			boolean distinct = true;
			String table = A.DATABASE_TABLE_game_head_tbl;
			String [] columns = new String []{KEY_ROWID,
					KEY_GAME_HEAD_ID,
					KEY_SIM_NUM,
					KEY_GAME_CODE,
					KEY_GAME_NAME,
					KEY_GAME_DESCRIPTION,
					KEY_GAME_RULES,
					KEY_GAME_INTERESTS,
					KEY_GAME_COUNTRIES,
					KEY_GAME_AGE_RANGE,
					KEY_GAME_GENDER,
					KEY_GAME_PLAYABILITY_DEPENDENCIES,
					KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES,
					KEY_GAME_PLAYABILITY_RANK_DEPENDANCY,
					KEY_GAME_AVAILABILITY,
					KEY_GAME_PRICE_CURRENCY,
					KEY_GAME_PRICE,
					KEY_GAME_ACTIVE_STATUS,
					KEY_GAME_IMG,
					KEY_GAME_AUTO_EXPLAIN,
					KEY_GAME_TIMED,
					KEY_GAME_TIMED_MIN,
					KEY_GAME_PAUSABLE,
					KEY_GAME_REPLAYABLE,
					KEY_GAME_INCLUSIVITY_BY_MOBILE,
					KEY_GAME_EXCLUSIVITY_BY_MOBILE,
					KEY_TIMESTAMP};
			
			String selection = KEY_GAME_CODE + " LIKE " + "\'"+game_code_str+"\'";
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
	public boolean updateGameHead_Item(long rowId,
			String KEY_GAME_HEAD_ID_str,
			String KEY_SIM_NUM_str,
			String KEY_GAME_CODE_str,
			String KEY_GAME_NAME_str,
			String KEY_GAME_DESCRIPTION_str,
			String KEY_GAME_RULES_str,
			String KEY_GAME_INTERESTS_str,
			String KEY_GAME_COUNTRIES_str,
			String KEY_GAME_AGE_RANGE_str,
			String KEY_GAME_GENDER_str,
			String KEY_GAME_PLAYABILITY_DEPENDENCIES_str,
			String KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str,
			String KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str,
			String KEY_GAME_AVAILABILITY_str,
			String KEY_GAME_PRICE_CURRENCY_str,
			String KEY_GAME_PRICE_str,
			String KEY_GAME_ACTIVE_STATUS_str,
			String KEY_GAME_IMG_str,
			String KEY_GAME_AUTO_EXPLAIN_str,
			String KEY_GAME_TIMED_str,
			String KEY_GAME_TIMED_MIN_str,
			String KEY_GAME_PAUSABLE_str,
			String KEY_GAME_REPLAYABLE_str,
			String KEY_GAME_INCLUSIVITY_BY_MOBILE_str,
			String KEY_GAME_EXCLUSIVITY_BY_MOBILE_str,
			String KEY_TIMESTAMP_str
			){
		ContentValues args = new ContentValues();
		
		args.put(KEY_GAME_HEAD_ID, KEY_GAME_HEAD_ID_str);
		args.put(KEY_SIM_NUM, KEY_SIM_NUM_str);
		args.put(KEY_GAME_CODE, KEY_GAME_CODE_str);
		args.put(KEY_GAME_NAME, KEY_GAME_NAME_str);
		args.put(KEY_GAME_DESCRIPTION, KEY_GAME_DESCRIPTION_str);
		args.put(KEY_GAME_RULES, KEY_GAME_RULES_str);
		args.put(KEY_GAME_INTERESTS, KEY_GAME_INTERESTS_str);
		args.put(KEY_GAME_COUNTRIES, KEY_GAME_COUNTRIES_str);
		args.put(KEY_GAME_AGE_RANGE, KEY_GAME_AGE_RANGE_str);
		args.put(KEY_GAME_GENDER, KEY_GAME_GENDER_str);
		args.put(KEY_GAME_PLAYABILITY_DEPENDENCIES, KEY_GAME_PLAYABILITY_DEPENDENCIES_str);
		args.put(KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES, KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str);
		args.put(KEY_GAME_PLAYABILITY_RANK_DEPENDANCY, KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str);
		args.put(KEY_GAME_AVAILABILITY, KEY_GAME_AVAILABILITY_str);
		args.put(KEY_GAME_PRICE_CURRENCY, KEY_GAME_PRICE_CURRENCY_str);
		args.put(KEY_GAME_PRICE, KEY_GAME_PRICE_str);
		args.put(KEY_GAME_ACTIVE_STATUS, KEY_GAME_ACTIVE_STATUS_str);
		args.put(KEY_GAME_IMG, KEY_GAME_IMG_str);
		args.put(KEY_GAME_AUTO_EXPLAIN, KEY_GAME_AUTO_EXPLAIN_str);
		args.put(KEY_GAME_TIMED, KEY_GAME_TIMED_str);
		args.put(KEY_GAME_TIMED_MIN, KEY_GAME_TIMED_MIN_str);
		args.put(KEY_GAME_PAUSABLE, KEY_GAME_PAUSABLE_str);
		args.put(KEY_GAME_REPLAYABLE, KEY_GAME_REPLAYABLE_str);
		args.put(KEY_GAME_INCLUSIVITY_BY_MOBILE, KEY_GAME_INCLUSIVITY_BY_MOBILE_str);
		args.put(KEY_GAME_EXCLUSIVITY_BY_MOBILE, KEY_GAME_EXCLUSIVITY_BY_MOBILE_str);
		args.put(KEY_TIMESTAMP, KEY_TIMESTAMP_str);
		
		String table = A.DATABASE_TABLE_game_head_tbl;
		ContentValues values = args;
		String whereClause = KEY_ROWID + "=" + rowId;
		String []whereArgs = null;
		
		return db.update(table, values, whereClause, whereArgs) > 0;
	}
	
	public boolean updateGameHead_with_local_id_forSpecificColumn(long rowId, String key_col_nm_str, String value_for_col_str){
		ContentValues args = new ContentValues();
		
		args.put(key_col_nm_str, value_for_col_str);
		
		String table = A.DATABASE_TABLE_game_head_tbl;
		ContentValues values = args;
		String whereClause = KEY_ROWID + "=" + rowId;
		String []whereArgs = null;
		
		return db.update(table, values, whereClause, whereArgs) > 0;
	}
	
	
}
