package org.sssta.sevenor.model;

import java.io.Serializable;

/**
 * Created by 林韬 on 2015/11/21.
 */
public class Person implements Serializable{
    private String name;
    private String image_url;
    private int sex = 0;


    public int resourceId;

    public Person(String name, String image_url,int sex) {
        this.name = name;
        this.image_url = image_url;
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
