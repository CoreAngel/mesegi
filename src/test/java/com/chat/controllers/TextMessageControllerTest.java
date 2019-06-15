package com.chat.controllers;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TextMessageControllerTest {

    @Test
    void getTimeFromDate() {
        String dateStr = "31-12-1998 23:37:50";
        SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Date date = dateFormater.parse(dateStr);
            TextMessageController controller = new TextMessageController("text", date);

            String resultDate = controller.getTimeFromDate();

            assertEquals(resultDate, "23:37:50");
        } catch (Exception e) {
            //igonore
        }




    }

}
