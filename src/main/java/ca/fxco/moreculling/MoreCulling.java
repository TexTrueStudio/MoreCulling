package ca.fxco.moreculling;

import ca.fxco.moreculling.config.MoreCullingConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MoreCulling.MOD_ID)
public class MoreCulling {

    public static BakedModelManager bakedModelManager = null;
    public static BlockRenderManager blockRenderManager = null;

    public static final String MOD_ID = "moreculling";
    public static final TagKey<Block> DONT_CULL = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MOD_ID, "dont_cull"));

    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static MoreCullingConfig CONFIG;

    public MoreCulling() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        MOD_BUS.addListener(this::onInitializeClient);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onInitializeClient(final FMLClientSetupEvent event) {}

    static {
        AutoConfig.register(MoreCullingConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(MoreCullingConfig.class).getConfig();
        MoreCulling.CONFIG.modCompatibility.defaultReturnValue(MoreCulling.CONFIG.useOnModdedBlocksByDefault);
    }
}
