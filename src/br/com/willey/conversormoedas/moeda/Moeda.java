package br.com.willey.conversormoedas.moeda;

public class Moeda {
    private String codigo;
    private String nome;

    public Moeda(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }


}
