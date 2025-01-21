package com.cbm.ecomart.controller;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagem")
public class GeradorImagemController {

    private final ImageModel imageModel;

    public GeradorImagemController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping
    public String gerarImagem(String prompt) {
        var options = ImageOptionsBuilder.builder()
                .withHeight(512)
                .withWidth(512)
                .build();

        var response = imageModel.call(new ImagePrompt(prompt, options));
        return response.getResult().getOutput().getUrl();
    }
}
