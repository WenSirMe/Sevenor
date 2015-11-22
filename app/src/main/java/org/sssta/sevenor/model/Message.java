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
    String title;

    public Message(int type, String text, int imageIndex,String title) {
        this.type = type;
        this.text = text;
        this.imageIndex = imageIndex;
        this.title = title;
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

    public String getTitle() {
        return title;
    }
}
