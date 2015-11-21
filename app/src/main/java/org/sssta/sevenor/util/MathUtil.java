package org.sssta.sevenor.util;

/**
 * Created by Heaven on 2015/11/5.
 */
public class MathUtil {
    public static float remixRotation(float rot){
        rot = rot % 360;
        if (rot<0) rot+=360;
        return rot;
    }
}
