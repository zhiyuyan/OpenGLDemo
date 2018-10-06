package com.android.yzy.opengldemo;

import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yzy on 2018/10/2.
 */

public class DrawSolarSystem extends OpenGLESActivity {

    private Star sun = new Star();
    private Star earth = new Star();
    private Star moon = new Star();

    private int angle = 0;

    @Override
    public void drawScene(GL10 gl) {
        super.drawScene(gl);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl,
                0.0f, 0.0f, 55.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);

        // Star A
        gl.glPushMatrix();
        gl.glRotatef(angle, 0, 0, 1);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        sun.draw(gl);
        gl.glPopMatrix();

        // Star B
        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(3, 0, 0);
        gl.glScalef(.5f, .5f, .5f);
        gl.glColor4f(0f, 0f, 1f, 1f);
        earth.draw(gl);


        // Star C
        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(2, 0, 0);
        gl.glScalef(0.5f, .5f, .5f);
        gl.glRotatef(angle*10, 0, 0, 1);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        moon.draw(gl);

        gl.glPopMatrix();

        gl.glPopMatrix();
        angle++;
    }
}
