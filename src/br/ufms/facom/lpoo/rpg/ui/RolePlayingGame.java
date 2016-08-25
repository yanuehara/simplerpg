package br.ufms.facom.lpoo.rpg.ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.ufms.facom.lpoo.rpg.controle.Controle;
import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Interface do RPG.
 * <p>
 * Implementa a exibição do tabuleiro (casas e personagens com suas armas,
 * níveis de vida, nomes), exibição das mensagens e entradas do usuário (seleção
 * de posição e seleção de personagem).
 * 
 * @author eraldo
 *
 */
public class RolePlayingGame extends Application {
	/**
	 * Canvas que exibe o tabuleiro.
	 */
	private Canvas canvas;

	/**
	 * Lista de mensagens. Cada mensagem é um par <String,Boolean>. O valor
	 * booleano indica se a mensagem é de erro ou não.
	 */
	private ObservableList<Pair<String, Boolean>> mensagens;

	/**
	 * Armazena os ícones dos personagens adicionados ao tabuleiro.
	 */
	private Map<String, Image> icons;

	/**
	 * Lista de personagens no tabuleiro.
	 */
	private List<Personagem> personagens;

	/**
	 * Objeto de controle do jogo.
	 */
	private Controle controle;

	/**
	 * Thread que executa o controle do jogo.
	 */
	private Thread threadControle;

	/**
	 * Objeto usado na sincronização da entrada do usuário (seleção de posição e
	 * de personagem com o mouse).
	 * <p>
	 * O procedimento de entrada é implementado como um produtor-consumidor. A
	 * thread de controle é o consumidor (de posições e de personagens). A
	 * interface (thread da aplicação) é o produtor. Entretanto, a produção é
	 * solicitada pelo consumidor (controle). Somente após o controle solicitar
	 * uma entrada (posição ou personagem) é que a entrada é produzida. Quando a
	 * entrada é solicitada pelo controle, a thread de controle é bloqueada e a
	 * interface aguarda o usuário fazer a seleção adequada. Assim que o usuário
	 * seleciona a entrada esperada, a interface a fornece à thread de controle.
	 */
	private SelecaoTabuleiro selecao;

	/**
	 * Largura, em pixels, do tabuleiro (canvas).
	 */
	private static final int W = 768;

	/**
	 * Altura, em pixels, do tabuleiro (canvas).
	 */
	private static final int H = 768;

	/**
	 * Largura, em casas, do tabuleiro.
	 */
	public static final int MAX_X = 8;

	/**
	 * Altura, em casas, do tabuleiro.
	 */
	public static final int MAX_Y = 8;

	/**
	 * Largura, em pixels, de uma casa do tabuleiro.
	 */
	private static final int CELL_W = W / MAX_X;

	/**
	 * Altura, em pixels, de uma casa do tabuleiro.
	 */
	private static final int CELL_H = H / MAX_Y;

	/**
	 * Ponto de entrada da aplicação.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Cria os atributos principais da interface.
	 */
	public RolePlayingGame() {
		personagens = new LinkedList<>();
		icons = new HashMap<>();
		controle = new Controle(this);
		mensagens = FXCollections.observableArrayList();
		selecao = new SelecaoTabuleiro();
	}

	/**
	 * Adiciona o personagem <code>p</code> ao tabuleiro. Este personagem será
	 * desenhado na próxima chamada ao método <code>atualizaTabuleiro()</code>.
	 * 
	 * @param p
	 */
	public void addPersonagem(Personagem p) {
		personagens.add(p);
	}

	/**
	 * Adiciona mensagem informativa.
	 * 
	 * @param msg
	 */
	public void info(String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mensagens.add(0, new Pair<>(msg, false));
			}
		});
	}

	/**
	 * Adiciona mensagem de erro.
	 * 
	 * @param msg
	 */
	public void erro(String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mensagens.add(0, new Pair<>(msg, true));
			}
		});
	}

	/**
	 * Desenha o tabuleiro atual na tela.
	 */
	public void atualizaTabuleiro() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				desenhaCanvas();
			}
		});
	}

	/**
	 * Espera o usuário selecionar um personagem no tabuleiro e o retorna.
	 * <p>
	 * Este método deve ser chamado pela thread de controle. A thread ficará
	 * bloqueada até o usuário selecionar um personagem ou a aplicação ser
	 * finalizada.
	 * 
	 * @return personagem selecionado.
	 * @throws InterruptedException
	 *             caso a aplicação seja finalizada antes da seleção do usuário.
	 */
	public Personagem selecionaPersonagem() throws InterruptedException {
		synchronized (selecao) {
			while (selecao.estado != EstadoSelecao.DESOCUPADO)
				selecao.wait();

			selecao.personagem = null;
			selecao.estado = EstadoSelecao.PERSONAGEM;

			while (selecao.personagem == null)
				selecao.wait();

			Personagem p = selecao.personagem;
			selecao.personagem = null;
			selecao.estado = EstadoSelecao.DESOCUPADO;

			return p;
		}
	}

	/**
	 * Espera o usuário selecionar uma casa do tabuleiro e a retorna.
	 * <p>
	 * Este método deve ser chamado pela thread de controle. A thread ficará
	 * bloqueada até o usuário selecionar uma casa ou a aplicação ser
	 * finalizada.
	 * 
	 * @return a casa (posição) selecionada.
	 * @throws InterruptedException
	 *             caso a aplicação seja finalizada antes da seleção do usuário.
	 */
	public Posicao selecionaPosicao() throws InterruptedException {
		synchronized (selecao) {
			while (selecao.estado != EstadoSelecao.DESOCUPADO)
				selecao.wait();

			selecao.pos = null;
			selecao.estado = EstadoSelecao.POSICAO;

			while (selecao.pos == null)
				selecao.wait();

			Posicao pos = selecao.pos;
			selecao.pos = null;
			selecao.estado = EstadoSelecao.DESOCUPADO;

			return pos;
		}
	}

	/**
	 * Desenha o tabuleiro e os personagens.
	 */
	private void desenhaCanvas() {
		// Desenha tabuleiro.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.GREY);
		for (int x = 1; x <= MAX_X; ++x)
			gc.strokeLine(x * CELL_W, 0, x * CELL_W, H);
		for (int y = 1; y <= MAX_Y; ++y)
			gc.strokeLine(0, y * CELL_H, W, y * CELL_H);

		// Desenha personagens.
		desenhaPersonagens(gc);
	}

	/**
	 * Desenha personagens sobre o tabuleiro.
	 * 
	 * @param gc
	 */
	private void desenhaPersonagens(GraphicsContext gc) {
		for (Personagem p : personagens) {
			// Converte posição em células para pixels.
			double x = p.getX() * CELL_W;
			double y = p.getY() * CELL_H;
			// Desenha ícone do personagem.
			gc.drawImage(getIcon(p), x, y);
			// Desenha ícone da arma.
			gc.drawImage(getIcon(p.getArma()), x + 72, y + 72);
			// Desenha nome do personagem.
			gc.setStroke(Color.BLUE);
			gc.strokeText(p.getNome(), x, y + 90);
			// Desenha barra de vida.
			gc.setFill(Color.GREY);
			gc.fillRect(x + 76, y + 4, 16, 64);
			int vida = p.getVida();
			if (vida < 0)
				vida = 0;
			else if (vida > 5)
				vida = 5;
			if (vida <= 1)
				gc.setFill(Color.RED);
			else if (vida <= 3)
				gc.setFill(Color.YELLOW);
			else
				gc.setFill(Color.GREEN);
			gc.fillRect(x + 76, y + 4 + (5 - vida) * 12.8, 16, 64 - (5 - vida) * 12.8);
		}
	}

	/**
	 * Dado um objeto associado a um ícone (personagem ou arma), carrega a
	 * imagem correspondente.
	 * 
	 * @param o
	 * @return
	 */
	private Image getIcon(Object o) {
		String className = o.getClass().getSimpleName();
		Image icon = icons.get(className);
		if (icon == null) {
			icon = new Image("/icons/" + className + ".png");
			icons.put(className, icon);
		}
		return icon;
	}

	/**
	 * Trata o evento de clique sobre o tabuleiro. As coordenadas fornecidas
	 * (<code>x</code> e <code>y</code>) já estão convertidas em casas do
	 * tabuleiro (coluna e linha).
	 * 
	 * @param x
	 *            índice horizontal (coluna) da casa que foi clicada.
	 * @param y
	 *            índice vertical (linha) da casa que foi clicada.
	 */
	private void onCanvasClick(int x, int y) {
		synchronized (selecao) {
			switch (selecao.estado) {
			case PERSONAGEM:
				if (selecao.personagem != null) {
					/*
					 * Um personagem já foi selecionado, mas não foi consumido.
					 * Notica todas as threads e retorna.
					 */
					selecao.notifyAll();
					return;
				}

				// Busca personagem que está na célula selecionada.
				for (Personagem p : personagens) {
					if (p.getX() == x && p.getY() == y) {
						/*
						 * Encontrou um persona naquela posição. Armazena ele no
						 * objeto selecao, notifica todas as threads e retorna.
						 */
						selecao.personagem = p;
						selecao.notifyAll();
						return;
					}
				}

				/*
				 * Usuário clicou sobre uma célula que não contém um personagem.
				 * Espera outro clique.
				 */
				return;

			case POSICAO:
				if (selecao.pos != null) {
					/*
					 * Uma posição já foi selecionada, mas não foi consumida.
					 * Notica todas as threads e retorna.
					 */
					selecao.notifyAll();
					return;
				}

				/*
				 * Atualiza a posição selecionada, notifica todas as threads e
				 * retorna.
				 */
				selecao.pos = new Posicao(x, y);
				selecao.notifyAll();
				return;

			default:
				/*
				 * Estado desconhecido. Notifica todas as threads e retorna.
				 * Isto é apenas por robustez, pois este caso não deveria
				 * ocorrer.
				 */
				System.err.println("Estado de seleção inválido!");
				selecao.notifyAll();
				return;

			}
		}
	}

	@Override
	public void start(Stage primaryStage) {
		// Cria painel grid.
		GridPane grid = new GridPane();

		// Canvas que exibe o tabuleiro.
		canvas = new Canvas(768, 768);
		desenhaCanvas();
		grid.add(canvas, 0, 0, 1, 2);
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				onCanvasClick((int) (event.getX() / CELL_W), (int) (event.getY() / CELL_H));
			}
		});

		// Lista de mensagens.
		ListView<Pair<String, Boolean>> viewMensagens = new ListView<>();
		grid.add(viewMensagens, 1, 0);
		viewMensagens.setPrefSize(256, 576);
		viewMensagens.setCellFactory((ListView<Pair<String, Boolean>> l) -> new MensagemCell());
		viewMensagens.setItems(mensagens);

		// Painel de botões.
		FlowPane paneBotoes = new FlowPane();
		grid.add(paneBotoes, 1, 1);
		Button btnSair = new Button("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		paneBotoes.getChildren().add(btnSair);

		// Configura Stage e o exibe.
		primaryStage.setTitle("Role Playing Game");
		Scene scene = new Scene(grid, 1024, 768);
		primaryStage.setScene(scene);
		primaryStage.show();

		/*
		 * Cria a thread de controle que fica, indefinidamente, invocando o
		 * método controle.executaTurno().
		 */
		threadControle = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						controle.executaTurno();
						synchronized (this) {
							Thread.sleep(100);
						}
					}
				} catch (InterruptedException e) {
				} finally {
					System.out.println("Thread de controle interrompiada com sucesso!");
				}
			}
		});

		// Inicia thread de controle.
		threadControle.start();
	}

	@Override
	public void stop() throws Exception {
		super.stop();

		// Interrompe thread de controle.
		threadControle.interrupt();
	}

}
