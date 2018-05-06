package ga.gaba.ChemBoi;

import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.Date;

/**
 * Created by glyczak on 3/19/18.
 */
public class BotEmbedBuilder extends EmbedBuilder {
    public BotEmbedBuilder() {
        this.setTimestamp(new Date().toInstant());
        this.setColor(new Color(1, 157, 224));
        this.setFooter("© Gabe Lyczak", "https://s.gravatar.com/avatar/50c131c8628c543170d24ced1fcb32ff?s=80");
    }
}
