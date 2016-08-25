package br.ufms.facom.lpoo.rpg.controle;

import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;
import br.ufms.facom.lpoo.rpg.personagem.Soldado;
import br.ufms.facom.lpoo.rpg.ui.RolePlayingGame;

/**
 * Controle do jogo: personagens e suas interações.
 * <p>
 * Esta é uma implementação de exemplo cujo único intuito é exemplificar os
 * principais métodos da classe de interface: <code>RolePlayingGame</code>. A
 * implementação apresentada aqui não condiz com os requisitos do trabalho
 * (temos apenas um tipo de personagem (<code>Soldado</code>) e um tipo de arma
 * (<code>Faca</code>). Apenas dois personagens são adicionados ao tabuleiro e a
 * interação entre eles não respeita nenhuma regra imposta no trabalho.
 * 
 * @author eraldo
 *
 */
public class Controle {

	/**
	 * Interface gráfica do jogo. Fornece métodos de entrada e saída.
	 */
	private RolePlayingGame rpg;

	/**
	 * Um personagem.
	 */
	private Soldado sold1;

	/**
	 * Outro personagem.
	 */
	private Soldado sold2;

	/**
	 * Cria um objeto de controle que usa o objeto <code>rpg</code> como
	 * interface com o usuário.
	 * 
	 * @param rpg
	 *            interface gráfica da aplicação.
	 */
	public Controle(RolePlayingGame rpg) {
		this.rpg = rpg;

		// Cria um personagem em um canto do tabuleiro e outro em outro canto.
		sold1 = new Soldado("Sold1", 0, 0);
		sold2 = new Soldado("Sold2", RolePlayingGame.MAX_X - 1, RolePlayingGame.MAX_Y - 1);

		// Adiciona os dois personagens ao tabuleiro.
		rpg.addPersonagem(sold2);
		rpg.addPersonagem(sold1);
	}

	/**
	 * Executa um turno do jogo. Este método é invocado pelo interface gráfica
	 * continuamente, enquanto a aplicação estiver rodando.
	 * <p>
	 * TODO: A implementação apresentada é apenas um exemplo que não condiz com
	 * os requisitos do trabalho. O turno implementado é muito simples. Cada
	 * jogador pode mover-se (sem restrições) e atacar o outro jogador. Nenhuma
	 * restrição é verificada com relação à velocidade do personagem, alcance
	 * das armas, pontos de vida, resultado de dados, etc.
	 * 
	 * @throws InterruptedException
	 *             Exceção lançada quando a aplicação é encerrada pelo usuário.
	 *             O controle do jogo é executado em uma thread separada da
	 *             thread principal da aplicação. Esta exceção é lançada para
	 *             permitir o encerramento da thread de controle. Entretanto, o
	 *             tratamento desta exceção é realizada pela classe de
	 *             interface. Nenhum tratamento deve ser realizado neste método.
	 */
	public void executaTurno() throws InterruptedException {
		/*
		 * Exibe mensagem avisando que o usuário precisa selecionar a posição do
		 * personagem 1.
		 */
		rpg.info(String.format("Personagem %s, selecione sua nova posição!", sold1.getNome()));

		/*
		 * Solicita uma casa do tabuleiro à interface. O usuário deverá
		 * selecionar (clicando com o mouse) em uma casa do tabuleiro. As
		 * coordenadas desta casa serão retornadas em um objeto Posicao
		 * (coordenadas x e y).
		 */
		Posicao pos = rpg.selecionaPosicao();

		// Altera a posição do personagem 1.
		sold1.setX(pos.x);
		sold1.setY(pos.y);

		/*
		 * Solicita à interface que o tabuleiro seja atualizado, pois a posição
		 * do personagem pode ter sido alterada.
		 */
		rpg.atualizaTabuleiro();

		/*
		 * Exibe mensagem avisando que o usuário precisa selecionar um oponente
		 * a ser atacado pelo personagem 1.
		 */
		rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", sold1.getNome()));

		/*
		 * Solicita um personagem à interface. O usuário deverá selecionar um
		 * personagem no tabuleiro (clicando com o mouse sobre o personagem).
		 */
		Personagem p = rpg.selecionaPersonagem();

		/*
		 * A única validação realizada é se o personagem não o mesmo que está
		 * atacando. Entretanto, no trabalho, diversas validações são
		 * necessárias.
		 */
		if (p != sold1)
			p.setVida(p.getVida() - 1);
		else
			rpg.erro("Você não pode atacar você mesmo! Perdeu a vez.");

		/*
		 * Solicita à interface que o tabuleiro seja atualizado, pois os pontos
		 * de vida de um personagem podem ter sido alterados.
		 */
		rpg.atualizaTabuleiro();

		/*
		 * Abaixo, as mesmas operações realizadas com o personagem 1 são
		 * realizadas com o personagem 2.
		 */

		rpg.info(String.format("Personagem %s, selecione sua nova posição!", sold2.getNome()));
		pos = rpg.selecionaPosicao();
		sold2.setX(pos.x);
		sold2.setY(pos.y);

		rpg.atualizaTabuleiro();

		rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", sold2.getNome()));
		p = rpg.selecionaPersonagem();
		if (p != sold2)
			p.setVida(p.getVida() - 1);
		else
			rpg.erro("Você não pode atacar você mesmo! Perdeu a vez.");

		rpg.atualizaTabuleiro();
	}
}
