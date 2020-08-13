package com.example.mytestsurfaceview;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class HookUtils {

    public static void hookGLSurfaceViewRender(View decorView) {
        try {
            Class<?> viewClass = Class.forName("android.opengl.GLSurfaceView");
            Field rendererField = viewClass.getDeclaredField("mRenderer");
            rendererField.setAccessible(true);

            List<View> viewList = getAllChildViews(decorView);
            for (View view : viewList) {
                if (view instanceof GLSurfaceView) {
                    Object renderInfo = rendererField.get(view);
                    GLSurfaceView.Renderer proxy = new RendererProxy((GLSurfaceView.Renderer) renderInfo);
                    rendererField.set(view, proxy);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                //再次 调用本身（递归）
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }

    static class RendererProxy implements GLSurfaceView.Renderer {
        private GLSurfaceView.Renderer renderer;

        public RendererProxy(GLSurfaceView.Renderer rendererProxy) {
            renderer = rendererProxy;
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            if (renderer != null) {
                renderer.onSurfaceCreated(gl, config);
            }
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            if (renderer != null) {
                renderer.onSurfaceChanged(gl, width, height);
            }
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            Log.e("hzjhzj", "proxy:" + " onDrawFrame");
            FPSUtils.doFps();
            if (renderer != null) {
                renderer.onDrawFrame(gl);
            }
        }
    }
}
