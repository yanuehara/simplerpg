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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

public class RolePlayingGame extends Application {
	private Canvas canvas;

	private ObservableList<Pair<String, Boolean>> mensagens;

	private Map<String, Image> icons;

	private List<Personagem> personagens;

	private Controle controle;

	private Thread threadControle;

	private Leitura leitura;

	private static final int W = 768;
	private static final int H = 768;

	public static final int MAX_X = 8;
	public static final int MAX_Y = 8;

	private static final int CELL_W = W / MAX_X;
	private static final int CELL_H = H / MAX_Y;

	public static void main(String[] args) {
		launch(args);
	}

	public RolePlayingGame() {
		personagens = new LinkedList<>();
		icons = new HashMap<>();
		controle = new Controle(this);
		mensagens = FXCollections.observableArrayList();
		leitura = new Leitura();
	}

	public void addPersonagem(Personagem p) {
		personagens.add(p);
	}

	public void info(String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mensagens.add(0, new Pair<>(msg, false));
			}
		});
	}

	public void erro(String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mensagens.add(0, new Pair<>(msg, true));
			}
		});
	}

	public void atualiza() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				desenhaCanvas();
			}
		});
	}

	public Personagem selecionaPersonagem() throws InterruptedException {
		synchronized (leitura) {
			while (leitura.estado != EstadoLeitura.DESOCUPADO)
				leitura.wait();

			leitura.personagem = null;
			leitura.estado = EstadoLeitura.PERSONAGEM;

			while (leitura.personagem == null)
				leitura.wait();

			Personagem p = leitura.personagem;
			leitura.personagem = null;
			leitura.estado = EstadoLeitura.DESOCUPADO;

			return p;
		}
	}

	public Posicao selecionaPosicao() throws InterruptedException {
		synchronized (leitura) {
			while (leitura.estado != EstadoLeitura.DESOCUPADO)
				leitura.wait();

			leitura.pos = null;
			leitura.estado = EstadoLeitura.POSICAO;

			while (leitura.pos == null)
				leitura.wait();

			Posicao pos = leitura.pos;
			leitura.pos = null;
			leitura.estado = EstadoLeitura.DESOCUPADO;

			return pos;
		}
	}

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

	private Image getIcon(Object o) {
		String className = o.getClass().getSimpleName();
		Image icon = icons.get(className);
		if (icon == null) {
			icon = new Image("/icons/" + className + ".png");
			icons.put(className, icon);
		}
		return icon;
	}

	private void onCanvasClick(int x, int y) {
		synchronized (leitura) {
			switch (leitura.estado) {
			case PERSONAGEM:
				if (leitura.personagem != null)
					return;

				// Busca personagem que está na célula selecionada.
				for (Personagem p : personagens) {
					if (p.getX() == x && p.getY() == y) {
						leitura.personagem = p;
						leitura.notifyAll();
						break;
					}
				}

				return;

			case POSICAO:
				if (leitura.pos != null)
					return;
				leitura.pos = new Posicao(x, y);
				leitura.notifyAll();
				return;

			default:
				return;

			}
		}
	}

	@Override
	public void start(Stage primaryStage) {
		// Cria painel grid.
		GridPane grid = new GridPane();

		// Canvas do tabuleiro.
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
				threadControle.interrupt();
				Platform.exit();
			}
		});
		paneBotoes.getChildren().add(btnSair);

		// Configura Stage e o exibe.
		primaryStage.setTitle("Role Playing Game");
		Scene scene = new Scene(grid, 1024, 768);
		primaryStage.setScene(scene);
		primaryStage.show();

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

		threadControle.start();
	}
}

class MensagemCell extends ListCell<Pair<String, Boolean>> {
	private Text mensagem;

	public MensagemCell() {
		mensagem = new Text();
		mensagem.setWrappingWidth(250);
	}

	@Override
	public void updateItem(Pair<String, Boolean> item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			mensagem.setText(item.getKey());
			if (item.getValue().booleanValue())
				mensagem.setFill(Color.RED);
			setGraphic(mensagem);
		} else
			mensagem.setText("");
	}
}

enum EstadoLeitura {
	/**
	 * Processo de leitura está desocupado, aguardando uma tarefa.
	 */
	DESOCUPADO,

	/**
	 * Processo de leitura está aguardando a seleção de um personagem.
	 */
	PERSONAGEM,

	/**
	 * Processo de leitura está aguardando a seleção de uma posição.
	 */
	POSICAO
}

class Leitura {
	EstadoLeitura estado;
	Personagem personagem;
	Posicao pos;

	Leitura() {
		estado = EstadoLeitura.DESOCUPADO;
	}
}
