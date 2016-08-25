package br.ufms.facom.lpoo.rpg.ui;

import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;

/**
 * Estado do processo de seleção.
 * 
 * @author eraldo
 *
 */
enum EstadoSelecao {
	/**
	 * Processo de seleção está desocupado. Nenhuma seleção foi solicitada pela
	 * thread de controle.
	 */
	DESOCUPADO,

	/**
	 * Processo de seleção está aguardando a seleção de um personagem. A thread
	 * de controle solicitou a seleção de um personagem e está aguardando o
	 * usuário selecionar.
	 */
	PERSONAGEM,

	/**
	 * Processo de leitura está aguardando a seleção de uma posição. A thread de
	 * controle solicitou a seleção de uma posição (casa do tabuleiro) e está
	 * aguardando o usuário selecionar.
	 */
	POSICAO
}

/**
 * Controla o processo de seleção de personagem ou de posição no tabuleiro.
 * <p>
 * Um objeto desta classe é usada para duas funções:
 * 
 * <ul>
 * <li>para sincronizar as threads produtoras e consumidoras das seleções
 * (aplicação e controle, respectivamente); e</li>
 * <li>para armazenar os objetos selecionados.</li>
 * </ul>
 * <p>
 * Apesar do atributo <code>estado</code> possuir três valores possíveis, o
 * processo de seleção possui dois estados intermediários adicionais que são
 * usados para controlar o processo produtor-consumidor. O processo de seleção
 * de personagem segue o fluxo apresentado abaixo.
 * <ol>
 * <li>A thread de cotrole invoca o método <code>selecionaPersonagem()</code> da
 * aplicação. Neste momento, <code>estado</code> deve conter o valor
 * <code>DESOCUPADO</code>. Se não for o caso, a thread de controle é bloqueada
 * até que esta condição torne-se verdadeira. (Entretanto, este caso nunca deve
 * ocorrer nesta implementação, pois temos apenas uma thread consumidora.)</li>
 * 
 * <li>O valor de <code>estado</code> é alterado para <code>PERSONAGEM</code> e
 * o valor de <code>personagem</code> é alterado para <code>null</code>. A
 * thread de controle é então bloqueada até que o valor de
 * <code>personagem</code> seja diferente de <code>null</code>.</li>
 * 
 * <li>Quando o usuário seleciona um personagem no tabuleiro, o valor de
 * <code>estado</code> é igual a <code>PERSONAGEM</code> e o valor do atributo
 * <code>personagem</code> é igual a <code>null</code>, então a thread da
 * aplicação sabe que deve atribuir o objeto personagem selecionado ao atributo
 * <code>personagem</code>. Logo em seguida, a thread da aplicação notifica a
 * thread de controle.</li>
 * 
 * <li>A thread de controle é notificada (lembrando que ela estava bloqueada
 * dentro do método <code>selecionaPersonagem()</code>). Então esta thread obtém
 * o personagem do atributo <code>personagem</code> e o retorna. Antes de
 * retornar, porém, a thread altera o valor de <code>estado</code> para
 * <code>DESOCUPADO</code> e o valor de <code>personagem</code> para
 * <code>null</code>.</li>
 * </ol>
 * 
 * O processo de seleção de posição é análogo. Ao invés de usar os atributos
 * <code>estado=PERSONAGEM</code> e <code>personagem</code>, este processo usa
 * os atributos <code>estado=POSICAO</code> e <code>posicao</code>.
 * 
 * @author eraldo
 *
 */
class SelecaoTabuleiro {
	/**
	 * Estado da seleção.
	 */
	EstadoSelecao estado;

	/**
	 * Personagem selecionado.
	 */
	Personagem personagem;

	/**
	 * Casa (posição) selecionada.
	 */
	Posicao pos;

	/**
	 * Construtor padrão.
	 */
	SelecaoTabuleiro() {
		estado = EstadoSelecao.DESOCUPADO;
	}
}