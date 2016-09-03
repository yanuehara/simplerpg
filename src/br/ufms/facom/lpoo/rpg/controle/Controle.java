package br.ufms.facom.lpoo.rpg.controle;

import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;
import br.ufms.facom.lpoo.rpg.personagem.Soldado;
import br.ufms.facom.lpoo.rpg.ui.RolePlayingGame;

/**
 * Controle do jogo: personagens e suas intera��es.
 * <p>
 * O intuito desta implementa��o � apenas exemplificar o uso dos principais
 * m�todos da classe de interface: <code>RolePlayingGame</code>. A implementa��o
 * apresentada aqui n�o condiz com os requisitos do trabalho (apenas um tipo de
 * personagem (<code>Soldado</code>) e um tipo de arma (<code>Faca</code>) s�o
 * implementados aqui). Apenas dois personagens (do mesmo tipo) s�o adicionados
 * ao tabuleiro e a intera��o entre eles n�o respeita as regras do trabalho.
 * 
 * @author eraldo
 *
 */
public class Controle {

	/**
	 * Interface gr�fica do jogo. Fornece m�todos de entrada e sa�da.
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
	 * interface com o usu�rio.
	 * 
	 * @param rpg
	 *            interface gr�fica da aplica��o.
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
	 * Executa um turno do jogo. Este m�todo � invocado pelo interface gr�fica
	 * continuamente, enquanto a aplica��o estiver rodando.
	 * <p>
	 * A implementa��o apresentada � apenas um exemplo que n�o condiz com os
	 * requisitos do trabalho. O turno implementado � muito simples. Cada
	 * jogador pode mover-se (sem restri��es) e atacar o outro jogador. Nenhuma
	 * restri��o � verificada com rela��o � velocidade do personagem, alcance
	 * das armas, pontos de vida, teste de habilidade, etc.
	 * 
	 * @throws InterruptedException
	 *             Exce��o lan�ada quando a aplica��o � encerrada pelo usu�rio.
	 *             O controle do jogo � executado em uma thread separada da
	 *             thread principal da aplica��o. Esta exce��o � lan�ada para
	 *             permitir o encerramento da thread de controle quando ela est�
	 *             esperando uma resposta da interface com rela��o a uma a��o do
	 *             usu�rio (selecionar personagem ou posi��o). O tratamento
	 *             desta exce��o � realizado pela classe da aplica��o
	 *             (<code>RolePlayingGame</code>). Esta exce��o n�o deve ser
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
	 * @return true se pode se mover, else se n�o
	 */
	public boolean testaPosicao(int x1, int y1, int x2, int y2, int distancia, String personagem){
		if((Math.abs(x1 - x2) + Math.abs(y1 - y2)) > distancia)
		{
			rpg.erro(String.format("Personagem %s, s� pode andar %d de dist�ncia", personagem, distancia));
			return true;
		}
		return false;	
	}
	
	
	
	public void executaTurno() throws InterruptedException {
		boolean nmoveu;
		/*
		 * Exibe mensagem avisando que o usu�rio precisa selecionar a posi��o do
		 * personagem 1.
		 */
		rpg.info(String.format("Personagem %s, selecione sua nova posi��o!", sold1.getNome()));

		/*
		 * Solicita uma casa do tabuleiro � interface. O usu�rio dever�
		 * selecionar (clicando com o mouse) em uma casa do tabuleiro. As
		 * coordenadas desta casa ser�o retornadas em um objeto Posicao
		 * (coordenadas x e y).
		 */
		Posicao pos = rpg.selecionaPosicao();

		// Altera a posi��o do personagem 1.
		nmoveu = testaPosicao(sold1.getX(), sold1.getY(), pos.x, pos.y, sold1.getVelocidade(), sold1.getNome());
		while(nmoveu){
			pos = rpg.selecionaPosicao();
			nmoveu = testaPosicao(sold1.getX(), sold1.getY(), pos.x, pos.y, sold1.getVelocidade(), sold1.getNome());
		}
		sold1.setX(pos.x);
		sold1.setY(pos.y);

		/*
		 * Solicita � interface que o tabuleiro seja atualizado, pois a posi��o
		 * do personagem pode ter sido alterada.
		 */
		rpg.atualizaTabuleiro();

		/*
		 * Exibe mensagem avisando que o usu�rio precisa selecionar um oponente
		 * a ser atacado pelo personagem 1.
		 */
		rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", sold1.getNome()));

		/*
		 * Solicita um personagem � interface. O usu�rio dever� selecionar um
		 * personagem no tabuleiro (clicando com o mouse sobre o personagem).
		 */
		Personagem p = rpg.selecionaPersonagem();

		/*
		 * A �nica valida��o realizada � se o personagem n�o o mesmo que est�
		 * atacando. Entretanto, no trabalho, diversas valida��es s�o
		 * necess�rias.
		 */
		if (p != sold1)
			p.setVida(p.getVida() - 1);
		else
			rpg.erro("Voc� n�o pode atacar voc� mesmo! Perdeu a vez.");

		/*
		 * Solicita � interface que o tabuleiro seja atualizado, pois os pontos
		 * de vida de um personagem podem ter sido alterados.
		 */
		rpg.atualizaTabuleiro();

		/*
		 * Abaixo, as mesmas opera��es realizadas com o personagem 1 s�o
		 * realizadas com o personagem 2.
		 */

		rpg.info(String.format("Personagem %s, selecione sua nova posi��o!", sold2.getNome()));
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
			rpg.erro("Voc� n�o pode atacar voc� mesmo! Perdeu a vez.");

		rpg.atualizaTabuleiro();
	}
}