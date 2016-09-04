/**
 * 
 */
package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;

/**
 *  Personagem médico.
 * <p>
 * Esta classe possui todos os atributos e métodos básicos do personagem médico.
 *
 */
public abstract class Medico implements Personagem {
	
	/**
	 * Arma do médico.
	 */
	protected Arma arma;

	/**
	 * Nível de vida do médico.
	 */
	private int vida;

	/**
	 * Nome do médico.
	 */
	private String nome;

	/**
	 * Posição do médico no tabuleiro.
	 */
	private Posicao posicao;
	
	/**
	 * Probabilidade do médico atacar.
	 */
	private int ataque;
	
	/**
	 * Probabilidade do médico de defender.
	 */
	private int defesa;
	
	/**
	 * Distância que o médico pode se mover no campo.
	 */
	private int velocidade;
	
	/**
	 * Cria um médico com o nome dado.
	 * 
	 * @param nome
	 */
	public Medico(String nome) {
		this.nome = nome;
		this.vida = 5;
		this.ataque = 5;
		this.defesa = 1;
		this.posicao = new Posicao();
		this.velocidade = 3;
	}

	/**
	 * Cria um médico com o nome e posição dados.
	 * 
	 * @param nome
	 * @param x
	 * @param y
	 */
	public Medico(String nome, int x, int y) {
		this(nome);
		this.posicao = new Posicao(x, y);
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
		this.vida = vida;
	}
}
