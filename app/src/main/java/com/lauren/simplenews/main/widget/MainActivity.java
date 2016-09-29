package com.lauren.simplenews.main.widget;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lauren.simplenews.R;
import com.lauren.simplenews.main.presenter.MainPresenter;
import com.lauren.simplenews.main.presenter.MainPresenterImpl;
import com.lauren.simplenews.main.view.MainView;
import com.lauren.simplenews.menu1.widget.Fragment1;
import com.lauren.simplenews.menu2.widget.Fragment2;
import com.lauren.simplenews.menu3.widget.Fragment3;
import com.lauren.simplenews.menu4.Fragment4;


public class MainActivity extends AppCompatActivity implements MainView {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //标题bar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //左抽屉布局界面
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close); //Toggle触发器、开关
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);//设置Toggle触发监听
        //设置左边抽屉界面内容
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView); //Drawer抽屉

        //设置右边各主界面的填充实现
        mMainPresenter = new MainPresenterImpl(this);

        switch2Frame1();
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

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //根据menuItem.getItemId切换左边Navigation
                        mMainPresenter.switchNavigation(menuItem.getItemId());
                        menuItem.setChecked(true);
                        //关闭左抽屉界面
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void switch2Frame1() {
        //获取fragment,开启一个事物,加入fragment,提交事物,这里是在框架容器frame_content中替换不同的Fragment框架
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new Fragment1()).commit();
        mToolbar.setTitle(R.string.navigation_menu1);
    }

    @Override
    public void switch2Frame2() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new Fragment2()).commit();
        mToolbar.setTitle(R.string.navigation_menu2);
    }

    @Override
    public void switch2Frame3() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new Fragment3()).commit();
        mToolbar.setTitle(R.string.navigation_menu3);
    }

    @Override
    public void switch2Frame4() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new Fragment4()).commit();
        mToolbar.setTitle(R.string.navigation_menu4);

    }

}
