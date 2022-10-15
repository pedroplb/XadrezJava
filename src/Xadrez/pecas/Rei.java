package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public class Rei extends PecaXadrez{
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
	
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
		
	}
	
	
	private boolean testaRook(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		
		return p!=null && p instanceof Torre && 
				p.getCor() == getCor() && p.getContaMovimento() == 0;
	}
	
	

	@Override
	public boolean[][] movimentoPossivel() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];
		
		Posicao p = new Posicao(0,0);
		
		// acima
		p.setValor(posicao.getLinha()-1, posicao.getColuna());
		
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo
		p.setValor(posicao.getLinha()+1, posicao.getColuna());
		
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna()-1);
		
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// direita
		p.setValor(posicao.getLinha(), posicao.getColuna()+1);
		
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// acima direita
		p.setValor(posicao.getLinha()-1, posicao.getColuna()-1);
		
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// acima esquerda
		p.setValor(posicao.getLinha()-1, posicao.getColuna()+1);
		
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo esquerda
		p.setValor(posicao.getLinha()+1, posicao.getColuna()-1);
		
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo direita
		p.setValor(posicao.getLinha()+1, posicao.getColuna()+1);
		
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// verificando movimento especial Rook Castling
		
		if(getContaMovimento() ==0 && !partidaXadrez.getCheck()) {
			// Rook do lado da torre
			
			Posicao posT1 = new Posicao (posicao.getLinha(), posicao.getColuna()+3);
			
			if (testaRook(posT1)) {
				Posicao p1 = new Posicao (posicao.getLinha(), posicao.getColuna()+1);
				Posicao p2 = new Posicao (posicao.getLinha(), posicao.getColuna()+2);
				if (getTabuleiro().peca(p1)==null && getTabuleiro().peca(p2)==null) {
					mat[posicao.getLinha()][posicao.getColuna()+2] = true;
				}
			}
			
			// Rook do lado da rainha
			
			Posicao posQ1 = new Posicao (posicao.getLinha(), posicao.getColuna()-4);
			
			if (testaRook(posQ1)) {
				Posicao p1 = new Posicao (posicao.getLinha(), posicao.getColuna()-1);
				Posicao p2 = new Posicao (posicao.getLinha(), posicao.getColuna()-2);
				Posicao p3 = new Posicao (posicao.getLinha(), posicao.getColuna()-3);
				if (getTabuleiro().peca(p1)==null && 
						getTabuleiro().peca(p2)==null &&
						getTabuleiro().peca(p3)==null) {
					mat[posicao.getLinha()][posicao.getColuna()-3] = true;
				}
			}
			
		}
		
		return mat;
	}

}
