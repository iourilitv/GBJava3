package com.batiaev.java2.lesson8.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseAuthService
 * Net Chat App of Java2
 * @author Anton Batiaev
 * @since 06/11/17
 */
public class BaseAuthService implements AuthService {

    //TODO lesson6-hw-Task3
    //инициализируем логгера
    private static final Logger log = LoggerFactory.getLogger(BaseAuthService.class);

    private class Entry {
        private String login;
        private String password;
        private String nick;

        public Entry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public String getNick() {
            return nick;
        }
    }

    private List<Entry> entries;

    public BaseAuthService() {
        init();
    }

    public int init() {
        entries = new ArrayList<>();

        //TODO Временно
        entries.add(new Entry("0", "0", "nick00"));
        entries.add(new Entry("1", "1", "nick01"));
        entries.add(new Entry("2", "2", "nick02"));
        entries.add(new Entry("3", "3", "nick03"));

        entries.add(new Entry("login1", "pass1", "nick1"));
        entries.add(new Entry("login2", "pass2", "nick2"));
        entries.add(new Entry("login3", "pass3", "nick3"));
        return entries.size();
    }

    @Override
    public String getNick(String login, String pass) {
        for (Entry e : entries) {
            if (e.getLogin().equals(login) && e.getPassword().equals(pass)) return e.getNick();
        }
        return null;
    }

    @Override
    public boolean login(String login, String pass) {
        for (Entry e : entries) {
            if (e.getLogin().equals(login) && e.getPassword().equals(pass)) return true;
        }
        return false;
    }

    @Override
    public boolean contains(String userName) {
        if (userName == null || userName.trim().isEmpty()) return false;

        for (Entry e : entries) {
            if (userName.equals(e.getNick())) return true;
        }
        return false;
    }
}