package com.reliance.multipleshapeimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by sunzhishuai on 17/2/17.
 * E-mail itzhishuaisun@sina.com
 */

public class VerifyView extends View {
    private VerifyAttrs mAttrs = new VerifyAttrs();
    private Paint mBgpaint;
    private Paint mMarkPaint;
    private Paint mhandPaint;
    int x = 100, y = 100, r = 100;
    private Random random;

    public VerifyView(Context context) {
        this(context, null);
    }

    public VerifyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs, defStyleAttr);
        random = new Random();
        init();
    }

    private void getAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VerifyView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.VerifyView_verify_bg:
                    mAttrs.verifyBg = ((BitmapDrawable) typedArray.getDrawable(index)).getBitmap();

                    break;
                case R.styleable.VerifyView_verify_height:
                    mAttrs.verifyHeight = typedArray.getDimension(index, 100);
                    break;
                case R.styleable.VerifyView_verify_with:
                    mAttrs.verifyWidh = typedArray.getDimension(index, 100);
                    break;
            }
        }
        typedArray.recycle();
    }

    private void init() {
        mBgpaint = new Paint();
        mMarkPaint = new Paint();

        mMarkPaint.setColor(Color.argb(77, 00, 00, 00));

        mhandPaint = new Paint();

    }


    private class VerifyAttrs {
        float verifyWidh;
        float verifyHeight;
        Bitmap verifyBg;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = 100;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = 100;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    float mHanderX = 100;
    float markX = 0;


    @Override
    protected void onDraw(Canvas canvas) {
        if (x==100&&y==100) {
            x = random.nextInt(getMeasuredWidth() - r) + r;
            y = random.nextInt(getMeasuredHeight() - r) + r;
        }
        drawBg(canvas);
        Bitmap bitmap = makeMark(x, y, r);
        drawShadow(canvas, bitmap, x, y, r);
        drawMark(canvas, bitmap, y - r);
        drawHander(canvas);
    }

    private void drawHander(Canvas canvas) {
        mhandPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, getHeight() - 100, getMeasuredWidth(), getHeight() - 40, mhandPaint);
        mhandPaint.setColor(Color.RED);
        canvas.drawRect(mHanderX - 100, getHeight() - 110, mHanderX + 100, getMeasuredHeight() - 30, mhandPaint);

    }

    private void drawMark(Canvas canvas, Bitmap bitmap, int location) {
        canvas.drawBitmap(bitmap, markX, location, mBgpaint);
    }

    private void drawShadow(Canvas canvas, Bitmap mark, int x, int y, int r) {
        canvas.drawCircle(x, y, r, mMarkPaint);
    }

    private Bitmap makeMark(int x, int y, int r) {
        Paint paint = new Paint();
        Bitmap bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Log.e("width", bitmap.getWidth() + "--");
        Log.e("height", bitmap.getHeight() + "--");
        canvas.drawCircle(x, y, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rect = new Rect();
        rect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Rect rect1 = new Rect();
        rect1.set(0, 0, mAttrs.verifyBg.getWidth(), mAttrs.verifyBg.getHeight());
        canvas.drawBitmap(mAttrs.verifyBg, rect1, rect, paint);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, x - r,
                y - r, r * 2, r * 2);
        Log.e("afterWidth", bitmap1.getWidth() + "--");
        Log.e("afterheight", bitmap1.getHeight() + "--");
        return bitmap1;
    }

    private void drawBg(Canvas canvas) {
        Rect rect = new Rect();
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        Rect rect1 = new Rect();
        rect1.set(0, 0, mAttrs.verifyBg.getWidth(), mAttrs.verifyBg.getHeight());
        canvas.drawBitmap(mAttrs.verifyBg, rect1, rect, mBgpaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return y > getMeasuredHeight() - 200 && y < getMeasuredHeight();
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        getHanderX(x);
        getMarkX(mHanderX);
        postInvalidate();
        return super.onTouchEvent(event);
    }

    private void getMarkX(float x) {
        markX = x - 100;
        if (markX > getMeasuredWidth() - r * 2) {
            markX = getMeasuredWidth() - r * 2;
        }
    }

    private void getHanderX(float x) {
        mHanderX = x;
        if (mHanderX < 100) {
            mHanderX = 100;
        }
        if (mHanderX > getMeasuredWidth() - 100) {
            mHanderX = getMeasuredWidth() - 100;
        }
    }


}
