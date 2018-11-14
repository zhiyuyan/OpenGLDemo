package com.android.yzy.opengldemo.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.yzy.opengldemo.render.AirHockeyRenderer;
import com.android.yzy.opengldemo.render.AirHockeyRendererN;


/**
 * Created by yzy on 2018/10/27.
 */

public class AirHockeyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        // 记得声明使用OpenGL ES 版本
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new AirHockeyRendererN(this));
        setContentView(glSurfaceView);
    }

}
