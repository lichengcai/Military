package com.military.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.military.R;
import com.military.ui.fragment.FragmentBase;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/22.
 */

public class FragmentVideo extends FragmentBase {
    private int mType = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("onViewCreate","mType--" + mType);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
    }

    public static FragmentVideo newInstance(int type) {
        FragmentVideo fragmentListNews = new FragmentVideo();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragmentListNews.setArguments(bundle);
        return fragmentListNews;
    }
}
