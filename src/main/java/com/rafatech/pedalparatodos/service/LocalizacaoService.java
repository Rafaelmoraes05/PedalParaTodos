package com.rafatech.pedalparatodos.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafatech.pedalparatodos.dto.LocalizacaoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalizacaoService {

    private static final String NOMINATIM_URL =
            "https://nominatim.openstreetmap.org/search";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<LocalizacaoDTO> buscarCidades(String query) {

        String url = NOMINATIM_URL +
                "?q=" + query +
                "&format=json" +
                "&addressdetails=1" +
                "&limit=5" +
                "&countrycodes=br" +
                "&accept-language=pt-BR";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "pedalparatodos-app");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        List<LocalizacaoDTO> resultados = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            for (JsonNode node : root) {

                String displayName = node.get("display_name").asText();
                JsonNode address = node.get("address");

                String cidade = address.has("city")
                        ? address.get("city").asText()
                        : address.has("town")
                        ? address.get("town").asText()
                        : null;

                String estado = address.has("state")
                        ? address.get("state").asText()
                        : null;

                String pais = address.has("country")
                        ? address.get("country").asText()
                        : null;

                if (cidade != null) {
                    resultados.add(
                            new LocalizacaoDTO(displayName, cidade, estado, pais)
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar resposta do Nominatim");
        }

        return resultados;
    }
}
