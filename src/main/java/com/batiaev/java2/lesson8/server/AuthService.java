package com.batiaev.java2.lesson8.server;

/**
 * AuthService
 * Net Chat App of Java2
 * @author Anton Batiaev
 * @since 06/11/17
 */
public interface AuthService {

    String getNick(String login, String pass);

    boolean login(String login, String pass);

    boolean contains(String userName);
}