package com.journalio.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Activity_Poster extends Activity {

	Context ctx;
	
	//initialise the relativelayouts
	RelativeLayout activity_inputer_parent_id_rl;
	
	//initialise the button
	Button bttn_new_post;
	
	//intialise the cancel button
	ImageView iv_img_cancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_poster);
		
		ctx = Activity_Poster.this;
		
		//now reference all the elements in the UI
		activity_inputer_parent_id_rl
		=(RelativeLayout)
		findViewById(R.id.activity_inputer_parent_id);
		
		bttn_new_post=(Button)findViewById(R.id.base_bttn_0);
		
		iv_img_cancel = (ImageView)findViewById(R.id.iv_img_cancel);
		
		bttn_new_post.setText(getResources().getString(R.string.new_post));
		
		bttn_new_post.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				if(v.getId()==bttn_new_post.getId()) {
				switch (event.getActionMasked()){

  						case MotionEvent.ACTION_DOWN:
  						case MotionEvent.ACTION_POINTER_DOWN: {
  							//change color or background  ( new )
  							break;
  						}

  						case MotionEvent.ACTION_UP:
  						case MotionEvent.ACTION_POINTER_UP: {
  							
  							//change color to default
  							
  							//do the action
  							//check the parent layout and if it is 
  							//not visible, make it visible.
  							if( !activity_inputer_parent_id_rl.isShown() ) {
  								activity_inputer_parent_id_rl.setVisibility(View.VISIBLE);
  							}
  							
  							break;
  						}
  						case MotionEvent.ACTION_MOVE:{
  							
  							//finger moved : deactivate the whole action 
  							
  							break;
  						}
  					}
				}
				
				return true;
			}
		});
		
		iv_img_cancel.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				if(v.getId()==iv_img_cancel.getId()) {
				switch (event.getActionMasked()){

  						case MotionEvent.ACTION_DOWN:
  						case MotionEvent.ACTION_POINTER_DOWN: {
  							//change color or background  ( new )
  							break;
  						}

  						case MotionEvent.ACTION_UP:
  						case MotionEvent.ACTION_POINTER_UP: {
  							
  							//change color to default
  							
  							//do the action
  							//check the parent layout and if it is 
  							//not visible, make it visible.
  							if( activity_inputer_parent_id_rl.isShown() ) {
  								activity_inputer_parent_id_rl.setVisibility(View.GONE);
  							}
  							
  							break;
  						}
  						case MotionEvent.ACTION_MOVE:{
  							
  							//finger moved : deactivate the whole action 
  							
  							break;
  						}
  					}
				}
				
				return true;
			}
		});
		
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
  								//do the action
  								Intent activity_Trending_intent = new Intent(Activity_Poster.this, Activity_MainNewsPage.class );
  								startActivity(activity_Trending_intent);
  								break;
  							case 1:	
  							//dont do anything . . .we are right here....
  							break;
  							case 2:
  								//this should take us to "My Profile"
  								Intent activity_Profile_intent = new Intent(Activity_Poster.this, Activity_Profile.class );
  								startActivity(activity_Profile_intent);
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
		
		//Navigation indicator for the app head
		for(int x=0; x<3; x++) {
			
			if(x==1){//My Post
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
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
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
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(activity_inputer_parent_id_rl.isShown()) {
			activity_inputer_parent_id_rl.setVisibility(View.GONE);
		}else {
			super.onBackPressed();
		}
	}

	
	
	
}
