/**
 * 
 */
package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;

/**
 * Personagem sniper.
 * <p>
 * Esta classe possui todos os atributos e métodos básicos do personagem sniper.
 *
 */
public abstract class SniperPersonagem implements Personagem {

	/**
	 * Arma do sniper.
	 */
	protected Arma arma;

	/**
	 * Nível de vida do sniper.
	 */
	private int vida;

	/**
	 * Nome do sniper.
	 */
	private String nome;

	/**
	 * Posição do sniper no tabuleiro.
	 */
	private Posicao posicao;
	
	/**
	 * Probabilidade do sniper atacar.
	 */
	private int ataque;
	
	/**
	 * Probabilidade do sniper de defender.
	 */
	private int defesa;
	
	/**
	 * Distância que o sniper pode se mover no campo.
	 */
	private int velocidade;
	
	/**
	 * Cria um sniper com o nome dado.
	 * 
	 * @param nome
	 */
	public SniperPersonagem(String nome) {
		this.nome = nome;
		vida = 5;
		ataque = 4;
		defesa = 3;
		posicao = new Posicao();
		velocidade = 1;
	}

	/**
	 * Cria um sniper com o nome e posição dados.
	 * 
	 * @param nome
	 * @param x
	 * @param y
	 */
	public SniperPersonagem(String nome, int x, int y) {
		this(nome);
		posicao = new Posicao(x, y);
	}
	
	@Override
	public int getDefesa() {
		return this.defesa;
	}

	@Override
	public int getAtaque() {
		return this.ataque;
	}

	@Override
	public int getVelocidade() {
		return this.velocidade;
	}

	@Override
	public int getVida() {
		return vida;
	}

	@Override
	public Arma getArma() {
		return arma;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public int getX() {
		return posicao.x;
	}

	@Override
	public int getY() {
		return posicao.y;
	}

	@Override
	public void setX(int x) {
		posicao.x = x;
	}

	@Override
	public void setY(int y) {
		posicao.y = y;
	}

	@Override
	public void setVida(int vida) {
		if(vida < 0)
		{
			this.vida = 0;
		}
		else if(vida > 5)
		{
			vida = 5;
		}
		else
			this.vida = vida;
	}

}
