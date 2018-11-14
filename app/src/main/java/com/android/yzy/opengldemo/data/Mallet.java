package com.android.yzy.opengldemo.data;

import com.android.yzy.opengldemo.programs.ColorShaderProgram;

import static com.android.yzy.opengldemo.data.Constants.BYTES_PER_FLOAT;

import static android.opengl.GLES20.*;

/**
 * Created by yzy on 2018/11/12.
 */

public class Mallet {

    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    protected static final int STRIE =
            (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;

    private static final float[] VERTEX_DATA = {
            0f, -0.4f, 0f, 0f, 1f,
            0f, 0.4f, 1f, 0f, 0f
    };

    private final VertexArray vertexArray;

    public Mallet() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(ColorShaderProgram colorShaderProgram) {
        vertexArray.setVertexAttribPointer(
                0,
                colorShaderProgram.getPositionLocation(),
                POSITION_COMPONENT_COUNT,
                STRIE
        );
        vertexArray.setVertexAttribPointer(
                POSITION_COMPONENT_COUNT,
                colorShaderProgram.getColorAttributeLocation(),
                COLOR_COMPONENT_COUNT,
                STRIE
        );
    }

    public void draw() {
        glDrawArrays(GL_POINTS, 0 , 2);
    }

}
