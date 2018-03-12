import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Albero_m_ario<T> {
	private static int m_ario;
	private int nNodi; // numero Nodi
	private int nNodiInf; // numero Nodi con informarzione
	private Nodo_m_ario<T> radice; // nodo radice
	private int h;	
	
	// Costruttore
	public Albero_m_ario(int m){
		m_ario=m;
		h=0;
		nNodi=0;
		nNodiInf=0;
	}
	
	// Restituire radice di un albero 
	public Nodo_m_ario<T> radice(){
		
		if (radice==null){
			System.out.println("radice non presente");
		    return null;
        }
		return radice;
	}
	
	// Inserisci radice in un albero vuoto
	public Nodo_m_ario<T> addRadice(T info){
		
		if(!isEmpty()){
			System.out.println("L'Albero non Ã¨ vuoto");
			return null;
		}else{
			radice=new Nodo_m_ario<T>(info);
			radice.setLivello(0);
			nNodiInf++;
			nNodi=nNodiInf+m_ario;
			return radice;
		}
	}
	

	
	// Inserire un nuovo nodo V come ï¬�glio i-esimo di un nodo U, gi`a presente nellâ€™albero. Il metodo riceve in ingresso il padre del nuovo nodo, lâ€™informazione di questâ€™ultimo e un intero i âˆˆ{1,2,...,m}per indicare se V sar`a il primo, il secondo, ..., lâ€™m-esimo ï¬�glio di U.
	public Nodo_m_ario<T> addFiglio(Nodo_m_ario<T> padre,T info,int numFiglio){
		Nodo_m_ario<T> nuovoNodo=new Nodo_m_ario<T>(info);
		if(numFiglio>=0 && numFiglio<=m_ario-1){
			if(padre.getFigli().get(numFiglio)==null){
				padre.getFigli().set(numFiglio, nuovoNodo);
				nuovoNodo.setPadre(padre);
				nuovoNodo.setLivello(padre.getLivello()+1);
				nNodiInf++;
				nNodi=nNodi+m_ario;
				if(nuovoNodo.getLivello()>h){
					h=nuovoNodo.getLivello();
				}
			}else{
				System.out.println("Posizione m-aria del nodo giÃ  occupata");
				return null;
			}
		}else{
			System.out.println("ArietÃ  inesistente");
			return null;
		}
		return nuovoNodo;
	}
	
	// Inserire un sottoalbero B in modo che la radice di B sia ï¬�glia i-esima di un nodo u dellâ€™albero.
	public void addAlbero(Albero_m_ario<T> B,Nodo_m_ario<T> padre, int numFiglio){
		if(numFiglio>=0 && numFiglio<=m_ario-1){
			if(padre.getFigli().get(numFiglio)==null){
				padre.getFigli().set(numFiglio, B.radice());
				B.radice().setPadre(padre);
				aggiornaLivelli(B.radice());
				nNodiInf=nNodiInf+B.getnNodiInf();
				nNodi=nNodi+B.getnNodi()-1;
				if(B.radice().getLivello()>h){
					h=h+B.getH()+1;
				}
				if(B.radice().getLivello()<h){
					if(B.getH()+B.radice().getLivello()>h){
						h=B.getH()+B.radice().getLivello();
					}
				}
				if(B.radice().getLivello()==h){
					h=h+B.getH();
				}
				B.radice=null;
			}else{
				System.out.print("Posizione m-aria del nodo giÃ  occupata");
			}
		}else{
			System.out.print("ArietÃ  inesistente");
		}
	}
	

	// Inserire una nuova radice in un albero non vuoto in modo che la vecchia radice sia ï¬�glia i-esima della nuova, dove i âˆˆ{1,2,...,m}. 
	public Nodo_m_ario<T> newRadice(Albero_m_ario<T> B,T info,int numFiglio){
		Nodo_m_ario<T> newRadice=new Nodo_m_ario<T>(info);
		if(B.radice()!=null){
			if(numFiglio>=0 && numFiglio<=m_ario-1){
				newRadice.getFigli().set(numFiglio, B.radice());
				B.radice().setPadre(newRadice);
				aggiornaLivelli(B.radice());
				radice=newRadice;
				nNodiInf++;
				nNodi=nNodi+m_ario;
				h++;
			}else{
				System.out.print("ArietÃ  inesistente");
				return null;
			}
		}else{
			System.out.print("Radice non presente quindi non sostituibile");
			return null;
		}
		return newRadice;
	}
	
	
	// Attraversare lâ€™albero in profondit`a e restituire la lista delle informazioni contenute nei nodi cos`Ä± incontrati (visita anticipata).  
	public LinkedList<T> visitaAnticipata(){
		LinkedList<T> anti = new LinkedList<T>();
		Stack<Nodo_m_ario<T>> pila=new Stack<Nodo_m_ario<T>>();
		if(nNodi!=0){
			pila.push(radice);
			while(!pila.isEmpty())	{
				Nodo_m_ario<T> temp=(Nodo_m_ario<T>) pila.pop();
				anti.add(temp.getInfo());
				for(int i=m_ario-1; i>=0;i--){
					if(temp.getFigli().get(i)!=null){
						pila.push(temp.getFigli().get(i));
					}
				}
			}
		}else{
			System.out.print("L'ALbero non contiene nodi");
		}
		System.out.println("VisitÃ  anticipata= " +anti);
		return anti;
	}
	
	// Attraversare lâ€™albero in ampiezza e restituire la lista delle informazioni contenute nei nodi cos`Ä± incontrati (visita per livelli). 
	public LinkedList<T> visitaLivelli(){
		LinkedList<T> livel = new LinkedList<T>();
		ArrayList<Nodo_m_ario<T>> coda = new ArrayList<Nodo_m_ario<T>>();
		if(nNodi!=0){
			coda.add(radice);
			while(!coda.isEmpty()){
				Nodo_m_ario<T> temp=(Nodo_m_ario<T>) coda.remove(0);
				livel.add(temp.getInfo());
				for(int i=0; i<=m_ario-1;i++){
					if(temp.getFigli().get(i)!=null){
						coda.add(temp.getFigli().get(i));
					}
				}
			}
		}else{
			System.out.print("L'ALbero non contiene nodi");
		}
		System.out.println("VisitÃ  per Livelli= " +livel);
		return livel;
	}
	
	// Aggiorna Livelli nodi
	public void aggiornaLivelli(Nodo_m_ario<T> x){
		ArrayList<Nodo_m_ario<T>> coda = new ArrayList<Nodo_m_ario<T>>();
		if(nNodi!=0){
			coda.add(x);
			while(!coda.isEmpty()){
				Nodo_m_ario<T> temp=(Nodo_m_ario<T>) coda.remove(0);
				temp.setLivello(temp.getPadre().getLivello()+1);
				for(int i=0; i<=m_ario-1;i++){
					if(temp.getFigli().get(i)!=null){
						coda.add(temp.getFigli().get(i));
					}
				}
			}
		}else{
			System.out.print("L'ALbero non contiene nodi");
		}
	}
	
	// Restituire la lista delle informazioni contenute nei nodi che si incontrano eï¬€ettuando una visita posticipata dellâ€™albero. 
	public LinkedList<T> visitaPosticipata() {
		LinkedList<T> post = new LinkedList<T>();
		visitaRicorsivaPost(radice, post);
		System.out.println("VisitÃ  posticipata= " +post);
		return post;
	}
	
	// Visita Posticitata Ricorsiva
	public void visitaRicorsivaPost(Nodo_m_ario<T> x, LinkedList<T> post) {
		if (x == null) {
			return;
		}
		for (int i = 0; i <= m_ario-1; i++) {
			visitaRicorsivaPost(x.getFigli().get(i), post);
		}
		post.add(x.getInfo());
	}
	
	
	// Effettuare la visita simmetrica dellâ€™albero e restituire la lista delle informazioni contenute nei nodi cos`Ä± incontrati, dove per visita simmetrica di un albero m-ario si intende la visita simmetrica dei sottoalberi dal primo allâ€™bm 2 c-esimo, seguita dalla visita della radice, seguita dalla visita simmetrica dei sottoalberi dal dm 2 e-esimo allâ€™m-esimo (per visita di un nodo si intende lâ€™accesso allâ€™informazione ivi contenuta, per visita di un sottoalbero si intende la visita di tutti i nodi del sottoalbero, una ed una sola volta). 
	public LinkedList<T> visitaSimmetrica() {
		LinkedList<T> sim = new LinkedList<T>();
		visitaRicorsivaSim(radice, sim);
		System.out.println("VisitÃ  simmetrica= " +sim);
		return sim;
	}
	
	// Visita Simmetrica Ricorsiva
	public void visitaRicorsivaSim(Nodo_m_ario<T> x, LinkedList<T> sim) {
		if (x == null) {
			return;
		}
		for (int i = 0; i < (m_ario-1)/2 ; i++) {
			visitaRicorsivaSim(x.getFigli().get(i), sim);
		}
		sim.add(x.getInfo());
		for(int i=(m_ario-1)/2; i<=m_ario-1;i++){
			visitaRicorsivaSim(x.getFigli().get(i), sim);
		}
	}
	
	// Restituire lâ€™altezza dellâ€™albero
	public int getH() {
		return h;
	}
	
	
	// Restituisce contatore numero Nodi con informazione
	public int getnNodiInf() {
		return nNodiInf;
	}
	
	// Stampa contatore numero Nodi con informazione
	public void printnNodiInf(){
		System.out.println("Numero nodi con Informazione = "+ nNodiInf);
	}
	
	// Restituisce contatore numero Nodi totali
	public int getnNodi() {
		return nNodi;
	}
	
	// Stampa contatore numero Nodi totali
	public void printnNodi(){
		System.out.println("Numero totale dei nodi = "+ nNodi);
	}
	
	// Stampa padre di un nodo
	public void printH(){
		System.out.println("L'altezza dell'ALbero Ã¨= "+getH());
	}
		
	// Controllo se albero Ã¨ vuoto, cioÃ¨ senza radice
	public boolean isEmpty() {
		return radice==null;
	}
	
	// Restituisce arietÃ  Albero
	public static int getarieta(){
		return m_ario;
	}
	
	// Stampa radice di un Albero
	public void printRadice(){
		System.out.println("La Radice dell'Albero Ã¨= "+radice.getInfo());
	}

}
