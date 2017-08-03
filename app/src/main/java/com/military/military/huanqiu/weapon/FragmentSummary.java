package com.military.huanqiu.weapon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.military.R;
import com.military.military.huanqiu.weapon.WeaponDetailActivity;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/12/6.
 */

public class FragmentSummary extends Fragment{
    @BindView(R.id.textSummary)
    TextView mTextSummary;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Document mDocument = WeaponDetailActivity.mDocument;
        if (mDocument != null) {
            Elements elements = mDocument.select("div.intron");
            mTextSummary.setText(elements.get(0).text());

        }

    }
}
