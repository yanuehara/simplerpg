package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;

/**
 * Interface que deve ser implementada por todos os tipos de personagem.
 * 
 * @author eraldo
 *
 */
public interface Personagem {

	/**
	 * Atributo de defesa do personagem: um valor inteiro no intervalo [1,5].
	 * 
	 * @return
	 */
	public int getDefesa();

	/**
	 * Atributo de ataque do personagem: um valor inteiro no intervalo [1,5].
	 * 
	 * @return
	 */
	public int getAtaque();

	/**
	 * Velocidade do personagem: um valor inteiro no intervalo [1,5].
	 * <p>
	 * Este valor indica o máximo de casas que o personagem pode se mover por
	 * turno. Sendo que a movimentação é restrita às direções verticais e
	 * horizontais, ou seja, não é permitido que o personagem se movimente na
	 * diagonal.
	 * 
	 * @return
	 */
	public int getVelocidade();

	/**
	 * Nível de vida do personagem: um valor inteiro no intervalo [0,5].
	 * <p>
	 * Quando o personagem tem nível de vida igual a zero, ele está morto.
	 * 
	 * @return
	 */
	public int getVida();

	/**
	 * Altera o nível de vida do personagem.
	 * 
	 * @param vida
	 */
	public void setVida(int vida);

	/**
	 * Retorna a arma do personagem.
	 * 
	 * @return
	 */
	public Arma getArma();

	/**
	 * Retorna o nome deste personagem.
	 * 
	 * @return
	 */
	public String getNome();

	/**
	 * Retorna o índice horizontal da posição do personagem no tabuleiro (casa).
	 * 
	 * @return
	 */
	public int getX();

	/**
	 * Altera a posição do personagem (índice horizontal).
	 * 
	 * @param x
	 */
	public void setX(int x);

	/**
	 * Retorna o índice vertical da posição do personagem no tabuleiro (casa).
	 * 
	 * @return
	 */
	public int getY();

	/**
	 * Altera a posição do personagem (índice vertical).
	 * 
	 * @param y
	 */
	public void setY(int y);
}
