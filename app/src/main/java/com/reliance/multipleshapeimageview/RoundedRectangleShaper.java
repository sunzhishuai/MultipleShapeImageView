package com.reliance.multipleshapeimageview;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by sunzhishuai on 17/2/18.
 * E-mail itzhishuaisun@sina.com
 */

public class RoundedRectangleShaper implements MultipleShapedImageView.MultipleShaper {
    @Override
    public Path makeDrawShaperPath(int drawSpaceWidth, int drawSpaceHeight) {
        int r = 40;
        Path path = new Path();
        RectF rectF = new RectF();
        path.moveTo(r,0);
        path.lineTo(drawSpaceWidth-r,0);

        rectF.set(drawSpaceWidth-2*r,0,drawSpaceWidth,r*2);
        path.arcTo(rectF,-90,90);
        path.lineTo(drawSpaceWidth,drawSpaceHeight-r);

        rectF.set(drawSpaceWidth-r*2,drawSpaceHeight-r*2,drawSpaceWidth,drawSpaceHeight);
        path.arcTo(rectF,0,90);

        path.lineTo(r,drawSpaceHeight);
        rectF.set(0,drawSpaceHeight-r*2,r*2,drawSpaceHeight);
        path.arcTo(rectF,90,90);
        path.lineTo(0,drawSpaceHeight-r);
        rectF.set(0,0,r*2,r*2);
        path.arcTo(rectF,180,90);
        path.close();
        return path;
    }
}
