/**
 * 
 */
package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;

/**
 *  Personagem m�dico.
 * <p>
 * Esta classe possui todos os atributos e m�todos b�sicos do personagem m�dico.
 *
 */
public abstract class Medico implements Personagem {
	
	/**
	 * Arma do m�dico.
	 */
	protected Arma arma;

	/**
	 * N�vel de vida do m�dico.
	 */
	private int vida;

	/**
	 * Nome do m�dico.
	 */
	private String nome;

	/**
	 * Posi��o do m�dico no tabuleiro.
	 */
	private Posicao posicao;
	
	/**
	 * Probabilidade do m�dico atacar.
	 */
	private int ataque;
	
	/**
	 * Probabilidade do m�dico de defender.
	 */
	private int defesa;
	
	/**
	 * Dist�ncia que o m�dico pode se mover no campo.
	 */
	private int velocidade;
	
	/**
	 * Cria um m�dico com o nome dado.
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
	 * Cria um m�dico com o nome e posi��o dados.
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
