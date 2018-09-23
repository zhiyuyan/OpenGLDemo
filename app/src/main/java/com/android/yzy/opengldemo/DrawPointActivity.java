package com.android.yzy.opengldemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yzy on 2018/9/23.
 */

public class DrawPointActivity extends OpenGLESActivity {

    float[] vertexArray = new float[] {
            -0.8f , -0.4f * 1.732f , 0.0f ,
            0.8f , -0.4f * 1.732f , 0.0f ,
            0.0f , 0.4f * 1.732f , 0.0f
    };

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        ByteBuffer buffer = ByteBuffer.allocateDirect(vertexArray.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        FloatBuffer vertextBuffer = buffer.asFloatBuffer();
        vertextBuffer.put(vertexArray);
        vertextBuffer.position(0);

        gl.glColor4f(1f, 0f, 0f, 1f);
        gl.glPointSize(8f);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -4);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertextBuffer);
        gl.glDrawArrays(GL10.GL_POINTS, 0, 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
