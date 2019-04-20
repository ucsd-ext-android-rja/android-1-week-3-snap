package com.ucsdextandroid1.snapapp.main;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.ucsdextandroid1.snapapp.R;

/**
 * Created by rjaylward on 4/15/19
 */
public class SnapTabsView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ImageView centerImage;
    private ImageView startImage;
    private ImageView endImage;
    private ImageView bottomImage;

    private View indicatorView;

    private ArgbEvaluator argbEvaluator;
    private int centerColor;
    private int sideColor;

    private int endViewsTranslationX;
    private int indicatorTranslationX;
    private int centerTranslationY;

    public SnapTabsView(@NonNull Context context) {
        this(context, null);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void setUpWithViewPager(final ViewPager viewPager) {
        viewPager.addOnPageChangeListener(this);

        startImage.setOnClickListener(v -> {
            if(viewPager.getCurrentItem() != 0)
                viewPager.setCurrentItem(0);
        });

        endImage.setOnClickListener(v -> {
            if(viewPager.getCurrentItem() != 2)
                viewPager.setCurrentItem(2);
        });
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_snap_tabs, this, true);

        centerImage = findViewById(R.id.vst_center_image);
        startImage = findViewById(R.id.vst_start_image);
        endImage = findViewById(R.id.vst_end_image);
        bottomImage = findViewById(R.id.vst_bottom_image);

        indicatorView = findViewById(R.id.vst_indicator);

        centerColor = ContextCompat.getColor(getContext(), R.color.white);
        sideColor = ContextCompat.getColor(getContext(), R.color.dark_grey);

        argbEvaluator = new ArgbEvaluator();

        indicatorTranslationX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());

        bottomImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                bottomImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                endViewsTranslationX = (int) ((bottomImage.getX() - startImage.getX()) - indicatorTranslationX);
                centerTranslationY = getHeight() - bottomImage.getBottom();
            }

        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position == 0) {
            setColor(1 - positionOffset);
            moveViews(1 - positionOffset);

            moveAndScaleCenter(1 - positionOffset);

            indicatorView.setTranslationX((positionOffset - 1) * indicatorTranslationX);
        }
        else if(position == 1) {
            setColor(positionOffset);
            moveViews(positionOffset);

            moveAndScaleCenter(positionOffset);
            indicatorView.setTranslationX(positionOffset * indicatorTranslationX);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void moveAndScaleCenter(float fractionFromCenter) {
        float scale = .7f + ((1 - fractionFromCenter) * .3f);

        centerImage.setScaleX(scale);
        centerImage.setScaleY(scale);

        int translation = (int) (fractionFromCenter * centerTranslationY);

        centerImage.setTranslationY(translation);
        bottomImage.setTranslationY(translation);

        bottomImage.setAlpha(1 - fractionFromCenter);
    }

    private void moveViews(float fractionFromCenter) {
        startImage.setTranslationX(fractionFromCenter * endViewsTranslationX);
        endImage.setTranslationX(-fractionFromCenter * endViewsTranslationX);

        indicatorView.setAlpha(fractionFromCenter);
        indicatorView.setScaleX(fractionFromCenter);
    }

    private void setColor(float fractionFromCenter) {
        int color = (int) argbEvaluator.evaluate(fractionFromCenter, centerColor, sideColor);

        centerImage.setColorFilter(color);
        startImage.setColorFilter(color);
        endImage.setColorFilter(color);
    }

}
