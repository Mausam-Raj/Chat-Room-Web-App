package com.Project.ChatRoomApp.Models;
/*
 * @author : Mausam Raj
 * @Date : 14-05-2024
 */

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Message {
//    Name of the sender.
    private String name;
//    Content of the message.
    private String content;

}
