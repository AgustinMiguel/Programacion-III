
public class ComparadorFamiliaMayor extends ComparadorFamilias {
	public int compare(Familia familia1, Familia familia2) {
		if (familia1.miembros()>familia2.miembros()) {
			return -1;
		}
		else if (familia1.miembros() == familia2.miembros()) {
			return 0;
		}else {
			return 1;
		}
	}
}
