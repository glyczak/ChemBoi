package ga.gaba.ChemBoi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by glyczak on 2/15/18.
 */
public class MessageListener extends ListenerAdapter {
    private static final MessageEmbed.Field[] HELP_TOPICS = {
            new MessageEmbed.Field("I can convert masses to moles:", "21.33 mg Ag2SO4", false),
            new MessageEmbed.Field("I can find thermodynamic stats for compounds:", "H2S (g) enthalpy", false),
            new MessageEmbed.Field("I can find molar masses of compounds.", "KNO3 molar mass", false),
            new MessageEmbed.Field("I can find reduction potentials.", "H2O2 reduction", false),
            new MessageEmbed.Field("I can find acid/base ionization constants.", "Aniline kb", false),
            new MessageEmbed.Field("I can find solubility product constants.", "Ni(CN)2 ksp", false),
            new MessageEmbed.Field("I can format chemical formulas.", "format C6H12O6", false),
            new MessageEmbed.Field("I can print out numeric constants.", "faraday's constant", false)
    };

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String lowerContent = content.toLowerCase();
        if(message.isMentioned(Bot.api.getSelfUser()) && !(content.contains("@everyone") || content.contains("@here"))) {
            EmbedBuilder builder = new BotEmbedBuilder();
            if(lowerContent.contains("diag")) {
                builder.setTitle(":wrench: Diagnostic Information");
                builder.addField("Ping Time", Long.toString(Bot.api.getPing()), false);
                builder.addField("Maximum Reconnect Delay", Integer.toString(Bot.api.getMaxReconnectDelay()), false);
                builder.addField("Bot User ID", Bot.api.getSelfUser().getId(), false);
                builder.addField("Current Chat ID", event.getChannel().getId(), false);
            } else {
                builder.setTitle(":wave: Hi there, I'm ChemBoi!");
                builder.setDescription("I was created to help out with common chemistry-related tasks.  Here are some of the things I know how to do:");
                for(MessageEmbed.Field helpTopic : HELP_TOPICS) {
                    builder.addField(helpTopic);
                }
            }
            event.getChannel().sendMessage(builder.build()).queue();
        } else {
            if(content.matches("\\d*\\.?\\d* ?[mk]?g \\w+")) {
                MolarMassParser parser = new MolarMassParser();
                MessageEmbed response = parser.fancyConvertMassToMoles(content);
                event.getChannel().sendMessage(response).queue();
            } else {
                if(lowerContent.contains("entropy") ||
                        lowerContent.contains("enthalpy") ||
                        lowerContent.contains("gibbs") ||
                        lowerContent.contains("free energy")) {
                    Pattern compoundPattern = Pattern.compile(
                            "(n\\-)?\\(?([A-Z][a-z]?\\d*)+(\\)\\d*)?( \\d?[+\\-])?(.*\\))?");
                    Matcher matcher = compoundPattern.matcher(content);
                    while(matcher.find()) {
                        event.getChannel().sendMessage(ThermoCompoundSearcher.getInstance().fancySearch(
                                content.substring(matcher.start(), matcher.end()))).queue();
                    }
                } else if(lowerContent.endsWith("molar mass")) {
                    Pattern compoundPattern = Pattern.compile(
                            "(\\(?[A-Z][a-z]?\\d*(\\)\\d+)?)+");
                    Matcher matcher = compoundPattern.matcher(content);
                    if(matcher.find()) {
                        MolarMassParser parser = new MolarMassParser();
                        MessageEmbed response = parser.getFancyMolarMass(
                                content.substring(matcher.start(), matcher.end()));
                        event.getChannel().sendMessage(response).queue();
                    }
                } else if(lowerContent.startsWith("format")) {
                    Pattern compoundPattern = Pattern.compile(
                            "(\\(?[A-Z][a-z]?\\d*(\\)\\d+)?)+( \\d?[+\\-])?( \\([a-z]+\\))?");
                    Matcher matcher = compoundPattern.matcher(content);
                    if(matcher.find()) {
                        EquationFormatter formatter = new EquationFormatter();
                        if(!lowerContent.contains("simple")) {
                            MessageEmbed response = formatter.fancyFormatCompound(
                                    content.substring(matcher.start(), matcher.end()));
                            event.getChannel().sendMessage(response).queue();
                        } else {
                            String response = formatter.formatCompound(
                                    content.substring(matcher.start(), matcher.end()));
                            event.getChannel().sendMessage(response).queue();
                        }
                    }
                } else if(lowerContent.contains("reduction") || lowerContent.contains("redox")) {
                    Pattern compoundPattern = Pattern.compile(
                            "[\\(\\[]?([A-Z][a-z]?\\d*)+([\\)\\]]\\d*[\\+\\-])?( \\d?[+\\-])?(.*\\))?");
                    Matcher matcher = compoundPattern.matcher(content);
                    while(matcher.find()) {
                        event.getChannel().sendMessage(ReductionPotentialSearcher.getInstance().fancySearch(
                                content.substring(matcher.start(), matcher.end()))).queue();
                    }
                } else if(lowerContent.endsWith(" ka") ||
                        lowerContent.endsWith(" kb")) {
                    MessageEmbed response = IonizationConstantSearcher.getInstance().fancySearch(content);
                    if(response != null) event.getChannel().sendMessage(response).queue();
                } else if(lowerContent.contains("ksp")) {
                    MessageEmbed response = KspSearcher.getInstance().fancySearch(content);
                    if(response != null) event.getChannel().sendMessage(response).queue();
                } else if(lowerContent.endsWith("constant")) {
                    MessageEmbed response = new ChemicalConstantSearcher().parseMessage(content);
                    if(response != null) event.getChannel().sendMessage(response).queue();
                } else if(lowerContent.contains("cutillo")) {
                    message.addReaction("\uD83C\uDF5E").queue();
                }
            }
        }
    }
}
