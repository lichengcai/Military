package com.military.widget.astuetz;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.military.R;


public class MyLinearLayout extends LinearLayout {
	public TextView textView;
	private ImageView imageView;
	private boolean isUp=true;
	private boolean isSelect;

	public MyLinearLayout(Context context){
		super(context);
		initView(context);
	}

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	private void initView(Context context){

		View layout= LayoutInflater.from(context).inflate(R.layout.mylinearlayout,null);
		textView=(TextView) layout.findViewById(R.id.myliear_text);
		imageView=(ImageView) layout.findViewById(R.id.myliear_image);
		addView(layout);
	}
	public  void setImageHide(){
		imageView.setVisibility(View.GONE);

	}
	public void setText(String text){
		textView.setText(text);
	}
	public void setTextColor(int color){
		textView.setTextColor(color);
	}
	public void setImageChange(boolean isUp){
		this.isUp=isUp;
		if (isUp) {
			imageView.setImageResource(R.mipmap.product_sort_arrow_up);
		}else {
			imageView.setImageResource(R.mipmap.product_sort_arrow_down);
		}
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}
	public interface OnClickSortChangeListener{
		public void onclickChange(int i, boolean isUp);
	}
}
