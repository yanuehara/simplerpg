package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.DesfibriladorAliado;

/**
 * Personagem médico aliado.
 * <p>
 * Esta classe implementa Médico Aliado.
 *
 */
public class MedicoAliado extends Medico {
	/**
	 * Constrói um MedicoAliado que possui um desfibrilador como arma.
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
