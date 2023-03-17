package ca.fxco.moreculling.utils;

import net.minecraftforge.fml.ModList;

public class CompatUtils {
    public static final boolean IS_SODIUM_LOADED = ModList.get().isLoaded("rubidium");
}
