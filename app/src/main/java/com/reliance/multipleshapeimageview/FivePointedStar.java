package com.reliance.multipleshapeimageview;

import android.graphics.Path;

/**
 * Created by sunzhishuai on 17/2/19.
 * E-mail itzhishuaisun@sina.com
 */

public class FivePointedStar implements MultipleShapedImageView.MultipleShaper {
    @Override
    public Path makeDrawShaperPath(int drawSpaceWidth, int drawSpaceHeight) {
        int radius = (drawSpaceWidth<drawSpaceHeight?drawSpaceWidth:drawSpaceHeight)/2;
        Path path = new Path();
        float radian = degree2Radian(36);// 36为五角星的角度
        float radius_in = (float) (radius * Math.sin(radian / 2) / Math
                .cos(radian)); // 中间五边形的半径

        path.moveTo((float) (radius * Math.cos(radian / 2)), 0);// 此点为多边形的起点
        path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
                        * Math.sin(radian)),
                (float) (radius - radius * Math.sin(radian / 2)));
        path.lineTo((float) (radius * Math.cos(radian / 2) * 2),
                (float) (radius - radius * Math.sin(radian / 2)));
        path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
                        * Math.cos(radian / 2)),
                (float) (radius + radius_in * Math.sin(radian / 2)));
        path.lineTo(
                (float) (radius * Math.cos(radian / 2) + radius
                        * Math.sin(radian)), (float) (radius + radius
                        * Math.cos(radian)));
        path.lineTo((float) (radius * Math.cos(radian / 2)),
                (float) (radius + radius_in));
        path.lineTo(
                (float) (radius * Math.cos(radian / 2) - radius
                        * Math.sin(radian)), (float) (radius + radius
                        * Math.cos(radian)));
        path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
                        * Math.cos(radian / 2)),
                (float) (radius + radius_in * Math.sin(radian / 2)));
        path.lineTo(0, (float) (radius - radius * Math.sin(radian / 2)));
        path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
                        * Math.sin(radian)),
                (float) (radius - radius * Math.sin(radian / 2)));

        path.close();// 使这些点构成封闭的多边形
        return path;
    }
    private float degree2Radian(int degree) {
        // TODO Auto-generated method stub
        return (float) (Math.PI * degree / 180);
    }
}
