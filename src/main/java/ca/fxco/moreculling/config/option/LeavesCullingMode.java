package ca.fxco.moreculling.config.option;

import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public enum LeavesCullingMode implements SelectionListEntry.Translatable {
    DEFAULT("options.gamma.default"),
    FAST("options.clouds.fast"),
    STATE("moreculling.config.options.blockstate"),
    CHECK("moreculling.config.options.check"),
    DEPTH("moreculling.config.options.depth"),
    VERTICAL("moreculling.config.options.vertical");

    private final String translationKey;

    LeavesCullingMode(String translationKey) {
        this.translationKey = translationKey;
    }

    public Text getText() {
        return new TranslatableText(this.translationKey);
    }

    @Override
    public @NotNull String getKey() {
        return this.translationKey;
    }

    public static Text[] getLocalizedNames() {
        LeavesCullingMode[] values = values();
        Text[] names = new Text[values.length];
        for (int i = 0; i < values.length; i++) names[i] = values[i].getText();
        return names;
    }
}
