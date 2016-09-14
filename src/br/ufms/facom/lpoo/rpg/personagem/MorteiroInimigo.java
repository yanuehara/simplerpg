package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;
import br.ufms.facom.lpoo.rpg.arma.Morteiro;

/**
 * Personagem morteiro.
 * <p>
 * Esta classe possui todos os atributos e métodos básicos do personagem morteiro.
 *
 */
public class MorteiroInimigo implements Personagem{
	/**
	 * Arma do morteiro.
	 */
	protected Arma arma;

	/**
	 * Nível de vida do morteiro.
	 */
	private int vida;

	/**
	 * Nome do morteiro.
	 */
	private String nome;

	/**
	 * Posição do morteiro no tabuleiro.
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
	 * Distância que o morteiro pode se mover no campo.
	 */
	private int velocidade;
	
	/**
	 * Cria um morteiro com o nome dado.
	 * 
	 * @param nome
	 */
	public MorteiroInimigo(String nome) {
		this.nome = nome;
		vida = 5;
		ataque = 4;
		defesa = 2;
		posicao = new Posicao();
		velocidade = 0;
	}

	/**
	 * Cria um morteiro com o nome e posição dados.
	 * 
	 * @param nome
	 * @param x
	 * @param y
	 */
	public MorteiroInimigo(String nome, Morteiro morteiro, int x, int y) {
		this.nome = nome;
		vida = 5;
		ataque = 4;
		defesa = 2;
		velocidade = 0;
		posicao = new Posicao(x, y);
		this.arma = morteiro;
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
