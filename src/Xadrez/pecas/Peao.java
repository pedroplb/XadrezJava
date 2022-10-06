package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaXadrez;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentoPossivel() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];
		Posicao p = new Posicao(0,0);
		
		if(getCor()==Cor.BRANCO) {
			p.setValor(posicao.getLinha() -1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValor(posicao.getLinha() -2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() -1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p) && 
					getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p) &&
					getContaMovimento() == 0) {
				
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValor(posicao.getLinha() -1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValor(posicao.getLinha() -1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			if(getCor()==Cor.PRETO) {
				p.setValor(posicao.getLinha() +1, posicao.getColuna());
				if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				p.setValor(posicao.getLinha() +2, posicao.getColuna());
				Posicao p2 = new Posicao(posicao.getLinha() +1, posicao.getColuna());
				if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p) && 
						getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p) &&
						getContaMovimento() == 0) {
					
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				p.setValor(posicao.getLinha() +1, posicao.getColuna()-1);
				if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				p.setValor(posicao.getLinha() +1, posicao.getColuna()+1);
				if(getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		}
		
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
