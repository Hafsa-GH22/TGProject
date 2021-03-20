package algorithmes;

import java.util.Comparator;

import elements.Sommet;

public class DegreeCompare implements Comparator<Sommet> {

	@Override
	public int compare(Sommet s1, Sommet s2) {
		int rt = 0;
		if(s1.getDegree() <= s2.getDegree())
			rt = -1;
		if(s1.getDegree() >= s2.getDegree())
			rt = 1;
		return rt;
	}

}
