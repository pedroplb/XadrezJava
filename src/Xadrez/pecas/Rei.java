package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaXadrez;
import jogoTabuleiro.Tabuleiro;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	public String toString() {
		return "R";
	}

}
