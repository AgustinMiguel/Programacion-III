
public class Arco<T> {
	
	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public Arco() {
		this.verticeDestino = 0;
		this.verticeOrigen = 0;
		this.etiqueta = null;
	}
	public String toString() {					//O(1)
		return this.verticeOrigen+ "->" +this.verticeDestino;
	}

	public int getVerticeOrigen() {					//O(1)
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {			//O(1)
		return verticeDestino;
	}
	
	public void setEtiqueta(T etiqueta) {				//O(1)
		this.etiqueta = etiqueta;
	}
	


	public T getEtiqueta() {				//O(1)
		return etiqueta;
	}

	public boolean equals(Arco<T> arco) {
		if((this.verticeDestino == arco.verticeDestino)&&(this.verticeOrigen == arco.verticeOrigen)){
			return true;
		}else {
			return false;
		}
	}
	
	
}
