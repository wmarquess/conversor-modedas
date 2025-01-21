package br.com.willey.conversormoedas.menu;

import br.com.willey.conversormoedas.exception.ConversaoException;
import br.com.willey.conversormoedas.moeda.Moeda;
import br.com.willey.conversormoedas.servico.ConversaoMoeda;

import java.util.Scanner;

public class Menu {
    private ConversaoMoeda conversaoMoeda;
    private Scanner scanner;

    private static final String USD = "USD";
    private static final String ARS = "ARS";
    private static final String BRL = "BRL";
    private static final String COP = "COP";

    public Menu() {
        this.conversaoMoeda = new ConversaoMoeda();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("***********************************************");
            System.out.println("Seja bem-vindo(a) ao conversor de Moedas!\n");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileiro");
            System.out.println("4) Real brasileiro =>> Dólar");
            System.out.println("5) Peso colombiano =>> Dólar");
            System.out.println("6) Dólar =>> Peso colombiano");
            System.out.println("7) Sair");
            System.out.println("***********************************************");
            System.out.println("Escolha uma opção válida:");

            try {
                opcao = scanner.nextInt();
                processarOpcao(opcao);
            } catch (Exception e) {
                System.out.println("[ERRO] Entrada inválida. Por favor, digite um número.");
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    realizarConversao(USD, ARS, "Dólar", "Peso argentino");
                    break;
                case 2:
                    realizarConversao(ARS, USD, "Peso argentino", "Dólar");
                    break;
                case 3:
                    realizarConversao(USD, BRL, "Dólar", "Real brasileiro");
                    break;
                case 4:
                    realizarConversao(BRL, USD, "Real brasileiro", "Dólar");
                    break;
                case 5:
                    realizarConversao(COP, USD, "Peso colombiano", "Dólar");
                    break;
                case 6:
                    realizarConversao(USD, COP, "Dólar", "Peso colombiano");
                    break;
                case 7:
                    System.out.println("Você escolheu sair. Obrigado por usar o conversor de moedas!");
                    break;
                default:
                    System.out.println("[ERRO] Opção inválida. Por favor, escolha uma opção de 1 a 7.");
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    scanner.nextLine();
            }
        } catch (ConversaoException e) {
            System.out.println("Erro na conversão: " + e.getMessage());
        }
    }

    private void realizarConversao(String codigoOrigem, String codigoDestino,
                                   String nomeOrigem, String nomeDestino) throws ConversaoException {
        System.out.printf("\nConversão de [%s] para [%s]\n", nomeOrigem, nomeDestino);
        System.out.printf("Digite o valor em %s: ", nomeOrigem);

        try {
            double valor = scanner.nextDouble();

            if (valor <= 0) {
                System.out.println("\n[ERRO] Por favor, digite um valor maior que zero.");
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
                scanner.nextLine();
                return;
            }

            Moeda moedaOrigem = new Moeda(codigoOrigem, nomeOrigem);
            Moeda moedaDestino = new Moeda(codigoDestino, nomeDestino);

            double resultado = conversaoMoeda.converter(moedaOrigem, moedaDestino, valor);

            System.out.printf("%.2f %s = %.2f %s%n",
                    valor, codigoOrigem, resultado, codigoDestino);


        } catch (Exception e) {
            throw new ConversaoException("Erro ao realizar conversão: " + e.getMessage());
        }

        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
