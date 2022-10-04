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
		validaPosicaoOrigem(origem);
		validaPosicaoDestino(origem,destino);
		Peca capturadaPeca = movimentar(origem, destino);
		return (PecaXadrez) capturadaPeca;
		
	}
	
	private Peca movimentar(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca capturadaPeca = tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, destino);
		return capturadaPeca;
	}
	
	private void validaPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.ehPeca(posicao)) {
			throw new XadrezException("Nao ha peca na posicao de origem");
		}

		if (!tabuleiro.peca(posicao).ehPossivelMover()) {
			throw new XadrezException("Nao ha movimentos possiveis para esta peca");
		}
	}
	
	private void validaPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new XadrezException("Destino invalido para a peca escolhida");
		}
		
	}
	
	private void colocaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocaPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
	}
	
	private void configInicial() {
		colocaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		colocaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}

}
