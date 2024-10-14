package model.estrutura.arvore;

public class No<T extends Comparable<T>> {
	
	private T elemento;
	private No<T> noDir;
	private No<T> noEsq;
	private No<T> raiz;
	private int balanceamento; 
	
	public No(T elemento) {
		this.elemento = elemento;
		this.raiz = null;
		this.noDir = this.noEsq = null;
		this.balanceamento = 0;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public No<T> getNoDir() {
		return noDir;
	}

	public void setNoDir(No<T> noDir) {
		this.noDir = noDir;
	}

	public No<T> getNoEsq() {
		return noEsq;
	}

	public void setNoEsq(No<T> noEsq) {
		this.noEsq = noEsq;
	}
	
	public void setBalanceamento(int balanceamento){
		this.balanceamento = balanceamento;
	}
	
	public int getBalanceamento(){
		return this.balanceamento;
	}
	
	public No<T> getRaiz(){
		return this.raiz;
	}
	
	public void setRaiz(No<T> raiz){
		this.raiz = raiz;
	}

	@Override
	public String toString() {
		return elemento.toString();
	}
}
