package com.lonch.mycehua;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作    者：Sword God
 * 版    本：1.1.0
 * 创建日期：2018/7/18
 * 描    述：设置侧滑页面大小可以改变其宽度
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tbHeadBar)
    Toolbar mTbHeadBar;
    //侧滑菜单布局
    @BindView(R.id.llMenu)
    LinearLayout mLlMenu;

    //侧滑菜单ListView放置菜单项
    @BindView(R.id.lvMenu)
    ListView mLvMenu;

    @BindView(R.id.ivContent)
    ImageView mIvContent;

    @BindView(R.id.dlMenu)
    DrawerLayout mMyDrawable;

    ActionBarDrawerToggle mToggle;

    private List<String> lvMenuList = new ArrayList<String>() {{
        add("angry");
        add("happy");
        add("sad");
        add("embarrassed");
    }};

    private List<Integer> imageList = new ArrayList<Integer>() {{
        add(R.drawable.refuse);
        add(R.drawable.goods);
        add(R.drawable.egis);
        add(R.drawable.finish);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //初始化Toolbar与DrawableLayout
        initToolBarAndDrawableLayout();

        mLvMenu.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, lvMenuList));
        mLvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mIvContent.setImageResource(imageList.get(position));
                mMyDrawable.closeDrawers();//收起抽屉
            }
        });
    }

    /**
     * 初始化
     */
    private void initToolBarAndDrawableLayout() {
        setSupportActionBar(mTbHeadBar);
        //以下俩方法设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题文字不可显示
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToggle = new ActionBarDrawerToggle(this, mMyDrawable, mTbHeadBar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                Toast.makeText(MainActivity.this, R.string.open, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                Toast.makeText(MainActivity.this, R.string.close, Toast.LENGTH_SHORT).show();
            }
        };
        //mMyDrawable.setDrawerListener(mToggle);不推荐
        mMyDrawable.addDrawerListener(mToggle);
        mToggle.syncState();//同步状态
    }
}
