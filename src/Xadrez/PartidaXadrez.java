package Xadrez;

import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configInicial();
	}
	
	// retorna matriz de pecas da partida
	public PecaXadrez[][] getPecas(){
		
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColuna()];
		
		for(int i=0;i<tabuleiro.getLinha();i++) {
			for(int j=0; j<tabuleiro.getColuna(); j++) {
				// downcasting para peca de xadrez pq o tabuleiro tem peca somente
				matriz[i][j] = (PecaXadrez)tabuleiro.peca(i,j);
			}
		}
		
		//retorna a matriz da partida
		return matriz;
	}
	
	private void configInicial() {
		tabuleiro.colocaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2,1));
		tabuleiro.colocaPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(0,4));
		tabuleiro.colocaPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7,4));
	}

}
