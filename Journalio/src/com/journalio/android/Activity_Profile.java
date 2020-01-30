package com.journalio.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Activity_Profile extends Activity {

	TextView tab_1;
	Button bttn_base_0, bttn_base_1;
	
	Context ctx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_my_profile);
		
		ctx = Activity_Profile.this;
		
		//Reference ui elements
		tab_1 = (TextView )findViewById(R.id.nav_tv_1);
		
		bttn_base_0=(Button)findViewById(R.id.base_bttn_0);
		
		bttn_base_1=(Button)findViewById(R.id.base_bttn_1);
		
		bttn_base_0.setText(getResources().getString(R.string.get_your_account_for_more_features));
		
		for(int i=0; i<3; i++) {
		nav_RL(3, "nav_rl_", i).setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				for(int j=0; j<3; j++) {
				
				if(v.getId()==nav_RL(3, "nav_rl_", j).getId()) {
				switch (event.getActionMasked()){

  						case MotionEvent.ACTION_DOWN:
  						case MotionEvent.ACTION_POINTER_DOWN: {
  							//change color or background  ( new )
  							break;
  						}
  						case MotionEvent.ACTION_UP:
  						case MotionEvent.ACTION_POINTER_UP: {
  							
  							switch(j) {
  							//change color to default
  							case 0:
  								//this should take us to "Trending" ie. Main News Area
  								Intent activity_Trending_intent = new Intent(Activity_Profile.this, Activity_MainNewsPage.class );
  								startActivity(activity_Trending_intent);
  								break;
  							case 1:	
  								//this should take us to "My Posts"
  								Intent activity_Post_intent = new Intent(Activity_Profile.this, Activity_Poster.class );
  								startActivity(activity_Post_intent);
  							break;
  							case 2:
  								//dont do anything . . .we are right here....
  								break;
  							default:
  								//DO NOTHING . . .
  								break;
  							
  							}
  							break;
  						}
  						case MotionEvent.ACTION_MOVE:{
  							
  							//finger moved : deactivate the whole action 
  							
  							break;
  						}
  					}
				}
				
			}
				return true;
			}
		});//END OF onTouch
	}//END OF for(){...}
		
		for(int x=0; x<3; x++) {
			
			if(x==2){//My Profile
				nav_V(3, "nav_v_", x).setVisibility(View.VISIBLE);
			}else{
				nav_V(3, "nav_v_", x).setVisibility(View.GONE);
			}
			
		}
		
		
	}
	
	
	public TextView nav_TV(int total_num_i, String prefix_str, int tv_i){

		String[] textView_tv_ids = new String[total_num_i];

		TextView[] textView_full_IDs = new TextView[total_num_i];

		textView_tv_ids[tv_i] = prefix_str+Integer.toString(tv_i);

		int textView_tv_ID = ctx.getResources().getIdentifier(textView_tv_ids[tv_i], "id", "com.journalio.android");
		textView_full_IDs[tv_i] = (TextView)findViewById(textView_tv_ID);

		return textView_full_IDs[tv_i];
	}
	
	public View nav_V(int total_num_i, String prefix_str, int v_i){

		String[] view_ids = new String[total_num_i];

		View[] view_full_IDs = new View[total_num_i];

		view_ids[v_i] = prefix_str+Integer.toString(v_i);

		int view_ID = ctx.getResources().getIdentifier(view_ids[v_i], "id", "com.journalio.android");
		view_full_IDs[v_i] = (View)findViewById(view_ID);

		return view_full_IDs[v_i];
	}
	
	public View nav_RL(int total_num_i, String prefix_str, int rl_i){

		String[] rl_ids = new String[total_num_i];

		RelativeLayout[] rl_full_IDs = new RelativeLayout[total_num_i];

		rl_ids[rl_i] = prefix_str+Integer.toString(rl_i);

		int rl_ID = ctx.getResources().getIdentifier(rl_ids[rl_i], "id", "com.journalio.android");
		rl_full_IDs[rl_i] = (RelativeLayout)findViewById(rl_ID);

		return rl_full_IDs[rl_i];
	}
	
}
