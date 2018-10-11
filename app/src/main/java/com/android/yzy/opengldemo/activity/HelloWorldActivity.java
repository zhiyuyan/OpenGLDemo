package com.android.yzy.opengldemo.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.yzy.opengldemo.render.HelloWorldRenderer;

/**
 * Created by yzy on 2018/10/9.
 */

public class HelloWorldActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new HelloWorldRenderer());
        setContentView(glSurfaceView);
    }
}
