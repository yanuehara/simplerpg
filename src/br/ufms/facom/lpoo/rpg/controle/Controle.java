package br.ufms.facom.lpoo.rpg.controle;

import br.ufms.facom.lpoo.rpg.arma.*;
import br.ufms.facom.lpoo.rpg.personagem.*;
import br.ufms.facom.lpoo.rpg.ui.RolePlayingGame;
import javafx.application.Platform;
import java.util.Random;

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
	private MiniGun minigun = new MiniGun();
	
	private SoldadoAliado soldA1 = new SoldadoAliado("SoldA1", rifle, 2, 1);
	private SoldadoAliado soldA2 = new SoldadoAliado("SoldA2", pistola, 3, 1);
	private SoldadoAliado soldA3 = new SoldadoAliado("SoldA3", rifle, 4, 1);
	private MedicoAliado medicoA1 = new MedicoAliado("MedicoA1", desfibrilador1, 2, 0);
	private SniperAliado sniperA1 = new SniperAliado("SniperA1", sniper, 4, 0);
	private Comandante comandanteA1 = new Comandante("Comandante", minigun, 3, 0);
	
	
	private SoldadoInimigo soldI1 = new SoldadoInimigo("SoldI1", pistola, 3, 4);
	private SoldadoInimigo soldI2 = new SoldadoInimigo("SoldI2", pistola, 1, 6);
	private SoldadoInimigo soldI3 = new SoldadoInimigo("SoldI3", pistola, 5, 7);
	private MedicoInimigo medicoI1 = new MedicoInimigo("MedicoI1", desfibrilador2, 2, 6);
	private SniperInimigo sniperI1 = new SniperInimigo("SniperI1", sniper, 3, 6);
	private MorteiroInimigo morteiroI1 = new MorteiroInimigo("MorteiroI1", morteiro, 2, 7);
	private MorteiroInimigo morteiroI2 = new MorteiroInimigo("MorteiroI2", morteiro, 5, 7);

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
	 * Verifica se o personagem esta vivo
	 * @param p
	 * @return
	 */
	public boolean aindaVivo(Personagem p)
	{
		if(p.getVida() > 0)
			return true;
		return false;
	}

	/**
	 * Dado dois personagens Rertorna a Distancia Entre eles
	 * @param p1
	 * @param p2
	 * @return
	 */
	public int distancia(Personagem p1, Personagem p2)
	{
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}
	/**
	 * Dado um posição e o alvo, testar se consegue chegar nesse posição
	 * se Sim, true, se não, false.
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param distancia
	 * @param personagem
	 * @return
	 */
	public boolean testaPosicao(int x1, int y1, int x2, int y2, int distancia, String personagem){
		if((Math.abs(x1 - x2) + Math.abs(y1 - y2)) > distancia)
		{
			rpg.erro(String.format("Personagem %s, só pode andar %d de distância", personagem, distancia));
			return true;
		}
		return false;	
	}
	
	/**
	 * Adiciona os Personagens na Fase
	 * @param aliados
	 * @param inimigos
	 */
	public void addPersonagensFase(int aliados, int inimigos)
	{
		for(int i=0; i<aliados; i++)
		{
			rpg.addPersonagem(personagensAliados[i]);
		}
		for(int i=0; i<inimigos; i++)
		{
			rpg.addPersonagem(personagensInimigos[i]);
		}
		
		rpg.atualizaTabuleiro();
		rpg.atualizaTabuleiro();
	}
	/**
	 * Remove os Personagens da Fase
	 * @param aliados
	 * @param inimigos
	 */
	public void removePersonagensFase(int aliados, int inimigos)
	{
		for(int i=0; i<aliados; i++)
		{
			rpg.removePersonagem(personagensAliados[i]);
		}
		for(int i=0; i<inimigos; i++)
		{
			rpg.removePersonagem(personagensInimigos[i]);
		}
		
		rpg.atualizaTabuleiro();
		rpg.atualizaTabuleiro();
	}
	
	/**
	 * Boolean que retorna se todos os Inimigos morreram
	 * Caso todos os aliados morram, o jogo fecha.
	 * @param aliados
	 * @param inimigos
	 * @return
	 */
	
	public boolean todoMundoMorreu(int aliados, int inimigos)
	{
		boolean morreuAliados = true;
		boolean morreuInimigos = true;
		for(int i=0; i<aliados; i++)
		{
			if(personagensAliados[i].getVida() > 0)
				morreuAliados = false;
		}
		
		if (morreuAliados) {
		    rpg.erro("Aliados todos Mortos, Game Over");
		    Platform.exit();
		 }
		
		
		for(int i=0; i<inimigos; i++)
		{
			if(personagensInimigos[i].getVida() > 0)
				morreuInimigos = false;
		}
		return morreuInimigos;
	}
	
	/**
	 * Calculo de acerto do atk.
	 * @param atacante
	 * @param defensor
	 * @return
	 */
	public boolean acertoAtk(Personagem atacante, Personagem defensor)
	{
		Random gerador = new Random();
		int acerto;
		
		acerto = 5 + atacante.getAtaque() - defensor.getDefesa();
		if(acerto >= (gerador.nextInt()%10))
			return true;
		return false;
	}
	
	/**
	 * IA do time inimigo
	 * @param nAliados
	 * @param nInimigos
	 */
	public void inteligenciaArtificial(int nAliados, int nInimigos)
	{
		int menorDistancia = 20;
		int distanciaPercorrida;
		int x = 0;
		int y = 0;
		Personagem p = personagensAliados[0];
		for(int i=0; i<nInimigos; i++)
		{
			if(aindaVivo(personagensInimigos[i]))
			{
				if(personagensInimigos[i] != medicoI1)
				{
					menorDistancia = 20;
					for(int j =0; j<nAliados; j++)
					{
						if(distancia(personagensInimigos[i], personagensAliados[j]) < menorDistancia)
						{
							menorDistancia = distancia(personagensInimigos[i], personagensAliados[j]);
							x = personagensAliados[j].getX();
							y = personagensAliados[j].getY();
							p = personagensAliados[j];
							
						}
					}
					distanciaPercorrida = personagensInimigos[i].getVelocidade();
					
					if(Math.abs(personagensInimigos[i].getX() - x) <= distanciaPercorrida)
					{
						distanciaPercorrida -= Math.abs(personagensInimigos[i].getX() - x);
						personagensInimigos[i].setX(x+1);
						if(distanciaPercorrida > 0)
						{
							if(Math.abs(personagensInimigos[i].getY() - y) <= distanciaPercorrida)
							{
								personagensInimigos[i].setY(y+1);
							}
							else
							{
								personagensInimigos[i].setY(personagensInimigos[i].getY() - distanciaPercorrida+1);
								distanciaPercorrida = 0;
							}
						}
					}
					else
					{
						personagensInimigos[i].setX(personagensInimigos[i].getX() - distanciaPercorrida+1);
						distanciaPercorrida = 0;
					}
					
					rpg.atualizaTabuleiro();
					
					if(distancia(personagensInimigos[i], p) <= personagensInimigos[i].getArma().getAlcance())
					{
						if(acertoAtk(personagensInimigos[i], p))
							p.setVida(p.getVida() - personagensInimigos[i].getArma().getDano());
					}
					rpg.atualizaTabuleiro();
				}	
				else
				{
					menorDistancia = 20;
					for(int j =0; j<nInimigos; j++)
					{
						if((distancia(personagensInimigos[i], personagensInimigos[j]) < menorDistancia) && personagensInimigos[j] != medicoI1)
						{
							menorDistancia = distancia(personagensInimigos[i], personagensInimigos[j]);
							x = personagensInimigos[j].getX();
							y = personagensInimigos[j].getY();
							p = personagensInimigos[j];
							
						}
					}
					distanciaPercorrida = personagensInimigos[i].getVelocidade();
					
					if(Math.abs(personagensInimigos[i].getX() - x) <= distanciaPercorrida)
					{
						distanciaPercorrida -= Math.abs(personagensInimigos[i].getX() - x);
						personagensInimigos[i].setX(x+1);
						if(distanciaPercorrida > 0)
						{
							if(Math.abs(personagensInimigos[i].getY() - y) <= distanciaPercorrida)
							{
								personagensInimigos[i].setY(y+1);
							}
							else
							{
								personagensInimigos[i].setY(personagensInimigos[i].getY() - distanciaPercorrida+1);
								distanciaPercorrida = 0;
							}
						}
					}
					else
					{
						personagensInimigos[i].setX(personagensInimigos[i].getX() - distanciaPercorrida+1);
						distanciaPercorrida = 0;
					}
					
					rpg.atualizaTabuleiro();
					
					if(distancia(personagensInimigos[i], p) <= personagensInimigos[i].getArma().getAlcance())
					{
						p.setVida(p.getVida() - personagensInimigos[i].getArma().getDano());
					}
				}
			}
		}
	}
	
	/**
	 * Executa um turno inteiro dos Aliados e dos Inimigos
	 * @param nAliados
	 * @param nInimigos
	 * @throws InterruptedException
	 */
	public void turno(int nAliados, int nInimigos) throws InterruptedException
	{
		boolean nmoveu;
		boolean nAtkAliados;;
		Posicao pos;
		Personagem p;
		for(int i=0; i<nAliados; i++)
			{
				if(aindaVivo(personagensAliados[i]))
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
					if(personagensAliados[i] != medicoA1)
					{
						rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", personagensAliados[i].getNome()));
						
						 p = rpg.selecionaPersonagem();
						 nAtkAliados = true;
						 
						for(int j=0; j<nAliados; j++)
						{
								if(p == personagensAliados[j] && p!= personagensAliados[i])
								{
									nAtkAliados = false;
									rpg.erro("Você não pode atacar aliados! Perdeu a vez.");
								}
						}
						if(nAtkAliados)
						{
							if (p != personagensAliados[i])
								if(distancia(personagensAliados[i], p) <= personagensAliados[i].getArma().getAlcance())
								{
									if(acertoAtk(personagensAliados[i], p))
										p.setVida(p.getVida() - personagensAliados[i].getArma().getDano());
									else
										rpg.info("Você errou o ataque!!");
								}
								else
								{
									rpg.erro("O alacance de sua arma não chega no alvo! Perdeu a vez.");
								}
							else
								rpg.erro("Você não pode atacar você mesmo! Perdeu a vez.");
						}
					}
					else
					{
						rpg.info(String.format("Personagem %s, selecione um aliado para curar!", personagensAliados[i].getNome()));
					
						p = rpg.selecionaPersonagem();
						nAtkAliados = true;
						
						for(int j=0; j<nInimigos; j++)
						{
							if(p == personagensInimigos[j])
							{
								nAtkAliados = false;
								rpg.erro("Você não pode curar inimigos! Perdeu a vez.");
							}
						}
						if(nAtkAliados)
						{
							if(distancia(personagensAliados[i], p) <= personagensAliados[i].getArma().getAlcance())
							{
								p.setVida(p.getVida() - personagensAliados[i].getArma().getDano());
							}
						}
						rpg.atualizaTabuleiro();
					}
					rpg.atualizaTabuleiro();
				}
		}
		inteligenciaArtificial(nAliados, nInimigos);
	}
	
	/**
	 * Como a posição inicial dos Aliados é sempre a mesma, um metodo é mais
	 * eficiente do que repetir 5 vezes a mesma coisa.
	 */
	public void restauraPosicaoAliados()
	{
		personagensAliados[0].setX(2);
		personagensAliados[0].setY(1);
		
		personagensAliados[1].setX(3);
		personagensAliados[1].setY(1);
		
		personagensAliados[2].setX(4);
		personagensAliados[2].setY(1);
		
		personagensAliados[3].setX(2);
		personagensAliados[3].setY(0);
		
		personagensAliados[4].setX(4);
		personagensAliados[4].setY(0);
	}
	/**
	 * Após uma fase restaurar a vida de todo mundo.
	 * @param aliados
	 * @param inimigos
	 */
	public void restauraVida(int aliados, int inimigos)
	{
		for(int i=0; i<aliados; i++)
		{
			personagensAliados[i].setVida(5);
		}
		
		for(int i=0; i<inimigos; i++)
		{
			personagensInimigos[i].setVida(5);
		}
	}
	/**
	 * Executa na verdade uma fase inteira.
	 * @throws InterruptedException
	 */
	public void executaTurno() throws InterruptedException {
		
		
		// Fase 1
	
		personagensAliados[0] = soldA1;
		personagensAliados[1] = soldA2;
		personagensAliados[2] = soldA3;
		personagensAliados[3] = medicoA1;
		personagensAliados[4] = sniperA1;
				
		personagensInimigos[0] = soldI1;
		personagensInimigos[1] = soldI2;
		personagensInimigos[2] = soldI3;
		
		this.addPersonagensFase(5, 3);
		

		while(!this.todoMundoMorreu(5, 3))
		{
			turno(5, 3);
		}
		
		
		restauraVida(5,3);
		restauraPosicaoAliados();
		rpg.atualizaTabuleiro();
		removePersonagensFase(5, 3);
		rpg.atualizaTabuleiro();

		// FASE 2
				
		personagensInimigos[0] = soldI1;
		personagensInimigos[1] = soldI2;
		personagensInimigos[2] = sniperI1;
		
		personagensInimigos[0].setX(2);
		personagensInimigos[0].setY(4);
		
		personagensInimigos[1].setX(4);
		personagensInimigos[1].setY(5);
		
		personagensInimigos[2].setX(3);
		personagensInimigos[2].setY(6);
		
		this.addPersonagensFase(5, 3);
		
		while(!this.todoMundoMorreu(5, 3))
		{
			turno(5, 3);
		}
		
		restauraVida(5,3);
		restauraPosicaoAliados();
		removePersonagensFase(5, 3);
		rpg.atualizaTabuleiro();
		
		
		

		// FASE 3
				
		personagensInimigos[0] = soldI1;
		personagensInimigos[1] = soldI2;
		personagensInimigos[2] = sniperI1;
		personagensInimigos[3] = medicoI1;
		
		personagensInimigos[0].setX(2);
		personagensInimigos[0].setY(4);
		
		personagensInimigos[1].setX(4);
		personagensInimigos[1].setY(5);
		
		personagensInimigos[2].setX(3);
		personagensInimigos[2].setY(6);
		
		personagensInimigos[3].setX(2);
		personagensInimigos[3].setY(6);
		
		this.addPersonagensFase(5, 4);
		
		while(!this.todoMundoMorreu(5, 4))
		{
			turno(5, 4);
		}
		
		restauraVida(5,4);
		restauraPosicaoAliados();
		removePersonagensFase(5, 4);
		rpg.atualizaTabuleiro();
		

		
		// FASE 4
		
		personagensInimigos[0] = soldI1;
		personagensInimigos[1] = soldI2;
		personagensInimigos[2] = soldI3;
		personagensInimigos[3] = sniperI1;
		personagensInimigos[4] = medicoI1;
		
		personagensInimigos[0].setX(1);
		personagensInimigos[0].setY(4);
		
		personagensInimigos[1].setX(2);
		personagensInimigos[1].setY(4);
		
		personagensInimigos[2].setX(4);
		personagensInimigos[2].setY(5);
		
		personagensInimigos[3].setX(3);
		personagensInimigos[3].setY(6);
		
		personagensInimigos[4].setX(2);
		personagensInimigos[4].setY(6);
		
		this.addPersonagensFase(5, 5);
		
		while(!this.todoMundoMorreu(5, 5))
		{
			turno(5, 5);
		}
		
		restauraVida(5,5);
		restauraPosicaoAliados();
		removePersonagensFase(5, 5);
		rpg.atualizaTabuleiro();
		
		
		// FASE 5
		personagensAliados[5] = comandanteA1;
		
		personagensInimigos[0] = soldI1;
		personagensInimigos[1] = soldI2;
		personagensInimigos[2] = morteiroI1;
		personagensInimigos[3] = morteiroI2;
		personagensInimigos[4] = sniperI1;
		
		personagensInimigos[0].setX(3);
		personagensInimigos[0].setY(4);
		
		personagensInimigos[1].setX(4);
		personagensInimigos[1].setY(4);
		
		personagensInimigos[2].setX(2);
		personagensInimigos[2].setY(7);
		
		personagensInimigos[3].setX(5);
		personagensInimigos[3].setY(7);
		
		personagensInimigos[4].setX(3);
		personagensInimigos[4].setY(7);
		
		this.addPersonagensFase(6, 5);
		
		while(!this.todoMundoMorreu(6, 5))
		{
			turno(6, 5);
		}
		
		restauraVida(6,5);
		restauraPosicaoAliados();
		removePersonagensFase(6, 5);
		rpg.atualizaTabuleiro();

		while(true);

	}
}