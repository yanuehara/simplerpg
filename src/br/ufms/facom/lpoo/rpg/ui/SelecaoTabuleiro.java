package br.ufms.facom.lpoo.rpg.ui;

import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;

/**
 * Estado do processo de sele��o.
 * 
 * @author eraldo
 *
 */
enum EstadoSelecao {
	/**
	 * Processo de sele��o est� desocupado. Nenhuma sele��o foi solicitada pela
	 * thread de controle.
	 */
	DESOCUPADO,

	/**
	 * Processo de sele��o est� aguardando a sele��o de um personagem. A thread
	 * de controle solicitou a sele��o de um personagem e est� aguardando o
	 * usu�rio selecionar.
	 */
	PERSONAGEM,

	/**
	 * Processo de leitura est� aguardando a sele��o de uma posi��o. A thread de
	 * controle solicitou a sele��o de uma posi��o (casa do tabuleiro) e est�
	 * aguardando o usu�rio selecionar.
	 */
	POSICAO
}

/**
 * Controla o processo de sele��o de personagem ou de posi��o no tabuleiro.
 * <p>
 * Um objeto desta classe � usada para duas fun��es:
 * 
 * <ul>
 * <li>para sincronizar as threads produtoras e consumidoras das sele��es
 * (aplica��o e controle, respectivamente); e</li>
 * <li>para armazenar os objetos selecionados.</li>
 * </ul>
 * <p>
 * Apesar do atributo <code>estado</code> possuir tr�s valores poss�veis, o
 * processo de sele��o possui dois estados intermedi�rios adicionais que s�o
 * usados para controlar o processo produtor-consumidor. O processo de sele��o
 * de personagem segue o fluxo apresentado abaixo.
 * <ol>
 * <li>A thread de cotrole invoca o m�todo <code>selecionaPersonagem()</code> da
 * aplica��o. Neste momento, <code>estado</code> deve conter o valor
 * <code>DESOCUPADO</code>. Se n�o for o caso, a thread de controle � bloqueada
 * at� que esta condi��o torne-se verdadeira. (Entretanto, este caso nunca deve
 * ocorrer nesta implementa��o, pois temos apenas uma thread consumidora.)</li>
 * 
 * <li>O valor de <code>estado</code> � alterado para <code>PERSONAGEM</code> e
 * o valor de <code>personagem</code> � alterado para <code>null</code>. A
 * thread de controle � ent�o bloqueada at� que o valor de
 * <code>personagem</code> seja diferente de <code>null</code>.</li>
 * 
 * <li>Quando o usu�rio seleciona um personagem no tabuleiro, o valor de
 * <code>estado</code> � igual a <code>PERSONAGEM</code> e o valor do atributo
 * <code>personagem</code> � igual a <code>null</code>, ent�o a thread da
 * aplica��o sabe que deve atribuir o objeto personagem selecionado ao atributo
 * <code>personagem</code>. Logo em seguida, a thread da aplica��o notifica a
 * thread de controle.</li>
 * 
 * <li>A thread de controle � notificada (lembrando que ela estava bloqueada
 * dentro do m�todo <code>selecionaPersonagem()</code>). Ent�o esta thread obt�m
 * o personagem do atributo <code>personagem</code> e o retorna. Antes de
 * retornar, por�m, a thread altera o valor de <code>estado</code> para
 * <code>DESOCUPADO</code> e o valor de <code>personagem</code> para
 * <code>null</code>.</li>
 * </ol>
 * 
 * O processo de sele��o de posi��o � an�logo. Ao inv�s de usar os atributos
 * <code>estado=PERSONAGEM</code> e <code>personagem</code>, este processo usa
 * os atributos <code>estado=POSICAO</code> e <code>posicao</code>.
 * 
 * @author eraldo
 *
 */
class SelecaoTabuleiro {
	/**
	 * Estado da sele��o.
	 */
	EstadoSelecao estado;

	/**
	 * Personagem selecionado.
	 */
	Personagem personagem;

	/**
	 * Casa (posi��o) selecionada.
	 */
	Posicao pos;

	/**
	 * Construtor padr�o.
	 */
	SelecaoTabuleiro() {
		estado = EstadoSelecao.DESOCUPADO;
	}
}