package br.ufms.facom.lpoo.rpg.personagem;

/**
 * Personagem soldado inimigo.
 * <p>
 * Esta classe implementa os diferentes tipos de soldado inimigo.
 *
 */
public class SoldadoInimigo extends Soldado {
	
	/**
	 * Constrói um SoldadoInimigo que possui uma pistola como arma.
	 * @param nome
	 * @param pistola
	 * @param x
	 * @param y
	 */
	public SoldadoInimigo(String nome, Pistola pistola, int x, int y) {
		super(nome, x, y);
		arma = new Pistola();
		
	}
	
	/**
	 * Constrói um SoldadoAliado que possui um rifle como arma.
	 * @param nome
	 * @param rifle
	 * @param x
	 * @param y
	 */
	public SoldadoInimigo(String nome, Rifle rifle, int x, int y) {
		super(nome, x, y);
		arma = new Rifle();
		
	}
}
