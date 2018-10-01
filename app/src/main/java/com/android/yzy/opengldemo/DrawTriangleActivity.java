package com.android.yzy.opengldemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yzy on 2018/10/1.
 */

public class DrawTriangleActivity extends OpenGLESActivity {

    float vertexArray[] = {
            -0.8f, -0.4f * 1.732f, 0.0f,
            0.0f, -0.4f * 1.732f, 0.0f,
            -0.4f, 0.4f * 1.732f, 0.0f,

            0.0f, -0.0f * 1.732f, 0.0f,
            0.8f, -0.0f * 1.732f, 0.0f,
            0.4f, 0.4f * 1.732f, 0.0f
    };

    long mLastTime;

    int index;

    @Override
    public void drawScene(GL10 gl) {
        super.drawScene(gl);

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexArray.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = vbb.asFloatBuffer();
        floatBuffer.put(vertexArray);
        floatBuffer.position(0);

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -4.0f);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0 ,floatBuffer);

        long now = System.currentTimeMillis();
        if (now - mLastTime > 2000) {
            mLastTime = now;
            index++;
            index%=3;
        }
        switch (index) {
            case 0:
                gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
                gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
                break;
            case 1:
                gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 6);
                break;
            case 2:
                gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
                gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 6);
                break;
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
