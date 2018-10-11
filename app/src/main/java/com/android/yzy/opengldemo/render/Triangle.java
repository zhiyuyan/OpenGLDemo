package com.android.yzy.opengldemo.render;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by yzy on 2018/10/7.
 */

public class Triangle extends Shape{

    // 顶点Buffer
    private FloatBuffer mVertexBuffer;

    // 顶点着色器
    private final String mVertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "   gl_Position = vPosition;" +
                    "}";

    // 片元着色器
    private final String mFragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "   gl_FragColor = vColor;" +
                    "}";

    private int mProgram;

    static final int COORDS_PER_VERTEX = 3;

    private float[] mVertex = {
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    private final int vertexCount = mVertex.length / COORDS_PER_VERTEX;

    private final int vertexStride = COORDS_PER_VERTEX * 4;

    private float[] color = {1.0f, 1.0f, 1.0f, 1.0f};

    public Triangle() {

        ByteBuffer bb = ByteBuffer.allocateDirect(mVertex.length * 4);
        bb.order(ByteOrder.nativeOrder());
        mVertexBuffer = bb.asFloatBuffer();
        mVertexBuffer.put(mVertex);
        mVertexBuffer.position(0);

        // 顶点着色器
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, mVertexShaderCode);

        // 片元着色器
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, mFragmentShaderCode);

        // 创建一个空的OpenGLES程序
        mProgram = GLES20.glCreateProgram();
        // 将顶点着色器加入到程序
        GLES20.glAttachShader(mProgram, vertexShader);
        // 将片元着色器加入到程序
        GLES20.glAttachShader(mProgram, fragmentShader);
        // 连接到着色器程序
        GLES20.glLinkProgram(mProgram);

    }

    public void draw() {
        // 将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram);

        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // 启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(positionHandle);

        // 准备三角形的坐标数据
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, mVertexBuffer);

        // 获取片元着色器的vColor成员的句柄
        int colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        // 设置绘制三角形的颜色
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
        // 绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        // 禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

}
