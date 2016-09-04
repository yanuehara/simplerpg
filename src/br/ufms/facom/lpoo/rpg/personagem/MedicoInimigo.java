package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.DesfibriladorInimigo;

/**
 * Personagem médico inimigo.
 * <p>
 * Esta classe implementa Médico Inimigo.
 *
 */
public class MedicoInimigo extends Medico {
	/**
	 * Constrói um MedicoInimigo que possui um desfibrilador como arma.
	 * @param nome
	 * @param pistola
	 * @param x
	 * @param y
	 */
	public MedicoInimigo(String nome, DesfibriladorInimigo desfribilador, int x, int y) {
		super(nome, x, y);
		this.arma = desfribilador;
		
	}

}
