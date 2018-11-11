package com.android.yzy.opengldemo.render;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.android.yzy.opengldemo.R;
import com.android.yzy.opengldemo.util.ShaderHelper;
import com.android.yzy.opengldemo.util.TextResourceReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yzy on 2018/10/27.
 */

public class AirHockeyRenderer implements GLSurfaceView.Renderer{

    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int BYTES_PER_FLOAT = 4;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;

    private Context mContext;

    private static final String A_POSITION = "a_Position";
    private int aPositionLocation;

    private static final String A_COLOR = "a_Color";
    private int aColor;

    private int mProgram;

    public AirHockeyRenderer(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 准备顶点数据
        float[] tableVertices = {
                // Triangle
                0f, 0f, 1f, 1f, 1f,
                -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
                0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
                0.5f, 0.5f, 0.7f, 0.7f, 0.7f,
                -0.5f, 0.5f, 0.7f, 0.7f, 0.7f,
                -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,

                // Line
                -0.5f, 0f, 1f, 0f, 0f,
                0.5f, 0f, 1f, 0f, 0f,

                // Point 1
                0f, -0.25f, 0f, 0f, 1f,

                // Point 2
                0f, 0.25f, 1f, 0f, 0f
        };

        FloatBuffer vertexData = ByteBuffer.allocateDirect(tableVertices.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(tableVertices);

        // 设置背景颜色
        GLES20.glClearColor(0.0f,0.0f, 0.0f, 0.0f);

        // 读入着色器代码
        String vertexShaderSource = TextResourceReader
                .readTextFileFromResource(mContext, R.raw.simple_vertex_shader);
        String frameShaderSource = TextResourceReader
                .readTextFileFromResource(mContext, R.raw.simple_frame_shader);

        // 编译着色器
        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.compileFragmentShader(frameShaderSource);

        // 链接着色器
        mProgram = ShaderHelper.linkProgram(vertexShader, fragmentShader);

        // 验证OpenGL程序对象
        ShaderHelper.validateProgram(mProgram);

        // 使用OpenGL程序对象
        GLES20.glUseProgram(mProgram);

        vertexData.position(0);
        aPositionLocation = GLES20.glGetAttribLocation(mProgram, A_POSITION);
        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT,
                false, STRIDE, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);

        vertexData.position(POSITION_COMPONENT_COUNT);
        aColor = GLES20.glGetAttribLocation(mProgram, A_COLOR);
        GLES20.glVertexAttribPointer(aColor, COLOR_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, vertexData);
        GLES20.glEnableVertexAttribArray(aColor);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置视口，surface的大小
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // 清除颜色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // 绘制桌子
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);

        // 绘制分隔线
        GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);

        // 绘制点
        GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);

        GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);
    }
}
