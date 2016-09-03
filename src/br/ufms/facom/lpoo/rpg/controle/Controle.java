package br.ufms.facom.lpoo.rpg.controle;

import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;
import br.ufms.facom.lpoo.rpg.personagem.Soldado;
import br.ufms.facom.lpoo.rpg.ui.RolePlayingGame;

/**
 * Controle do jogo: personagens e suas interações.
 * <p>
 * O intuito desta implementação é apenas exemplificar o uso dos principais
 * métodos da classe de interface: <code>RolePlayingGame</code>. A implementação
 * apresentada aqui não condiz com os requisitos do trabalho (apenas um tipo de
 * personagem (<code>Soldado</code>) e um tipo de arma (<code>Faca</code>) são
 * implementados aqui). Apenas dois personagens (do mesmo tipo) são adicionados
 * ao tabuleiro e a interação entre eles não respeita as regras do trabalho.
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
	 * A implementação apresentada é apenas um exemplo que não condiz com os
	 * requisitos do trabalho. O turno implementado é muito simples. Cada
	 * jogador pode mover-se (sem restrições) e atacar o outro jogador. Nenhuma
	 * restrição é verificada com relação à velocidade do personagem, alcance
	 * das armas, pontos de vida, teste de habilidade, etc.
	 * 
	 * @throws InterruptedException
	 *             Exceção lançada quando a aplicação é encerrada pelo usuário.
	 *             O controle do jogo é executado em uma thread separada da
	 *             thread principal da aplicação. Esta exceção é lançada para
	 *             permitir o encerramento da thread de controle quando ela está
	 *             esperando uma resposta da interface com relação a uma ação do
	 *             usuário (selecionar personagem ou posição). O tratamento
	 *             desta exceção é realizado pela classe da aplicação
	 *             (<code>RolePlayingGame</code>). Esta exceção não deve ser
	 *             capturada aqui.
	 */
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param distancia
	 * @param personagem
	 * @return true se pode se mover, else se não
	 */
	public boolean testaPosicao(int x1, int y1, int x2, int y2, int distancia, String personagem){
		if((Math.abs(x1 - x2) + Math.abs(y1 - y2)) > distancia)
		{
			rpg.erro(String.format("Personagem %s, só pode andar %d de distância", personagem, distancia));
			return true;
		}
		return false;	
	}
	
	
	
	public void executaTurno() throws InterruptedException {
		boolean nmoveu;
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
		nmoveu = testaPosicao(sold1.getX(), sold1.getY(), pos.x, pos.y, sold1.getVelocidade(), sold1.getNome());
		while(nmoveu){
			pos = rpg.selecionaPosicao();
			nmoveu = testaPosicao(sold1.getX(), sold1.getY(), pos.x, pos.y, sold1.getVelocidade(), sold1.getNome());
		}
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
		nmoveu = testaPosicao(sold1.getX(), sold1.getY(), pos.x, pos.y, sold1.getVelocidade(), sold2.getNome());
		while(nmoveu){
			pos = rpg.selecionaPosicao();
			nmoveu = testaPosicao(sold2.getX(), sold2.getY(), pos.x, pos.y, sold1.getVelocidade(), sold2.getNome());
		}
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