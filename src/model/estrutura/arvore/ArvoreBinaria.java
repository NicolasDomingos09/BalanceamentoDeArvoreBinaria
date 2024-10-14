package model.estrutura.arvore;

public class ArvoreBinaria<T extends Comparable<T>> {
	/*
	 * Uso do Comparable
	 * compareTo() pode retornar 3 valores
	 * retorna -1 se o valor comparado é menor
	 * retorna 0 se o valor comparado é igual
	 * retorna 1 se o valor comparado é maior
	 */
	
	private No<T> raiz;
	
	public ArvoreBinaria() {
		this.raiz = null;
	}
	
	public void adicionar(T elemento) {
		No<T> novo = new No<>(elemento);
		
		if(this.raiz == null) {
			this.raiz = novo; //Adiciona nova raiz caso a arvore esteja vazia
			defineBalanceamento(this.raiz);
		}else {
			No<T> buffer = this.raiz;
			
			while(buffer != null) { //Busca onde o novo node será colocado e adiciona
				if(novo.getElemento().compareTo(buffer.getElemento()) < 0 ) { //Testa se o novo elemento é menor que o atual
					if(buffer.getNoEsq() != null) {
						buffer = buffer.getNoEsq();
					}else {
						buffer.setNoEsq(novo); //atualiza ponteiro do node atual (buffer) para o novo node à esquerda
						novo.setRaiz(buffer);
						defineBalanceamento(this.raiz);
						this.raiz = balancear(this.raiz);
						break;
					}
				}else {
					if(buffer.getNoDir() != null) {
						buffer = buffer.getNoDir();
					}else {
						buffer.setNoDir(novo); //atualiza ponteiro do node atual (buffer) para o novo node à direita
						novo.setRaiz(buffer);
						defineBalanceamento(this.raiz);
						this.raiz = balancear(this.raiz);
						break;
					}
				}
			}
		}
	}
	
	public int altura(No<T> atual){
		if(atual == null){
			return -1;
		}
		if (atual.getNoDir() == null && atual.getNoEsq() == null){
			return 0;
		} else if(atual.getNoEsq() == null){
			return 1 + altura(atual.getNoDir());
		} else if(atual.getNoDir ()== null){
			return 1 + altura(atual.getNoEsq());
		} else {
			if(altura(atual.getNoEsq()) > altura(atual.getNoDir())){
				return 1 + altura(atual.getNoEsq());
			}else {
				return 1 + altura(atual.getNoDir());
			}
		}
	}
	
	public void defineBalanceamento(No<T> atual){
		atual.setBalanceamento(altura(atual.getNoEsq()) - altura(atual.getNoDir()));
		
		if(atual.getNoDir() != null){
			defineBalanceamento(atual.getNoDir());
		}
		
		if(atual.getNoEsq() != null){
			defineBalanceamento(atual.getNoEsq());
		}
	}
	
	public No<T> rotacaoEsquerda(No<T> atual){
		No<T> aux = atual.getNoDir();
		aux.setRaiz(atual.getRaiz());
		
		if(aux.getNoEsq() != null)
			aux.getNoEsq().setRaiz(atual);
		
		atual.setRaiz(aux);
		atual.setNoDir(aux.getNoEsq());
		aux.setNoEsq(atual);
		
		if(aux.getRaiz() != null){
			if(aux.getRaiz().getNoDir() == atual){
				aux.getRaiz().setNoDir(atual);
			} else if(aux.getRaiz().getNoEsq() == atual){
				aux.getRaiz().setNoEsq(aux);
			}
		}
		defineBalanceamento(aux);
		return aux;
	}
	
	public No<T> rotacaoDireita(No<T> atual){
		No<T> aux = atual.getNoEsq();
		aux.setRaiz(atual.getRaiz());
		
		if(aux.getNoDir() != null){
			aux.getNoDir().setRaiz(atual);
		}
		
		atual.setRaiz(aux);
		atual.setNoEsq(aux.getNoDir());
		aux.setNoDir(atual);
		
		if(aux.getRaiz() != null){
			if(aux.getRaiz().getNoDir() == null){
				aux.getRaiz().setNoDir(aux);
			} else if(aux.getRaiz().getNoEsq() == atual){
				aux.getRaiz().setNoEsq(aux);
			}
		}
		defineBalanceamento(aux);
		return aux;
	}
	
	public No<T> rotacaoDuplaDireita(No<T> atual){
		No<T> aux = atual.getNoEsq();
		atual.setNoEsq(rotacaoEsquerda(aux));
		No<T> aux2 = rotacaoDireita(atual);
		return aux2;
	}
	
	public No<T> rotacaoDuplaEsquerda(No<T> atual){
		No<T> aux = atual.getNoDir();
		atual.setNoDir(rotacaoDireita(aux));
		No<T> aux2 = rotacaoEsquerda(atual);
		return aux2;
	}
	
	public void balancear(){
		this.raiz = balancear(this.raiz);
	}
	public No<T> balancear(No<T> atual){
		if(atual.getBalanceamento() == 2 && atual.getNoEsq().getBalanceamento() >= 0){
			atual = rotacaoDireita(atual);
		} else if(atual.getBalanceamento() == -2 && atual.getNoDir().getBalanceamento() <= 0){
			atual = rotacaoDuplaEsquerda(atual);
		} else if(atual.getBalanceamento() == 2 && atual.getNoEsq().getBalanceamento() < 0) {
			atual = rotacaoDuplaDireita(atual);
		} else if(atual.getBalanceamento() == -2 && atual.getNoDir().getBalanceamento() > 0){
			atual = rotacaoDuplaEsquerda(atual);
		}
		
		if(atual.getNoDir() != null){
			balancear(atual.getNoDir());
		}
		
		if(atual.getNoEsq() != null){
			balancear(atual.getNoEsq());
		}
		
		return atual;
	}
	
	public void exibirEmOrdem() { 
		//A exibição em ordem mostra a arvore completa em ordem crescente
		ordem(this.raiz);
		System.out.println();
	}
	
	private void ordem(No<T> atual) {
		if(atual != null) { //Se o atual não é null, método continua
			ordem(atual.getNoEsq()); //Chamada recursiva do método para buscar o elemento da esquerda
			System.out.print(atual.getElemento() + " "); //Print elemento
			ordem(atual.getNoDir()); //Chamada recursiva do método para buscar o elemento da direita
		}
	}
	
	public void exibirPreOrdem() {
		/*
		 * A exibição em pre-ordem mostra a arvore organizada por subarvores
		 * Printa primeiro o pai, depois o menor e por fim o maior
		 */
		preOrdem(this.raiz);
		System.out.println();
	}
	
	private void preOrdem(No<T> atual) {
		if(atual != null) { //Se o atual não é null, método continua
			System.out.print(atual.getElemento() + " "); //Print elemento pré-busca
			preOrdem(atual.getNoEsq()); //Chamada recursiva do método para buscar o elemento da esquerda
			preOrdem(atual.getNoDir()); //Chamada recursiva do método para buscar o elemento da direita
		}
	}
	
	public void exibirPosOrdem() {
		/*
		 * A exibição em pos-ordem mostra a arvore do nível mais profundo até o topo
		 * Printa primeiro o filho menor, depois o maior e por fim o pai
		 */
		posOrdem(this.raiz);
		System.out.println();
	}
	
	private void posOrdem(No<T> atual) {
		if(atual != null) { //Se o atual não é null, método continua
			posOrdem(atual.getNoEsq()); //Chamada recursiva do método para buscar o elemento da esquerda
			posOrdem(atual.getNoDir()); //Chamada recursiva do método para buscar o elemento da direita
			System.out.print(atual.getElemento() + " "); //Print elemento pós-busca
		}
	}
	
	public void remover(T elemento) throws Exception {
		if(this.raiz == null)
			throw new Exception("A árvore está vazia");

		No<T> atual = this.raiz; //Buffer de atual começando em raiz
		No<T> pai = null; //Pai do atual. Começa em null pois a raiz não tem pai
		
		//Enquanto não achar o elemento e não terminar a árvore segue while
		while(atual != null && atual.getElemento() != elemento) { 
			pai = atual; //pai recebe o elemento atual, já que o atual receberá um novo node
			if(elemento.compareTo(atual.getElemento()) < 0) { //Se o compareTo retornar um número negativo, o elemento é menor do que o node
				atual = atual.getNoEsq(); //atual recebe esquerda pois elemento é menor
			}else {
				atual = atual.getNoDir(); //atual recebe direita pois elemento é maior
			}
		}
		
		if(atual == null) { //Se atual for null, a árvore foi percorrida e o elemento não existe
			throw new Exception("Elemento não encontrado");
		}else {
			removeNode(pai, atual); //se não, chama remoção de node
		}
	}
	
	private void removeNode(No<T> pai, No<T> atual) {
		/*
		 * Três condições de remoção
		 * 1 - Nó com dois filhos
		 * 2 - Nó com um filho
		 * 3 - Nó folha
		 */
		
		if(atual.getNoDir() != null && atual.getNoEsq() != null) { //Node possui dois filhos
			/*
			 * Para fazer a substituição de um nó que possui dois filhos
			 * uma das abordagens é usar o menor valor disponível à esquerda da subárvore à direita do node que será removido
			 */
			No<T> substituto = atual.getNoDir();
			No<T> paiDoSubsTituto = atual;
			while(substituto.getNoEsq() != null) {
				paiDoSubsTituto = substituto;
				substituto = substituto.getNoEsq(); //Busco o menor valor disponível para a substituição
			}
			
			//Redefinição de ponteiros. Referência para os filhos do antigo node
			substituto.setNoEsq(atual.getNoEsq());
			
			if(pai == null) { //Se não possuir pai, node é raiz da árvore
				substituto.setNoDir(paiDoSubsTituto);
				this.raiz = substituto;
			}else {//Não é raiz
				if(substituto.getElemento().compareTo(pai.getElemento()) < 0) { //Substituto é menor que pai
					pai.setNoEsq(substituto);
				}else { //É maior que pai
					pai.setNoDir(substituto);
				}
			}
			
			//Tratando o pai do substituto
			if(substituto.getElemento().compareTo(paiDoSubsTituto.getElemento()) < 0) { //substituto é menor que o pai dele
				paiDoSubsTituto.setNoEsq(null); //Removendo a referência do substituto
				substituto.setNoDir(paiDoSubsTituto); //Atribuindo o pai do substituto como direita do substituto
			}else {
				paiDoSubsTituto.setNoDir(null); //Não é menor, significa que já está referenciado pelo substituto, então basta sumir com a referência ao substituto
			}
		}else if(atual.getNoDir() == null && atual.getNoEsq() == null) { 
			/*
			 * Não possui filhos, é um nó folha
			 * Para remover um nó folha basta remover a referência do pai ao filho
			 */
			
			if(pai == null) { //É raiz
				this.raiz = null;
			}else { //Não é raiz
				if(atual.getElemento().compareTo(pai.getElemento()) < 0) { //Node a ser removido é menor que pai
					pai.setNoEsq(null);
				}else { //é maior que o pai
					pai.setNoDir(null);
				}
			}
		}else { 
			/*
			 * Possui apenas um filho
			 * Para remover um node que possui apenas um filho, deve-se mover o filho para a posição do atual.
			 */
			No<T> filho = null;
			if(pai != null) { //Não é raiz
				if (atual.getNoDir() != null) { // Possui filho à direita
					filho = atual.getNoDir();
					if(filho.getElemento().compareTo(pai.getElemento()) < 0) {
						pai.setNoEsq(filho);
					}else {
						pai.setNoDir(filho);
					}
				} else { // Possui filho à esquerda
					filho = atual.getNoEsq();
					if(filho.getElemento().compareTo(pai.getElemento()) < 0) {
						pai.setNoEsq(filho);
					}else {
						pai.setNoDir(filho);
					}
				}
			}else {//É raiz
				if (atual.getNoDir() != null) { // Possui filho à direita
					filho = atual.getNoDir();
					this.raiz = filho;
				} else { // Possui filho à esquerda
					filho = atual.getNoEsq();
					this.raiz = filho;
				}
			}
		}
	}

	/*
	 * Impresão em preOrdem feita por stringbuilder e usando recursividade
	 */
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringHelper(raiz, sb);
        return sb.toString();
    }

    private void toStringHelper(No<T> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.getElemento()).append(" ");
            toStringHelper(node.getNoEsq(), sb);
            toStringHelper(node.getNoDir(), sb);
        }
    }
}
