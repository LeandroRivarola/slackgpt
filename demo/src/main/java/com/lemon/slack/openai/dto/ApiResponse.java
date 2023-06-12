package com.lemon.slack.openai.dto;

import java.util.List;

public class ApiResponse {

    private List<Choice> choices;

    public ApiResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public ApiResponse() {

    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {

        private int index;
        private Message message;

        public Choice(int index, Message message) {
            this.index = index;
            this.message = message;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public Choice() {

        }
    }

}
