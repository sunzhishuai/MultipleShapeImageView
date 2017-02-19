package com.reliance.multipleshapeimageview;

import android.graphics.Path;

/**
 * Created by sunzhishuai on 17/2/19.
 * E-mail itzhishuaisun@sina.com
 */

public class CircleShaper implements MultipleShapedImageView.MultipleShaper {
    @Override
    public Path makeDrawShaperPath(int drawSpaceWidth, int drawSpaceHeight) {
        Path path = new Path();
        int radius = (drawSpaceWidth<drawSpaceHeight?drawSpaceWidth:drawSpaceHeight)/2;
        path.addCircle(radius,radius,radius, Path.Direction.CW);
        return path;
    }
}
