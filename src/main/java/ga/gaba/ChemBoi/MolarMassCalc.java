package ga.gaba.ChemBoi;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class MolarMassCalc {
	private static MolarMassCalc instance;
	private Map<String, Double> elementsMap;

	public static MolarMassCalc getInstance() {
		if(instance == null) {
			instance = new MolarMassCalc();
		}
		return instance;
	}

	private MolarMassCalc() {
		elementsMap = new HashMap<>();

		String totalElemData = "H,1.008;C,12.011;N,14.007;O,15.999;P,30.973;S,32.06;Se,78.971;Li,6.94;Na,22.989;K,39.0983;Rb,85.4678;Cs,132.90;Fr,223.00;Be,9.0121;Mg,24.305;Ca,40.078;Sr,87.62;Ba,132.327;Ra,226.00;Sc,44.955;Ti,47.867;V,50.9415;Cr,51.9961;Mn,54.938;Fe,55.845;Co,58.933;Ni,58.6934;Cu,63.546;Zn,65.38;Y,88.90584;Zr,91.224;Nb,92.90637;Mo,95.95;Tc,98.00;Ru,101.07;Rh,102.90;Pd,106.42;Ag,107.8682;Cd,112.414;Hf,178.49;Ta,180.94;W,183.84;Re,186.207;Os,193.23;Ir,192.217;Pt,195.084;Au,196.96;Hg,200.59;Rf,267.00;Db,268.00;Sg,271.00;Bh,272.00;Hs,270.00;Mt,276.00;Ds,281.00;Rg,280.00;Cn,285.00;Al,26.981;Ga,69.723;In,114.818;Sn,118.710;TI,204.38;Pb,207.20;Bi,208.98;Uut,284.00;Fl,289.00;Uup,288.00;Lv,293.00;B,10.81;Si,28.085;Ge,72.63;As,74.921;Sb,121.760;Te,127.60;Po,209.00;F,18.998;Cl,35.45;Br,79.904;I,126.90;At,210.00;Uus,294.00;He,4.002602;Ne,20.1797;Ar,39.948;Kr,83.798;Xe,131.293;Rn,222.00;Uuo,294.00;La,138.90;Ce,140.116;Pr,140.90;Nd,144.242;Pm,145.00;Sm,150.36;Eu,151.964;Gd,157.25;Tb,158.92;Dy,162.500;Ho,164.93;Er,167.259;Tm,168.93;Yb,173.054;Lu,174.9668;Ac,227.00;Th,232.0377;Pa,231.03;U,238.02;Np,237.00;Pu,244.00;Am,243.00;Cm,247.00;Bk,247.00;Cf,251.00;Es,252.00;Fm,257.00;Md,258.00;No,259.00;Lr,262.00";

		String[] elementsData = totalElemData.split(";");

		for (String elemData : elementsData) {
			String[] readInfo = elemData.split(",");
			elementsMap.put(readInfo[0], Double.parseDouble(readInfo[1]));
		}
	}

	public Double calculate(String input) {
		Pattern p = Pattern.compile("(([A-Z]{1}?[a-z]{0,2})(\\d*))");

		Matcher m = p.matcher(input);
		Double mass = 0.0; // mass of the compound or element
		StringBuilder buf = new StringBuilder();

		while (m.find()) {
			buf.append(m.group(1));
			Double elementMass = elementsMap.get(m.group(2));
			Integer numAtoms = 1;
			if (m.group(3) != null && m.group(3).trim().length() > 0) {
				numAtoms = Integer.parseInt(m.group(3));
			}
			if (elementMass == null) {
				throw new IllegalArgumentException(String.format(
						"Your input['%s'] is not a valid compound ", input));
			}

			mass += numAtoms * elementMass;
		}

		if (!input.equals(buf.toString())) {
			throw new IllegalArgumentException(String.format(
					"Your input['%s'] is not a valid compound ", input));
		}

		return mass;
	}
}
