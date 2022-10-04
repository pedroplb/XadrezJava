package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaXadrez;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movimentoPossivel() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];
		
		Posicao p = new Posicao(0,0);
		
		//acima
		p.setValor(posicao.getLinha()-1, posicao.getColuna());
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() -1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna()-1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() -1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setValor(posicao.getLinha(), posicao.getColuna()+1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo
		p.setValor(posicao.getLinha()+1, posicao.getColuna());
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
					
		return mat;
	}

}
