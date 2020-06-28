import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> {
	private LinkedList<Vertice<T>> vertices;

	public GrafoDirigido() {
		this.vertices = new LinkedList<Vertice<T>>();
	}

	public void agregarVertice(int idVertice) {				//O(V) siendo V la cantidad de vertices ya que recorre toda mi lista de vertices
		Vertice<T> vertice = new Vertice<T>(idVertice);
		if (!this.vertices.contains(vertice)) {
			vertices.add(vertice);
		}
	}

	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {				//O(V) siendo V la cantidad de vertices ya que recorre toda mi lista de vertices
		Vertice<T> vertice1 = null;
		Vertice<T> vertice2 = null;
		for (Vertice<T> v : vertices) {
			if (v.verticeId == verticeId1) {
				vertice1 = v;
			}
			if (v.verticeId == verticeId2) {
				vertice2 = v;
			}
		}
		if((vertice1 != null)&&(vertice2 != null)){
			vertice1.agregarArco(verticeId2, etiqueta);
		}
	}

	public void borrarVertice(int idVertice) {					//O(V) + O(A) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		Vertice<T> verticeO = null;								//			  siendo a la cantidad de arcos ya que recorre mi lista de arcos
		for (Vertice<T> v : vertices) {
			v.borrarArco(idVertice);
			if (v.verticeId == idVertice) {
				verticeO = v;
			}
		}
		vertices.remove(verticeO);
	}

	public void borrarArco(int verticeId1, int verticeId2) {       //O(V) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		Vertice<T> verticeO = null;
		for (Vertice<T> v : vertices) {
			if (v.verticeId == verticeId1) {
				verticeO = v;
			}
		}
		verticeO.borrarArco(verticeId2);
	}

	public boolean contieneVertice(int verticeId) {				 //O(V) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		for (Vertice<T> v : vertices) {
			if (v.verticeId == verticeId) {
				return true;
			}
		}
		return false;
	}

	public boolean existeArco(int verticeId1, int verticeId2) {		 //O(V) + O(A) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		Vertice<T> arco = null;										//			  siendo a la cantidad de arcos ya que recorre mi lista de arcos
		for (Vertice<T> v : vertices) {
			if (v.verticeId == verticeId1) {
				arco = v;
			}
		}
		if(arco!=null)
			return arco.existeArco(verticeId2);
		else 
			return false;
	}

	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {	 //O(V) + O(A) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		Arco<T> arco = new Arco<T>();								//			  siendo a la cantidad de arcos ya que recorre mi lista de arcos
		for (Vertice<T> v : vertices) {
			if (v.verticeId == verticeId1) {
				return v.obtenerArco(verticeId2);
			}
		}
		return null;
	}

	public int cantidadVertices() {										//O(1)
		return this.vertices.size();
	}
	
	public int cantidadArcos() {							//O(V) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		int cantidad = 0;
		for(Vertice v: vertices) {
			cantidad += v.cantidadArcos();
		}
		return cantidad;
	}

	public Iterator<Integer> obtenerVertices() {								//O(1)
		IteratorVertice it = new IteratorVertice(this.vertices.iterator());
		return it;
	}

	public Iterator<Integer> obtenerAdyacentes(int verticeId) {					//O(1)
		Iterator<Arco<T>> it = obtenerArcos(verticeId);
		Iterator<Integer> ite = new IteratorAdyacentes(it);
		return ite;
	}

	public Iterator<Arco<T>> obtenerArcos() {							//O(V) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		LinkedList<Arco<T>> arcos = new LinkedList<Arco<T>>();
		for (Vertice<T> v : vertices) {
			arcos.addAll(v.getArcos());
		}
		return arcos.iterator();
	}

	public Iterator<Arco<T>> obtenerArcos(int verticeId) {			//O(V) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		LinkedList<Arco<T>> arcos = new LinkedList<Arco<T>>();
		for(Vertice<T> v : vertices) {
			if(v.verticeId == verticeId) {
				arcos.addAll(v.getArcos());
			}
		}
		return arcos.iterator();
	}
}
