package controller;
import model.estrutura.arvore.ArvoreBinaria;

public class ArvoreBinariaController {
	public void teste() throws Exception {
		ArvoreBinaria<Integer> arvore = new ArvoreBinaria<Integer>();
		
		arvore.adicionar(46);
		arvore.adicionar(45);
		arvore.adicionar(41);
		arvore.adicionar(40);
		arvore.adicionar(36);
		arvore.adicionar(35);
		arvore.adicionar(31);
		arvore.adicionar(30);
		arvore.adicionar(29);
		arvore.adicionar(28);
		arvore.adicionar(27);
		arvore.adicionar(25);
		arvore.adicionar(24);
		arvore.adicionar(23);
		arvore.adicionar(22);
		
		//System.out.println(arvore.toString()+ "\n");
		arvore.exibirEmOrdem();
		arvore.exibirPosOrdem();
		arvore.exibirPreOrdem();
		
		//arvore.remover(30);
		//arvore.exibirPreOrdem();
		
		//arvore.remover(21);
		//arvore.exibirEmOrdem();
		
		//arvore.remover(31);
		//arvore.exibirPreOrdem();
		
		//arvore.remover(20);
		//arvore.exibirPreOrdem();
	}
}
