package com.ucsdextandroid1.snapapp.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rjaylward on 2019-04-19
 */
public class WindowUtil {

    public static void doOnApplyWindowInsetsToPadding(View view,  boolean addTop, boolean addBottom) {
        int top = view.getPaddingTop();
        int bottom = view.getPaddingBottom();

        view.setOnApplyWindowInsetsListener((v, insets) -> {
            int topPadding = addTop ? top + insets.getSystemWindowInsetTop() : v.getPaddingTop();
            int bottomPadding = addBottom ? bottom + insets.getSystemWindowInsetBottom() : v.getPaddingBottom();

            view.setPadding(v.getPaddingLeft(), topPadding, v.getPaddingRight(), bottomPadding);

            return insets;
        });

        requestApplyInsetsWhenAttached(view);
    }

    public static void doOnApplyWindowInsetsToMargins(View view, boolean addTop, boolean addBottom) {
        ViewGroup.MarginLayoutParams initialMargins = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

        int top = initialMargins.topMargin;
        int bottom = initialMargins.bottomMargin;

        view.setOnApplyWindowInsetsListener((v, insets) -> {
            ViewGroup.MarginLayoutParams margin = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            if(addTop)
                margin.topMargin = top + insets.getSystemWindowInsetTop();
            if(addBottom)
                margin.bottomMargin = bottom + insets.getSystemWindowInsetBottom();

            v.setLayoutParams(margin);

            return insets;
        });

        requestApplyInsetsWhenAttached(view);
    }

    public static void requestApplyInsetsWhenAttached(View view) {
        if(view.isAttachedToWindow()) {
            // We're already attached, just request as normal
            view.requestApplyInsets();
        }
        else {
            // We're not attached to the hierarchy, add a listener to request when we are
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {

                @Override
                public void onViewAttachedToWindow(View v) {
                    v.removeOnAttachStateChangeListener(this);
                    v.requestApplyInsets();
                }

                @Override
                public void onViewDetachedFromWindow(View v) { }

            });
        }
    }

}
