package com.android.yzy.opengldemo;

import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yzy on 2018/10/6.
 */

public class DrawSphereActivity extends OpenGLESActivity {

    private Sphere sphere = new Sphere();

    @Override
    public void drawScene(GL10 gl) {
        super.drawScene(gl);
        initScene(gl);
        sphere.draw(gl);
    }

    public void initScene(GL10 gl) {
        float[] amb = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] diff = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] spec = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] pos = {0.0f, 5.0f, 5.0f, 1.0f};
        float[] spot_dir = {0.0f, -1.0f, 0.0f};
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_CULL_FACE);

        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);

        ByteBuffer abb = ByteBuffer.allocateDirect(amb.length * 4);
        abb.order(ByteOrder.nativeOrder());
        FloatBuffer ambBuf = abb.asFloatBuffer();
        ambBuf.put(diff);
        ambBuf.position(0);

        ByteBuffer dbb = ByteBuffer.allocateDirect(diff.length * 4);
        dbb.order(ByteOrder.nativeOrder());
        FloatBuffer diffBuf = dbb.asFloatBuffer();
        diffBuf.put(diff);
        diffBuf.position(0);

        ByteBuffer sbb = ByteBuffer.allocateDirect(spec.length * 4);
        sbb.order(ByteOrder.nativeOrder());
        FloatBuffer specBuf = sbb.asFloatBuffer();
        specBuf.put(spec);
        specBuf.position(0);

        ByteBuffer pbb = ByteBuffer.allocateDirect(pos.length * 4);
        pbb.order(ByteOrder.nativeOrder());
        FloatBuffer posBuf = pbb.asFloatBuffer();
        posBuf.put(pos);
        posBuf.position(0);

        ByteBuffer spbb = ByteBuffer.allocateDirect(spot_dir.length * 4);
        spbb.order(ByteOrder.nativeOrder());
        FloatBuffer spot_dirBuff = spbb.asFloatBuffer();
        spot_dirBuff.put(spot_dir);
        spot_dirBuff.position(0);

        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambBuf);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffBuf);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specBuf);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, posBuf);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION, spot_dirBuff);
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_EXPONENT, 0.0f);
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_CUTOFF, 45.0f);

        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 0.0f, 4.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }
}
