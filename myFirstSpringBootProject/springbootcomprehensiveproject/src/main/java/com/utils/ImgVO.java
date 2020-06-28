package com.utils;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/4/20 17:19
 */
public class ImgVO {

    public ImgVO() {
    }

    public ImgVO(String img, String uuid) {
        this.img = img;
        this.uuid = uuid;
    }

    private String img;
    private String uuid;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ImgVO{" +
                "img='" + img + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}