package com.example.designtest.Adapter;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.designtest.Bean.NewsListItem;
import com.example.designtest.R;
import com.example.designtest.fragment.MenuFragment;

import java.util.List;

/**
 * Created by SongJian on 2015/11/10 0010.
 */
public class NewsTypeAdapter extends BaseAdapter {

    private List<NewsListItem> newsListItems = new MenuFragment().getItems();

    @Override
    public int getCount() {
        return newsListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return newsListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(new MenuFragment().getActivity()).inflate(R.layout.menu_item, parent, false);
        }
        TextView tv_item = (TextView) convertView.findViewById(R.id.tv_item);
        tv_item.setText(newsListItems.get(position).getTitle());
        return convertView;
    }
}
