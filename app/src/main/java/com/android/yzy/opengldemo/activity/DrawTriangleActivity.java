package com.android.yzy.opengldemo.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.yzy.opengldemo.render.MyGLRenderer;

/**
 * Created by yzy on 2018/10/7.
 */

public class DrawTriangleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView surfaceView = new GLSurfaceView(this);
        surfaceView.setEGLContextClientVersion(2);
        surfaceView.setRenderer(new MyGLRenderer());
        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(surfaceView);
    }
}
