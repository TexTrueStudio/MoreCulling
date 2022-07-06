package ca.fxco.moreculling.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "moreculling")
public class MoreCullingConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public boolean useBlockStateCulling = true;

    @ConfigEntry.Gui.Tooltip
    public boolean useCustomItemFrameRenderer = true;

    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean useItemFrameLOD = true;

    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean useItemFrame3FaceCulling = true;
}