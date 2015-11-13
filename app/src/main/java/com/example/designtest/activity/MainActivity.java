package com.example.designtest.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.designtest.R;

public class MainActivity extends AppCompatActivity {
    private String curId;

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
        curId = "latest";
    }
    public void setCurId(String id){
        curId = id;
    }

    //控件初始化
    @TargetApi(Build.VERSION_CODES.M)
    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setBackgroundColor(getColor(android.support.design.R.color.background_material_light));
        setSupportActionBar(mToolBar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                replaceFragment();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
        mFrameLayout = (FrameLayout) findViewById(R.id.f1_content);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    //Fragment的动态替换
    private void replaceFragment() {
        if (curId.equals("latest")){
            getSupportFragmentManager().beginTransaction().replace(R.id.f1_content,new Fragment(),"latest").commit();
        }
    }

    public void closeMenu(){
        mDrawerLayout.closeDrawers();
    }

    public void setSwipeRefreshEnable(boolean enable){
        mSwipeRefreshLayout.setEnabled(enable);
    }

    public void setToolBarTitle(String text){
        mToolBar.setTitle(text);
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
