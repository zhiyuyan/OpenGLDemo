package com.android.yzy.opengldemo.programs;

import android.content.Context;

import com.android.yzy.opengldemo.R;


import static android.opengl.GLES20.*;
/**
 * Created by yzy on 2018/11/12.
 */

public class ColorShaderProgram extends ShaderProgram {

    private final int uMatrixLocation;

    private final int aPositionLocation;
    private final int aColorLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.simple_vertex_shader, R.raw.simple_frame_shader);

        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);

        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aColorLocation = glGetAttribLocation(program, A_COLOR);
    }

    public void setUniforms(float[] matrix) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }

    public int getPositionLocation() {
        return aPositionLocation;
    }

    public int getColorAttributeLocation() {
        return aColorLocation;
    }
}
