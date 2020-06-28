package com.entity.chat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/15 20:58
 */
public class Message {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String content;
}
