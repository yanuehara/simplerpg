/**
 * 
 */
package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.MiniGun;
/**
 * Implementa a classe Comandante, um soldado especial que ajuda na última fase do jogo.
 *
 */
public class Comandante extends Soldado {
	/**
	 * Constrói um Comandante que possui uma minigun como arma.
	 * @param nome
	 * @param minigun
	 * @param x
	 * @param y
	 */
	public Comandante(String nome, MiniGun minigun, int x, int y) {
		super(nome, x, y);
		this.ataque = 4;
		this.defesa = 2;
		
		arma = minigun;		
	}
	
}
