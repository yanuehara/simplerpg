package br.ufms.facom.lpoo.rpg.personagem;

/**
 * Armazena uma posi��o no tabuleiro (casa).
 * <p>
 * Uma posi��o � representada por um �ndice horizontal (x) e um �ndice vertical
 * (y). Ambos os �ndices devem ser n�meros inteiros no intervalo [0,7].
 * 
 * @author eraldo
 *
 */
public class Posicao {
	/**
	 * �ndice horizontal (coluna).
	 */
	public int x;

	/**
	 * �ndice vertical (linha).
	 */
	public int y;

	/**
	 * Cria uma posi��o que corresponde � casa (0,0).
	 */
	public Posicao() {
	}

	/**
	 * Cria uma posi��o que corresponde � casa (x,y).
	 * 
	 * @param x
	 *            �ndice horizontal.
	 * @param y
	 *            �ndice vertical.
	 */
	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}
}