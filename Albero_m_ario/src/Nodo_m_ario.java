import java.util.ArrayList;
import java.util.LinkedList;

@SuppressWarnings("static-access")
public class Nodo_m_ario<T> {
	
	private T info;
	private Nodo_m_ario<T> padre;
	private ArrayList<Nodo_m_ario<T>> figli;
	private int livello; 
	private  Albero_m_ario<T> A;
	private int m=A.getarieta();
	
	// Costruttore 
	public Nodo_m_ario(T x){
		info=x;
		padre=null;
		figli=new ArrayList<Nodo_m_ario<T>>();
		for(int i=0;i<=m-1;i++){
			figli.add(null);
		}
	}
	
	// Cambiare il contenuto di un nodo. 
	public void changeInfo(T info){
		this.info=info;
	}
	
	// Restituire la lista delle informazioni dei ï¬�gli di un nodo. 
	public LinkedList<T> infoFigli(){
		
		LinkedList<T> inf=new LinkedList<T>();
		for(int i=0; i<=m-1;i++){
			if(figli.get(i)!=null){
				inf.add(figli.get(i).getInfo());
			}
		}
		System.out.println("Le informazioni dei figli del nodo sono= "+inf);
		return inf;
	}
	
	// Restituire il numero di ï¬�gli non vuoti di un nodo. 
		public int nFigli(){
			int nNodiFigli=0;
			for(int i=0;i<=m-1;i++){
				if(figli.get(i)!=null){
					nNodiFigli++;
				}
			}
			System.out.println("Il numero di figli non vuoti del nodo Ã¨= "+nNodiFigli);
			return nNodiFigli;
		}
	
	// Restituisce l'Informazione di un nodo
	public T getInfo() {
		return info;
	}
	
	// Mutatore informazione di un nodo
	public void setInfo(T info) {
		this.info = info;
	}
	
	// Restituisce il padre di un nodo
	public Nodo_m_ario<T> getPadre() {
		return padre;
	}
	
	// Mutatore padre di un nodo
	public void setPadre(Nodo_m_ario<T> padre) {
		this.padre = padre;
	}
	
	// Restituisce l'Array dei figli di un nodo
	public ArrayList<Nodo_m_ario<T>> getFigli() {
		return figli;
	}
	
	// Mutatore figli di un nodo 
	public void setFigli(ArrayList<Nodo_m_ario<T>> figli) {
		this.figli = figli;
	}

	// Restituisce livello di un nodo
	public int getLivello() {
		return livello;
	}

	// Mutatore livello di un nodo 
	public void setLivello(int livello) {
		this.livello = livello;
	}	
	
	// Stampa padre di un nodo
	public void printPadre(){
		if(getPadre()!=null){
			System.out.println("Il padre del nodo Ã¨= "+padre.getInfo());
		}else{
			System.out.println("Il padre del nodo Ã¨ inesistente, perchÃ¨ Ã¨ una radice.");
		}
	}
	
	// Stampa informazione di un nodo
	public void printInfo(){
		System.out.println("L'Informazione del nodo Ã¨= "+getInfo());
	}
		
	// Stampa livello di un nodo
	public void printLivello(){
		System.out.println("Il livello del nodo Ã¨= "+getLivello());
	}
}
