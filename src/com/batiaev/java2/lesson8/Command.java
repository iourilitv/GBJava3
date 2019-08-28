package com.batiaev.java2.lesson8;

/**
 * Command
 * Net Chat App of Java2
 * @author Anton Batiaev
 * @since 09/11/17
 */
public enum Command {//TODO Useful to use
    AUTH_COMMAND("/auth"),
    AUTHOK_COMMAND("/authok"),
    DISCONNECTED("/disconnected"),
    PRIVATE_MESSAGE("/w"),
    CHAT_MESSAGE("/clients");

    public String getText() {
        return text;
    }

    private String text;

    Command(String s) {
        text = s;
    }
}