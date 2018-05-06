package ga.gaba.ChemBoi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glyczak on 3/19/18.
 */
public class IonizationConstantSearcher {
    private static IonizationConstantSearcher instance;

    private List<IonizationConstant> weakAcids;
    private List<IonizationConstant> weakBases;

    private IonizationConstantSearcher() {
        String KaJson = "[{\"name\":\"Acetic acid\",\"formula\":\"CH3COOH\",\"k\":[1.8E-5]},{\"name\":\"Acetylsalicylic acid (aspirin)\",\"formula\":\"HC9H7O4\",\"k\":[3.0E-4]},{\"name\":\"Aluminum ion\",\"formula\":\"Al(H2O)4 3+\",\"k\":[1.2E-5]},{\"name\":\"Arsenic acid\",\"formula\":\"H3AsO4\",\"k\":[2.5E-4,5.6E-8,3.0E-13]},{\"name\":\"Ascorbic acid\",\"formula\":\"H2C6H6O6\",\"k\":[7.9E-5,1.6E-12]},{\"name\":\"Benzoic acid\",\"formula\":\"C6H5COOH\",\"k\":[6.3E-5]},{\"name\":\"Carbonic acid\",\"formula\":\"H2CO3\",\"k\":[4.2E-7,4.8E-11]},{\"name\":\"Ferric ion\",\"formula\":\"Fe(H2O)6 3+\",\"k\":[0.004]},{\"name\":\"Formic acid\",\"formula\":\"HCOOH\",\"k\":[1.8E-4]},{\"name\":\"Hydrocyanic acid\",\"formula\":\"HCN\",\"k\":[4.0E-10]},{\"name\":\"Hydrofluoric acid\",\"formula\":\"HF\",\"k\":[7.2E-4]},{\"name\":\"Hydrogen peroxide\",\"formula\":\"H2O2\",\"k\":[2.4E-12]},{\"name\":\"Hydrosulfuric acid\",\"formula\":\"H2S\",\"k\":[1.0E-7,1.0E-19]},{\"name\":\"Hypochlorous acid\",\"formula\":\"HClO\",\"k\":[3.5E-8]},{\"name\":\"Nitrous acid\",\"formula\":\"HNO2\",\"k\":[4.5E-4]},{\"name\":\"Oxalic acid\",\"formula\":\"H2C2O4\",\"k\":[0.059,6.4E-5]},{\"name\":\"Phenol\",\"formula\":\"C6H5OH\",\"k\":[1.0E-10]},{\"name\":\"Phosphoric acid\",\"formula\":\"H3PO4\",\"k\":[0.0075,6.2E-8,3.6E-13]},{\"name\":\"Sulfuric acid\",\"formula\":\"H2SO4\",\"k\":[0.012]},{\"name\":\"Sulfurous acid\",\"formula\":\"H2SO3\",\"k\":[0.017,6.4E-8]}]\n";
        String KbJson = "[{\"name\":\"Ammonia\",\"formula\":\"NH3\",\"k\":[1.8E-5]},{\"name\":\"Aniline\",\"formula\":\"C6H5NH2\",\"k\":[7.4E-10]},{\"name\":\"Caffeine\",\"formula\":\"C8H10N4O2\",\"k\":[4.1E-4]},{\"name\":\"Codeine\",\"formula\":\"C18H21O3N\",\"k\":[8.9E-7]},{\"name\":\"Diethylamine\",\"formula\":\"(C2H5)2NH\",\"k\":[6.9E-4]},{\"name\":\"Dimethylamine\",\"formula\":\"(CH3)2NH\",\"k\":[5.9E-4]},{\"name\":\"Ethylamine\",\"formula\":\"C2H5NH2\",\"k\":[4.3E-4]},{\"name\":\"Hydroxylamine\",\"formula\":\"NH2OH\",\"k\":[9.1E-9]},{\"name\":\"Isoquinoline\",\"formula\":\"C9H7N\",\"k\":[2.5E-9]},{\"name\":\"Methylamine\",\"formula\":\"CH3NH2\",\"k\":[4.2E-4]},{\"name\":\"Morphine\",\"formula\":\"C17H19O3N\",\"k\":[7.4E-7]},{\"name\":\"Piperidine\",\"formula\":\"C5H11N\",\"k\":[0.0013]},{\"name\":\"Pyridine\",\"formula\":\"C5H5N\",\"k\":[1.5E-9]},{\"name\":\"Quinoline\",\"formula\":\"C9H7N\",\"k\":[6.3E-10]},{\"name\":\"Triethanolamine\",\"formula\":\"C6H15O3N\",\"k\":[5.8E-7]},{\"name\":\"Triethylamine\",\"formula\":\"(C2H5)3N\",\"k\":[5.2E-4]},{\"name\":\"Trimethylamine\",\"formula\":\"(CH3)3N\",\"k\":[6.3E-5]}]\n";
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        weakAcids = gson.fromJson(KaJson, new TypeToken<ArrayList<IonizationConstant>>(){}.getType());
        weakBases = gson.fromJson(KbJson, new TypeToken<ArrayList<IonizationConstant>>(){}.getType());
    }

    public static IonizationConstantSearcher getInstance() {
        if(instance == null) instance = new IonizationConstantSearcher();
        return instance;
    }

    public MessageEmbed fancySearch(String query) {
        EmbedBuilder builder = new BotEmbedBuilder();
        String lowerQuery = query.toLowerCase();
        if(query.length() > 3) query = query.substring(0, query.length() - 3);
        boolean found = false;
        if(lowerQuery.endsWith(" ka")) {
            for(IonizationConstant acid : weakAcids) {
                if(acid.toString().contains(query)) {
                    found = true;
                    boolean multiLine = acid.getProtonCount() > 1;
                    builder.addField(acid.toFormattedString(),
                            acid.getFormattedK(1),
                            multiLine);
                    if(multiLine) {
                        builder.addField("", acid.getFormattedK(2), true);
                        builder.addField("", acid.getFormattedK(3), true);
                    }
                }
            }
            if(found) builder.setDescription("First Ionization - **Kₐ₁**; Second Ionization - **Kₐ₂**; Third Ionization - **Kₐ₃**");
        } else if(lowerQuery.endsWith(" kb")) {
            for (IonizationConstant base : weakBases) {
                if (base.toString().contains(query)) {
                    found = true;
                    builder.addField(base.toFormattedString(), base.getFormattedK(1), false);
                }
            }
            if(found) builder.setDescription("Ionization - **Kb**");
        }
        builder.setTitle(found ?
                String.format(":regional_indicator_k: Ionization Constant Search Results for \"%s\"", query) :
                String.format(":regional_indicator_k: No Results Found for \"%s\"", query), null);;
        return builder.build();
    }
}
