package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Sniper;
/**
 * Personagem sniper inimigo.
 * <p>
 * Esta classe implementa o sniper inimigo.
 *
 */
public class SniperInimigo extends SniperPersonagem {
	/**
	 * Constrói um SniperInimigo que possui uma sniper como arma.
	 * @param nome
	 * @param sniper
	 * @param x
	 * @param y
	 */
	public SniperInimigo(String nome, Sniper sniper, int x, int y) {
		super(nome, x, y);
		arma = sniper;
			
	}
}
