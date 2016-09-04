package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;

/**
 * Personagem morteiro.
 * <p>
 * Esta classe possui todos os atributos e m�todos b�sicos do personagem morteiro.
 *
 */
public abstract class MorteiroPersonagem implements Personagem{
	/**
	 * Arma do morteiro.
	 */
	protected Arma arma;

	/**
	 * N�vel de vida do morteiro.
	 */
	private int vida;

	/**
	 * Nome do morteiro.
	 */
	private String nome;

	/**
	 * Posi��o do morteiro no tabuleiro.
	 */
	private Posicao posicao;
	
	/**
	 * Probabilidade do morteiro atacar.
	 */
	private int ataque;
	
	/**
	 * Probabilidade do morteiro de defender.
	 */
	private int defesa;
	
	/**
	 * Dist�ncia que o morteiro pode se mover no campo.
	 */
	private int velocidade;
	
	/**
	 * Cria um morteiro com o nome dado.
	 * 
	 * @param nome
	 */
	public MorteiroPersonagem(String nome) {
		this.nome = nome;
		vida = 5;
		ataque = 4;
		defesa = 2;
		posicao = new Posicao();
		velocidade = 0;
	}

	/**
	 * Cria um morteiro com o nome e posi��o dados.
	 * 
	 * @param nome
	 * @param x
	 * @param y
	 */
	public MorteiroPersonagem(String nome, int x, int y) {
		this.nome = nome;
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
		this.vida = vida;
	}
}