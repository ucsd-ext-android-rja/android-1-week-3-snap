package com.ucsdextandroid1.snapapp.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.ucsdextandroid1.snapapp.R;
import com.ucsdextandroid1.snapapp.util.WindowUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.am_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_search_white_24dp);
        setSupportActionBar(toolbar);
        WindowUtil.doOnApplyWindowInsetsToMargins(toolbar, true, false);

        final View background = findViewById(R.id.am_background_view);
        ViewPager viewPager = findViewById(R.id.am_view_pager);
        viewPager.setOffscreenPageLimit(2);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        SnapTabsView snapTabsView = findViewById(R.id.am_snap_tabs);
        snapTabsView.setUpWithViewPager(viewPager);

        viewPager.setCurrentItem(1);

        final int colorBlue = ContextCompat.getColor(this, R.color.light_blue);
        final int colorPurple = ContextCompat.getColor(this, R.color.light_purple);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0) {
                    background.setBackgroundColor(colorBlue);
                    background.setAlpha(1 - positionOffset);
                }
                else if(position == 1) {
                    background.setBackgroundColor(colorPurple);
                    background.setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                ActionBar actionBar = getSupportActionBar();
                if(actionBar != null)
                    actionBar.setTitle(adapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) { }

        });
    }
}
