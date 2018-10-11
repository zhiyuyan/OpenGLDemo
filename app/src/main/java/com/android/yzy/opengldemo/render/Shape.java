package com.android.yzy.opengldemo.render;

import android.opengl.GLES20;

/**
 * Created by yzy on 2018/10/7.
 */

public abstract class Shape{


    public int loadShader(int type, String shaderCode) {
        // 根据type创建顶点着色器或片元着色器
        int shader = GLES20.glCreateShader(type);
        // 将资源加入到着色器中，并进行编译
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

}
