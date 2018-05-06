package ga.gaba.ChemBoi;

/**
 * Created by glyczak on 2/24/18.
 */
public class ThermoCompound {
    private String name;
    private float enthalpy;
    private float entropy;
    private float freeEnergy;

    public ThermoCompound() {}

    public ThermoCompound(String name, float enthalpy, float entropy, float freeEnergy) {
        this.name = name;
        this.enthalpy = enthalpy;
        this.entropy = entropy;
        this.freeEnergy = freeEnergy;
    }

    @Override
    public String toString() {
        return String.format("```%s\n∆H° = %.3f kJ/mol\n S° = %.3f J/molK\n∆G° = %.3f kJ/mol```", name, enthalpy, entropy, freeEnergy);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEnthalpy() {
        return this.enthalpy;
    }

    public void setEnthalpy(float enthalpy) {
        this.enthalpy = enthalpy;
    }

    public float getEntropy() {
        return this.entropy;
    }

    public void setEntropy(float entropy) {
        this.entropy = entropy;
    }

    public float getFreeEnergy() {
        return this.freeEnergy;
    }

    public void setFreeEnergy(float freeEnergy) {
        this.freeEnergy = freeEnergy;
    }
}
