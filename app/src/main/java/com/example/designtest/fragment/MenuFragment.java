package com.example.designtest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.designtest.Adapter.NewsTypeAdapter;
import com.example.designtest.Bean.NewsListItem;
import com.example.designtest.R;
import com.example.designtest.utils.Constant;
import com.example.designtest.utils.HttpUtils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.util.zip.Inflater;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SOngJIan on 2015/11/10 0010.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {
    private ListView lv_item;
    private TextView tv_download;
    private TextView tv_main;
    private TextView tv_fav;

    private List<NewsListItem> items = new ArrayList<>();
    private Handler handler = new Handler();
    NewsTypeAdapter newsTypeAdapter = new NewsTypeAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        tv_download = (TextView) view.findViewById(R.id.tv_download);
        tv_download.setOnClickListener(this);
        tv_main = (TextView) view.findViewById(R.id.tv_main);
        tv_main.setOnClickListener(this);
        tv_fav = (TextView) view.findViewById(R.id.tv_fav);
        tv_fav.setOnClickListener(this);

        return view;
    }

    public List<NewsListItem> getItems() {
        HttpUtils.get(Constant.THEMES, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray itemsArray = response.getJSONArray("others");
                    for (int i = 0; i < itemsArray.length(); i++) {
                        NewsListItem newsListItem = new NewsListItem();
                        JSONObject itemObject = itemsArray.getJSONObject(i);
                        newsListItem.setId(itemObject.getString("id"));
                        newsListItem.setTitle(itemObject.getString("name"));
                        items.add(newsListItem);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            lv_item.setAdapter(newsTypeAdapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Log.i("cc",items.toString());
        return items;
    }

    @Override
    public void onClick(View v) {

    }

}
