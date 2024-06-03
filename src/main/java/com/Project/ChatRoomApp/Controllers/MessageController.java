package com.Project.ChatRoomApp.Controllers;
/*
 * @author : Mausam Raj
 * @Date : 14-05-2024
 */

import com.Project.ChatRoomApp.Models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @MessageMapping("/send")
    @SendTo("/topic/chat/messages")
    public Message getContent(@RequestBody Message message) {
//        Method to get the content of the message.
        return message;
    }
}
