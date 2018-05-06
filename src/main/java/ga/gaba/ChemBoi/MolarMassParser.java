package ga.gaba.ChemBoi;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class MolarMassParser {
	private final String ILLEGAL_COMPOUND_MESSAGE = "The specified compound does not exist.";

	private class ChemicalSample extends Compound {
		double moles;
		String unitPrefix;

		public ChemicalSample(String formula, double sampleMass, String unitPrefix) {
			super(formula);
			this.moles = sampleMass / this.getMolarMass();
			this.unitPrefix = unitPrefix;
		}

		public double getMoles() {
			return moles;
		}

		public String getPrefix() {
			return unitPrefix;
		}

		public double getSampleMass() {
			return moles * this.getMolarMass();
		}
	}

	private ChemicalSample parseChemicalSample(String input) {
		/*\
		 *	Formatting Example:
		 *		2.183 mg Ag2SO4
		\*/
		int unitIndex = input.indexOf("g");
		String prefix = input.substring(unitIndex - 1, unitIndex);
		Double mass;
		if(prefix.equals("m") || prefix.equals("k") || prefix.equals(" ")) {
			String space = input.substring(unitIndex - 2, unitIndex - 1);
			if(space.equals(" ")) {
				mass = Double.parseDouble(input.substring(0, unitIndex - 2));
			} else {
				mass = Double.parseDouble(input.substring(0, unitIndex - 1));
			}
		} else {
			mass = Double.parseDouble(input.substring(0, unitIndex));
			prefix = "";
		}
		return new ChemicalSample(input.substring(unitIndex + 2), mass, prefix);
	}

    public String convertMassToMoles(String input) {
		try {
			ChemicalSample c = parseChemicalSample(input);
			return String.format("%s %smol %s",
				c.getMoles(), c.getPrefix(), c.toString());
		} catch(IllegalArgumentException e) {
			return ILLEGAL_COMPOUND_MESSAGE;
		}
    }

    public MessageEmbed fancyConvertMassToMoles(String input) {
    	EmbedBuilder builder = new BotEmbedBuilder();
    	try {
    		ChemicalSample c = parseChemicalSample(input);
			builder.setTitle(String.format(":pencil: %s %sg of %s to moles",
					c.getSampleMass(), c.getPrefix(),
					new EquationFormatter().formatCompound(c.toString())));
			builder.setDescription(String.format("%s %smol %s",
					c.getMoles(), c.getPrefix(), new EquationFormatter().formatCompound(c.toString())));
		} catch (IllegalArgumentException e) {
			builder.setTitle(String.format(":pencil: %s to moles", input));
			builder.setDescription(ILLEGAL_COMPOUND_MESSAGE);
		}
		return builder.build();
	}

    public String getMolarMass(String input) {
        try {
            Compound compound = new Compound(input);
            return String.format("%s g/mol", compound.getMolarMass());
        } catch(IllegalArgumentException e) {
            return ILLEGAL_COMPOUND_MESSAGE;
        }
    }

    public MessageEmbed getFancyMolarMass(String input) {
		EmbedBuilder builder = new BotEmbedBuilder();
		builder.setTitle(String.format(":scales: Molar Mass of %s", new EquationFormatter().formatCompound(input)));
		builder.setDescription(getMolarMass(input));
		return builder.build();
	}
}
