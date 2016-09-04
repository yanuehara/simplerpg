package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.DesfibriladorInimigo;

/**
 * Personagem m�dico inimigo.
 * <p>
 * Esta classe implementa M�dico Inimigo.
 *
 */
public class MedicoInimigo extends Medico {
	/**
	 * Constr�i um MedicoInimigo que possui um desfibrilador como arma.
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
