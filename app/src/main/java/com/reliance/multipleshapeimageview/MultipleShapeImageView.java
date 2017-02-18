package com.reliance.multipleshapeimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by sunzhishuai on 17/2/14.
 * E-mail itzhishuaisun@sina.com
 */

public class MultipleShapeImageView extends View {
    private final int FILLXY = 14;
    private final int CENTER = 15;

    private MultioleShaperAttrs mAttrs = new MultioleShaperAttrs();


    private static final String TAG = "MultipleShapeImageView";
    private Paint mMakeBitmap;
    private Paint mDrawPick;

    public MultipleShapeImageView(Context context) {
        this(context, null);

    }

    public MultipleShapeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public MultipleShapeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs, defStyleAttr);

    }

    private void getAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.multiple_shape_image_view, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.multiple_shape_image_view_image_scale_type:
                    this.mAttrs.scaleType = typedArray.getInt(attr, FILLXY);
                    break;
                case R.styleable.multiple_shape_image_view_multi_image:
                    BitmapDrawable drawable = (BitmapDrawable) typedArray.getDrawable(attr);
                    this.mAttrs.src = drawable.getBitmap();
                    break;
            }
        }
        typedArray.recycle();
        Log.e(TAG, this.mAttrs.toString());
        init();
    }

    private void init() {
        mMakeBitmap = new Paint();
        mMakeBitmap.setAntiAlias(true);
        mDrawPick = new Paint();
        mDrawPick.setAntiAlias(true);
    }

    private class MultioleShaperAttrs {
        private int scaleType;
        private Bitmap src;

        @Override
        public String toString() {
            return "MultioleShaperAttrs{" +
                    ", scaleType=" + scaleType +
                    ", src=" + src +
                    '}';
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMeasureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMeasureMode == MeasureSpec.AT_MOST) {
            widthMeasureWidthSize = mAttrs.src.getWidth();
        }
        if (heightMeasureMode == MeasureSpec.AT_MOST) {
            heightMeasureHeightSize = mAttrs.src.getHeight();
        }
        setMeasuredDimension(widthMeasureWidthSize, heightMeasureHeightSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap captureBitmap = getCaptureBitmapByPath();
        int drawSize = getDrawSize();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(captureBitmap, drawSize, drawSize, false);
        canvas.drawBitmap(scaledBitmap, 0, 0, mDrawPick);
    }

    private int getDrawSize() {
        return getMeasuredWidth() < getMeasuredHeight() ? getMeasuredWidth() : getMeasuredHeight();
    }


    /**
     * 抠图
     *
     * @return
     */
    private Bitmap getCaptureBitmapByPath() {
        int height = mAttrs.src.getHeight();
        int width = mAttrs.src.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        int r = (width < height ? width : height) / 2;

        if (drawShper != null) {
            Path path = drawShper.makeDrawShaperPath(width, height);
            canvas.drawPath(path, mMakeBitmap);
        } else {
            return mAttrs.src;
        }
        mMakeBitmap.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mAttrs.src, 0, 0, mMakeBitmap);
        return bitmap;
    }

    private MultipleShaper drawShper = null;


    public interface MultipleShaper {
        Path makeDrawShaperPath(int drawSpaceWidth, int drawSpaceHeight);
    }

    public void setMultipleShaper(MultipleShaper shaper) {
        drawShper = shaper;
        postInvalidate();
    }
}
