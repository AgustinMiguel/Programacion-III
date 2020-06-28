

import java.util.ArrayList;


public class main {
	static int bonusMax = Integer.MAX_VALUE;
	static DiaDeVisita dias[] = new DiaDeVisita[11];
	static DiaDeVisita bettersDias[] = new DiaDeVisita[11];
	static int contador = 0; 
	
	public static void calculateBonus(ArrayList<Familia> familias) {
		int bonus = 0;
		int pointer = 0;
		
		if (familias != null) {
			backTraking(familias, pointer, bonus);
		}
	}	
	
	
	private static void backTraking(ArrayList<Familia> familias, int pointer, int bonus) {
		int toAdd = 0;
		contador++;
		if (pointer == familias.size()) {
			if (isSolucion(bonus)) {
				bonusMax = bonus;
				bettersDias = dias.clone();
			}
		} else {
			Familia familia = familias.get(pointer);
			for (int iteracion = 0; iteracion < 3; iteracion++) {
				int dia = familia.preferenciaEn(iteracion);
				if (dias[dia].addFamilias(familia)) {
					if (dia != familia.diaPreferido()) {
						toAdd = bonus(familia, iteracion);
						bonus += toAdd;
					}
					if (!bonoMayor(bonus))
						backTraking(familias, pointer + 1, bonus);
					dias[dia].deleteFamilia(familia);
					bonus -= toAdd;
					toAdd = 0;
				}
			}
		}
	}

	private static boolean bonoMayor(int bonus) {
		return bonus>bonusMax;
	}
	private static boolean isSolucion(int bonus) {
		return (bonus < bonusMax);
	}


	private static int bonus(Familia familia, int dia) { 
		int total = 25 + (10 * familia.miembros()) + (5 * dia);
		return total;
	}
	
	private static void loadArray() {
		for (int i = 0; i < dias.length; i++) {
			DiaDeVisita dia = new DiaDeVisita();
			dias[i] = dia;
			bettersDias[i] = dia;
		}
	}
	
	public static void main(String... args) {
		System.out.println("--------------------------------dataset1---------------------------------------");
		CSVReader reader = new CSVReader("./data/familias-1.csv");
		ArrayList<Familia> familias = reader.read();
		loadArray();
		calculateBonus(familias);
		System.out.println(bonusMax);
		System.out.println(contador+" estados " );
		contador =0;
		System.out.println("--------------------------------dataset2---------------------------------------");
		CSVReader reader2 = new CSVReader("./data/familias-2.csv");
		ArrayList<Familia> familias2 = reader2.read();
		loadArray();
		calculateBonus(familias2);
		System.out.println(bonusMax);	
		System.out.println(contador+" estados " );
	}
}
