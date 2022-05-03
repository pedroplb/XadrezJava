package Xadrez;

import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;
import jogoTabuleiro.Peca;
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
	
	public PecaXadrez facaMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao) {
		Posicao origem = origemPosicao.paraPosicao();
		Posicao destino = destinoPosicao.paraPosicao();
		validaPosicao(origem);
		Peca capturadaPeca = movimentar(origem, destino);
		return (PecaXadrez) capturadaPeca;
		
	}
	
	private Peca movimentar(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca capturadaPeca = tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, destino);
		return capturadaPeca;
	}
	
	private void validaPosicao(Posicao posicao) {
		if(!tabuleiro.ehPeca(posicao)) {
			throw new XadrezException("Não há peça na posicao de origem");
		}
	}
	
	private void colocaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocaPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
	}
	
	private void configInicial() {
		colocaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		colocaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}

}
