package com.android.yzy.opengldemo.programs;

import android.content.Context;


import com.android.yzy.opengldemo.R;

import static android.opengl.GLES20.*;
/**
 * Created by yzy on 2018/11/12.
 */

public class TextureShaderProgram extends ShaderProgram {

    private final int uMatrixLocation;
    private final int uTextureUnitLocation;

    private final int aPositionLocation;
    private final int aTextureCoordinatesLocation;

    public TextureShaderProgram(Context context) {
        super(context, R.raw.texture_vertex_shader,
                R.raw.texture_fragment_shader);

        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
        uTextureUnitLocation = glGetUniformLocation(program, U_TEXTURE_UNIT);

        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aTextureCoordinatesLocation = glGetAttribLocation(program, A_TEXTURE_COORDINATES);
    }

    public void setUniforms(float[] matrix, int textureId) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

        glActiveTexture(GL_TEXTURE0);

        glBindTexture(GL_TEXTURE_2D, textureId);

        glUniform1i(uTextureUnitLocation, 0);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }

    public int getTextureCoordinatesLocation() {
        return aTextureCoordinatesLocation;
    }
}
