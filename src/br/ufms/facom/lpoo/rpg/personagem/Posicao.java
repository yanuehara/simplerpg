package br.ufms.facom.lpoo.rpg.personagem;

/**
 * Armazena uma posição no tabuleiro (casa).
 * <p>
 * Uma posição é representada por um índice horizontal (x) e um índice vertical
 * (y). Ambos os índices devem ser números inteiros no intervalo [0,7].
 * 
 * @author eraldo
 *
 */
public class Posicao {
	/**
	 * Índice horizontal (coluna).
	 */
	public int x;

	/**
	 * Índice vertical (linha).
	 */
	public int y;

	/**
	 * Cria uma posição que corresponde à casa (0,0).
	 */
	public Posicao() {
	}

	/**
	 * Cria uma posição que corresponde à casa (x,y).
	 * 
	 * @param x
	 *            índice horizontal.
	 * @param y
	 *            índice vertical.
	 */
	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}
}