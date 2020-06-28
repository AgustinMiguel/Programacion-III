import java.util.ArrayList;
import java.util.Iterator;
public class DiaVisita {
	private int cantidadPersonas;
	private ArrayList<Familia> familias;
	public DiaVisita() {
		this.cantidadPersonas = 0;
		this.familias = new ArrayList<Familia>();
	}
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	public boolean addFamilias(Familia familia) {
		if(this.cantidadPersonas + familia.miembros()<=340) {
			this.familias.add(familia);
			this.cantidadPersonas+= familia.miembros();
			return true;
		}
		return false;
	} 
	public boolean isFull() {
		if(this.cantidadPersonas <340) {
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
		return this.familias.remove(familia);
	}
	
}
