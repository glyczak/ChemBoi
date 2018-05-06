package ga.gaba.ChemBoi;

/**
 * Created by glyczak on 3/18/18.
 */
public class ReductionPotential {
    private String reaction;
    private float potential;

    public String getReaction() {
        return this.reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public float getPotential() {
        return this.potential;
    }

    public void setPotential(float potential) {
        this.potential = potential;
    }

    public ReductionPotential() {}

    public ReductionPotential(String reaction, float potential) {
        this.reaction = reaction;
        this.potential = potential;
    }

    @Override
    public String toString() {
        return String.format("```%s\nE₀ = %.3f V```", reaction, potential);
    }
}
