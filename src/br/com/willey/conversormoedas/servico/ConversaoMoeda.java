package br.com.willey.conversormoedas.servico;

import br.com.willey.conversormoedas.exception.ConversaoException;
import br.com.willey.conversormoedas.moeda.Moeda;

public class ConversaoMoeda {
    private ExchangeRateAPI api;

    public ConversaoMoeda() {
        this.api = new ExchangeRateAPI();
    }

    public double converter(Moeda moedaOrigem, Moeda moedaDestino, double valor) throws ConversaoException {
        if (valor <= 0) {
            throw new ConversaoException("Valor deve ser maior que zero");
        }

        double taxa = api.obterTaxaConversao(moedaOrigem.getCodigo(), moedaDestino.getCodigo());
        return valor * taxa;
    }
}
