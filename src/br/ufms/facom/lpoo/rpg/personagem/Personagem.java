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
	 * Este valor indica o m�ximo de casas que o personagem pode se mover por
	 * turno. Sendo que a movimenta��o � restrita �s dire��es verticais e
	 * horizontais, ou seja, n�o � permitido que o personagem se movimente na
	 * diagonal.
	 * 
	 * @return
	 */
	public int getVelocidade();

	/**
	 * N�vel de vida do personagem: um valor inteiro no intervalo [0,5].
	 * <p>
	 * Quando o personagem tem n�vel de vida igual a zero, ele est� morto.
	 * 
	 * @return
	 */
	public int getVida();

	/**
	 * Altera o n�vel de vida do personagem.
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
	 * Retorna o �ndice horizontal da posi��o do personagem no tabuleiro (casa).
	 * 
	 * @return
	 */
	public int getX();

	/**
	 * Altera a posi��o do personagem (�ndice horizontal).
	 * 
	 * @param x
	 */
	public void setX(int x);

	/**
	 * Retorna o �ndice vertical da posi��o do personagem no tabuleiro (casa).
	 * 
	 * @return
	 */
	public int getY();

	/**
	 * Altera a posi��o do personagem (�ndice vertical).
	 * 
	 * @param y
	 */
	public void setY(int y);
}