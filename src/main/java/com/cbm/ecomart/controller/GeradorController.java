package com.cbm.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerador")
public class GeradorController {

    private final ChatClient chatClient;

    public GeradorController (@Qualifier("gpt-4o-mini") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping
    public String gerarProdutos() {
        var userInput = "Gerar 5 produtos ecologicos";

        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }
}
