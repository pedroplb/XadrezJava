package Xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrez.pecas.Bispo;
import Xadrez.pecas.Cavalo;
import Xadrez.pecas.Peao;
import Xadrez.pecas.Rainha;
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
	private boolean checkMate;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno  = 1;
		jogadorAtual = Cor.BRANCO;
		configInicial();
	}

	public int getTurno() {
		return this.turno;
	}

	public boolean getCheckMate() {
		return this.checkMate;
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
		System.out.println("origem " + origem);
		validaPosicaoDestino(origem,destino);
		System.out.println("destino " + destino);
		Peca capturadaPeca = movimentar(origem, destino);
		System.out.println("capturadaPeca  " + capturadaPeca);


		if(testaCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturadaPeca);
			throw new XadrezException("Voce nao pode se colocar em check");
		}

		System.out.println("ponto 0 ");
		System.out.println(oponente(jogadorAtual));

		Cor teste = Cor.BRANCO;
		System.out.println(testaCheck(teste));
		System.out.println("ponto 1 ");

		check = (testaCheck(oponente(jogadorAtual))) ? true : false;

		System.out.println(check);



		if(testaCheckMate(oponente(jogadorAtual))){
			checkMate = true;
		}
		else {
			System.out.println("ponto final");
			proximoTurno();
		}

		return (PecaXadrez) capturadaPeca;

	}

	private Peca movimentar(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(origem);
		p.somaContaMovimento();
		Peca capturadaPeca = tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, destino);

		if (capturadaPeca !=null) {
			pecasNoTabuleiro.remove(capturadaPeca);
			pecasCapturadas.add(capturadaPeca);
		}

		//rook do lado do rei
		if(p instanceof Rei && destino.getColuna() == origem.getLinha()+2) {
			Posicao origemT = new Posicao(origem.getLinha(),origem.getColuna()+3);
			Posicao destinoT = new Posicao(origem.getLinha(),origem.getColuna()+1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemT);
			tabuleiro.colocaPeca(torre, destinoT);
			torre.somaContaMovimento();						
		}


		//rook do lado do rei
		if(p instanceof Rei && destino.getColuna() == origem.getLinha()-2) {
			Posicao origemT = new Posicao(origem.getLinha(),origem.getColuna()-4);
			Posicao destinoT = new Posicao(origem.getLinha(),origem.getColuna()-1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemT);
			tabuleiro.colocaPeca(torre, destinoT);
			torre.somaContaMovimento();						
		}


		return capturadaPeca;
	}

	private void desfazerMovimento (Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, origem);
		p.subtraiContaMovimento();

		if(pecaCapturada !=null) {
			tabuleiro.colocaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		
		//rook do lado do rei
		if(p instanceof Rei && destino.getColuna() == origem.getLinha()+2) {
			Posicao origemT = new Posicao(origem.getLinha(),origem.getColuna()+3);
			Posicao destinoT = new Posicao(origem.getLinha(),origem.getColuna()+1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(destinoT);
			tabuleiro.colocaPeca(torre, origemT);
			torre.subtraiContaMovimento();						
		}


		//rook do lado do rei
		if(p instanceof Rei && destino.getColuna() == origem.getLinha()-2) {
			Posicao origemT = new Posicao(origem.getLinha(),origem.getColuna()-4);
			Posicao destinoT = new Posicao(origem.getLinha(),origem.getColuna()-1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(destinoT);
			tabuleiro.colocaPeca(torre, origemT);
			torre.subtraiContaMovimento();						
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
		throw new IllegalStateException("Não ha rei " + cor + " no tabuleiro");
	}

	private boolean testaCheck(Cor cor) {
		Posicao reiPosicao = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Peca> oponentePecas = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : oponentePecas) {
			boolean[][] mat = p.movimentoPossivel();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testaCheckMate(Cor cor) {
		if (!testaCheck(cor)) {
			return false;
		}

		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());

		for(Peca p : list) {
			boolean[][] mat = p.movimentoPossivel();
			for (int i=0; i<tabuleiro.getLinha();i++) {
				for (int j=0; j<tabuleiro.getColuna();j++) {
					if(mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().paraPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = movimentar(origem, destino);
						boolean testaCheck = testaCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);

						if(!testaCheck) {
							return false;
						}

					}
				}
			}
		}

		return true;
	}




	private void colocaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocaPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void configInicial() {
		colocaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

		colocaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		colocaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
		colocaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
		colocaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
		colocaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
		colocaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
		colocaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
		colocaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
		colocaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));
	}

}
