package br.ufms.facom.lpoo.rpg.controle;

import br.ufms.facom.lpoo.rpg.arma.*;
import br.ufms.facom.lpoo.rpg.personagem.*;
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

	private DesfibriladorAliado desfibrilador1 = new DesfibriladorAliado();
	private DesfibriladorInimigo desfibrilador2 = new DesfibriladorInimigo();
	private Morteiro morteiro = new Morteiro();
	private Pistola pistola = new Pistola();
	private Rifle rifle = new Rifle();
	private Sniper sniper = new Sniper();
	
	private SoldadoAliado soldA1 = new SoldadoAliado("SoldA1", pistola, 0, 0);
	private SoldadoAliado soldA2 = new SoldadoAliado("SoldA2", pistola, 1, 0);
	private SoldadoAliado soldA3 = new SoldadoAliado("SoldA3", pistola, 2, 0);
	private MedicoAliado medicoA1 = new MedicoAliado("MedicoA1", desfibrilador1, 3, 0);
	private SniperAliado sniperA1 = new SniperAliado("SniperA1", sniper, 4, 0);
	
	private SoldadoInimigo soldI1 = new SoldadoInimigo("SoldI1", pistola, 6, 6);
	private SoldadoInimigo soldI2 = new SoldadoInimigo("SoldI2", pistola, 5, 5);
	private SoldadoInimigo soldI3 = new SoldadoInimigo("SoldI3", pistola, 4, 4);
	private MedicoInimigo medicoI1 = new MedicoInimigo("MedicoI1", desfibrilador2, RolePlayingGame.MAX_X - 4, RolePlayingGame.MAX_Y - 0);
	private SniperInimigo sniperI1 = new SniperInimigo("SniperI1", sniper, RolePlayingGame.MAX_X - 5, RolePlayingGame.MAX_Y - 0);
	private MorteiroInimigo morteiroI1 = new MorteiroInimigo("MorteiroI1", morteiro,RolePlayingGame.MAX_X - 5, RolePlayingGame.MAX_Y - 0);
	private MorteiroInimigo morteiroI2 = new MorteiroInimigo("MorteiroI2", morteiro,RolePlayingGame.MAX_X - 6, RolePlayingGame.MAX_Y - 0);

	private Personagem[] personagensAliados = new Personagem[6];
	private Personagem[] personagensInimigos = new Personagem[5];
	/**
	 * Cria um objeto de controle que usa o objeto <code>rpg</code> como
	 * interface com o usuário.
	 * 
	 * @param rpg
	 *            interface gráfica da aplicação.
	 */
	public Controle(RolePlayingGame rpg) {
		this.rpg = rpg;

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
		
		// Fase 1
		Posicao pos;
		Personagem p;
		
		rpg.addPersonagem(soldA1);
		rpg.addPersonagem(soldA2);
		rpg.addPersonagem(soldA3);
		rpg.addPersonagem(medicoA1);
		rpg.addPersonagem(sniperA1);
		
		personagensAliados[0] = soldA1;
		personagensAliados[1] = soldA2;
		personagensAliados[2] = soldA3;
		personagensAliados[3] = medicoA1;
		personagensAliados[4] = sniperA1;
		
				
		rpg.addPersonagem(soldI1);
		rpg.addPersonagem(soldI2);
		rpg.addPersonagem(soldI3);
		
		personagensInimigos[0] = soldI1;
		personagensInimigos[1] = soldI2;
		personagensInimigos[2] = soldI3;
		
		rpg.atualizaTabuleiro();
		rpg.atualizaTabuleiro();
		for(int j=0; j<2; j++)
		{
			for(int i=0; i<5; i++)
			{
				rpg.info(String.format("Personagem %s, selecione sua nova posição!", personagensAliados[i].getNome()));
				pos = rpg.selecionaPosicao();
				
				nmoveu = testaPosicao(personagensAliados[i].getX(), personagensAliados[i].getY(), pos.x, pos.y, personagensAliados[i].getVelocidade(), personagensAliados[i].getNome());
				while(nmoveu){
					pos = rpg.selecionaPosicao();
					nmoveu = testaPosicao(personagensAliados[i].getX(), personagensAliados[i].getY(), pos.x, pos.y, personagensAliados[i].getVelocidade(), personagensAliados[i].getNome());
				}
				personagensAliados[i].setX(pos.x);
				personagensAliados[i].setY(pos.y);
				
				rpg.atualizaTabuleiro();
				
				rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", personagensAliados[i].getNome()));
				
				 p = rpg.selecionaPersonagem();
				 
				if (p != personagensAliados[i])
					p.setVida(p.getVida() - 1);
				else
					rpg.erro("Você não pode atacar você mesmo! Perdeu a vez.");
	
				rpg.atualizaTabuleiro();
			}
			
			for(int i=0; i<3; i++)
			{
				rpg.info(String.format("Personagem %s, selecione sua nova posição!", personagensInimigos[i].getNome()));
				pos = rpg.selecionaPosicao();
				
				nmoveu = testaPosicao(personagensInimigos[i].getX(), personagensInimigos[i].getY(), pos.x, pos.y, personagensInimigos[i].getVelocidade(), personagensInimigos[i].getNome());
				while(nmoveu){
					pos = rpg.selecionaPosicao();
					nmoveu = testaPosicao(personagensInimigos[i].getX(), personagensInimigos[i].getY(), pos.x, pos.y, personagensInimigos[i].getVelocidade(), personagensInimigos[i].getNome());
				}
				personagensInimigos[i].setX(pos.x);
				personagensInimigos[i].setY(pos.y);
				
				rpg.atualizaTabuleiro();
				
				rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", personagensInimigos[i].getNome()));
				
				 p = rpg.selecionaPersonagem();
				 
				if (p != personagensInimigos[i])
					p.setVida(p.getVida() - 1);
				else
					rpg.erro("Você não pode atacar você mesmo! Perdeu a vez.");
	
				rpg.atualizaTabuleiro();
			}
		}
		
		rpg.removePersonagem(soldA1);
		rpg.removePersonagem(soldA2);
		rpg.removePersonagem(soldA3);
		rpg.removePersonagem(medicoA1);
		rpg.removePersonagem(sniperA1);
		
		rpg.removePersonagem(soldI1);
		rpg.removePersonagem(soldI2);
		rpg.removePersonagem(soldI3);
		
		rpg.atualizaTabuleiro();
		while(true)
		{
			
		}
		


	}
}