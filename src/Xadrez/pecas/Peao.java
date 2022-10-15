package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public class Peao extends PecaXadrez {
	
	private PartidaXadrez partidaXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public boolean[][] movimentoPossivel() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];
		
		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {
			p.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().ehPeca(p2) && getContaMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}	
			
			// enPassant
			if (posicao.getLinha()==3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
				if(getTabuleiro().posicaoExiste(esquerda) && 
						ehPecaOponente(esquerda) && 
						getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassantVulneravel()) {
					mat[esquerda.getLinha()-1][esquerda.getColuna()] = true;
					
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna()+1);
				if(getTabuleiro().posicaoExiste(direita) && 
						ehPecaOponente(direita) && 
						getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVulneravel()) {
					mat[direita.getLinha()-1][direita.getColuna()] = true;
					
				}
			}
			
		}
		else {
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().ehPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().ehPeca(p2) && getContaMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && ehPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// enPassant
			if (posicao.getLinha()==4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
				if(getTabuleiro().posicaoExiste(esquerda) && 
						ehPecaOponente(esquerda) && 
						getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassantVulneravel()) {
					mat[esquerda.getLinha()+1][esquerda.getColuna()] = true;
					
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
				if(getTabuleiro().posicaoExiste(direita) && 
						ehPecaOponente(direita) && 
						getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVulneravel()) {
					mat[direita.getLinha()+1][direita.getColuna()] = true;
					
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
