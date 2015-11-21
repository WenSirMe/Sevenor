package org.sssta.sevenor.util;

import org.sssta.sevenor.R;
import org.sssta.sevenor.model.Message;

import java.util.ArrayList;

/**
 * Created by Heaven on 2015/11/21.
 */
public class ContentUtil {
    private static boolean hasInit = false;
    private static String[] texts = {
            "我家门前有两棵柳树，一颗是桃树，另一颗也是杨树，还有一条哈士奇。",
            "这世界从来不关心我们怎样活着。\n也不会在意我们之中是否有人活着.",
            "The Android platform includes support for the Bluetooth network stack," +
                    " which allows a device to wirelessly exchange data with other " +
                    "Bluetooth devices. The application framework provides access to " +
                    "the Bluetooth functionality through the Android Bluetooth APIs. " +
                    "These APIs let applications wirelessly connect to other Bluetooth devices, " +
                    "enabling point-to-point and multipoint wireless features.The Android platform " +
                    "includes support for the Bluetooth network stack, which" +
                    " allows a device to wirelessly exchange data with other Bluetooth" +
                    " devices. The application framework provides access to the Bluetooth " +
                    "functionality through the Android Bluetooth APIs. These APIs let " +
                    "applications wirelessly connect to other Bluetooth devices, enabling " +
                    "point-to-point and multipoint wireless features."
    };
    public static ArrayList<Message> getImageContent(){
        int i;
        ArrayList<Message> arrayList = new ArrayList<>();
        for (i=0;i<7;i++)
            arrayList.add(new Message(0,"哈哈", R.drawable.bg_temp_image_8-(i%2)*2));
        return arrayList;
    }
    public static ArrayList<Message> getTextContent(){
        int i;
        if (!hasInit)
            init();
        ArrayList<Message> arrayList = new ArrayList<>();
        for (i=0;i<7;i++)
            arrayList.add(new Message(0,texts[i%(texts.length)], 0));
        return arrayList;
    }
    private static void init(){
        hasInit = true;
        StringBuffer stringBuffer = new StringBuffer(texts[2]);
        texts[2] = stringBuffer.append(texts[2]).append(texts[2]).append(texts[2]).toString();
    }
}
