package com.cbm.ecomart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerador")
public class GeradorController {

    @GetMapping
    public String gerarProdutos() {
        return "Gerando...";
    }
}
