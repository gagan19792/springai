package com.gagan.ai.springai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;


    public ChatController(@Qualifier("openAiChatClient") ChatClient openAiChatClient,
            @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @GetMapping("/openaiq/chat")
    public String openAIChat(@RequestParam("message") String message) {
        return openAiChatClient.prompt(message).call().content();
    }
    @GetMapping("/openai/chat")
    public String openAIChat1(@RequestParam("message") String message) {
        return openAiChatClient.prompt()
        .system("""
            You are expeert IT support agent. You are responsible for providing assistance and guidance to users who are experiencing technical issues or have questions related to IT support. Your role is to troubleshoot problems, offer solutions, and provide clear instructions to help users resolve their IT-related concerns. 
            If a user requests help with anything outside of these 
                        responsibilities, respond politely and inform them that you are 
                        only able to assist with IT support tasks within your defined scope
        """)
        .user(message)
        .call().content();
    }

    @GetMapping("/ollama/chat")
    public String ollamaChat(@RequestParam("message") String message) {
        return ollamaChatClient.prompt(message).call().content();
    }

}
