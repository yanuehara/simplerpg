package br.ufms.facom.lpoo.rpg.arma;

/**
 * Interface a ser implementada por todos os tipos de arma.
 * 
 * @author eraldo
 *
 */
public interface Arma {
	/**
	 * Alcance da arma em n�mero de casas de acordo com a dist�ncia de Manhattan
	 * (d = |x1 - x2| + |y1 - y2|). Este valor deve ser um inteiro no intervalo
	 * [1,5].
	 * <p>
	 * Armas de combate corpo a corpo t�m alcance igual a 1. Armas de combate a
	 * dist�ncia t�m alcance maior do que 1.
	 * 
	 * @return
	 */
	public int getAlcance();
	
	public int getDano();
}