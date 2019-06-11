package com.chat.message.type;

import com.chat.message.NetMessage;

import java.util.Date;

public class TextMessage extends NetMessage {

    private String name;
    private long id;
    private String text;
    private Date date = null;

    public TextMessage(String name, long id, String text) {
        this.name = name;
        this.id = id;
        this.text = text;
    }

    public String getUserName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getUserID() {
        return id;
    }
}
