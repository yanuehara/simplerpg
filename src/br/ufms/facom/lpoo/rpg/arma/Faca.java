package br.ufms.facom.lpoo.rpg.arma;

/**
 * Implementa uma arma: faca.
 * <p>
 * Esta implementa��o � apenas um exemplo que n�o segue as especifica��es do
 * trabalho.
 * 
 * @author eraldo
 *
 */
public class Faca implements Arma {

	@Override
	public int getAlcance() {
		return 0;
	}
	
	@Override
	public int getDano(){
		return 1;
	}
}