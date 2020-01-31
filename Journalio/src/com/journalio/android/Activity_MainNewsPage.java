package com.journalio.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Activity_MainNewsPage extends Activity {

	TextView tab_1;
	Button bttn_base_0, bttn_base_1;
	
	TextView news_hd, news_detail;
	RelativeLayout activity_outputer_id;
	ImageView iv_img_cancel;
	
	ScrollView sv_outputer_0;
	RelativeLayout sv_r_outputer_0;
	ImageView iv_img_outputer;
	//outputer_tv_{0,1},
	EditText et_outputer;
	LinearLayout base_bttns_parent_sv_id;
	Button base_bttn_sv_0, base_bttn_sv_1;//{0,1}

	
	Context ctx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_news_page_layout);
		
		ctx = Activity_MainNewsPage.this;
		
		//Reference ui elements
		tab_1 = (TextView )findViewById(R.id.nav_tv_1);
		
		bttn_base_0=(Button)findViewById(R.id.base_bttn_0);
		
		bttn_base_1=(Button)findViewById(R.id.base_bttn_1);
		
		bttn_base_0.setText(getResources().getString(R.string.get_your_account_for_more_features));
		
		news_hd = (TextView )findViewById(R.id.news_hd); 
		news_detail = (TextView )findViewById(R.id.news_detail);
		activity_outputer_id=(RelativeLayout)findViewById(R.id.activity_outputer_id);
		iv_img_cancel = (ImageView )findViewById(R.id.iv_img_cancel);
		
		 sv_outputer_0 =(ScrollView)findViewById(R.id.sv_outputer_0);
		 sv_r_outputer_0 =(RelativeLayout)findViewById(R.id.sv_r_outputer_0);
		 iv_img_outputer =(ImageView)findViewById(R.id.iv_img_outputer);
		//outputer_tv_{0,1},
		 et_outputer =(EditText)findViewById(R.id.et_outputer);
		 base_bttns_parent_sv_id =(LinearLayout)findViewById(R.id.base_bttns_parent_sv_id);
		 base_bttn_sv_0 =(Button)findViewById(R.id.base_bttn_sv_0); 
		 base_bttn_sv_1 =(Button)findViewById(R.id.base_bttn_sv_1);
		
		 //Set all default texts
		 base_bttn_sv_0.setText(getResources().getString(R.string.send_comment));
		 base_bttn_sv_1.setText(getResources().getString(R.string.view_comment));
		 
		 //For demo purposes, set the texts for the main
		 //textViews : ie. the news header and the news details
		 nav_TV(2, "outputer_tv_", 0).setText("Developer Gets $72.8 Billion");
					
					nav_TV(2, "outputer_tv_", 1).setText(
							"\n\nEarlier today, the 4 young "
							+ "Ghanaina Developers closed a deal with Sundai Pichai"
							+ " of Google for aldafjalkdjfak oi joaidjfo "
							+ "ijofaidjoaisdpfoia fyuao ojaodifh ayf9a8dfuoie"
							+ "ruqoieroqi hodosifjoa ijfaoidjfo aidfjoaijdpofi"
							+ "ajofdijaodfij oijsdoifjo josdijfoijdfajdlfk lask"
							+ "jdf ajdfjaoidaohdfiuahdifhqejrkajf;akdhfoahdfoaid "
							+ "Earlier today, the 4 young Ghanaina Developers closed "
							+ "a deal with Sundai Pichai of Google for aldafjalkdjfak "
							+ "oi joaidjfo ");
		 
		 for(int x=0; x<3; x++) {
			
			//because we are in the Activity_MainNewsPage,
			//turn off all the views under the tv except 
			//the first one.
			if(x==0){//trending
				nav_V(3, "nav_v_", x).setVisibility(View.VISIBLE);
			}else{
				nav_V(3, "nav_v_", x).setVisibility(View.GONE);
			}
			
		}
		 
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
  								//we are right here . . .dont do anything
  								break;
  							case 1:	
  							//do the action
  							Intent activity_Post_intent = new Intent(Activity_MainNewsPage.this, Activity_Poster.class );
  							startActivity(activity_Post_intent);
  							break;
  							case 2:
  								//this should take us to "My Profile"
  								Intent activity_Profile_intent = new Intent(Activity_MainNewsPage.this, Activity_Profile.class );
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
		
		
		
		news_hd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!activity_outputer_id.isShown()) {
					activity_outputer_id.setVisibility(View.VISIBLE);
				}
			}
		});
		
		news_detail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!activity_outputer_id.isShown()) {
					activity_outputer_id.setVisibility(View.VISIBLE);
				}
			}
		});
		activity_outputer_id.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v){
				
			}
		});
		iv_img_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(activity_outputer_id.isShown()) {
					activity_outputer_id.setVisibility(View.GONE);
				}
			}
		});
		
		base_bttn_sv_0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//check if there is content inside the Edittext
				//and get it and concatenate
				
				if(et_outputer.getText().toString().trim().equals("")){
					//dont post
				}else {
					//get the textView (for demo purposes)
					//and now add or concatenate the text 
					//entered by the user or reader with
					//the textView up there.
					
					nav_TV(2, "outputer_tv_", 1).setText(
							nav_TV(2, "outputer_tv_", 1).getText().toString()
							+"\n\n"
							+ ""
							+ et_outputer.getText().toString().trim()
					);
					
					//clear the editText
					et_outputer.setText("");
					
				}
				
			}
		});
		
		base_bttn_sv_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//when this button is clicked, show all the comments 
				//by users/readers for this particular news
				
				
			}
		});
		
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
