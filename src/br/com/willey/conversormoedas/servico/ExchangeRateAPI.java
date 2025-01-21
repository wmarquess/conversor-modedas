package br.com.willey.conversormoedas.servico;

import br.com.willey.conversormoedas.exception.ConversaoException;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI {
    private static final String API_KEY = "61687996a23fb609dbf90b73";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final Gson gson;
    private final HttpClient client;

    public ExchangeRateAPI() {
        this.gson = new Gson();
        this.client = HttpClient.newHttpClient();
    }

    public double obterTaxaConversao(String moedaOrigem, String moedaDestino) throws ConversaoException {
        try {
            String url = BASE_URL + API_KEY + "/pair/" + moedaOrigem + "/" + moedaDestino;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new ConversaoException("Erro ao acessar a API de conversão. Status: " + response.statusCode());
            }

            ExchangeRateResponse taxaResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

            if (!"success".equals(taxaResponse.result)) {
                throw new ConversaoException("Erro na resposta da API: " + taxaResponse.result);
            }

            return taxaResponse.conversionRate;

        } catch (Exception e) {
            throw new ConversaoException("Erro na conversão de moeda: " + e.getMessage(), e);
        }
    }

    private static class ExchangeRateResponse {
        private String result;

        @SerializedName("base_code")
        private String baseCode;

        @SerializedName("target_code")
        private String targetCode;

        @SerializedName("conversion_rate")
        private double conversionRate;
    }
}
