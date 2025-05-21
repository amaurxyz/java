package br.edu.ifsp.spo.java.cards.regras;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;

import java.util.List;

public class PontuadorClassico implements Pontuador {
    public int verificarPontuacao(List<Carta> cartas){

        int pontuacao = 0;

        for(Carta carta : cartas){
            switch(carta.getValor()){
                case AS -> pontuacao += 1;
                case DOIS -> pontuacao += 2;
                case TRES -> pontuacao += 3;
                case QUATRO -> pontuacao += 4;
                case CINCO -> pontuacao += 5;
                case SEIS ->  pontuacao += 6;
                case SETE -> pontuacao += 7;
                case OITO -> pontuacao += 8;
                case NOVE -> pontuacao += 9;
                case DEZ, DAMA, VALETE, REI -> pontuacao += 10;
            }
        }

        return pontuacao;
    }

    public String verificarResultado (Jogador jogador1, Jogador jogador2){
        int pontuacao1 = verificarPontuacao(jogador1.getMao());
        int pontuacao2 = verificarPontuacao(jogador2.getMao());
        String resultado = "";

        if (pontuacao1 == pontuacao2){
            resultado = "\n ninguem ganhou ";
        }

        else if (pontuacao1>pontuacao2 && pontuacao1<=21 || pontuacao1<pontuacao2 && pontuacao1 <= 21){
            resultado ="\n parabens "+jogador1.getNome() +" vc ganhou ";
        }



        else if (pontuacao2>pontuacao1 && pontuacao2<=21 || pontuacao2<pontuacao1 && pontuacao2 <= 21){
            resultado ="\n parabens "+ jogador2.getNome() +" vc ganhou ";
        }


        else if (pontuacao1>21 && pontuacao2>21){
            resultado ="\n parabens "+ jogador1.getNome() +" e "+ jogador2.getNome()+" vcs são burros e perderam ";
        }

        return resultado;
    }

    public String estourou (Jogador jogador1, Jogador jogador2){
        int pontuacao1 = verificarPontuacao(jogador1.getMao());
        int pontuacao2 = verificarPontuacao(jogador2.getMao());

        String estouro ="Erro";

        if (pontuacao1>21 && pontuacao2<21){
            estouro = "\n parabens "+ jogador2.getNome() +" vc ganhou ";
        }

        if (pontuacao1<21 && pontuacao2>21){
            estouro = "\n parabens "+ jogador1.getNome() +" vc ganhou ";
        }
        return estouro;
    }
}

