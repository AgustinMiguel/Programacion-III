import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class main {
	static int tiempoMaximo;
	static LinkedList<Integer> maxSequencia = new LinkedList<Integer>();
	
	public static void getSequencia(GrafoDirigido grafo, HashMap<Integer, Tarea> map, int idOrigen) {     //O(V) + O(A) siendo v los vertices y a todos los arcos
		if (map.containsKey(idOrigen)) {
			int vertice = 0;
			int tiempoTotal = 0;
			LinkedList<Integer> sequencia = new LinkedList<Integer>();
			sequencia.add(idOrigen);
			Iterator<Arco> arcos = grafo.obtenerArcos(idOrigen);
			if (arcos.hasNext()) {
				while (arcos.hasNext()) {
					Arco arco = arcos.next();
					vertice = arco.getVerticeDestino();
					sequencia.add(vertice);
					Tarea tarea = map.get(vertice);
					tiempoTotal +=tarea.getDuracion();
					getSequencia(grafo, vertice, map, sequencia, tiempoTotal);
					sequencia.remove(sequencia.size()-1);
					tiempoTotal -= tarea.getDuracion();
				}
			}
		}
	}

	private static void getSequencia(GrafoDirigido grafo, int vertice, HashMap<Integer, Tarea> map, LinkedList<Integer> sequencia, int tiempoTotal) {	//O(A) siendo a todos los arcos
		Iterator<Arco> arcos = grafo.obtenerArcos(vertice);
		if (arcos.hasNext()) {
			while (arcos.hasNext()) {
				Arco arco = arcos.next();
				vertice = arco.getVerticeDestino();
				sequencia.add(vertice);
				Tarea tarea = map.get(vertice);
				tiempoTotal += tarea.getDuracion()+(Integer) arco.getEtiqueta();
				getSequencia(grafo, vertice, map, sequencia, tiempoTotal);
				sequencia.remove(sequencia.size()-1); 
				tiempoTotal -= tarea.getDuracion()+(Integer) arco.getEtiqueta();
			}
		}else {
	         if(tiempoTotal>tiempoMaximo) {
	        	 maxSequencia.clear();
	        	 maxSequencia.addAll(sequencia);
	        	 tiempoMaximo = tiempoTotal;
	         }
		}
	}

	public static <T> void main(String[] args) {
		GrafoDirigido<Object> grafito = new GrafoDirigido<>();
		HashMap<Integer, Tarea> map = new HashMap<Integer, Tarea>();
		int idOrigen = 0;
        Tarea tarea0 = new Tarea(0,"a");
        Tarea tarea1 = new Tarea(4,"a");
        Tarea tarea2 = new Tarea(18,"a");
        Tarea tarea3 = new Tarea(4,"a");
        Tarea tarea4 = new Tarea(13,"a");
        Tarea tarea5 = new Tarea(22,"a");
        Tarea tarea6 = new Tarea(18,"a");
        Tarea tarea7 = new Tarea(12,"a");
        Tarea tarea8 = new Tarea(3,"a");
        Tarea tarea9 = new Tarea(2,"a");
        Tarea tarea10 = new Tarea(3,"a");
        Tarea tarea11 = new Tarea(1,"a");
        Tarea tarea12 = new Tarea(5,"a");
        map.put(0, tarea0);
        map.put(1, tarea1);
        map.put(2, tarea2);
        map.put(3, tarea3);
        map.put(4, tarea4);
        map.put(5, tarea5);
        map.put(6, tarea6);
        map.put(7, tarea7);
        map.put(8, tarea8);
        map.put(9, tarea9);
        map.put(10, tarea10);
        map.put(11, tarea11);
        map.put(12, tarea12);
        grafito.agregarVertice(0);
        grafito.agregarVertice(1);
        grafito.agregarVertice(2);
        grafito.agregarVertice(3);
        grafito.agregarVertice(4);
        grafito.agregarVertice(5);
        grafito.agregarVertice(6);
        grafito.agregarVertice(7);
        grafito.agregarVertice(8);
        grafito.agregarVertice(9);
        grafito.agregarVertice(10);
        grafito.agregarVertice(11);
        grafito.agregarVertice(12);
        grafito.agregarArco(0,1,0);
        grafito.agregarArco(0,2,0);
        grafito.agregarArco(1,3,3);
        grafito.agregarArco(2,5,1);
        grafito.agregarArco(2,7,18);
        grafito.agregarArco(3,4,5);
        grafito.agregarArco(4,11,3);
        grafito.agregarArco(11,12,9);
        grafito.agregarArco(3,6,8);
        grafito.agregarArco(6,12,2);
        grafito.agregarArco(6,10,6);
        grafito.agregarArco(5,6,2);
        grafito.agregarArco(7,8,7);
        grafito.agregarArco(8,9,4);
        grafito.agregarArco(9,10,1);
		System.out.println("SEQUENCIA");
		getSequencia(grafito, map, idOrigen);
		for(Integer i: maxSequencia) {
			System.out.println(i);
		}
		System.out.println("Tarda " + tiempoMaximo + "hs");
	}
}