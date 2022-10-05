package Xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;
import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	private int turno;
	private Cor jogadorAtual;
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	//começa com falso por padrão
	private boolean check;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno  = 1;
		jogadorAtual = Cor.BRANCO;
		configInicial();
	}
	
	public int getTurno() {
		return this.turno;
	}
	
	public Cor getJogadorAtual() {
		return this.jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
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
	
	public boolean [][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
		
		Posicao posicao = posicaoOrigem.paraPosicao();
		validaPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentoPossivel();
	}

	
	public PecaXadrez facaMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao) {
		Posicao origem = origemPosicao.paraPosicao();
		Posicao destino = destinoPosicao.paraPosicao();
		validaPosicaoOrigem(origem);
		validaPosicaoDestino(origem,destino);
		Peca capturadaPeca = movimentar(origem, destino);
		
		if(testaCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturadaPeca);
			throw new XadrezException("Voce nao pode se colocar em check");
		}
		
		check = (testaCheck(oponente(jogadorAtual))) ? true : false;
		
		proximoTurno();
		return (PecaXadrez) capturadaPeca;
		
	}
	
	private Peca movimentar(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca capturadaPeca = tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, destino);
		
		if (capturadaPeca !=null) {
			pecasNoTabuleiro.remove(capturadaPeca);
			pecasCapturadas.add(capturadaPeca);
		}
		
		return capturadaPeca;
	}
	
	private void desfazerMovimento (Posicao origem, Posicao destino, Peca pecaCapturada) {
		Peca p = tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, origem);
		
		if(pecaCapturada !=null) {
			tabuleiro.colocaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validaPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.ehPeca(posicao)) {
			throw new XadrezException("Nao ha peca na posicao de origem");
		}

		if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peca escolhida nao e a sua");
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
	
	private void proximoTurno() {
		turno ++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return(cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	
	private PecaXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Nao ha rei desta cor no tabuleiro");		
	}
	
	private boolean testaCheck(Cor cor) {
		Posicao reiPosicao = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Peca> pecaOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Peca p : pecaOponente) {
			boolean [][] mat = p.movimentoPossivel();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	
	private void colocaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocaPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
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
