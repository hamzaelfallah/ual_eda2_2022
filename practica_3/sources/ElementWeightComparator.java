package practica_03;

import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class ElementWeightComparator.
 */
public class ElementWeightComparator implements Comparator<Element>{

	/**
	 * Compare.
	 *
	 * @param element1 the element 1
	 * @param element2 the element 2
	 * @return the int
	 */
	@Override
	public int compare(Element element1, Element element2) {
		return Double.compare(element1.getWeight(), element2.getWeight());
	}

}
