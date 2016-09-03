package br.ufms.facom.lpoo.rpg.arma;

public abstract class Desfibrilador implements Arma {

	@Override
	public int getAlcance() {
		//é a arma		
		
		return 2;
	}

	@Override
	public int getDano() {
		// TODO Auto-generated method stub
		return -3;//pq ele cura
	}

}
//med 3, mort4, snip4, soldm 2, soldc4 dano