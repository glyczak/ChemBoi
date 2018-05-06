package ga.gaba.ChemBoi;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by glyczak on 3/4/18.
 */
public class EquationFormatter {
    public MessageEmbed fancyFormatCompound(String input) {
        EmbedBuilder builder = new BotEmbedBuilder();
        builder.setTitle(String.format(":paintbrush: Format %s", input));
        builder.setDescription(formatCompound(input));
        return builder.build();
    }

    public String formatCompound(String compound) {
        StringBuilder formattedEquation = new StringBuilder();
        char next;
        int i;
        for(i = 0; i < compound.length(); i++){
            next = compound.charAt(i);
            if(next != ' ') {
                formattedEquation.append(toSubscript(next));
            } else break;
        }
        for(i += 1; i < compound.length(); i++) {
            next = compound.charAt(i);
            if(next != ' ') {
                formattedEquation.append(toSuperscript(next));
            } else break;
        }
        for(i += 1; i < compound.length(); i++) {
            next = compound.charAt(i);
            formattedEquation.append(toSuperscript(next));
        }
        return formattedEquation.toString();
    }

    public String formatEquation(String equation) {
        StringBuilder formattedEquation = new StringBuilder();
        Pattern compoundPattern = Pattern.compile("([A-Z][a-z]?\\d*)+(\\(\\w+\\))?( \\d?[\\+\\-])?");
        equation = equation.replaceAll("(\\d*)e -", "$1e⁻");
        equation = equation.replaceFirst("=", "⇌");
        Matcher matcher = compoundPattern.matcher(equation);
        int i = 0;
        while(matcher.find()) {
            formattedEquation.append(equation.substring(i, matcher.start()));
            i = matcher.start();
            for(; i < matcher.end(); i++) {
                if(equation.charAt(i) == ' ') break;
                formattedEquation.append(toSubscript(equation.charAt(i)));
            }
            try {
                if(equation.charAt(i + 1) == '+' && equation.charAt(i + 3) != '+') {
                    formattedEquation.append(" +");
                } else {
                    for(++i; i < matcher.end(); i++) {
                        formattedEquation.append(toSuperscript(equation.charAt(i)));
                    }
                }
            } catch(StringIndexOutOfBoundsException e) {
                if(i < matcher.end()) formattedEquation.append(equation.substring(i, matcher.end()));
            }
            i = matcher.end();
        }
        return formattedEquation.toString();
    }

    private char toSubscript(char c) {
        switch (c) {
            case '0':
                return '₀';
            case '1':
                return '₁';
            case '2':
                return '₂';
            case '3':
                return '₃';
            case '4':
                return '₄';
            case '5':
                return '₅';
            case '6':
                return '₆';
            case '7':
                return '₇';
            case '8':
                return '₈';
            case '9':
                return '₉';
            default:
                return c;
        }
    }

    public char toSuperscript(char c) {
        switch (c) {
            case '+':
                return '⁺';
            case '-':
                return '⁻';
            case '0':
                return '⁰';
            case '1':
                return '¹';
            case '2':
                return '²';
            case '3':
                return '³';
            case '4':
                return '⁴';
            case '5':
                return '⁵';
            case '6':
                return '⁶';
            case '7':
                return '⁷';
            case '8':
                return '⁸';
            case '9':
                return '⁹';
            default:
                return c;
        }
    }
}
