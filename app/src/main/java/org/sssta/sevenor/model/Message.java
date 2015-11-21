package org.sssta.sevenor.model;

import org.sssta.sevenor.R;

/**
 * Created by Heaven on 2015/11/21.
 */
public class Message {
    public static final int IMAGE_MESSAGE = 0;
    public static final int ATRICLE_MESSAGE = 1;
    int type;
    String text;
    int imageIndex;

    public Message(int type, String text, int imageIndex) {
        this.type = type;
        this.text = text;
        this.imageIndex = imageIndex;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getImageIndex() {
        return imageIndex;
    }
}
