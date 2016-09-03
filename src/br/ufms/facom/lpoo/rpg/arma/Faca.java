package br.ufms.facom.lpoo.rpg.arma;

/**
 * Implementa uma arma: faca.
 * <p>
 * Esta implementação é apenas um exemplo que não segue as especificações do
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