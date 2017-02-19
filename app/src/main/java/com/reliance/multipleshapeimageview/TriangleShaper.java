package com.reliance.multipleshapeimageview;

import android.graphics.Path;

/**
 * Created by sunzhishuai on 17/2/18.
 * E-mail itzhishuaisun@sina.com
 */

public class TriangleShaper implements MultipleShapedImageView.MultipleShaper {
    @Override
    public Path makeDrawShaperPath(int drawSpaceWidth, int drawSpaceHeight) {
        Path path = new Path();
        path.moveTo(drawSpaceWidth/2,0);
        path.lineTo(drawSpaceWidth,drawSpaceHeight);
        path.lineTo(0,drawSpaceHeight);
        path.lineTo(drawSpaceWidth/2,0);
        path.close();
        return path;
    }
}
