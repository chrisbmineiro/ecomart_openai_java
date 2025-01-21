package com.cbm.ecomart.controller;

import com.cbm.ecomart.service.ContadorDeTokensService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorizador")
public class CategorizadorController {

    ContadorDeTokensService contador = new ContadorDeTokensService();

    private final ChatClient chatClient;

    public CategorizadorController(@Qualifier("gpt-4o-mini") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping
    public String categorizarProdutos(String produto) {
        var system = """
                Você é um categorizador de produtos e deve responder apenas o nome da categoria do produto informado
                
                Escolha uma categoria dentro da lista abaixo:
                1. Higiene pessoal
                2. Eletrônicos
                3. Esportes
                4. Outros
                
                ###### exemplo de uso:
                
                Pergunta: Bola de futebol
                Resposta: Esportes
                """;

        var tokens = contador.contarTokens(system, produto);
        if (tokens > 3000) {
            throw new RuntimeException("O limite de tokens foi excedido");
        }

        return this.chatClient.prompt()
                .system(system)
                .user(produto)
                .options(ChatOptionsBuilder.builder()
                        .withTemperature(0.85)
                        .build())
                .call()
                .content();
    }
}
