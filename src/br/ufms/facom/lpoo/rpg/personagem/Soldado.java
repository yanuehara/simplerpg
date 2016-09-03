package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;

/**
 * Personagem soldado.
 * <p>
 * Esta classe possui todos os atributos e métodos básicos do personagem soldado.
 * 
 * @author eraldo
 *
 */
public abstract class Soldado implements Personagem {

	/**
	 * Arma do soldado.
	 */
	protected Arma arma;

	/**
	 * Nível de vida do soldado.
	 */
	private int vida;

	/**
	 * Nome do soldado.
	 */
	private String nome;

	/**
	 * Posição do soldado no tabuleiro.
	 */
	private Posicao posicao;
	
<<<<<<< HEAD
	/**
	 * Probabilidade do soldado atacar.
	 */
	private int ataque;
	
	/**
	 * Probabilidade do soldado de defender.
	 */
	private int defesa;
=======
>>>>>>> origin/master

	private int distancia;
	
	/**
	 * Cria um soldado com o nome dado.
	 * 
	 * @param nome
	 */
	public Soldado(String nome) {
		this.nome = nome;
		vida = 5;
		ataque = 2;
		defesa = 1;
		posicao = new Posicao();
	}

	/**
	 * Cria um soldado com o nome e posição dados.
	 * 
	 * @param nome
	 * @param x
	 * @param y
	 */
	public Soldado(String nome, int x, int y) {
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
		return 2;
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