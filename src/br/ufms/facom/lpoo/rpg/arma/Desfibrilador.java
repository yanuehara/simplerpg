package br.ufms.facom.lpoo.rpg.arma;

public abstract class Desfibrilador implements Arma {

	@Override
	public int getAlcance() {
		return 1;
	}

	@Override
	public int getDano() {
		return -3;	
	}

}