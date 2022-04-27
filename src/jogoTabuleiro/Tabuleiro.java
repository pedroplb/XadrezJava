package jogoTabuleiro;

public class Tabuleiro {
	
	private int linha;
	private int coluna;
	/* matriz de peças*/
	private Peca[][] pecas;
	
	public Tabuleiro(int linha, int coluna) {
		
		if(linha <1 || coluna  <1) {
			throw new TabuleiroException("Quantidade de linhas e colunas inválidas");
		}
		
		this.linha = linha;
		this.coluna = coluna;
		pecas = new Peca[linha][coluna];
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	public Peca peca(int linha, int coluna) {
		if(!posicaoExiste(linha,coluna)) {
			throw new TabuleiroException("Posição inválida");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição inválida");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocaPeca(Peca peca, Posicao posicao) {
		if(ehPeca(posicao)) {
			throw new TabuleiroException("Posição Ocupada " + posicao);
		}
		
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean posicaoExiste (int linha, int coluna) {
		//dependendo da condição retorna verdadeiro ou falso
		return (linha>=0 && coluna>=0 && linha<this.linha && coluna<this.coluna);
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean ehPeca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição inválida");
		}
		return peca(posicao)!=null;
	}
}
