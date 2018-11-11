package com.android.yzy.opengldemo.util;

/**
 * Created by yzy on 2018/11/11.
 */

public class MatrixHelper {

    public static void perspectiveM(float[] m, float yFovInDegrees, float aspect, float n, float f) {

        // 计算焦距
        final float angleInRadians = (float) (yFovInDegrees * Math.PI / 180.0);

        final float a = (float) (1.0f / Math.tan(angleInRadians / 2.0f));

        m[0] = a / aspect;
        m[1] = 0f;
        m[2] = 0f;
        m[3] = 0f;

        m[4] = 0f;
        m[5] = a;
        m[6] = 0f;
        m[7] = 0f;

        m[8] = 0f;
        m[9] = 0f;
        m[10] = -((f + n) / (f - n));
        m[11] = -1f;

        m[12] = 0f;
        m[13] = 0f;
        m[14] = -((2f * f * n) / (f-n));
        m[15] = 0f;

    }

}
