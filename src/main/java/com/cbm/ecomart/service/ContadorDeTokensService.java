package com.cbm.ecomart.service;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.ModelType;

import java.util.Objects;

public class ContadorDeTokensService {

    private static final String DEFAULT_MODEL = "GPT_4O_MINI";

    /**
     * Conta os tokens para um texto combinado de `system` e `user` baseado no modelo configurado.
     *
     * @param system Texto do sistema.
     * @param user Texto do usuário.
     * @return Número de tokens contados.
     * @throws IllegalArgumentException Se os parâmetros forem nulos ou vazios.
     * @throws IllegalStateException Se ocorrer um erro ao obter o encoding ou contar os tokens.
     */
    public int contarTokens(String system, String user) {
        validateInput(system, user);

        try {
            // Inicializa o registro de codificação
            var registry = Encodings.newDefaultEncodingRegistry();

            // Obtém o encoding para o modelo configurado
            var enc = registry.getEncodingForModel(ModelType.valueOf(DEFAULT_MODEL));

            // Conta os tokens do texto combinado
            return enc.countTokens(system + user);

        } catch (Exception e) {
            throw new IllegalStateException("Erro ao contar tokens: " + e.getMessage(), e);
        }
    }

    /**
     * Valida as entradas para garantir que não sejam nulas ou vazias.
     *
     * @param system Texto do sistema.
     * @param user Texto do usuário.
     */
    private void validateInput(String system, String user) {
        if (Objects.isNull(system) || system.isBlank()) {
            throw new IllegalArgumentException("O texto 'system' não pode ser nulo ou vazio.");
        }
        if (Objects.isNull(user) || user.isBlank()) {
            throw new IllegalArgumentException("O texto 'user' não pode ser nulo ou vazio.");
        }
    }
}
