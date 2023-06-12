package com.lemon.slack.openai.controller;

import com.lemon.slack.openai.dto.ApiResponse;
import com.lemon.slack.openai.request.ApiRequest;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.Conversation;
import com.slack.api.model.Message;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class SlackController {

    @Value("${slack.token}")
    private String slackToken;

    @Value("${slack.channel}")
    private String slackChannel;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate gptTemplate;

    @PostMapping("/slack/summarize")
    public String summarizeSlackChannel() {

        String slackChannelContent = getSlackChat();

        String summarizedContent = sendContentToGpt(slackChannelContent);

        return summarizedContent;
    }

    private void findConversation(String name) {

        var client = Slack.getInstance().methods();
        var logger = LoggerFactory.getLogger("my-awesome-slack-app");

        try {
            var result = client.conversationsList(r -> r
                    .token(slackToken)
            );

            for (Conversation channel : result.getChannels()) {

                if (channel.getName().equals("general")) {

                    var conversationId = channel.getId();
                    logger.info("Found conversation ID: {}", conversationId);
                    break;
                }
            }
        } catch (IOException | SlackApiException e) {
            logger.error("error: {}", e.getMessage(), e);
        }
    }

    private String getSlackChat() {

        findConversation("general");

        var client = Slack.getInstance().methods();
        var logger = LoggerFactory.getLogger("my-awesome-slack-app");
        try {
            var result = client.conversationsHistory(r -> r
                    .token(slackToken)
                    .channel("C05C1QZCP7T")
            );
            Optional<List<Message>> conversationHistory = Optional.ofNullable(result.getMessages());

            logger.info("{} messages found in {}", conversationHistory.orElse(emptyList()).size(), "C05C1QZCP7T");
            return result.getMessages().toString();

        } catch (IOException | SlackApiException e) {
            logger.error("error: {}", e.getMessage(), e);
            return null;
        }
    }


    private String sendContentToGpt(String content) {

        ApiRequest request = new ApiRequest(model, "Haz un resumen de este grupo de slack,"
                + " separando el contenido segun quien escribe" + content);

        ApiResponse response = gptTemplate.postForObject(apiUrl, request, ApiResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        return response.getChoices().get(0).getMessage().getContent();
    }
}
