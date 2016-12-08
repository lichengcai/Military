package com.military.huanqiu.weapon;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.military.R;
import com.military.huanqiu.weapon.WeaponDetailActivity;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/12/5.
 */

public class FragmentWeaponDetail extends Fragment {
    @BindView(R.id.textDetail)
    TextView mTextDetail;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weapon_detail,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Document mDocument = WeaponDetailActivity.mDocument;
        if (mDocument != null) {
            Elements elements1 = mDocument.select("div.otherList");
            mTextDetail.setText(elements1.text());
        }

    }
}
