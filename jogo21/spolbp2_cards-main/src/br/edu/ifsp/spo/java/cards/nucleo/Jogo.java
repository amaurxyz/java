package br.edu.ifsp.spo.java.cards.nucleo;

import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.ui.JogoUI;

import java.util.Scanner;

public class Jogo {

    private Baralho baralho;
    private Jogador jogador1;
    private Jogador jogador2;
    private Pontuador pontuador;
    private JogoUI ui;
    private int vitoriasJogador1 = 0;
    private int vitoriasJogador2 = 0;
    private int rodadasParaVencer;

    public Jogo() {
        this.ui = new JogoUI();
        this.pontuador = this.ui.escolherPontuador();
        Scanner sc = new Scanner(System.in);

        this.jogador1 = new Jogador(ui.solicitarNomeJogador(1));
        this.jogador2 = new Jogador(ui.solicitarNomeJogador(2));

        System.out.print("Digite o número de rodadas necessárias para vencer: ");
        this.rodadasParaVencer = sc.nextInt();
        sc.nextLine(); // consumir quebra de linha

        iniciarRodadas();
    }

    private void iniciarRodadas() {
        while (vitoriasJogador1 < rodadasParaVencer && vitoriasJogador2 < rodadasParaVencer) {
            this.baralho = new Baralho();
            jogador1.getMao().clear();
            jogador2.getMao().clear();

            for (int i = 0; i < 2; i++) {
                jogador1.receberCarta(baralho.tirarCarta());
                jogador2.receberCarta(baralho.tirarCarta());
            }

            String resultadoRodada = jogarRodada();
            System.out.println(resultadoRodada);

            if (resultadoRodada.contains(jogador1.getNome())) {
                vitoriasJogador1++;
            } else if (resultadoRodada.contains(jogador2.getNome())) {
                vitoriasJogador2++;
            }

            System.out.println("\nPlacar parcial:");
            System.out.println(jogador1.getNome() + ": " + vitoriasJogador1 + " vitória(s)");
            System.out.println(jogador2.getNome() + ": " + vitoriasJogador2 + " vitória(s)");
        }

        System.out.println("\n=== Fim do jogo ===");
        if (vitoriasJogador1 > vitoriasJogador2) {
            System.out.println("🏆 " + jogador1.getNome() + " venceu o jogo!");
        } else {
            System.out.println("🏆 " + jogador2.getNome() + " venceu o jogo!");
        }
    }

    private String jogarRodada() {
        while (true) {
            System.out.print("\nJogadores: ");
            System.out.print(jogador1);
            System.out.print("\nA pontuação do jogador 1 é: " + pontuador.verificarPontuacao(jogador1.getMao()));
            System.out.print(jogador2);
            System.out.print("\nA pontuação do jogador 2 é: " + pontuador.verificarPontuacao(jogador2.getMao()));

            String estouro = pontuador.estourou(jogador1, jogador2);
            if (!estouro.equals("Erro")) {
                return estouro;
            }

            String n1 = jogarTurno(pontuador.verificarPontuacao(jogador1.getMao()), jogador1);
            String n2 = jogarTurno(pontuador.verificarPontuacao(jogador2.getMao()), jogador2);

            if (n1.equalsIgnoreCase("N") && n2.equalsIgnoreCase("N")) {
                return pontuador.verificarResultado(jogador1, jogador2);
            }
        }
    }

    private String jogarTurno(int pontuacao, Jogador jogador) {
        Scanner sc = new Scanner(System.in);
        String letra = "N";
        if (pontuacao < 21) {
            System.out.print("\nDeseja pegar outra carta " + jogador.getNome() + "? S/N ");
            String verificar = sc.nextLine();
            if (verificar.equalsIgnoreCase("S")) {
                jogador.receberCarta(baralho.tirarCarta());
                letra = verificar;
            }
        }
        return letra;
    }
}
