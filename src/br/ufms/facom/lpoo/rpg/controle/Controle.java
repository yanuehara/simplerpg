package br.ufms.facom.lpoo.rpg.controle;

import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;
import br.ufms.facom.lpoo.rpg.personagem.Soldado;
import br.ufms.facom.lpoo.rpg.ui.RolePlayingGame;

public class Controle {

	private RolePlayingGame rpg;

	private Soldado sold1;

	private Soldado sold2;

	public Controle(RolePlayingGame rpg) {
		this.rpg = rpg;
		sold1 = new Soldado("Sold1", 0, 0);
		sold2 = new Soldado("Sold2", RolePlayingGame.MAX_X - 1, RolePlayingGame.MAX_Y - 1);
		rpg.addPersonagem(sold2);
		rpg.addPersonagem(sold1);
	}

	public void executaTurno() throws InterruptedException {
		rpg.info(String.format("Personagem %s, selecione sua nova posição!", sold1.getNome()));
		Posicao pos = rpg.selecionaPosicao();
		sold1.setX(pos.x);
		sold1.setY(pos.y);

		rpg.atualiza();

		rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", sold1.getNome()));
		Personagem p = rpg.selecionaPersonagem();
		if (p != sold1)
			p.setVida(p.getVida() - 1);
		else
			rpg.erro("Você não pode atacar você mesmo! Perdeu a vez.");

		rpg.atualiza();

		rpg.info(String.format("Personagem %s, selecione sua nova posição!", sold2.getNome()));
		pos = rpg.selecionaPosicao();
		sold2.setX(pos.x);
		sold2.setY(pos.y);

		rpg.atualiza();

		rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", sold2.getNome()));
		p = rpg.selecionaPersonagem();
		if (p != sold2)
			p.setVida(p.getVida() - 1);
		else
			rpg.erro("Você não pode atacar você mesmo! Perdeu a vez.");

		rpg.atualiza();
	}
}
