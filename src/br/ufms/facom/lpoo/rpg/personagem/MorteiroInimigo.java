package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.personagem.MorteiroPersonagem;
import br.ufms.facom.lpoo.rpg.arma.Morteiro;

public class MorteiroInimigo extends MorteiroPersonagem {
	/**
	 * Constr�i um MorteiroAliado que possui uma morteiro como arma.
	 * @param nome
	 * @param morteiro
	 * @param x
	 * @param y
	 */
	public MorteiroInimigo(String nome, Morteiro morteiro, int x, int y) {
		super(nome, x, y);
		this.arma = morteiro;
	}

}
