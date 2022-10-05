package application;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;
import Xadrez.XadrezException;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();
		
		//interface de usuario
		
		while(true){
			try {
				UI.clearScreen();
				UI.imprimePartida(partidaXadrez, capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lePosicaoXadrez(sc);
				
				boolean [][] movimentoPossivel = partidaXadrez.movimentosPossiveis(origem);
				UI.clearScreen();
				UI.imprimeTabuleiro(partidaXadrez.getPecas(), movimentoPossivel);
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lePosicaoXadrez(sc);
				
				PecaXadrez capturadaPeca = partidaXadrez.facaMovimentoXadrez(origem, destino);
				if (capturadaPeca !=null) {
					capturada.add(capturadaPeca);
				}
			}
			catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
		
	}
	

}