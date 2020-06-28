
import java.util.ArrayList;
import java.util.Iterator;
public class Main {
	static CSVReader reader = new CSVReader("./data/familias.csv");
	static int bono = 0;
	static DiaVisita dias[] = new DiaVisita[101];
	static ArrayList<Familia> familias = reader.read();
	
	
	private static int calcularBono(Familia familia, int dia) { 
		int total = 25 + (10 * familia.miembros()) + (5 * dia + 1);
		return total;
	}

	private static void loadArray() {
		for (int i = 0; i < dias.length; i++)
			dias[i] = new DiaVisita();
	}

	private static void insert(Familia f) { // INSERTA LA FAMILIA EN EL MEJOR LUGAR POSIBLE
		int iteracion = 0;
		boolean loAgrego = false;
		while ((loAgrego == false) && (iteracion < 8)) {
			int dia = f.preferenciaEn(iteracion);
			if (dias[dia].addFamilias(f)) {
				loAgrego = true;
				if (dia != f.diaPreferido()) {
					bono += calcularBono(f, iteracion);
				}
			} else {
				iteracion++;
			}
			if (iteracion == 8) {
				System.out.println("NO ENTRO");
			}
		}
	}
	
	private static boolean insertInFavorite(Familia f) {
		int dia= f.diaPreferido();
		if(dias[dia].addFamilias(f)) {
			familias.remove(f);
			return true;
		}
		return false;
	}
	
	public static void insertInFavoriteDay() {
		if (familias != null) {
			for (int i =0; i< familias.size(); i++ ) {
				Familia f = familias.get(i);
				if(insertInFavorite(f)) {
					i--;
				}
			}
		}
	}

	public static void bestVistDays() {
		if (familias != null) {
			for (Familia f : familias) {
				insert(f);
			}
		}
	}
	
	public static void Improvement() {
		ArrayList<Familia> toInsert = new ArrayList<Familia>();
		int iteracion = 0;
		int dia = 0;
		int diaFavorito = 0;
		int bonoFamilia = 0;
		for (Familia familia : familias) {
			diaFavorito = familia.diaPreferido();
			if (!dias[diaFavorito].attend(familia)) {
				iteracion = 1; // SALTEO EL DIA FAVORITO
				while (iteracion < 8) {
					dia = familia.preferenciaEn(iteracion);
					if (dias[dia].attend(familia)) {
						bonoFamilia = calcularBono(familia, iteracion);
						iteracion = 8;
					}
					iteracion++;
				}
				toInsert = makeRoom(dias[diaFavorito].getCantidadPersonas(), familia.miembros(), diaFavorito);
				if (toInsert.size()!=0) {
					dias[dia].deleteFamilia(familia);
					dias[diaFavorito].addFamilias(familia);
					bono = bono - bonoFamilia;
					for (Familia f : toInsert) {
						insert(f);
					}
				}
			}
		}
	}

	private static ArrayList<Familia> makeRoom(int cantidadPersonas, int miembros, int dia) {
		ArrayList<Familia> familias = new ArrayList<Familia>();
		ArrayList<Familia> familiasToDelete = new ArrayList<Familia>();
		Iterator<Familia> it = dias[dia].obetenerFamilias();
		Familia familia;
		int espacioLibre = 340 - cantidadPersonas;
		int spaceRoom = miembros - espacioLibre;
		if ((spaceRoom > 0) && (spaceRoom < miembros)) {
			int bonoARestar = 0;
			while (it.hasNext()) {
				familia = it.next();
				if ((familia.diaPreferido() != dia) && (spaceRoom < miembros)
						&& (familia.indiceDePreferencia(7) != dia)) {
					int indice = familia.indiceDePreferencia(dia);
					bonoARestar = calcularBono(familia, indice);
					bono = bono - bonoARestar;
					spaceRoom = spaceRoom + familia.miembros();
					familias.add(familia);
					familiasToDelete.add(familia);
				}
			}
			for (Familia f : familiasToDelete) {
				dias[dia].deleteFamilia(f);
			}
		}
		if (spaceRoom >= miembros) {
			return familias;
		} else {
			for (int i = 0; i < familias.size(); i++) {
				dias[dia].addFamilias(familias.get(i));
				familias.remove(i);
			}
		}
		return familias;
	}

	public static void main(String... args) {
		loadArray();
		ComparadorFamilias c = new ComparadorFamiliaMenor();
		familias.sort(c);
		insertInFavoriteDay();
		bestVistDays();
		System.out.println(bono);
		Improvement();
		System.out.println("MEJORA LOCAL");
		System.out.println(bono);                   
	}
}