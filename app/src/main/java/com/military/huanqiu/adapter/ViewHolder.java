package com.military.huanqiu.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * 写Apdater的时候用这个可以减少很多代码量 
 * @author wuruize
 */
public class ViewHolder {

	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view,int id) {
		SparseArray<View> viewHolder = (SparseArray<View>)view.getTag();
		if(viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if(childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T)childView;
	}
}
