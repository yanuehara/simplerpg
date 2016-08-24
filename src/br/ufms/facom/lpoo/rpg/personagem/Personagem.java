package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;

public interface Personagem {
	public int getDefesa();

	public int getAtaque();

	public int getVelocidade();

	public int getVida();

	public void setVida(int vida);

	public Arma getArma();

	public String getNome();

	public int getX();

	public void setX(int x);

	public int getY();

	public void setY(int y);
}
