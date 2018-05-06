package ga.gaba.ChemBoi;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

/**
 * Created by glyczak on 3/19/18.
 */
public class ChemicalConstantSearcher {
    public MessageEmbed parseMessage(String message) {
        String messageLower = message.toLowerCase();
        EmbedBuilder builder = new BotEmbedBuilder();
        builder.setTitle(":book: Chemistry Constants");
        if(messageLower.contains("gas") ||
                message.contains("R")) {
            builder.addField("Ideal Gas Constant - **R**", "8.314 J/molK", true);
            builder.addField("", "0.08206 Latm/molK", true);
            builder.addField("", "62.36 Ltorr/molK", true);
        } else if(messageLower.contains("faraday") ||
                messageLower.contains("redox") ||
                message.contains("F")) {
            builder.addField("Faraday's Constant - **F**", "96485 C/mol", false);
        } else if(messageLower.contains("planck")) {
            builder.addField("Planck's Constant - **h**", "6.626 × 10⁻³⁴ Js", false);
        } else if(messageLower.contains("light") ||
                messageLower.startsWith("c ") ||
                messageLower.endsWith(" c")) {
            builder.addField("Speed of Light - **c**", "2.998 × 10⁸ m/s", false);
        } else if(messageLower.contains("avogadro") ||
                message.contains("NA") ||
                message.contains("L")) {
            builder.addField("Avogadro's Number", "6.022 × 10²³ 1/mol", false);
        } else if(messageLower.contains("electron") ||
                messageLower.contains("e-")) {
            builder.addField("Electron Charge - ***e***", "-1.602 × 10⁻¹⁹ C", false);
        } else {
            return null;
        }
        return builder.build();
    }
}
