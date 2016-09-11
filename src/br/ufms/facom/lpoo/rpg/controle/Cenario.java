package br.ufms.facom.lpoo.rpg.controle;

public class Cenario {
	private String nome;
	
	public Cenario(String fase){
		nome = fase;
	}
	
	public String getNome(){
		return nome;
	}
}
