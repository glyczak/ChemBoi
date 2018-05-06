package ga.gaba.ChemBoi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by glyczak on 3/19/18.
 */
public class IonizationConstant {
    private String name;
    private String formula;
    private float[] k;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public float[] getK() {
        return this.k;
    }

    public void setK(float[] k) {
        this.k = k;
    }

    public int getProtonCount() {
        return k.length;
    }

    public float getK(int ionization) {
        return k[ionization - 1];
    }

    public String getFormattedK(int ionization) {
        try {
            String k = String.format("%.1e", getK(ionization));
            Pattern pattern = Pattern.compile("e(\\-\\d+)");
            Matcher matcher = pattern.matcher(k);
            StringBuilder formattedK = new StringBuilder();
            EquationFormatter formatter = new EquationFormatter();
            if(matcher.find()) {
                formattedK.append(k.substring(0, matcher.start()));
                formattedK.append(" × 10");
                for(char c : matcher.group(1).toCharArray()) {
                    formattedK.append(formatter.toSuperscript(c));
                }
                return formattedK.toString();
            }
        } catch(ArrayIndexOutOfBoundsException e) {}
        return "";
    }

    public String toString() {
        return name + " " + formula;
    }

    public String toFormattedString() {
        return name + " " + new EquationFormatter().formatCompound(formula);
    }
}
