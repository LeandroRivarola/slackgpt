package com.lemon.slack.openai.request;

import com.lemon.slack.openai.dto.Message;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiRequest {

    private String model;
    private List<Message> messages;

    public ApiRequest(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

}
