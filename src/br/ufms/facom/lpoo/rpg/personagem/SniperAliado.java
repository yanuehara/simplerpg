package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Sniper;
/**
 * Personagem sniper aliado.
 * <p>
 * Esta classe implementa o sniper liado.
 *
 */
public class SniperAliado extends SniperPersonagem {
	/**
	 * Constrói um SniperAliado que possui uma sniper como arma.
	 * @param nome
	 * @param sniper
	 * @param x
	 * @param y
	 */
	public SniperAliado(String nome, Sniper sniper, int x, int y) {
		super(nome, x, y);
		arma = sniper;
			
	}
}
