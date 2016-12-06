package com.military.huanqiu.weapon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.military.R;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/12/6.
 */

public class FragmentParameter extends Fragment {
    @BindView(R.id.textParameter)
    TextView mTextParameter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parameter,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Document mDocument = WeaponDetailActivity.mDocument;
        if (mDocument != null) {
            Elements elements = mDocument.select("div.intron");
//                    frg.textOne.setText(elements.get(0).text());
            Log.d("elements","elements--" + elements);
            Elements elements1 = mDocument.select("div.otherList");
//                    frg.textTwo.setText(elements1.text());
            for (Element element : elements1) {
                Log.d("elements","name--" + element.select("h3").get(0).text());
            }
            Log.d("elements","elements1==" + elements1.size() + "\n " + elements1.get(0).text());

            Elements elements2 = mDocument.select("div.side");
            mTextParameter.setText(elements2.get(0).text());
        }

    }
}
