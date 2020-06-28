import java.util.ArrayList;
import java.util.Iterator;
public class DiaDeVisita {
	private int cantidadPersonas;
	private ArrayList<Familia> familias;
	public DiaDeVisita() {
		this.cantidadPersonas = 0;
		this.familias = new ArrayList<Familia>();
	}
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	public boolean addFamilias(Familia familia) {
		if(this.cantidadPersonas + familia.miembros()<=30) {
			this.familias.add(familia);
			this.cantidadPersonas+= familia.miembros();
			return true;
		}
		return false;
	} 
	
	public String toString() {
		return this.getCantidadPersonas()+ "";
	}
	public boolean isFull() {
		if(this.cantidadPersonas <30) {
			return false;
		}
		return true;
	}
	
	public boolean attend(Familia familia) {
		return familias.contains(familia);
	}
	
	public Iterator<Familia> obetenerFamilias(){
		return familias.iterator();
	}
	public boolean deleteFamilia(Familia familia) {
		if(familias.contains(familia)) {
			this.cantidadPersonas -= familia.miembros();
		}
		return this.familias.remove(familia);
	}
	
}
