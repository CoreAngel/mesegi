package com.chat.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class ChatControllerTest {
    private final ChatController controller = new ChatController();

    @BeforeEach
    void setUserData() {
        TreeMap<Long, String> list = new TreeMap<>();
        list.put((long)1, "test1");
        controller.setUserData(0, "test", list);
    }

    @Test
    void addUser() {
        controller.addUser(2, "test3");
        assertEquals(controller.getUsers().size(), 2);
    }

    @Test
    void removeUser() {
        controller.removeUser(1);
        assertEquals(controller.getUsers().size(), 0);
    }

}
