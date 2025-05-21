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

    public Jogo(){
        this.ui = new JogoUI();

        this.pontuador = this.ui.escolherPontuador();

        this.baralho = new Baralho();
        this.jogador1 = new Jogador(ui.solicitarNomeJogador(1));
        this.jogador2 = new Jogador(ui.solicitarNomeJogador(2));

        for(int i = 0; i < 2; i++){
            this.jogador1.receberCarta(this.baralho.tirarCarta());
            this.jogador2.receberCarta(this.baralho.tirarCarta());
        }
    }

    public String Jogar(int pontuacao, Jogador jogador) {
        Scanner sc = new Scanner(System.in);
        String letra = "N";
        if (pontuacao < 21) {
            System.out.print(" \n Deseja pegar outra carta "+jogador.getNome()+"? S/N ");
            String verificar = sc.nextLine();

            if (verificar.equalsIgnoreCase("S")) {
                jogador.receberCarta(this.baralho.tirarCarta());
                letra = verificar;
            }
        }
        return letra;
    }

    @Override
    public String toString() {
        String resultado = "Jogo de Baralho Genérico";

        while (true){
        System.out.print("\n Jogadores: ");
        System.out.print( this.jogador1.toString());
        System.out.print( "\n A pontuação do jogador 1 é: " + this.pontuador.verificarPontuacao(this.jogador1.getMao()));
        System.out.print( this.jogador2.toString());
        System.out.print( "\n A pontuação do jogador 2 é: " + this.pontuador.verificarPontuacao(this.jogador2.getMao()));

        String estouro = this.pontuador.estourou(jogador1, jogador2);
        if (estouro != "Erro"){
            return estouro;
        }

        String n1= Jogar(this.pontuador.verificarPontuacao(this.jogador1.getMao()),jogador1);
        String n2= Jogar(this.pontuador.verificarPontuacao(this.jogador2.getMao()),jogador2);

        if (n1.equals("N") && n2.equals("N")){
            resultado = this.pontuador.verificarResultado(jogador1 , jogador2 ) ;
            return resultado;
        }
    }
}}

