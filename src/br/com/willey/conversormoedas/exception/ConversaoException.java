package br.com.willey.conversormoedas.exception;

public class ConversaoException extends Exception {
    public ConversaoException(String mensagem) {
        super(mensagem);
    }

    public ConversaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
