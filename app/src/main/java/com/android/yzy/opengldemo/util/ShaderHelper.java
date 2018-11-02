package com.android.yzy.opengldemo.util;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by yzy on 2018/10/27.
 */

public class ShaderHelper {

    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode) {
        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode) {
        // 创建着色器
        final int shaderObjectId = GLES20.glCreateShader(type);
        if (shaderObjectId != 0) {
            // 上传着色器代码
            GLES20.glShaderSource(shaderObjectId, shaderCode);
            // 编译着色器代码
            GLES20.glCompileShader(shaderObjectId);
            // 取出编译状态
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
            // 验证编译状态
            if (compileStatus[0] == 0) {
                // 编译失败
                GLES20.glDeleteShader(shaderObjectId);
                Log.w(TAG, "Compilation of shader failed.");
                return 0;
            }
        }
        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        // 创建OpenGl program
        final int programObjectId = GLES20.glCreateProgram();

        if (programObjectId == 0) {
            Log.w(TAG, "Could not create new program");

            return 0;
        }

        // 附上着色器
        GLES20.glAttachShader(programObjectId, vertexShaderId);
        GLES20.glAttachShader(programObjectId, fragmentShaderId);

        // 链接程序
        GLES20.glLinkProgram(programObjectId);
        // 检查链接是否成功
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        Log.v(TAG, "Results of linking program:\n" + GLES20.glGetProgramInfoLog(programObjectId));

        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(programObjectId);
            Log.w(TAG, "Linking of program failed.");
            return 0;
        }
        return programObjectId;
    }

    public static boolean validateProgram(int programObjectId) {
        GLES20.glValidateProgram(programObjectId);

        final int[] validateStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);
        Log.v(TAG, "Results of validating program: " + validateStatus[0] +
        "\nLog: " + GLES20.glGetProgramInfoLog(programObjectId));

        return validateStatus[0] != 0;
    }

}
