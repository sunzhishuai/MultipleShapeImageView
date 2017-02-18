package com.reliance.multipleshapeimageview;

import android.graphics.Path;

/**
 * Created by sunzhishuai on 17/2/19.
 * E-mail itzhishuaisun@sina.com
 */

public class HeartShaped implements MultipleShapeImageView.MultipleShaper {
    @Override
    public Path makeDrawShaperPath(int drawSpaceWidth, int drawSpaceHeight) {
        // 绘制心形
        Path path = new Path();
        path.moveTo((float) (0.5*drawSpaceWidth), (float) (0.17*drawSpaceHeight));
        path.cubicTo((float) (0.15*drawSpaceWidth), (float) (-0.35*drawSpaceHeight), (float) (-0.4*drawSpaceWidth), (float) (0.45*drawSpaceHeight), (float) (0.5*drawSpaceWidth),drawSpaceHeight);
        path.moveTo((float) (0.5*drawSpaceWidth),drawSpaceHeight);
        path.cubicTo((float) (drawSpaceWidth+0.4*drawSpaceWidth), (float) (0.45*drawSpaceHeight),(float) (drawSpaceWidth-0.15*drawSpaceWidth), (float) (-0.35*drawSpaceHeight),(float) (0.5*drawSpaceWidth), (float) (0.17*drawSpaceHeight));
        path.close();
        return path;
    }
}
