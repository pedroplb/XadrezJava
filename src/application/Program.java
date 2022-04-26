package application;
import Xadrez.PartidaXadrez;

public class Program {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		//interface de usuario
		UI.imprimeTabuleiro(partidaXadrez.getPecas());

	}
	

}