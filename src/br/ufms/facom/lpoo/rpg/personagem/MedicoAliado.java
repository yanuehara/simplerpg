package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.DesfibriladorAliado;

/**
 * Personagem m�dico aliado.
 * <p>
 * Esta classe implementa M�dico Aliado.
 *
 */
public class MedicoAliado extends Medico {
	/**
	 * Constr�i um MedicoAliado que possui um desfibrilador como arma.
	 * @param nome
	 * @param pistola
	 * @param x
	 * @param y
	 */
	public MedicoAliado(String nome, DesfibriladorAliado desfribilador, int x, int y) {
		super(nome, x, y);
		this.arma = desfribilador;
		
	}

}
