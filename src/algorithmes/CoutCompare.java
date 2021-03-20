package algorithmes;

import java.util.Comparator;
import elements.Arete;

public class CoutCompare implements Comparator<Arete> {

	@Override
	public int compare(Arete a1, Arete a2) {
		int rt = 0;
		if(a1.getCout() <= a2.getCout())
			rt = -1;
		if(a1.getCout() >= a2.getCout())
			rt = 1;
		return rt;
	}

}
