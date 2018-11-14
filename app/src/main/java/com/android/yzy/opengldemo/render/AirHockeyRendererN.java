package com.android.yzy.opengldemo.render;

import android.content.Context;
import static android.opengl.GLES20.*;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.android.yzy.opengldemo.R;
import com.android.yzy.opengldemo.data.Mallet;
import com.android.yzy.opengldemo.data.Table;
import com.android.yzy.opengldemo.programs.ColorShaderProgram;
import com.android.yzy.opengldemo.programs.TextureShaderProgram;
import com.android.yzy.opengldemo.util.MatrixHelper;
import com.android.yzy.opengldemo.util.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yzy on 2018/10/27.
 */

public class AirHockeyRendererN implements GLSurfaceView.Renderer{

    private Context mContext;

    private final float[] projectionMatrix = new float[16];

    private final float[] modelMatrix = new float[16];

    private Table table;
    private Mallet mallet;

    private TextureShaderProgram textureShaderProgram;
    private ColorShaderProgram colorShaderProgram;

    private int texture;

    public AirHockeyRendererN(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        table = new Table();
        mallet = new Mallet();

        textureShaderProgram = new TextureShaderProgram(mContext);
        colorShaderProgram = new ColorShaderProgram(mContext);

        texture = TextureHelper.loadTexture(mContext, R.drawable.air_hockey_surface);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置视口，surface的大小
        glViewport(0, 0, width, height);

        MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1f, 10f);
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, 0f, 0f, -2f);

        Matrix.translateM(modelMatrix, 0, 0f, 0f, -2.5f);
        Matrix.rotateM(modelMatrix, 0, -60f, 1f, 0f, 0f);

        final float[] temp = new float[16];
        Matrix.multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
        System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);

        textureShaderProgram.useProgram();
        textureShaderProgram.setUniforms(projectionMatrix, texture);
        table.bindData(textureShaderProgram);
        table.draw();

        colorShaderProgram.useProgram();
        colorShaderProgram.setUniforms(projectionMatrix);
        mallet.bindData(colorShaderProgram);
        mallet.draw();
    }
}
