package practica_03;

// TODO: Auto-generated Javadoc
/**
 * The Class Element.
 */
public class Element {
	
	/** The name. */
	private String name;
    
    /** The weight. */
    private double weight;
    
    /** The ganancia. */
    private double ganancia;
    
    /** The amount. */
    private double amount; 
    
    /**
     * Instantiates a new element.
     */
    public Element() {
    	
    }
    
    /**
     * Instantiates a new element.
     *
     * @param name the name
     * @param weight the weight
     * @param ganancia the ganancia
     */
    public Element(String name, double weight, double ganancia) {
        this.name = name;
        this.weight = weight;
        this.ganancia = ganancia;
        this.amount = 1;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the ganancia.
     *
     * @return the ganancia
     */
    public double getGanancia() {
        return ganancia;
    }

    /**
     * Sets the ganancia.
     *
     * @param ganancia the new ganancia
     */
    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }
    
    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return name + " -> Weight = " + (weight*amount) + " -> ganancia = " + (ganancia*amount);
    }

}
