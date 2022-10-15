package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaXadrez;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] movimentoPossivel() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];
		
		Posicao p = new Posicao(0,0);
		
		//acima esquerda
		p.setValor(posicao.getLinha()-1, posicao.getColuna()-1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() -1, p.getColuna()-1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//acima direita
		p.setValor(posicao.getLinha()-1, posicao.getColuna()+1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() -1, p.getColuna()+1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo direita
		p.setValor(posicao.getLinha()+1, posicao.getColuna()+1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(posicao.getLinha()+1, posicao.getColuna()+1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo esquerda
		p.setValor(posicao.getLinha()+1, posicao.getColuna()-1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(posicao.getLinha()+1, posicao.getColuna()-1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
					
		return mat;
	}

}
