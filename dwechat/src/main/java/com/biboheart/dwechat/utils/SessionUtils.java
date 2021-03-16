package com.biboheart.dwechat.utils;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static void showSession(HttpSession session, String page) {
        if (session.isNew()) {
            System.out.println("Successfully creates a session ，the id of session ：" + session.getId() + "---from page:" + page);
        } else {
            System.out.println("session already exists in the server, the id of session ：" + session.getId() + "---from page:" + page);
        }
    }
}
