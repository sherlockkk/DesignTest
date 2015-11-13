package com.example.designtest.activity;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.designtest.R;

public class MainActivity extends AppCompatActivity {
private Toolbar mToolBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLatest();
        initView();
    }

    //加载最后一个Fragment
    private void loadLatest() {
        getSupportFragmentManager().beginTransaction().replace(R.id.f1_content, new Fragment(), "latest").commit();
    }

    //控件初始化
    private void initView() {
         mToolBar = (Toolbar) findViewById(R.id.toolbar);
         mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
         mFrameLayout = (FrameLayout) findViewById(R.id.f1_content);
         mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        onListener();
    }

    //控件的监听
    private void onListener() {
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                replaceFragment();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        };
    }

    //Fragment的动态替换
    private void replaceFragment() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
