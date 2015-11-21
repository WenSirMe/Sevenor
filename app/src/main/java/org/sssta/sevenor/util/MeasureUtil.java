package org.sssta.sevenor.util;

/**
 * Created by Heaven on 2015/11/21.
 */
public class MeasureUtil {
    private static int windowWidth;
    private static int windowHeight;

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static void setWindowWidth(int windowWidth) {
        MeasureUtil.windowWidth = windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static void setWindowHeight(int windowHeight) {
        MeasureUtil.windowHeight = windowHeight;
    }
}
