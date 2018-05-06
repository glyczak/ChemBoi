package ga.gaba.ChemBoi;

public class Compound {
    private String formula;
    private double molarMass;

    public Compound(String formula) {
        this.formula = formula;
        this.molarMass = MolarMassCalc.getInstance().calculate(formula);
    }

    @Override
    public String toString() {
        return formula;
    }

    public double getMolarMass() {
        return molarMass;
    }
}
