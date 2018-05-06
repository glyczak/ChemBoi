package ga.gaba.ChemBoi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by glyczak on 3/30/18.
 */
public class KspSearcher {
    private static KspSearcher instance;
    private HashMap<String, Float> values;
    private static final Pattern compoundPattern = Pattern.compile("(\\(?[A-Z][a-z]?\\)?\\d*){2,}");

    private KspSearcher() {
        String json = "{\"AgOH\":2.0E-8,\"CuS\":7.9E-37,\"PbS\":3.2E-28,\"FeCO3\":3.5E-11,\"Ag2SO4\":1.7E-5,\"Mn(OH)2\":4.6E-14,\"Ca(OH)2\":7.9E-6,\"Zn3(PO4)2\":9.1E-33,\"CuCO3\":2.5E-10,\"PbSO4\":1.8E-8,\"CaS\":8.0E-6,\"MnCO3\":1.8E-11,\"CaC2O4\":2.3E-9,\"Al(OH)3\":1.9E-33,\"PbF2\":3.7E-8,\"Cr(OH)3\":6.7E-31,\"Pb(OH)2\":2.8E-16,\"MgCO3\":4.0E-5,\"MgC2O4\":8.6E-5,\"BaC2O4\":1.1E-7,\"Ni(CN)2\":3.0E-23,\"Ba3(PO4)2\":1.3E-29,\"AgBr\":3.3E-13,\"Mg(OH)2\":1.5E-11,\"AlPO4\":1.3E-20,\"PbCrO4\":1.8E-14,\"AgCN\":1.2E-16,\"MnS\":5.1E-15,\"Ag2CO3\":8.1E-12,\"CaF2\":3.9E-11,\"Pb3(PO4)2\":2.9E-44,\"Ag2S\":0.0,\"Fe2S3\":0.0,\"PbI2\":8.7E-9,\"MgF2\":6.4E-9,\"AgI\":1.5E-16,\"CrPO4\":2.4E-23,\"Ni(OH)2\":2.8E-16,\"CaSO3\":1.3E-8,\"CoS\":5.9E-21,\"BaCO3\":8.1E-9,\"CaSO4\":2.4E-5,\"Fe(OH)3\":6.3E-38,\"Fe(OH)2\":7.9E-15,\"AgCl\":1.8E-10,\"Zn(CN)2\":8.0E-12,\"CaCO3\":3.8E-9,\"ZnCO3\":1.5E-11,\"BaF2\":1.7E-6,\"FeS\":4.9E-18,\"BaSO4\":1.1E-10,\"BaSO3\":8.0E-7,\"CoCO3\":8.0E-13,\"Ag2CrO4\":9.0E-12,\"CaCrO4\":7.1E-4,\"Cu(OH)2\":1.6E-19,\"NiS\":3.0E-21,\"Co(OH)2\":2.5E-16,\"PbCO3\":1.5E-13,\"PbCl2\":1.7E-5,\"PbBr2\":6.3E-6,\"BaCrO4\":2.0E-10,\"Ag3PO4\":1.3E-20,\"Zn(OH)2\":4.5E-17,\"Ca3(PO4)2\":1.0E-25,\"NiCO3\":6.6E-9,\"ZnS\":2.0E-25}";
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        values = gson.fromJson(json, new TypeToken<HashMap<String, Float>>(){}.getType());
    }

    private String getFormattedKsp(Float ksp) {
        try {
            String k = String.format("%.1e", ksp);
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

    public MessageEmbed fancySearch(String input) {
        Matcher matcher = compoundPattern.matcher(input);
        if(matcher.find()) {
            String compound = input.substring(matcher.start(), matcher.end());
            Float ksp = values.get(compound);
            EmbedBuilder builder = new BotEmbedBuilder();
            if(ksp != null) {
                builder.setDescription("Solubility Product Constant - **Kₛₚ**");
                compound = new EquationFormatter().formatCompound(compound);
                builder.addField(compound, getFormattedKsp(ksp), false);
            }
            builder.setTitle(":regional_indicator_k: " + ((ksp != null) ?
                    String.format("Solubility Product Constant Search Results for \"%s\"", compound) :
                    String.format("No Results Found for \"%s\"", compound)));
            return builder.build();
        }
        return null;
    }

    public static KspSearcher getInstance() {
        if(instance == null) instance = new KspSearcher();
        return instance;
    }
}
