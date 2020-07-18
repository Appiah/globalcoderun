package com.aireceive.android.edureceive;
	

import com.aireceive.android.A;
import com.aireceive.android.Get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import com.aireceive.android.R;

public class AdapterForGamesCenter extends SimpleAdapter{

	ArrayList<HashMap<String, String>> game_items;
	
	public AdapterForGamesCenter adapter;
	
	private Activity mContext;
    private Context context_casted;
    public LayoutInflater inflater=null;
	
    private List<? extends Map<String, ?>> items;
    
	public AdapterForGamesCenter(Activity context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		mContext = context;
		context_casted = (Context)context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public DBAdapter_Game_Head db_game_hd;
	
	public void refresh( List<? extends Map<String, ?>> data){
    	this.items = data;
    	notifyDataSetChanged();
    }
    
	@Override
	public ViewBinder getViewBinder() {
		// TODO Auto-generated method stub
		return super.getViewBinder();
		//859527
		//Erikuz-16
	}

	@Override
	public void setViewBinder(ViewBinder viewBinder) {
		// TODO Auto-generated method stub
		super.setViewBinder(viewBinder);
	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetInvalidated() {
		// TODO Auto-generated method stub
		super.notifyDataSetInvalidated();
	}

	@Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View rowView=convertView;
        if(convertView==null)
            rowView = inflater.inflate(R.layout.game_capsule, null);
     
        /*
        <!-- game_capsular_ll, game_pid_tv, game_owner_id_tv, game_chkbx_tv, game_img_iv, game_bttm_options_ll
        game_tv_hsv, game_tv_ll, game_nm_tv, game_sub_tv_sv, game_sub_tv_ll, game_sub_tv, game_total_tv, game_slct_iv -->
        */
        
       final LinearLayout game_capsular_ll, game_bttn_options_ll, game_tv_ll, game_sub_tv_ll;
       final TextView game_pid_tv, game_nm_tv, game_sub_tv, game_total_tv;
       final ImageView game_img_iv, game_slct_iv;
       final HorizontalScrollView game_tv_hsv;
       final ScrollView game_sub_tv_sv;
        
        game_capsular_ll = (LinearLayout)rowView.findViewById(R.id.game_capsular_ll);
        game_pid_tv = (TextView)rowView.findViewById(R.id.game_pid_tv);
        game_img_iv = (ImageView)rowView.findViewById(R.id.game_img_iv);
        game_bttn_options_ll = (LinearLayout)rowView.findViewById(R.id.game_bttn_options_ll);
        game_tv_hsv = (HorizontalScrollView)rowView.findViewById(R.id.game_tv_hsv);
        game_tv_ll = (LinearLayout)rowView.findViewById(R.id.game_tv_ll);
        game_nm_tv = (TextView)rowView.findViewById(R.id.game_nm_tv);
        game_sub_tv_sv = (ScrollView)rowView.findViewById(R.id.game_sub_tv_sv);
        game_sub_tv_ll = (LinearLayout)rowView.findViewById(R.id.game_sub_tv_ll);
        game_sub_tv = (TextView)rowView.findViewById(R.id.game_sub_tv);
        game_total_tv = (TextView)rowView.findViewById(R.id.game_total_tv);
        game_slct_iv = (ImageView)rowView.findViewById(R.id.game_slct_iv);
        
        db_game_hd = new DBAdapter_Game_Head(mContext);
        //HashMap<String, String> data = (HashMap<String, String>)getItem(position);
        final HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
        
		//String local_sqlite_item_id_str=(String)data.get(DBAdapter_Game_Head.KEY_ROWID);
		//String item_id_str=(String)data.get(DBAdapter_Game_Head.KEY_ITEM_ID);
        
        long rowId;
		String KEY_ROWID_str, KEY_GAME_HEAD_ID_str, KEY_SIM_NUM_str, KEY_GAME_CODE_str, KEY_GAME_NAME_str, 
		KEY_GAME_DESCRIPTION_str, KEY_GAME_RULES_str, KEY_GAME_INTERESTS_str, KEY_GAME_COUNTRIES_str, 
		KEY_GAME_AGE_RANGE_str, KEY_GAME_GENDER_str, KEY_GAME_PLAYABILITY_DEPENDENCIES_str, 
		KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str, KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str, 
		KEY_GAME_AVAILABILITY_str, KEY_GAME_PRICE_CURRENCY_str, KEY_GAME_PRICE_str, 
		KEY_GAME_ACTIVE_STATUS_str, KEY_GAME_IMG_str, KEY_GAME_AUTO_EXPLAIN_str, KEY_GAME_TIMED_str, 
		KEY_GAME_TIMED_MIN_str, KEY_GAME_PAUSABLE_str, KEY_GAME_REPLAYABLE_str, KEY_GAME_INCLUSIVITY_BY_MOBILE_str, 
		KEY_GAME_EXCLUSIVITY_BY_MOBILE_str, KEY_TIMESTAMP_str;
		
		KEY_ROWID_str = (String)data.get(DBAdapter_Game_Head.KEY_ROWID);
		KEY_GAME_HEAD_ID_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_HEAD_ID);
		KEY_SIM_NUM_str = (String)data.get(DBAdapter_Game_Head.KEY_SIM_NUM);
		KEY_GAME_CODE_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_CODE);
		KEY_GAME_NAME_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_NAME);
		KEY_GAME_DESCRIPTION_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_DESCRIPTION);
		KEY_GAME_RULES_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_RULES);
		KEY_GAME_INTERESTS_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_INTERESTS);
		KEY_GAME_COUNTRIES_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_COUNTRIES);
		KEY_GAME_AGE_RANGE_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_AGE_RANGE);
		KEY_GAME_GENDER_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_GENDER);
		KEY_GAME_PLAYABILITY_DEPENDENCIES_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_DEPENDENCIES);
		KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_SCORE_DEPENDENCIES);
		KEY_GAME_PLAYABILITY_RANK_DEPENDANCY_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_PLAYABILITY_RANK_DEPENDANCY);
		KEY_GAME_AVAILABILITY_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_AVAILABILITY);
		KEY_GAME_PRICE_CURRENCY_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_PRICE_CURRENCY);
		KEY_GAME_PRICE_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_PRICE);
		KEY_GAME_ACTIVE_STATUS_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_ACTIVE_STATUS);
		KEY_GAME_IMG_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_IMG);
		KEY_GAME_AUTO_EXPLAIN_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_AUTO_EXPLAIN);
		KEY_GAME_TIMED_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_TIMED);
		KEY_GAME_TIMED_MIN_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_TIMED_MIN);
		KEY_GAME_PAUSABLE_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_PAUSABLE);
		KEY_GAME_REPLAYABLE_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_REPLAYABLE);
		KEY_GAME_INCLUSIVITY_BY_MOBILE_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_INCLUSIVITY_BY_MOBILE);
		KEY_GAME_EXCLUSIVITY_BY_MOBILE_str = (String)data.get(DBAdapter_Game_Head.KEY_GAME_EXCLUSIVITY_BY_MOBILE);
		KEY_TIMESTAMP_str = (String)data.get(DBAdapter_Game_Head.KEY_TIMESTAMP);
        
		//Set HIDDEN TVs
		game_pid_tv.setText(KEY_GAME_CODE_str);
		
		//Set texts
		game_nm_tv.setText(KEY_GAME_NAME_str);
		game_sub_tv.setText(KEY_GAME_DESCRIPTION_str);
		
		//Set Visibilities
		game_sub_tv_sv.setVisibility(View.VISIBLE);
		game_sub_tv_ll.setVisibility(View.VISIBLE);
		game_sub_tv.setVisibility(View.VISIBLE);
		
		game_img_iv.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				//Globalz.showToastr(context_casted, "IV :: Load Game Content for { "+game_pid_tv.getText().toString()+" }");
				sendMsgToParentToStartGameContent(game_pid_tv.getText().toString());
			}
		});
		
		game_nm_tv.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				//Globalz.showToastr(context_casted, "TV :: Load Game Content for { "+game_pid_tv.getText().toString()+" }");
				sendMsgToParentToStartGameContent(game_pid_tv.getText().toString());
			}
		});
		
		
      //END OF THE "rowView" ... now return it ... m33333nn ... lol
        return rowView;
        
	}
	
	private void sendMsgToParentToStartGameContent(String game_code_str){
		Message msg = Message.obtain();
		//msg.what = item_local_id_i;
		//msg.what = unrdMsgs_SelectedList.size();
		msg.obj=game_code_str;
		Activity_GameCenter.load_Game_Content_Handler.sendMessage(msg);
	}
	
	
}
