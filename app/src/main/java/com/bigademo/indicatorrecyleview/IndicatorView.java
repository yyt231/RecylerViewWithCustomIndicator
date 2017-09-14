package com.bigademo.indicatorrecyleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Tony on 2017/9/13.
 */

public class IndicatorView extends FrameLayout {
    private ImageView backImage;
    public ImageView scrollbarImage;


    public IndicatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        backImage = new ImageView(context);
        scrollbarImage = new ImageView(context);
        backImage.setImageDrawable(ta.getDrawable(R.styleable.IndicatorView_bgcolor));
        scrollbarImage.setImageDrawable(ta.getDrawable(R.styleable.IndicatorView_barcolor));
        ta.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        FrameLayout.LayoutParams backImageLP = new LayoutParams(width, FrameLayout.LayoutParams.MATCH_PARENT);
        backImage.setLayoutParams(backImageLP);
        FrameLayout.LayoutParams barLP = new LayoutParams(width / 2, FrameLayout.LayoutParams.MATCH_PARENT);
        scrollbarImage.setLayoutParams(barLP);
        removeAllViews();
        super.addView(backImage);
        super.addView(scrollbarImage);

    }

    public void setBarScrollListener(BarScrollListener listener) {
    }

    ;

    interface BarScrollListener {
        void scroll(int recylerViewX);
    }
}
