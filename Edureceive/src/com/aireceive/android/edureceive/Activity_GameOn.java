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
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.aireceive.android.R;
import com.aireceive.android.connectnetwork.JSONParserHTTP;
import com.aireceive.android.unlock_app_n_features.DBAdapter_App;

public class Activity_GameOn extends Activity{

	SharedPreferences prefs_user_data, prefs_app_tip_stage;
	
    public static final String TAG = "Game On";
	
	Context context;
	
	LinearLayout game_pb_ll, game_score_tv_iv_ll, question_n_controls_ll, game_content_sv_ll, game_content_question_sv_ll, 
	question_image_ll, opts_ll, game_opts_sv_ll;
	ProgressBar game_pb;
	
	RelativeLayout game_on_parent_rl, game_player_score_n_rank_rl, game_tr_progress_rl, game_controls_parent_rl, 
	game_controls_switch_rl, game_controls_rl, game_controls_opts_rl, game_controls_nav_rl, game_controls_aux_rl,
	game_controls_indicator_rl, games_active_hsv_rl, game_tl_bttns_rl;
	TextView game_score_tv, question_content_tv;
	ImageView game_score_iv, question_image_iv, games_active_hsv_on_iv;
	ScrollView game_content_sv, game_opts_sv;
	HorizontalScrollView games_active_hsV;
	
	/*
	 ImageView :  game_rank_{0-1}, game_tr_bttns_{0-1}, game_opt_iv_{0-3}, game_opt_brief_iv_{0-3}, 
	 ImageView : game_controls_kill_switch_iv_{0-3}, game_bttns_nav_{0-3}, game_hc_iv_{0-4}, game_hc_bttn_{0-4}
	 ImageView : game_tl_bttns_{0-2}
	 View : game_tr_level_view_{0-9}, game_page_padder_v_{0-1},  game_hsv_child_view_{0-3}
	 RelativeLayout : game_content_opts_parent_rl_{0-3}, game_hsv_child_rl_{0-4},
	 ScrollView : game_content_opts_sv_ll_{0-3}
	 LinearLayout : game_opt_brief_tv_n_iv_ll_{0-3}, game_opt_iv_ll_{0-3}, game_hsv_child_ll_{0-4}
	 TextView : game_opt_brief_{0-3}, game_opt_full_{0-3}, game_opt_{0-3}, game_bttns_opt_{0-3}, 
	 TextView : game_bttns_aux_{0-1}, game_hc_nm_tv_{0-4}, game_hc_sub_nm_tv_{0-4}
	*/
	
	public String game_rank_str = "game_rank_";
	public String game_tr_bttns_str = "game_tr_bttns_";
	public String game_opt_iv_str = "game_opt_iv_";
	public String game_opt_brief_iv_str = "game_opt_brief_iv_";
	public String game_controls_kill_switch_iv_str = "game_controls_kill_switch_iv_";
	public String game_bttns_nav_str = "game_bttns_nav_";
	public String game_hc_iv_str = "game_hc_iv_";
	public String game_hc_bttn_str = "game_hc_bttn_";
	public String game_tl_bttns_str = "game_tl_bttns_";
	public String game_tr_level_view_str = "game_tr_level_view_";
	public String game_page_padder_v_str = "game_page_padder_v_";
	public String game_hsv_child_view_str = "game_hsv_child_view_";
	public String game_content_opts_parent_rl_str = "game_content_opts_parent_rl_";
	public String game_hsv_child_rl_str = "game_hsv_child_rl_";
	public String game_content_opts_sv_ll_str = "game_content_opts_sv_ll_";
	public String game_opt_brief_tv_n_iv_ll_str = "game_opt_brief_tv_n_iv_ll_";
	public String game_opt_iv_ll_str = "game_opt_iv_ll_";
	public String game_hsv_child_ll_str = "game_hsv_child_ll_";
	public String game_opt_brief_str = "game_opt_brief_";
	public String game_opt_full_str = "game_opt_full_";
	public String game_opt_str = "game_opt_";
	public String game_bttns_opt_str = "game_bttns_opt_";
	public String game_bttns_aux_str = "game_bttns_aux_";
	public String game_hc_nm_tv_str = "game_hc_nm_tv_";
	public String game_hc_sub_nm_tv_str = "game_hc_sub_nm_tv_";
	
	public static final int TOTAL_GAME_BTTNS_OPTS = 4;
	public static final int GAME_BTTNS_OPTS_A = 0;
	public static final int GAME_BTTNS_OPTS_B = 1;
	public static final int GAME_BTTNS_OPTS_C = 2;
	public static final int GAME_BTTNS_OPTS_D = 3;
	
	public static final int TOTAL_GAME_BTTN_TLS = 3;
	public static final int GAME_BTTN_TL_BACK = 0;
	public static final int GAME_BTTN_TL_SOUND = 1;
	public static final int GAME_BTTN_TL_PAUSE = 2;
	
	public static final int TOTAL_GAME_PROGESS_VIEWS = 10;
	
	public DBAdapter_Game_Content db_game_content;
	
	 public String KEY_ROWID_str_N, KEY_GAME_CONTENT_ID_str_N, KEY_GAME_HEAD_ID_str_N,
		KEY_GAME_CODE_str_N, KEY_QUESTION_LEVEL_str_N, KEY_QUESTION_CONTENT_str_N, KEY_QUESTION_IMAGE_str_N, 
		KEY_OPT_A_str_N, KEY_OPT_A_IMAGE_str_N, KEY_OPT_A_COMMENT_str_N, KEY_OPT_A_PTS_str_N,
		KEY_OPT_B_str_N, KEY_OPT_B_IMAGE_str_N, KEY_OPT_B_COMMENT_str_N, KEY_OPT_B_PTS_str_N,
		KEY_OPT_C_str_N, KEY_OPT_C_IMAGE_str_N, KEY_OPT_C_COMMENT_str_N, KEY_OPT_C_PTS_str_N,
		KEY_OPT_D_str_N, KEY_OPT_D_IMAGE_str_N, KEY_OPT_D_COMMENT_str_N, KEY_OPT_D_PTS_str_N,
		KEY_CORRECT_ANSWER_str_N, KEY_CORRECT_ANSWER_EXPLANATION_str_N, KEY_TOTAL_TIME_PER_QUESTION_MIN_str_N,
		KEY_TIMESTAMP_str_N;
	    
	 	public int total_game_content_i;
	    public int current_game_pointer_i=0;
	    public int current_game_correct_answer_i;
	    public int current_game_score_i;//TODO : to be gotten from preference
	    
	    protected DBAdapter_App db_app_data;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_game_on);
		
  		prefs_user_data = getSharedPreferences(A.PREFS_USER_DATA, MODE_PRIVATE);
    
  		context = Activity_GameOn.this;
		
  		db_game_content = new DBAdapter_Game_Content(context);
  		db_app_data = new DBAdapter_App(context);
  		
  		game_score_tv_iv_ll = (LinearLayout)findViewById(R.id.game_score_tv_iv_ll);
  		question_n_controls_ll = (LinearLayout)findViewById(R.id.question_n_controls_ll);
  		game_content_sv_ll = (LinearLayout)findViewById(R.id.game_content_sv_ll);
  		game_content_question_sv_ll = (LinearLayout)findViewById(R.id.game_content_question_sv_ll);
  		question_image_ll = (LinearLayout)findViewById(R.id.question_image_ll);
  		opts_ll = (LinearLayout)findViewById(R.id.opts_ll);
  		game_opts_sv_ll = (LinearLayout)findViewById(R.id.game_opts_sv_ll);
  		
  		
  		//RELATIVELAYOUT
  		game_on_parent_rl = (RelativeLayout)findViewById(R.id.game_on_parent_rl);

  		game_player_score_n_rank_rl = (RelativeLayout)findViewById(R.id.game_player_score_n_rank_rl);
  		game_tr_progress_rl = (RelativeLayout)findViewById(R.id.game_tr_progress_rl);
  		game_tl_bttns_rl = (RelativeLayout)findViewById(R.id.game_tl_bttns_rl);
  		
  		
  		//TEXTVIEW
  		game_score_tv = (TextView)findViewById(R.id.game_score_tv);
  		question_content_tv = (TextView)findViewById(R.id.question_content_tv);
  		
  		
  		//IMAGEVIEW
  		game_score_iv = (ImageView)findViewById(R.id.game_score_iv);
  		question_image_iv = (ImageView)findViewById(R.id.question_image_iv);
  		
  		
  		init_LoadingPB();
  		
  		init_LHS_Controls();//soft back, sound, pause
  		
  		init_TOP_Rank_n_Score();
  		
  		init_RHS_GameProgress();
  		
  		init_GameContent();
  		
  		/*<!-- game_content_opts_parent_rl_0, 
  		 * game_content_opts_sv_ll_0, 
  		 * game_opt_brief_tv_n_iv_ll_0, 
  		 * game_opt_brief_0, game_opt_iv_ll_0, 
  		 * game_opt_iv_0, 
  		 * game_opt_full_0, 
  		 * game_opt_0, 
  		 * game_opt_brief_iv_0 
  		-->
  		*/
  		
  		for(int i=0; i<TOTAL_GAME_BTTNS_OPTS; i++){
        	final int j = i;
        
        	nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_str, i).setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					for(int x=0; x<TOTAL_GAME_BTTNS_OPTS; x++){
						if(v.getId()==nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_str, x).getId()){
						
							switch (event.getActionMasked()) {
							
								case MotionEvent.ACTION_DOWN:
								case MotionEvent.ACTION_POINTER_DOWN: {
									
									switch(x){
									case GAME_BTTNS_OPTS_A:
										//Globalz.showToastr(context, "A : Pressed");
										break;
									case GAME_BTTNS_OPTS_B:
										//Globalz.showToastr(context, "B : Pressed");							
										break;
									case GAME_BTTNS_OPTS_C:
										//Globalz.showToastr(context, "C : Pressed");
										break;
									case GAME_BTTNS_OPTS_D:
										//Globalz.showToastr(context, "D : Pressed");
										break;
									default:
										
										break;
									}
									
									break;
								}
									
								case MotionEvent.ACTION_UP:
								case MotionEvent.ACTION_POINTER_UP:{
									
									current_game_score_i = Integer.parseInt(game_score_tv.getText().toString());
									
									if(x==current_game_correct_answer_i){
										A.showToastr(context, "CORRECT");
										Log.d(TAG+" >> "+"CURRENT_GAME_SCORE_i :: ", Integer.toString(current_game_score_i));
										current_game_score_i=current_game_score_i+1;
										game_score_tv.setText(Integer.toString(current_game_score_i));
										Log.d(TAG+" >> "+"CURRENT_GAME_SCORE_i :: (_AFTER_) ", Integer.toString(current_game_score_i));
										doActionsAfterOptionSelected();
									}else{
										A.showToastr(context, "WRONG");
										doActionsAfterOptionSelected();
									}
									
									switch(x){
										case GAME_BTTNS_OPTS_A:
											//Globalz.showToastr(context, "A : Selected");
											break;
										case GAME_BTTNS_OPTS_B:
											//Globalz.showToastr(context, "B : Selected");								
											break;
										case GAME_BTTNS_OPTS_C:
											//Globalz.showToastr(context, "C : Selected");
											break;
										case GAME_BTTNS_OPTS_D:
											//Globalz.showToastr(context, "D : Selected");
											break;
										default:
											
											break;
									}
									
									break;
								}
								
							} 
						
						}
					
					}
					return true;
				}

			
			});
        	
        }//END of : for() ... loop	
  		
  		for(int i=0; i<TOTAL_GAME_BTTNS_OPTS; i++){
        	final int j = i;
        
        	nav_IV(TOTAL_GAME_BTTNS_OPTS, game_opt_brief_iv_str, i).setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					for(int x=0; x<TOTAL_GAME_BTTNS_OPTS; x++){
						if(v.getId()==nav_IV(TOTAL_GAME_BTTNS_OPTS, game_opt_brief_iv_str, x).getId()){
						
							switch (event.getActionMasked()) {
							
								case MotionEvent.ACTION_DOWN:
								case MotionEvent.ACTION_POINTER_DOWN: {
									
									switch(x){
									case GAME_BTTNS_OPTS_A:
										
										break;
									case GAME_BTTNS_OPTS_B:
																			
										break;
									case GAME_BTTNS_OPTS_C:
										
										break;
									case GAME_BTTNS_OPTS_D:
										
										break;
									default:
										
										break;
									}
									
									break;
								}
									
								case MotionEvent.ACTION_UP:
								case MotionEvent.ACTION_POINTER_UP:{
									
									
									if(nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).isShown() || nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_full_str, x).isShown() ){
										nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_full_str, x).setVisibility(View.GONE);
										nav_IV(TOTAL_GAME_BTTNS_OPTS, game_opt_brief_iv_str, x).setImageResource(R.drawable.down_0);
										
									}else{
										nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_full_str, x).setVisibility(View.VISIBLE);
										nav_IV(TOTAL_GAME_BTTNS_OPTS, game_opt_brief_iv_str, x).setImageResource(R.drawable.up_0);
										
									}
									
									switch(x){
										case GAME_BTTNS_OPTS_A:
											if(KEY_OPT_A_IMAGE_str_N.equals("-")){
												nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).setVisibility(View.GONE);
											}else{
												nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).setVisibility(View.VISIBLE);
											}
											break;
										case GAME_BTTNS_OPTS_B:
											if(KEY_OPT_B_IMAGE_str_N.equals("-")){
												nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).setVisibility(View.GONE);
											}else{
												nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).setVisibility(View.VISIBLE);
											}								
											break;
										case GAME_BTTNS_OPTS_C:
											if(KEY_OPT_C_IMAGE_str_N.equals("-")){
												nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).setVisibility(View.GONE);
											}else{
												nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).setVisibility(View.VISIBLE);
											}
											break;
										case GAME_BTTNS_OPTS_D:
											if(KEY_OPT_D_IMAGE_str_N.equals("-")){
												nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).setVisibility(View.GONE);
											}else{
												nav_LL(TOTAL_GAME_BTTNS_OPTS, game_opt_iv_ll_str, x).setVisibility(View.VISIBLE);
											}
											break;
										default:
											
											break;
									}
									
									break;
								}
								
							} 
						
						}
					
					}
					return true;
				}
			});
        	
        }//END of : for() ... loop	
  		
  		
  		//SOFT BACK
  		nav_IV(TOTAL_GAME_BTTN_TLS, game_tl_bttns_str, GAME_BTTN_TL_BACK).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				finish();
			}
		});
  		
  		//SOUND SWITCH / CONTROLS
  		nav_IV(TOTAL_GAME_BTTN_TLS, game_tl_bttns_str, GAME_BTTN_TL_SOUND).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				A.showToastr(context, ">>> CONTROL SOUND LEVELS <<<");
			}
		});
  		
  		//PAUSE GAME
  		nav_IV(TOTAL_GAME_BTTN_TLS, game_tl_bttns_str, GAME_BTTN_TL_PAUSE).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				A.showToastr(context, ">>> PAUSE <<<");
			}
		});
  		
  		
  		releazExtra();
  		
  		
  		
	}//END of : onCreate()
		
	protected void doActionsAfterOptionSelected() {
		//TODO ~ show the progress of game at the RHS
		
		//TODO : save the response locally : User_Records
		
		//TODO : show the next question/game
		db_game_content.open();
		//Get the "n-th" position of question that bears the current "game_code"
		Cursor c_02 = db_game_content.getGameContent_Item_with_GAME_CODE(game_head_code_str);
		Log.d(TAG+" >> "+"TOTAL OF GAME CONTENT with this Game Code : ["+game_head_code_str+"] ", Integer.toString(c_02.getCount()));
		if(current_game_pointer_i < c_02.getCount() ){
			db_game_content.close();
			int current_game_pointer_INCREASED_i = current_game_pointer_i+1;
			Log.d(TAG+" >> "+"CURRENT_GAME_POINTER_i ::  ", Integer.toString(current_game_pointer_i));
			
			setGameContent_Now(game_head_code_str, current_game_pointer_INCREASED_i);
		
			Log.d(TAG+" >> "+"CURRENT_GAME_POINTER_i (_AFTER_) ::  ", Integer.toString(current_game_pointer_INCREASED_i));
		}else{
			A.showToastr(context, "END OF THE GAME");
		}
		
	}
	
	private void init_LoadingPB() {
		//LINEARLAYOUT
  		game_pb_ll = (LinearLayout)findViewById(R.id.game_pb_ll);
  		//PROGRESSBAR
  		game_pb = (ProgressBar)findViewById(R.id.game_pb);
	}
	private void init_LHS_Controls() {
		// TODO Auto-generated method stub
		
	}
	private void init_TOP_Rank_n_Score() {
		// TODO Auto-generated method stub
		
	}
	private void init_RHS_GameProgress() {
		// TODO Auto-generated method stub
		
	}
	private void init_GameContent() {
		// TODO Auto-generated method stub
		
	}

	public static String game_head_code_str;
	private void releazExtra(){
		
		Intent incomingIntent = getIntent();
		if(incomingIntent.hasExtra(DBAdapter_Game_Head.KEY_GAME_CODE)){
			game_head_code_str=incomingIntent.getStringExtra(DBAdapter_Game_Head.KEY_GAME_CODE);
			
			if( (!game_head_code_str.equals(A.EMPTY_STR)) && (game_head_code_str.length()>=4) ){
				
				if(A.hasConnection(context)){
					FetchGameContent fetchGameContent;
					fetchGameContent = new FetchGameContent();
					fetchGameContent.execute();
				}else{
					
					setGameContent_Now(game_head_code_str, 0);
					
				}
				
			}else{
				finish();
			}
			
			
		}else{
			
		}

	}
	
	
	
	
	
	//SERVER STUFF
  	public ProgressDialog pDialog;
  	
  	JSONParserHTTP jParser = new JSONParserHTTP();
  	
  	JSONObject c = new JSONObject();
  	
  	JSONObject game_data_json;
  	
  	// products JSONArray
  	JSONArray game_content_Arr = null;
  	JSONObject game_content_Obj = null;
	
  	public int success;
  	
	//Save Interest as string into the db
    class FetchGameContent extends AsyncTask<String, String, String>{//This is to be call when sending the cell number to the server

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pDialog = new ProgressDialog(Activity_GameOn.this);
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
			Log.d(TAG+" >> "+"TO SERVER - USER SIM ID: ", user_sim_id_str);
			}
			//Start parameterisations
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			//Let the pairing begin
			params.add(new BasicNameValuePair(A.USER_SIM_NUM_ID, user_sim_id_str));
			params.add(new BasicNameValuePair(A.USER_SIM_NUM, user_sim_num_str));
			params.add(new BasicNameValuePair(DBAdapter_Game_Head.KEY_GAME_CODE, game_head_code_str));
			try{
				game_data_json = jParser.makeHttpRequest(context, A.URL_FETCH_GAME_CONTENT,
						A.returnHTTP_REQ_Path( db_app_data,A.URL_FETCH_GAME_CONTENT), A.JSON_STATIC_GET, params);//so we would decide on how to save the interest in the php file
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
{"game_content":
	[
		{
		
			"game_content":
				{
					"id":"1",
					"game_head_tbl_id":"1",
					"game_code":"jv01",
					"question_level":"{0}",
					"question_content":"what is a compiler ?",
					"question_image":A.EMPTY_STR,
					"opt_a":"A compiler is a unit holder that translates a low-level-language program, such as a Java program, into an equivalent high-level-language program. ",
					"opt_a_image":A.EMPTY_STR,
					"opt_a_comment":A.EMPTY_STR,
					"opt_a_pts":A.EMPTY_STR,
					"opt_b":"A compiler is a program that translates a high-level-language program, such as a Java program, into an equivalent low-level-language program. ",
					"opt_b_image":A.EMPTY_STR,
					"opt_b_comment":A.EMPTY_STR,
					"opt_b_pts":A.EMPTY_STR,
					"opt_c":"A compiler is atranslator of any language program into an equivalent high-level-language program. ",
					"opt_c_image":A.EMPTY_STR,
					"opt_c_comment":A.EMPTY_STR,
					"opt_c_pts":A.EMPTY_STR,
					"opt_d":"A compiler is a program that translates a high-level-language program, such as a Java pro- gram, into a reverse low-level-language program.",
					"opt_d_image":A.EMPTY_STR,
					"opt_d_comment":A.EMPTY_STR,
					"opt_d_pts":A.EMPTY_STR,
					"correct_answer":"{1}",
					"correct_answer_explanation":A.EMPTY_STR,
					"total_time_per_question_min":"1",
					"time_stamp":"2016-06-07 17:03:47"
				}
		
		},...n
	],

"success":1,

"total":10


} 
*/
				try{
					if(Get.DEV_MODE(context)){
						Log.d(TAG+" >> "+"FROM SERVER Saving Game Content { H, C, U } JSON RESPONSE : ", game_data_json.toString());
					}
					
					if(game_data_json.has(A.TAG_SUCCESS)){
						success = game_data_json.getInt(A.TAG_SUCCESS);
						
						long rowId;
						
						String KEY_GAME_CONTENT_ID_str, KEY_GAME_HEAD_ID_str,
						KEY_GAME_CODE_str, KEY_QUESTION_LEVEL_str, KEY_QUESTION_CONTENT_str, KEY_QUESTION_IMAGE_str, 
						KEY_OPT_A_str, KEY_OPT_A_IMAGE_str, KEY_OPT_A_COMMENT_str, KEY_OPT_A_PTS_str,
						KEY_OPT_B_str, KEY_OPT_B_IMAGE_str, KEY_OPT_B_COMMENT_str, KEY_OPT_B_PTS_str,
						KEY_OPT_C_str, KEY_OPT_C_IMAGE_str, KEY_OPT_C_COMMENT_str, KEY_OPT_C_PTS_str,
						KEY_OPT_D_str, KEY_OPT_D_IMAGE_str, KEY_OPT_D_COMMENT_str, KEY_OPT_D_PTS_str,
						KEY_CORRECT_ANSWER_str, KEY_CORRECT_ANSWER_EXPLANATION_str, KEY_TOTAL_TIME_PER_QUESTION_MIN_str,
						KEY_TIMESTAMP_str;
						
						
						game_content_Arr = game_data_json.getJSONArray(A.TAG_GAME_CONTENT);
						
						for (int x = 0; x<game_content_Arr.length(); x++){
							
							game_content_Obj = game_content_Arr.getJSONObject(x).getJSONObject(A.TAG_GAME_CONTENT);
							
							KEY_GAME_CONTENT_ID_str = game_content_Obj.getString(A.ID);
							KEY_GAME_HEAD_ID_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_GAME_HEAD_ID);
							KEY_GAME_CODE_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_GAME_CODE);
							KEY_QUESTION_LEVEL_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_QUESTION_LEVEL);
							if(KEY_QUESTION_LEVEL_str.equals(A.EMPTY_STR)){KEY_QUESTION_LEVEL_str="-";}
							
							KEY_QUESTION_CONTENT_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_QUESTION_CONTENT);
							if(KEY_QUESTION_CONTENT_str.equals(A.EMPTY_STR)){KEY_QUESTION_CONTENT_str="-";}
							
							KEY_QUESTION_IMAGE_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_QUESTION_IMAGE);
							if(KEY_QUESTION_IMAGE_str.equals(A.EMPTY_STR)){KEY_QUESTION_IMAGE_str="-";}
							//OPT_A
							KEY_OPT_A_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_A);
							if(KEY_OPT_A_str.equals(A.EMPTY_STR)){KEY_OPT_A_str="-";}
							
							KEY_OPT_A_IMAGE_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_A_IMAGE);
							if(KEY_OPT_A_IMAGE_str.equals(A.EMPTY_STR)){KEY_OPT_A_IMAGE_str="-";}
							
							KEY_OPT_A_COMMENT_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_A_COMMENT);
							if(KEY_OPT_A_COMMENT_str.equals(A.EMPTY_STR)){KEY_OPT_A_COMMENT_str="-";}
							
							KEY_OPT_A_PTS_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_A_PTS);
							if(KEY_OPT_A_PTS_str.equals(A.EMPTY_STR)){KEY_OPT_A_PTS_str="0";}
							//OPT_B
							KEY_OPT_B_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_B);
							if(KEY_OPT_B_str.equals(A.EMPTY_STR)){KEY_OPT_B_str="-";}
							
							KEY_OPT_B_IMAGE_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_B_IMAGE);
							if(KEY_OPT_B_IMAGE_str.equals(A.EMPTY_STR)){KEY_OPT_B_IMAGE_str="-";}
							
							KEY_OPT_B_COMMENT_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_B_COMMENT);
							if(KEY_OPT_B_COMMENT_str.equals(A.EMPTY_STR)){KEY_OPT_B_COMMENT_str="-";}
							
							KEY_OPT_B_PTS_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_B_PTS);
							if(KEY_OPT_B_PTS_str.equals(A.EMPTY_STR)){KEY_OPT_B_PTS_str="0";}
							//OPT_C
							KEY_OPT_C_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_C);
							if(KEY_OPT_C_str.equals(A.EMPTY_STR)){KEY_OPT_C_str="-";}
							
							KEY_OPT_C_IMAGE_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_C_IMAGE);
							if(KEY_OPT_C_IMAGE_str.equals(A.EMPTY_STR)){KEY_OPT_C_IMAGE_str="-";}
							
							KEY_OPT_C_COMMENT_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_C_COMMENT);
							if(KEY_OPT_C_COMMENT_str.equals(A.EMPTY_STR)){KEY_OPT_C_COMMENT_str="-";}
							
							KEY_OPT_C_PTS_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_C_PTS);
							if(KEY_OPT_C_PTS_str.equals(A.EMPTY_STR)){KEY_OPT_C_PTS_str="0";}
							//OPT_D
							KEY_OPT_D_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_D);
							if(KEY_OPT_D_str.equals(A.EMPTY_STR)){KEY_OPT_D_str="-";}
							
							KEY_OPT_D_IMAGE_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_D_IMAGE);
							if(KEY_OPT_D_IMAGE_str.equals(A.EMPTY_STR)){KEY_OPT_D_IMAGE_str="-";}
							
							KEY_OPT_D_COMMENT_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_D_COMMENT);
							if(KEY_OPT_D_COMMENT_str.equals(A.EMPTY_STR)){KEY_OPT_D_COMMENT_str="-";}
							
							KEY_OPT_D_PTS_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_OPT_D_PTS);
							if(KEY_OPT_D_PTS_str.equals(A.EMPTY_STR)){KEY_OPT_D_PTS_str="0";}
							
							KEY_CORRECT_ANSWER_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_CORRECT_ANSWER);
							if(KEY_CORRECT_ANSWER_str.equals(A.EMPTY_STR)){KEY_CORRECT_ANSWER_str="-";}
							
							KEY_CORRECT_ANSWER_EXPLANATION_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_CORRECT_ANSWER_EXPLANATION);
							if(KEY_CORRECT_ANSWER_EXPLANATION_str.equals(A.EMPTY_STR)){KEY_CORRECT_ANSWER_EXPLANATION_str="-";}
							
							KEY_TOTAL_TIME_PER_QUESTION_MIN_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_TOTAL_TIME_PER_QUESTION_MIN);
							if(KEY_TOTAL_TIME_PER_QUESTION_MIN_str.equals(A.EMPTY_STR)){KEY_TOTAL_TIME_PER_QUESTION_MIN_str="1";}
							
							KEY_TIMESTAMP_str = game_content_Obj.getString(DBAdapter_Game_Content.KEY_TIMESTAMP);
							if(KEY_TIMESTAMP_str.equals(A.EMPTY_STR)){KEY_TIMESTAMP_str="-";}
							
							//Check if this game_head exist or not
							db_game_content.open();
							
								Cursor c_all = db_game_content.getGameContent_Item_with_GAME_CODE(KEY_GAME_CODE_str);
								if(c_all.getCount()>=1){
									
									//put a level of condition demanded for a question to qualify as appropriate to go into the local db
									if(!KEY_GAME_CONTENT_ID_str.equals("-")
											&&(!KEY_GAME_CODE_str.equals("-"))
											&&(!KEY_QUESTION_CONTENT_str.equals("-"))
											&&(!KEY_OPT_A_str.equals("-"))
											&&(!KEY_OPT_B_str.equals("-"))
											&&(!KEY_OPT_C_str.equals("-"))
											&&(!KEY_OPT_D_str.equals("-"))
											&&( (Integer.parseInt(KEY_OPT_A_PTS_str)>=1) || (Integer.parseInt(KEY_OPT_B_PTS_str)>=1) || (Integer.parseInt(KEY_OPT_C_PTS_str)>=1) || (Integer.parseInt(KEY_OPT_D_PTS_str)>=1) )
											&&(!KEY_CORRECT_ANSWER_str.equals("-"))
											&&( (KEY_CORRECT_ANSWER_str.contains(Integer.toString(GAME_BTTNS_OPTS_A))) || (KEY_CORRECT_ANSWER_str.contains(Integer.toString(GAME_BTTNS_OPTS_B))) || (KEY_CORRECT_ANSWER_str.contains(Integer.toString(GAME_BTTNS_OPTS_C))) || (KEY_CORRECT_ANSWER_str.contains(Integer.toString(GAME_BTTNS_OPTS_D))) )
											){
									
										//Now get game content id : and do update or verification
										Cursor c_01 = db_game_content.getGameContent_Item_with_GAME_CONTENT_ID(KEY_GAME_CONTENT_ID_str);
										if(c_01.getCount()>=1){//update
											
											if(c_01.moveToPosition(0)){//checking to be sure that the cursor did move to the very first position
												
												rowId = Long.parseLong(c_01.getString(DBAdapter_Game_Content.KEY_ROWID_pos));
												
												db_game_content.updateGameContent_Item(rowId, KEY_GAME_CONTENT_ID_str, KEY_GAME_HEAD_ID_str, 
													KEY_GAME_CODE_str, KEY_QUESTION_LEVEL_str, KEY_QUESTION_CONTENT_str, KEY_QUESTION_IMAGE_str, 
													KEY_OPT_A_str, KEY_OPT_A_IMAGE_str, KEY_OPT_A_COMMENT_str, KEY_OPT_A_PTS_str, 
													KEY_OPT_B_str, KEY_OPT_B_IMAGE_str, KEY_OPT_B_COMMENT_str, KEY_OPT_B_PTS_str, 
													KEY_OPT_C_str, KEY_OPT_C_IMAGE_str, KEY_OPT_C_COMMENT_str, KEY_OPT_C_PTS_str, 
													KEY_OPT_D_str, KEY_OPT_D_IMAGE_str, KEY_OPT_D_COMMENT_str, KEY_OPT_D_PTS_str, 
													KEY_CORRECT_ANSWER_str, KEY_CORRECT_ANSWER_EXPLANATION_str, KEY_TOTAL_TIME_PER_QUESTION_MIN_str, 
													KEY_TIMESTAMP_str);
											}
											
										}else{//insert
											db_game_content.insertGameContent_Item(KEY_GAME_CONTENT_ID_str, KEY_GAME_HEAD_ID_str, 
													KEY_GAME_CODE_str, KEY_QUESTION_LEVEL_str, KEY_QUESTION_CONTENT_str, KEY_QUESTION_IMAGE_str, 
													KEY_OPT_A_str, KEY_OPT_A_IMAGE_str, KEY_OPT_A_COMMENT_str, KEY_OPT_A_PTS_str, 
													KEY_OPT_B_str, KEY_OPT_B_IMAGE_str, KEY_OPT_B_COMMENT_str, KEY_OPT_B_PTS_str, 
													KEY_OPT_C_str, KEY_OPT_C_IMAGE_str, KEY_OPT_C_COMMENT_str, KEY_OPT_C_PTS_str, 
													KEY_OPT_D_str, KEY_OPT_D_IMAGE_str, KEY_OPT_D_COMMENT_str, KEY_OPT_D_PTS_str, 
													KEY_CORRECT_ANSWER_str, KEY_CORRECT_ANSWER_EXPLANATION_str, KEY_TOTAL_TIME_PER_QUESTION_MIN_str, 
													KEY_TIMESTAMP_str);
										}
										
									}//END of : PREFECT QUESTION CONDITION
								}else{
									if(!KEY_GAME_CONTENT_ID_str.equals("-")
											&&(!KEY_GAME_CODE_str.equals("-"))
											&&(!KEY_QUESTION_CONTENT_str.equals("-"))
											&&(!KEY_OPT_A_str.equals("-"))
											&&(!KEY_OPT_B_str.equals("-"))
											&&(!KEY_OPT_C_str.equals("-"))
											&&(!KEY_OPT_D_str.equals("-"))
											&&( (Integer.parseInt(KEY_OPT_A_PTS_str)>=1) || (Integer.parseInt(KEY_OPT_B_PTS_str)>=1) || (Integer.parseInt(KEY_OPT_C_PTS_str)>=1) || (Integer.parseInt(KEY_OPT_D_PTS_str)>=1) )
											&&(!KEY_CORRECT_ANSWER_str.equals("-"))
											&&( (KEY_CORRECT_ANSWER_str.contains(Integer.toString(GAME_BTTNS_OPTS_A))) || (KEY_CORRECT_ANSWER_str.contains(Integer.toString(GAME_BTTNS_OPTS_B))) || (KEY_CORRECT_ANSWER_str.contains(Integer.toString(GAME_BTTNS_OPTS_C))) || (KEY_CORRECT_ANSWER_str.contains(Integer.toString(GAME_BTTNS_OPTS_D))) )
											){
												db_game_content.insertGameContent_Item(KEY_GAME_CONTENT_ID_str, KEY_GAME_HEAD_ID_str, 
														KEY_GAME_CODE_str, KEY_QUESTION_LEVEL_str, KEY_QUESTION_CONTENT_str, KEY_QUESTION_IMAGE_str, 
														KEY_OPT_A_str, KEY_OPT_A_IMAGE_str, KEY_OPT_A_COMMENT_str, KEY_OPT_A_PTS_str, 
														KEY_OPT_B_str, KEY_OPT_B_IMAGE_str, KEY_OPT_B_COMMENT_str, KEY_OPT_B_PTS_str, 
														KEY_OPT_C_str, KEY_OPT_C_IMAGE_str, KEY_OPT_C_COMMENT_str, KEY_OPT_C_PTS_str, 
														KEY_OPT_D_str, KEY_OPT_D_IMAGE_str, KEY_OPT_D_COMMENT_str, KEY_OPT_D_PTS_str, 
														KEY_CORRECT_ANSWER_str, KEY_CORRECT_ANSWER_EXPLANATION_str, KEY_TOTAL_TIME_PER_QUESTION_MIN_str, 
														KEY_TIMESTAMP_str);
									}
								}
							
							db_game_content.close();
							
						}//END of : for() ... loop
						
						setGameContent_Now(game_head_code_str, current_game_pointer_i);
						
						if(Get.DEV_MODE(context)){
							//Log success val
							Log.d(TAG+" >> "+"FROM SERVER SAVING GAME HEAD, CONTENT & USER DATA SUCCESS VALUE : ", Integer.toString(success));
						}
					}
					
				}catch(JSONException e){
					if(Get.DEV_MODE(context)){
						e.printStackTrace();
					}
				}
			}else{//EMPTY "game_data_json"
				setGameContent_Now(game_head_code_str, current_game_pointer_i);
			}
		  }//End of isShowing()	
		}
	
    }
 
    //check for user / player random-ness of question preference by the user to decide whether to begin from question position "0" or "n" , where n = random number between 0 and total number of questions minus one.
    protected void setGameContent_Now(String game_head_code_str, int n){
    	
    	
    	db_game_content.open();
    	//Get the "n-th" position of question that bears the current "game_code"
    	Cursor c_02 = db_game_content.getGameContent_Item_with_GAME_CODE(game_head_code_str);
		
    	total_game_content_i = c_02.getCount(); 
    	
    	if( (total_game_content_i>=1) && (c_02.moveToPosition(n)) ){
    		
    		current_game_pointer_i = n;
    		
    		KEY_ROWID_str_N = c_02.getString(DBAdapter_Game_Content.KEY_ROWID_pos);
			
			KEY_GAME_CONTENT_ID_str_N = c_02.getString(DBAdapter_Game_Content.KEY_GAME_CONTENT_ID_pos);
			
			KEY_GAME_HEAD_ID_str_N = c_02.getString(DBAdapter_Game_Content.KEY_GAME_HEAD_ID_pos);
			
			KEY_GAME_CODE_str_N = c_02.getString(DBAdapter_Game_Content.KEY_GAME_CODE_pos);
			
			KEY_QUESTION_LEVEL_str_N = c_02.getString(DBAdapter_Game_Content.KEY_QUESTION_LEVEL_pos);
			KEY_QUESTION_CONTENT_str_N = c_02.getString(DBAdapter_Game_Content.KEY_QUESTION_CONTENT_pos);
			KEY_QUESTION_IMAGE_str_N = c_02.getString(DBAdapter_Game_Content.KEY_QUESTION_IMAGE_pos);
			
			KEY_OPT_A_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_A_pos);
			KEY_OPT_A_IMAGE_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_A_IMAGE_pos);
			KEY_OPT_A_COMMENT_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_A_COMMENT_pos);
			KEY_OPT_A_PTS_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_A_PTS_pos);
			
			KEY_OPT_B_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_B_pos);
			KEY_OPT_B_IMAGE_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_B_IMAGE_pos);
			KEY_OPT_B_COMMENT_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_B_COMMENT_pos);
			KEY_OPT_B_PTS_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_B_PTS_pos);
			
			KEY_OPT_C_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_C_pos);
			KEY_OPT_C_IMAGE_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_C_IMAGE_pos);
			KEY_OPT_C_COMMENT_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_C_COMMENT_pos);
			KEY_OPT_C_PTS_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_C_PTS_pos);
			
			KEY_OPT_D_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_D_pos);
			KEY_OPT_D_IMAGE_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_D_IMAGE_pos);
			KEY_OPT_D_COMMENT_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_D_COMMENT_pos);
			KEY_OPT_D_PTS_str_N = c_02.getString(DBAdapter_Game_Content.KEY_OPT_D_PTS_pos);
			
			KEY_CORRECT_ANSWER_str_N = c_02.getString(DBAdapter_Game_Content.KEY_CORRECT_ANSWER_pos);
			KEY_CORRECT_ANSWER_EXPLANATION_str_N = c_02.getString(DBAdapter_Game_Content.KEY_CORRECT_ANSWER_EXPLANATION_pos);
			KEY_TOTAL_TIME_PER_QUESTION_MIN_str_N = c_02.getString(DBAdapter_Game_Content.KEY_TOTAL_TIME_PER_QUESTION_MIN_pos);
			KEY_TIMESTAMP_str_N = c_02.getString(DBAdapter_Game_Content.KEY_TIMESTAMP_pos);
    		
			if(Get.DEV_MODE(context)){
			Log.d(TAG+" >> "+"START >>>>>>>>>>>>>>>>> ","  GAME CONTENT  <<<<<<<<<<<<<<<< START");
			Log.d(TAG+" >> "+"KEY_ROWID_str_N : ", KEY_ROWID_str_N);
			
			Log.d(TAG+" >> "+"KEY_GAME_CONTENT_ID_str_N : ", KEY_GAME_CONTENT_ID_str_N);
			
			Log.d(TAG+" >> "+"KEY_GAME_HEAD_ID_str_N : ", KEY_GAME_HEAD_ID_str_N);
			Log.d(TAG+" >> "+"KEY_GAME_CODE_str_N : ", KEY_GAME_CODE_str_N);
			Log.d(TAG+" >> "+"KEY_QUESTION_LEVEL_str_N : ", KEY_QUESTION_LEVEL_str_N);
			Log.d(TAG+" >> "+"KEY_QUESTION_CONTENT_str_N : ", KEY_QUESTION_CONTENT_str_N);
			Log.d(TAG+" >> "+"KEY_QUESTION_IMAGE_str_N : ", KEY_QUESTION_IMAGE_str_N);
			
			Log.d(TAG+" >> "+"KEY_OPT_A_str_N : ", KEY_OPT_A_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_A_IMAGE_str_N : ", KEY_OPT_A_IMAGE_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_A_COMMENT_str_N : ", KEY_OPT_A_COMMENT_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_A_PTS_str_N : ", KEY_OPT_A_PTS_str_N);
			
			Log.d(TAG+" >> "+"KEY_OPT_B_str_N : ", KEY_OPT_B_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_B_IMAGE_str_N : ", KEY_OPT_B_IMAGE_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_B_COMMENT_str_N : ", KEY_OPT_B_COMMENT_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_B_PTS_str_N : ", KEY_OPT_B_PTS_str_N);
			
			Log.d(TAG+" >> "+"KEY_OPT_C_str_N : ", KEY_OPT_C_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_C_IMAGE_str_N : ", KEY_OPT_C_IMAGE_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_C_COMMENT_str_N : ", KEY_OPT_C_COMMENT_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_C_PTS_str_N : ", KEY_OPT_C_PTS_str_N);
			
			Log.d(TAG+" >> "+"KEY_OPT_D_str_N : ", KEY_OPT_D_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_D_IMAGE_str_N : ", KEY_OPT_D_IMAGE_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_D_COMMENT_str_N : ", KEY_OPT_D_COMMENT_str_N);
			Log.d(TAG+" >> "+"KEY_OPT_D_PTS_str_N : ", KEY_OPT_D_PTS_str_N);
			
			Log.d(TAG+" >> "+"KEY_CORRECT_ANSWER_str_N : ",KEY_CORRECT_ANSWER_str_N );
			Log.d(TAG+" >> "+"KEY_CORRECT_ANSWER_EXPLANATION_str_N : ", KEY_CORRECT_ANSWER_EXPLANATION_str_N);
			Log.d(TAG+" >> "+"KEY_TOTAL_TIME_PER_QUESTION_MIN_str_N : ", KEY_TOTAL_TIME_PER_QUESTION_MIN_str_N);
			Log.d(TAG+" >> "+"KEY_TIMESTAMP_str_N : ", KEY_TIMESTAMP_str_N);
			Log.d(TAG+" >> "+"END >>>>>>>>>>>>>>>>> ","  GAME CONTENT  <<<<<<<<<<<<<<<< END");
			}
			
			//Set Elements
			/*
			<!-- game_on_parent_rl, game_player_score_n_rank_rl, game_rank_{0-1}, game_score_tv_iv_ll , game_score_tv, game_score_iv -->
		    <!-- game_tr_progress_rl, game_tr_bttns_{0-1}, game_tr_level_view_{0-8}, -->
		    <!-- question_n_controls_ll, game_content_sv, game_content_sv_ll, game_content_question_sv_ll, question_image_ll, question_image_iv,  question_content_tv, -->
		    <!-- opts_ll, game_opts_sv, game_opts_sv_ll, game_content_opts_parent_rl_{0-3}, -->
		    <!-- game_content_opts_parent_rl_{0-3}, game_content_opts_sv_ll_0, game_opt_brief_tv_n_iv_ll_0, game_opt_brief_0, game_opt_iv_ll_0, game_opt_iv_0, game_opt_full_0, game_opt_0, game_opt_brief_iv_0 -->
		    <!-- game_page_padder_v_{0-1} -->
		    <!-- game_controls_parent_rl, game_controls_switch_rl, game_controls_kill_switch_iv_0, game_controls_rl, game_controls_opts_rl, game_bttns_opt_{0-3} -->
		    <!-- game_controls_nav_rl, game_bttns_nav_{0-3} -->
		    <!-- game_controls_aux_rl, game_bttns_aux_{0-1}, game_controls_indicator_rl -->

		    <!-- games_active_hsv_rl, games_active_hsV, game_hsv_child_rl_{0-4} -->
		    <!-- game_hc_iv_0, game_hsv_child_ll_0, game_hc_nm_tv_0, game_hc_sub_nm_tv_0, game_hc_bttn_0 -->
		    <!-- game_hsv_child_view_{0-3} , games_active_hsv_on_iv -->

		    <!-- game_tl_bttns_rl, game_tl_bttns_{0-2} -->
		    */
			
			//Set the correct answer programmatically
			String correct_ans_str = KEY_CORRECT_ANSWER_str_N.replace("{", A.EMPTY_STR);
			correct_ans_str = correct_ans_str.replace("}", A.EMPTY_STR);
			current_game_correct_answer_i = Integer.parseInt(correct_ans_str);
			
			//check image to see if it should be visible and gone
			if( (!KEY_QUESTION_IMAGE_str_N.equals("-")) && (!KEY_QUESTION_IMAGE_str_N.equals("-")) && (KEY_QUESTION_IMAGE_str_N.length()>=5) ){
				//Start downloading the image from online
				
				//make the question image visible
				question_image_ll.setVisibility(View.VISIBLE);
				question_image_iv.setVisibility(View.VISIBLE);
				
			}else{//kill the question image
				
				question_image_ll.setVisibility(View.GONE);
				question_image_iv.setVisibility(View.GONE);
				
			}
			
			//Set the question
			question_content_tv.setText(KEY_QUESTION_CONTENT_str_N);
			
			//Set the answer option A
			nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_brief_str, GAME_BTTNS_OPTS_A).setText(KEY_OPT_A_str_N);
			nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_full_str, GAME_BTTNS_OPTS_A).setText(KEY_OPT_A_str_N);
			
			//Set the answer option B
			nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_brief_str, GAME_BTTNS_OPTS_B).setText(KEY_OPT_B_str_N);
			nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_full_str, GAME_BTTNS_OPTS_B).setText(KEY_OPT_B_str_N);
			
			//Set the answer option C
			nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_brief_str, GAME_BTTNS_OPTS_C).setText(KEY_OPT_C_str_N);
			nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_full_str, GAME_BTTNS_OPTS_C).setText(KEY_OPT_C_str_N);
			
			//Set the answer option D
			nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_brief_str, GAME_BTTNS_OPTS_D).setText(KEY_OPT_D_str_N);
			nav_TV(TOTAL_GAME_BTTNS_OPTS, game_opt_full_str, GAME_BTTNS_OPTS_D).setText(KEY_OPT_D_str_N);
			
			double v_d;
			double final_v_d;
			int final_v_i;
			//Set the progress of the game  : KILL ALL THE VIEWS before setting the 
			for(int x=0; x<TOTAL_GAME_PROGESS_VIEWS; x++){
				
				if(current_game_pointer_i<1){
					nav_VW(TOTAL_GAME_PROGESS_VIEWS, game_tr_level_view_str, x).setVisibility(View.INVISIBLE);
				}else{
					if(current_game_pointer_i==1){
						nav_VW(TOTAL_GAME_PROGESS_VIEWS, game_tr_level_view_str, 0).setVisibility(View.VISIBLE);
					}else{
						
						Log.d("CURRENT_GAME_POINTER_i ( END OF QUERY ) : ", Integer.toString(current_game_pointer_i));
						
						v_d = ( (double)current_game_pointer_i / (double)total_game_content_i );// 2/9 = 0.22222222222
						Log.d("v_d : ", String.valueOf(v_d));//22.22222222
						
						final_v_d = (v_d) * ((double)TOTAL_GAME_PROGESS_VIEWS);
						Log.d("final_v_d : ", String.valueOf(final_v_d));//22.22222222223
						
						final_v_i = (int) final_v_d;
						Log.d("final_v_i : ", Integer.toString(final_v_i));//222
						
						for(int y=0; y<final_v_i; y++){
							nav_VW(TOTAL_GAME_PROGESS_VIEWS, game_tr_level_view_str, y).setVisibility(View.VISIBLE);
						}
					}
				}
			}
    	}else{
    		String final_game_scores_str = Integer.toString(current_game_score_i);
    		String final_game_content_total_str = Integer.toString(total_game_content_i);
    		A.showToastr(context, ">>> GAME COMPLETED SUCCESSFULLY \n[ "+final_game_scores_str+"/"+final_game_content_total_str+" ] Score, \nRank : BEGINNER DEMO <<<");
    		finish();
    	}
    	
    	db_game_content.close();
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

	
	public Button nav_BT(int total_num_i, String prefix_str, int bt_i){
		
		String[] button_bt_ids = new String[total_num_i];
		
		Button[] button_full_IDs = new Button[total_num_i];
		
		button_bt_ids[bt_i] = prefix_str+Integer.toString(bt_i);
		
		int button_bt_ID = context.getResources().getIdentifier(button_bt_ids[bt_i], A.ID, A.getAppPackageName(context));
		button_full_IDs[bt_i] = (Button)findViewById(button_bt_ID);
		
		return button_full_IDs[bt_i];
	}
	
	public RelativeLayout nav_RL(int total_num_i, String prefix_str, int rl_i){
		
		String[] rL_rl_ids = new String[total_num_i];
		
		RelativeLayout[] rL_full_IDs = new RelativeLayout[total_num_i];
		
		rL_rl_ids[rl_i] = prefix_str+Integer.toString(rl_i);
		
		int rL_rl_ID = context.getResources().getIdentifier(rL_rl_ids[rl_i], A.ID, A.getAppPackageName(context));
		rL_full_IDs[rl_i] = (RelativeLayout)findViewById(rL_rl_ID);
		
		return rL_full_IDs[rl_i];
	}
	
	public ImageView nav_IV(int total_num_i, String prefix_str, int iv_i){
		
		String[] imgView_iv_ids = new String[total_num_i];
		
		ImageView[] imgView_full_IDs = new ImageView[total_num_i];
		
		imgView_iv_ids[iv_i] = prefix_str+Integer.toString(iv_i);
		
		int imgView_iv_ID = context.getResources().getIdentifier(imgView_iv_ids[iv_i], A.ID, A.getAppPackageName(context));
		imgView_full_IDs[iv_i] = (ImageView)findViewById(imgView_iv_ID);
		
		return imgView_full_IDs[iv_i];
	}
	
	public View nav_VW(int total_num_i, String prefix_str, int vw_i){
		
		String[] View_iv_ids = new String[total_num_i];
		
		View[] View_full_IDs = new View[total_num_i];
		
		View_iv_ids[vw_i] = prefix_str+Integer.toString(vw_i);
		
		int View_iv_ID = context.getResources().getIdentifier(View_iv_ids[vw_i], A.ID, A.getAppPackageName(context));
		View_full_IDs[vw_i] = (View)findViewById(View_iv_ID);
		
		return View_full_IDs[vw_i];
	}
	
	public TextView nav_TV(int total_num_i, String prefix_str, int tv_i){
		
		String[] textView_tv_ids = new String[total_num_i];
		
		TextView[] textView_full_IDs = new TextView[total_num_i];
		
		textView_tv_ids[tv_i] = prefix_str+Integer.toString(tv_i);
		
		int textView_tv_ID = context.getResources().getIdentifier(textView_tv_ids[tv_i], A.ID, A.getAppPackageName(context));
		textView_full_IDs[tv_i] = (TextView)findViewById(textView_tv_ID);
		
		return textView_full_IDs[tv_i];
	}
	
	public LinearLayout nav_LL(int total_num_i, String prefix_str, int ll_i){
		
		String[] ll_ids = new String[total_num_i];
		
		LinearLayout[] ll_full_IDs = new LinearLayout[total_num_i];
		
		ll_ids[ll_i] = prefix_str+Integer.toString(ll_i);
		
		int ll_ID = context.getResources().getIdentifier(ll_ids[ll_i], A.ID, A.getAppPackageName(context));
		ll_full_IDs[ll_i] = (LinearLayout)findViewById(ll_ID);
		
		return ll_full_IDs[ll_i];
	}
	
	public ProgressBar nav_PB(int total_num_i, String prefix_str, int pb_i){
		
		String[] pb_ids = new String[total_num_i];
		
		ProgressBar[] pb_full_IDs = new ProgressBar[total_num_i];
		
		pb_ids[pb_i] = prefix_str+Integer.toString(pb_i);
		
		int item_main_pb_ID = context.getResources().getIdentifier(pb_ids[pb_i], A.ID, A.getAppPackageName(context));
		pb_full_IDs[pb_i] = (ProgressBar)findViewById(item_main_pb_ID);
		
		return pb_full_IDs[pb_i];
	}

	
	
}
