package ca.fxco.moreculling.mixin.compat;

import ca.fxco.moreculling.MoreCulling;
import ca.fxco.moreculling.utils.CullingUtils;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import me.jellysquid.mods.sodium.client.render.occlusion.BlockOcclusionCache;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Restriction(require = @Condition("rubidium"))
@Mixin(BlockOcclusionCache.class)
public class BlockOcclusionCache_sodiumMixin {

    /*
     * Sodium support
     */


    @Inject(
            method = "shouldDrawSide",
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/BlockView;getBlockState(Lnet/minecraft/util/math/BlockPos;)" +
                            "Lnet/minecraft/block/BlockState;",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true
    )
    private void useMoreCulling(BlockState selfState, BlockView view, BlockPos pos,
                                Direction facing, CallbackInfoReturnable<Boolean> cir, BlockPos.Mutable adjPos) {
        if (MoreCulling.CONFIG.useBlockStateCulling)
            cir.setReturnValue(CullingUtils.shouldDrawSideCulling(selfState, view, pos, facing, adjPos));
    }
}
