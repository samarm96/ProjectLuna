package com.luna.game.Engine.UI;

import java.util.ArrayList;
import java.util.List;

public class MessageBox {
    
    private List<String> messages;

    public MessageBox() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        if(messages.size() == 10) {
            messages.remove(0);
        }

        messages.add(message);
    }

    public String getMessages() {
        StringBuffer sb = new StringBuffer();
        for(String message : messages) {
            sb.append(System.lineSeparator());
            sb.append(message);
        }

        return sb.toString();
    }
}
