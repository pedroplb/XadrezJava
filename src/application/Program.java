package application;
import java.util.Scanner;

import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		//interface de usuario
		
		while(true){
			UI.imprimeTabuleiro(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = UI.lePosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = UI.lePosicaoXadrez(sc);
			
			PecaXadrez capturadaPeca = partidaXadrez.facaMovimentoXadrez(origem, destino);
			
		}
		
	}
	

}