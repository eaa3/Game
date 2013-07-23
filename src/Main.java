import java.awt.EventQueue;


public class Main {

	/**
	 * @author: Ermano Ardiles Arruda - eaa3@cin.ufpe.br
	 * @comments: Pequeno jogo feito para demonstrar Busca em Largura
	 * @disciplina: Algoritmos e Estruturas de Dados - if672cc
	 * @local: Centro de Informatica (CIn) - UFPE
	 */
	public static void main(String[] args) {

		final Game game = new Game();
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				game.setVisible(true);
				
			}
		});

	}

}
