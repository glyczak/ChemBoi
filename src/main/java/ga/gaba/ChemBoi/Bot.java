package ga.gaba.ChemBoi;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

/**
 * Created by glyczak on 2/15/18.
 */
public class Bot {
    public static JDA api;
    public static void main(String[] arguments) throws Exception
    {
        String token = arguments[0];
        System.err.println(token);
        api = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .addEventListener(new MessageListener())
                .buildAsync();
        api.getPresence().setGame(Game.of(Game.GameType.LISTENING, "@ChemBoi"));
    }
}
