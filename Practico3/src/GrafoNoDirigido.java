
public class GrafoNoDirigido<T> extends GrafoDirigido<T> {

	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {				//O(V) siendo V la cantidad de vertices ya que recorre toda mi lista de vertices
		super.agregarArco(verticeId1, verticeId2, etiqueta);
		super.agregarArco(verticeId2, verticeId1, etiqueta);
	}
	
	public void borrarArco(int verticeId1, int verticeId2) {							//O(V) + O(A) siendo v la cantidad de vertices ya que recorre mi lista de vertices
		super.borrarArco(verticeId1, verticeId2);										//			  siendo a la cantidad de arcos ya que recorre mi lista de arcos
		super.borrarArco(verticeId2, verticeId1);
	}

}