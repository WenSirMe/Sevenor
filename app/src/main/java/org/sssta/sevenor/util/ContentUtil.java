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
            "公然抱我入竹去,唇角口燥呼喊不得.\n归来倚仗自叹息.\n\n 同行十二年 \n不知木兰是何人",
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
                    "point-to-point and multipoint wireless features.",
            "鱼的化石在地壳中能存在两亿年。\n\n这两亿年过去，山变成海，海又变成山。所有岩石都风化殆尽。\n\n" +
                    "而那条鱼可能还固执的认为自己还活着，固执的相信下一个两亿年，能够遇见谁，爱上谁。",
            " 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤 蛤蛤蛤蛤蛤蛤蛤"
    };
    private static String[] titles = {
            "鲁迅原名柴大彪",
            "古文",
            "我把API文档复制了三遍",
            "晚上三点多困了乱写",
            "凑一篇显得多"
    };
    public static ArrayList<Message> getImageContent(){
        int i;
        ArrayList<Message> arrayList = new ArrayList<>();
        for (i=0;i<7;i++)
            arrayList.add(new Message(0,"哈哈", R.drawable.bg_temp_image_8-(i%2)*2, ""));
        return arrayList;
    }
    public static ArrayList<Message> getTextContent(){
        int i;
        if (!hasInit)
            init();
        ArrayList<Message> arrayList = new ArrayList<>();
        for (i=0;i<7;i++)
            arrayList.add(new Message(0,texts[i%(texts.length)], 0, titles[i%(texts.length)]));
        return arrayList;
    }
    private static void init(){
        hasInit = true;
        StringBuffer stringBuffer = new StringBuffer(texts[2]);
        texts[2] = stringBuffer.append(texts[2]).append(texts[2]).append(texts[2]).toString();
    }
}
