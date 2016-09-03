package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Pistola;
import br.ufms.facom.lpoo.rpg.arma.Rifle;

/**
 * Personagem soldado aliado.
 * <p>
 * Esta classe implementa os diferentes tipos de Soldado Aliado.
 *
 */
public class SoldadoAliado extends Soldado {
	/**
	 * Constrói um SoldadoAliado que possui uma pistola como arma.
	 * @param nome
	 * @param pistola
	 * @param x
	 * @param y
	 */
	public SoldadoAliado(String nome, Pistola pistola, int x, int y) {
		super(nome, x, y);
		arma = pistola;
		
	}
	
	/**
	 * Constrói um SoldadoAliado que possui um rifle como arma.
	 * @param nome
	 * @param rifle
	 * @param x
	 * @param y
	 */
	public SoldadoAliado(String nome, Rifle rifle, int x, int y) {
		super(nome, x, y);
		arma = rifle;
		
	}
	

}
