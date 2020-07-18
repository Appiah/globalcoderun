package com.aireceive.android.edureceive;
	

import com.aireceive.android.A;
import com.aireceive.android.Get;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.aireceive.android.R;
import com.aireceive.android.connectnetwork.JSONParserHTTP;
import com.aireceive.android.unlock_app_n_features.DBAdapter_App;


public class Activity_GameCenter extends ListActivity {

	SharedPreferences prefs_user_data;
	
	Context context;
	
	ImageView bk, app_logo, sub_app_logo_iv;
    TextView app_nm_tv, sub_app_nm_tv;
	
    public DBAdapter_Game_Head db_game_head;
    
    ArrayList<HashMap<String, String>> game_head_arrayListHashmap;
    
    public AdapterForGamesCenter adapter;
    
    public static Handler load_Game_Content_Handler;
    
    protected DBAdapter_App db_app_data;
    
	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_list_search_layout);
		
		prefs_user_data = getSharedPreferences(A.PREFS_USER_DATA, MODE_PRIVATE);
		
		context = Activity_GameCenter.this;
		
		db_app_data = new DBAdapter_App(context);
		
		//IMAGEVIEWS REFERENCING
		bk = (ImageView)findViewById(R.id.back_img_lnk );
		app_logo = (ImageView)findViewById(R.id.app_logo_iv);
		sub_app_logo_iv = (ImageView)findViewById(R.id.sub_app_logo_img);
		
		//TEXTVIEWS REFERENCING
		app_nm_tv = (TextView)findViewById(R.id.app_name_txt);
		sub_app_nm_tv = (TextView)findViewById(R.id.sub_app_nm_txt);
		
		
		//Set the text for the app name
		app_nm_tv.setText(A.getResTxt(getApplicationContext(), R.string.games));
		sub_app_nm_tv.setText(A.getResTxt(getApplicationContext(), R.string.select) +" "+ A.getResTxt(getApplicationContext(), R.string.welcome));
		//User the AppTip element to tell user/player about current game / quick quiz / opinion pull / election / choice selection / 
		
		db_game_head = new DBAdapter_Game_Head(context);
		
		game_head_arrayListHashmap = new ArrayList<HashMap<String, String>>();
		
		load_Game_Content_Handler = new Handler(){
	   	    public void handleMessage(Message paramAnonymousMessage){
	   	    	String tapped_game_code_str = (String) paramAnonymousMessage.obj;
	   	    	
	   	    	Intent game_on_intent = new Intent(Activity_GameCenter.this, Activity_GameOn.class);
	   	    	game_on_intent.putExtra(DBAdapter_Game_Head.KEY_GAME_CODE, tapped_game_code_str);
	   	    	startActivity(game_on_intent);
	   	    	
	   	    }
	   	};
		
		
		
		ListView lv = getListView();
		
		if(A.hasConnection(context)){
			try{
				FetchGameHead fetchGameHeadAndContentTask;
				fetchGameHeadAndContentTask = new FetchGameHead();
				fetchGameHeadAndContentTask.execute();
			}catch(Exception e){
				if(Get.DEV_MODE(context)){
					e.printStackTrace();
				}
			}
		}else{
			A.showToastr(context, "Pls. Check Internet Connection");
			
			db_game_head.open();
				Cursor c_01 = db_game_head.getAllGameHeads_Items();
			
			
			if(c_01.getCount()>=1){
				loadGameHeads();
			}else{
				//Show an alertDialog : Help user to turn on internet quicker
				A.showToastr(context, "Show alertDialog to turn on the internet.");
			}
			
			db_game_head.close();
		}
		
	}
	
	//SERVER STUFF
  	public ProgressDialog pDialog;
  	
  	JSONParserHTTP jParser = new JSONParserHTTP();
  	
  	JSONObject c = new JSONObject();
  	
  	JSONObject game_data_json;
  	
  	// products JSONArray
  	JSONArray game_head_Arr = null;
  	JSONObject game_head_Obj = null;
	
  	public int success;
  	
	//Save Interest as string into the db
    class FetchGameHead extends AsyncTask<String, String, String>{//This is to be call when sending the cell number to the server

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pDialog = new ProgressDialog(Activity_GameCenter.this);
			pDialog.setMessage(getResources().getString(R.string.loading));
			pDialog.setIndeterminate(true);
			pDialog.setCancelable(false);
			pDialog.show();
			
		}
		
		@Override
		protected String doInBackground(String... args){
		
			String user_sim_id_str, user_sim_num_str;
			
			user_sim_id_str = Get.user_sim_id(context);
			
			user_sim_num_str = Get.user_gsm_num(context);
			
			if(Get.DEV_MODE(context)){
			//Log info :
			Log.d("TO SERVER - USER SIM ID: ", user_sim_id_str);
			}
			//Start parameterisations
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			//Let the pairing begin
			params.add(new BasicNameValuePair(A.USER_SIM_NUM_ID, user_sim_id_str));
			params.add(new BasicNameValuePair(A.USER_SIM_NUM, user_sim_num_str));
			try{
				game_data_json = jParser.makeHttpRequest(context, A.URL_FETCH_GAME_HEAD,
						A.returnHTTP_REQ_Path( db_app_data,A.URL_FETCH_GAME_HEAD), A.JSON_STATIC_GET, params);//so we would decide on how to save the interest in the php file
			}catch(Exception e){
				if(Get.DEV_MODE(context)){
					Log.e("Error : jParser-ing : ", e.toString());
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			
			super.onPostExecute(result);
			
			pDialog.dismiss();
			
			if(!pDialog.isShowing()){//Progress dialog cleared
			 if(game_data_json!=null){
/*
			"{
				 "game_head":
				 [
					 {
						 "game_head":{
							 "id":"1",
							 "sim_num":"233242550332",
							 "game_code":"jv01",
							 "game_name":"Java Quick Game",
							 "game_description":"A very simple set of questions in java as a game form. Play And be smart in java",
							 "game_rules":"Simply tap on the correct answer to score. No",
							 "game_interests":A.EMPTY_STR,
							 "game_countries":A.EMPTY_STR,
							 "game_age_range":A.EMPTY_STR,
							 "game_gender":"0",
							 "game_playability_dependencies":A.EMPTY_STR,
							 "game_playability_score_dependencies":A.EMPTY_STR,
							 "game_playability_rank_dependency":"00",
							 "game_availability":"0",
							 "game_price_currency":"63",
							 "game_price":"{0}",
							 "game_active_status":"0",
							 "game_img":A.EMPTY_STR,
							 "game_auto_explain":"1",
							 "game_timed":"0",
							 "game_timed_min":"0",
							 "game_pausable":"1",
							 "game_replayable":"1",
							 "game_inclusivity_by_mobile":A.EMPTY_STR,
							 "game_exclusivity_by_mobile":A.EMPTY_STR,
							 "time_stamp":"2016-06-07 16:42:00"
						 }
					 }
				 ],
				 	"success":1,
				 	"total":10
			}" 
*/
				try{
					if(Get.DEV_MODE(context)){
						Log.d("FROM SERVER Saving Game Head { H, C, U } JSON RESPONSE : ", game_data_json.toString());
					}
					if(game_data_json.has(A.TAG_SUCCESS)){
						success = game_data_json.getInt(A.TAG_SUCCESS);
						
						long rowId;
						String KEY_ROWID_str, KEY_GAME_HEAD_ID_str, KEY_SIM_NUM_str, KEY_GAME_CODE_str, KEY_GAME_NAME_str, 
						KEY_GAME_DESCRIPTION_str, KEY_GAME_RULES_str, KEY_GAME_INTERESTS_str, KEY_GAME_COUNTRIES_str, 
						KEY_GAME_AGE_RANGE_str, KEY_GAME_GENDER_str, KEY_GAME_PLAYABILITY_DEPENDENCIES_str, 
						KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str, KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str, 
						KEY_GAME_AVAILABILITY_str, KEY_GAME_PRICE_CURRENCY_str, KEY_GAME_PRICE_str, 
						KEY_GAME_ACTIVE_STATUS_str, KEY_GAME_IMG_str, KEY_GAME_AUTO_EXPLAIN_str, KEY_GAME_TIMED_str, 
						KEY_GAME_TIMED_MIN_str, KEY_GAME_PAUSABLE_str, KEY_GAME_REPLAYABLE_str, KEY_GAME_INCLUSIVITY_BY_MOBILE_str, 
						KEY_GAME_EXCLUSIVITY_BY_MOBILE_str, KEY_TIMESTAMP_str;
						
						
						game_head_Arr = game_data_json.getJSONArray(A.TAG_GAME_HEAD);
						
						for (int x = 0; x<game_head_Arr.length(); x++){
							
							game_head_Obj = game_head_Arr.getJSONObject(x).getJSONObject(A.TAG_GAME_HEAD);
							
							KEY_GAME_HEAD_ID_str = game_head_Obj.getString(A.ID);
							KEY_SIM_NUM_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_SIM_NUM);
							KEY_GAME_CODE_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_CODE);
							KEY_GAME_NAME_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_NAME);
							KEY_GAME_DESCRIPTION_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_DESCRIPTION);
							KEY_GAME_RULES_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_RULES);
							
							KEY_GAME_INTERESTS_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_INTERESTS);
							if(KEY_GAME_INTERESTS_str.equals(A.EMPTY_STR)){KEY_GAME_INTERESTS_str="-";}
							
							KEY_GAME_COUNTRIES_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_COUNTRIES);
							if(KEY_GAME_COUNTRIES_str.equals(A.EMPTY_STR)){KEY_GAME_COUNTRIES_str="-";}
							
							KEY_GAME_AGE_RANGE_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_AGE_RANGE);
							if(KEY_GAME_AGE_RANGE_str.equals(A.EMPTY_STR)){KEY_GAME_AGE_RANGE_str="-";}
							
							KEY_GAME_GENDER_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_GENDER);
							if(KEY_GAME_GENDER_str.equals(A.EMPTY_STR)){KEY_GAME_GENDER_str="0";}
							
							KEY_GAME_PLAYABILITY_DEPENDENCIES_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_DEPENDENCIES);
							if(KEY_GAME_PLAYABILITY_DEPENDENCIES_str.equals(A.EMPTY_STR)){KEY_GAME_PLAYABILITY_DEPENDENCIES_str="-";}
							
							KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES);
							if(KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str.equals(A.EMPTY_STR)){KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str="-";}
							
							KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_RANK_DEPENDANCY);
							if(KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str.equals(A.EMPTY_STR)){KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str="-";}
							
							KEY_GAME_AVAILABILITY_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_AVAILABILITY);
							if(KEY_GAME_AVAILABILITY_str.equals(A.EMPTY_STR)){KEY_GAME_AVAILABILITY_str="0";}
							
							KEY_GAME_PRICE_CURRENCY_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_PRICE_CURRENCY);
							if(KEY_GAME_PRICE_CURRENCY_str.equals(A.EMPTY_STR)){KEY_GAME_PRICE_CURRENCY_str="0";}
							
							KEY_GAME_PRICE_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_PRICE);
							if(KEY_GAME_PRICE_str.equals(A.EMPTY_STR)){KEY_GAME_PRICE_str="0";}
							
							KEY_GAME_ACTIVE_STATUS_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_ACTIVE_STATUS);
							if(KEY_GAME_ACTIVE_STATUS_str.equals(A.EMPTY_STR)){KEY_GAME_ACTIVE_STATUS_str="1";}
							
							KEY_GAME_IMG_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_IMG);
							if(KEY_GAME_IMG_str.equals(A.EMPTY_STR)){KEY_GAME_IMG_str="-";}
							
							KEY_GAME_AUTO_EXPLAIN_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_AUTO_EXPLAIN);
							if(KEY_GAME_AUTO_EXPLAIN_str.equals(A.EMPTY_STR)){KEY_GAME_AUTO_EXPLAIN_str="1";}
							
							KEY_GAME_TIMED_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_TIMED);
							if(KEY_GAME_TIMED_str.equals(A.EMPTY_STR)){KEY_GAME_TIMED_str="0";}
							
							KEY_GAME_TIMED_MIN_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_TIMED_MIN);
							if(KEY_GAME_TIMED_MIN_str.equals(A.EMPTY_STR)){KEY_GAME_TIMED_MIN_str="0";}
							
							KEY_GAME_PAUSABLE_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_PAUSABLE);
							if(KEY_GAME_PAUSABLE_str.equals(A.EMPTY_STR)){KEY_GAME_PAUSABLE_str="1";}
							
							KEY_GAME_REPLAYABLE_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_REPLAYABLE);
							if(KEY_GAME_REPLAYABLE_str.equals(A.EMPTY_STR)){KEY_GAME_REPLAYABLE_str="1";}
							
							KEY_GAME_INCLUSIVITY_BY_MOBILE_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_INCLUSIVITY_BY_MOBILE);
							if(KEY_GAME_INCLUSIVITY_BY_MOBILE_str.equals(A.EMPTY_STR)){KEY_GAME_INCLUSIVITY_BY_MOBILE_str="-";}
							
							KEY_GAME_EXCLUSIVITY_BY_MOBILE_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_GAME_EXCLUSIVITY_BY_MOBILE);
							if(KEY_GAME_EXCLUSIVITY_BY_MOBILE_str.equals(A.EMPTY_STR)){KEY_GAME_EXCLUSIVITY_BY_MOBILE_str="-";}
							
							KEY_TIMESTAMP_str = game_head_Obj.getString(DBAdapter_Game_Head.KEY_TIMESTAMP);
							
							//Check if this game_head exist or not
							db_game_head.open();
							
							Cursor c_all = db_game_head.getAllGameHeads_Items();
							
							if(c_all.getCount()>=1){
								
								Cursor c = db_game_head.getGameHead_Item_with_GAME_CODE(KEY_GAME_CODE_str);
								if(c.getCount()>=1){
									
									//get the local "long" sqlite row id for this game head
									if(c.moveToPosition(0)){//checking to be sure that the cursor did move to the very first position
										
										rowId = Long.parseLong(c.getString(DBAdapter_Game_Head.KEY_ROWID_pos));
										
										//game head already exist : pls UPDATE game head
										db_game_head.updateGameHead_Item(rowId, KEY_GAME_HEAD_ID_str, KEY_SIM_NUM_str, KEY_GAME_CODE_str, KEY_GAME_NAME_str, KEY_GAME_DESCRIPTION_str,
												KEY_GAME_RULES_str, KEY_GAME_INTERESTS_str, KEY_GAME_COUNTRIES_str, KEY_GAME_AGE_RANGE_str, KEY_GAME_GENDER_str, KEY_GAME_PLAYABILITY_DEPENDENCIES_str, 
												KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str, KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str, KEY_GAME_AVAILABILITY_str, KEY_GAME_PRICE_CURRENCY_str, 
												KEY_GAME_PRICE_str, KEY_GAME_ACTIVE_STATUS_str, KEY_GAME_IMG_str, KEY_GAME_AUTO_EXPLAIN_str, KEY_GAME_TIMED_str, KEY_GAME_TIMED_MIN_str, 
												KEY_GAME_PAUSABLE_str, KEY_GAME_REPLAYABLE_str, KEY_GAME_INCLUSIVITY_BY_MOBILE_str, KEY_GAME_EXCLUSIVITY_BY_MOBILE_str, KEY_TIMESTAMP_str);
										
									}
									
								}else{
									
									//game head does not exist : pls INSERT game head
									db_game_head.insertGameHead_Item(KEY_GAME_HEAD_ID_str, KEY_SIM_NUM_str, KEY_GAME_CODE_str, KEY_GAME_NAME_str, KEY_GAME_DESCRIPTION_str, 
											KEY_GAME_RULES_str, KEY_GAME_INTERESTS_str, KEY_GAME_COUNTRIES_str, KEY_GAME_AGE_RANGE_str, KEY_GAME_GENDER_str, KEY_GAME_PLAYABILITY_DEPENDENCIES_str, 
											KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str, KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str, KEY_GAME_AVAILABILITY_str, KEY_GAME_PRICE_CURRENCY_str, 
											KEY_GAME_PRICE_str, KEY_GAME_ACTIVE_STATUS_str, KEY_GAME_IMG_str, KEY_GAME_AUTO_EXPLAIN_str, KEY_GAME_TIMED_str, KEY_GAME_TIMED_MIN_str, 
											KEY_GAME_PAUSABLE_str, KEY_GAME_REPLAYABLE_str, KEY_GAME_INCLUSIVITY_BY_MOBILE_str, KEY_GAME_EXCLUSIVITY_BY_MOBILE_str, KEY_TIMESTAMP_str);
									
								}
							}else{
								//game head does not exist : pls INSERT game head
								db_game_head.insertGameHead_Item(KEY_GAME_HEAD_ID_str, KEY_SIM_NUM_str, KEY_GAME_CODE_str, KEY_GAME_NAME_str, KEY_GAME_DESCRIPTION_str, 
										KEY_GAME_RULES_str, KEY_GAME_INTERESTS_str, KEY_GAME_COUNTRIES_str, KEY_GAME_AGE_RANGE_str, KEY_GAME_GENDER_str, KEY_GAME_PLAYABILITY_DEPENDENCIES_str, 
										KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str, KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str, KEY_GAME_AVAILABILITY_str, KEY_GAME_PRICE_CURRENCY_str, 
										KEY_GAME_PRICE_str, KEY_GAME_ACTIVE_STATUS_str, KEY_GAME_IMG_str, KEY_GAME_AUTO_EXPLAIN_str, KEY_GAME_TIMED_str, KEY_GAME_TIMED_MIN_str, 
										KEY_GAME_PAUSABLE_str, KEY_GAME_REPLAYABLE_str, KEY_GAME_INCLUSIVITY_BY_MOBILE_str, KEY_GAME_EXCLUSIVITY_BY_MOBILE_str, KEY_TIMESTAMP_str);
							}
							db_game_head.close();
							
						}
						
						
						getJSON_of_GameHeads();
						
						//Now set the list items
						setListItems();
						
						if(Get.DEV_MODE(context)){
							//Log success val
							Log.d("FROM SERVER SAVING GAME HEAD, CONTENT & USER DATA SUCCESS VALUE : ", Integer.toString(success));
						}
					}
					
				}catch(JSONException e){
					if(Get.DEV_MODE(context)){
						e.printStackTrace();
					}
				}
			}else{//EMPTY "game_data_json"
				
			}
		  }//End of isShowing()	
		}

	}
	
    protected void getJSON_of_GameHeads() {
		String KEY_ROWID_str;
		String KEY_GAME_HEAD_ID_str;
		String KEY_SIM_NUM_str;
		String KEY_GAME_CODE_str;
		String KEY_GAME_NAME_str;
		String KEY_GAME_DESCRIPTION_str;
		String KEY_GAME_RULES_str;
		String KEY_GAME_INTERESTS_str;
		String KEY_GAME_COUNTRIES_str;
		String KEY_GAME_AGE_RANGE_str;
		String KEY_GAME_GENDER_str;
		String KEY_GAME_PLAYABILITY_DEPENDENCIES_str;
		String KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str;
		String KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str;
		String KEY_GAME_AVAILABILITY_str;
		String KEY_GAME_PRICE_CURRENCY_str;
		String KEY_GAME_PRICE_str;
		String KEY_GAME_ACTIVE_STATUS_str;
		String KEY_GAME_IMG_str;
		String KEY_GAME_AUTO_EXPLAIN_str;
		String KEY_GAME_TIMED_str;
		String KEY_GAME_TIMED_MIN_str;
		String KEY_GAME_PAUSABLE_str;
		String KEY_GAME_REPLAYABLE_str;
		String KEY_GAME_INCLUSIVITY_BY_MOBILE_str;
		String KEY_GAME_EXCLUSIVITY_BY_MOBILE_str;
		String KEY_TIMESTAMP_str;
		if(game_head_arrayListHashmap!=null && game_head_arrayListHashmap.size()>0){
			game_head_arrayListHashmap.clear();
		}
		
		db_game_head.open();
		Cursor c_01 = db_game_head.getAllGameHeads_Items();
		for(int i=0; i<c_01.getCount(); i++){
			
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			
			if(c_01.moveToPosition(i)){
				
				KEY_ROWID_str = c_01.getString(DBAdapter_Game_Head.KEY_ROWID_pos);
				KEY_GAME_HEAD_ID_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_HEAD_ID_pos);
				KEY_SIM_NUM_str = c_01.getString(DBAdapter_Game_Head.KEY_SIM_NUM_pos);
				KEY_GAME_CODE_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_CODE_pos);
				KEY_GAME_NAME_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_NAME_pos);
				KEY_GAME_DESCRIPTION_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_DESCRIPTION_pos);
				KEY_GAME_RULES_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_RULES_pos);
				KEY_GAME_INTERESTS_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_INTERESTS_pos);
				KEY_GAME_COUNTRIES_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_COUNTRIES_pos);
				KEY_GAME_AGE_RANGE_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_AGE_RANGE_pos);
				KEY_GAME_GENDER_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_GENDER_pos);
				KEY_GAME_PLAYABILITY_DEPENDENCIES_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_DEPENDENCIES_pos);
				KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_pos);
				KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_pos);
				KEY_GAME_AVAILABILITY_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_AVAILABILITY_pos);
				KEY_GAME_PRICE_CURRENCY_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_PRICE_CURRENCY_pos);
				KEY_GAME_PRICE_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_PRICE_pos);
				KEY_GAME_ACTIVE_STATUS_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_ACTIVE_STATUS_pos);
				KEY_GAME_IMG_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_IMG_pos);
				KEY_GAME_AUTO_EXPLAIN_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_AUTO_EXPLAIN_pos);
				KEY_GAME_TIMED_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_TIMED_pos);
				KEY_GAME_TIMED_MIN_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_TIMED_MIN_pos);
				KEY_GAME_PAUSABLE_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_PAUSABLE_pos);
				KEY_GAME_REPLAYABLE_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_REPLAYABLE_pos);
				KEY_GAME_INCLUSIVITY_BY_MOBILE_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_INCLUSIVITY_BY_MOBILE_pos);
				KEY_GAME_EXCLUSIVITY_BY_MOBILE_str = c_01.getString(DBAdapter_Game_Head.KEY_GAME_EXCLUSIVITY_BY_MOBILE_pos);
				KEY_TIMESTAMP_str = c_01.getString(DBAdapter_Game_Head.KEY_TIMESTAMP_pos);
				
				map.put(DBAdapter_Game_Head.KEY_ROWID, KEY_ROWID_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_HEAD_ID, KEY_GAME_HEAD_ID_str);
				map.put(DBAdapter_Game_Head.KEY_SIM_NUM, KEY_SIM_NUM_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_CODE, KEY_GAME_CODE_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_NAME, KEY_GAME_NAME_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_DESCRIPTION, KEY_GAME_DESCRIPTION_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_RULES, KEY_GAME_RULES_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_INTERESTS, KEY_GAME_INTERESTS_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_COUNTRIES, KEY_GAME_COUNTRIES_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_AGE_RANGE, KEY_GAME_AGE_RANGE_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_GENDER, KEY_GAME_GENDER_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_DEPENDENCIES, KEY_GAME_PLAYABILITY_DEPENDENCIES_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES, KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_RANK_DEPENDANCY, KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_AVAILABILITY, KEY_GAME_AVAILABILITY_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_PRICE_CURRENCY, KEY_GAME_PRICE_CURRENCY_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_PRICE, KEY_GAME_PRICE_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_ACTIVE_STATUS, KEY_GAME_ACTIVE_STATUS_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_IMG, KEY_GAME_IMG_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_AUTO_EXPLAIN, KEY_GAME_AUTO_EXPLAIN_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_TIMED, KEY_GAME_TIMED_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_TIMED_MIN, KEY_GAME_TIMED_MIN_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_PAUSABLE, KEY_GAME_PAUSABLE_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_REPLAYABLE, KEY_GAME_REPLAYABLE_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_INCLUSIVITY_BY_MOBILE, KEY_GAME_INCLUSIVITY_BY_MOBILE_str);
				map.put(DBAdapter_Game_Head.KEY_GAME_EXCLUSIVITY_BY_MOBILE, KEY_GAME_EXCLUSIVITY_BY_MOBILE_str);
				map.put(DBAdapter_Game_Head.KEY_TIMESTAMP, KEY_TIMESTAMP_str);
				
				// adding HashList to ArrayList
				game_head_arrayListHashmap.add(map);
				
			}//END of : if(c.moveToPosition(i))
			
		}//END of : for() loop
		db_game_head.close();
	}
    
    protected void setListItems() {
    	runOnUiThread(new Runnable() {
			public void run() {
				adapter = new AdapterForGamesCenter(Activity_GameCenter.this, game_head_arrayListHashmap, R.layout.game_capsule, new String[]{}, new int[]{});			
				setListAdapter(adapter);
								
			}//end of "public void run()"	
		});
    }
    
    protected void loadGameHeads(){
    	getJSON_of_GameHeads();
		
		//Now set the list items
		setListItems();
		
		adapter.notifyDataSetChanged();
		
    }
    
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	public void onContextMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onContextMenuClosed(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
