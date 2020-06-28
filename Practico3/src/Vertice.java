import java.util.LinkedList;

public class Vertice<T> {
	protected int verticeId;
	private LinkedList<Arco<T>> arcos;
	
	public Vertice(int verticeId) {					
		this.verticeId = verticeId;
		this.arcos = new LinkedList<Arco<T>>();
	}
	
	public int getVerticeId() {				//O(1)
		return verticeId;
	}

	public LinkedList<Arco<T>> getArcos() {		//O(1)
		return arcos;
	}
	
	public void agregarArco(int vertice, T etiqueta) {					//O(1)
		Arco<T> arco = new Arco<T>(this.verticeId, vertice, etiqueta);
		if(!arcos.contains(arco)) {
			this.arcos.add(arco);
		}
	}

	public void borrarArco(int idVertice) {					//O(A) siendo a la cantidad de Arcos, ya que recorro mi lista de arcos
		for(Arco<T> a: arcos) {
			if(a.getVerticeDestino()==idVertice) {
				arcos.remove(a);
			}
		}
	}
	
	public int cantidadArcos() {			//O(1)
		return this.arcos.size();
	}
	
	public boolean existeArco(int verticeId2) {			//O(A) siendo a la cantidad de Arcos, ya que recorro mi lista de arcos
		for(Arco<T> a: arcos) {
			if(a.getVerticeDestino() == verticeId2) {
				return true;
			}
		}
		return false;
	}

	protected Arco<T> obtenerArco(int verticeId2) {		//O(A) siendo a la cantidad de Arcos, ya que recorro mi lista de arcos
		for(Arco<T> a: arcos) {
			if(a.getVerticeDestino() == verticeId2) {
				return a;
			}
		}
		return null;
	}
}