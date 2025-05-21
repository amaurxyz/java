package br.edu.ifsp.spo.java.cards.regras;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;

import java.util.List;

public interface Pontuador {
    int verificarPontuacao(List<Carta> cartas);

    public String verificarResultado (Jogador jogador1, Jogador jogador2);

    public String estourou (Jogador jogador1, Jogador jogador2);
}
