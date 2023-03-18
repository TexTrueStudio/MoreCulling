package ca.fxco.moreculling.api.model;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * BakedOpacity is an interface that should be used on classes that extend BakedModel
 * It allows your custom models to take full advantage of MoreCulling's culling techniques.
 * @since 0.3.0
 */

public interface BakedOpacity {

    /**
     * States if any of the textures of the model that are on a face of the block are translucent.
     * If they are not translucent, MoreCulling will be able to provide faster culling for its states.
     *
     * Some baked models will require a blockstate in order to provide more accurate translucency checks,
     * usually if no blockstate is passed it will work fine, although some baked models will always return true.
     * If possible, the default state of the block will be used.
     * @since 0.12.0
     */
    default boolean hasTextureTranslucency(@Nullable BlockState state, @Nullable Direction direction) {
        return true;
    }

    /**
     * This just acts like hasTextureTranslucency(state, null)
     * Using this method is slower than if you also pass the direction
     * @since 0.8.0
     */
    default boolean hasTextureTranslucency(@Nullable BlockState state) {
        return hasTextureTranslucency(state, null);
    }

    /**
     * This just acts like hasTextureTranslucency(null, null)
     * Using this method is slower than the others
     * @since 0.3.0
     */
    default boolean hasTextureTranslucency() {
        return hasTextureTranslucency(null, null);
    }

    /**
     * When called this method will reset the translucency cache of the model.
     * This should be called if the texture of the model is ever changed!
     * @since 0.7.0
     */
    default void resetTranslucencyCache() {}

    /**
     * This method will return a list of all models contained within this model.
     * Currently, this is only used for debugging and development. Although it may be used in the future!
     * @since 0.8.0
     * @deprecated this will be removed in the following versions, and it is no longer supported
     */
    @Deprecated
    default @Nullable List<BakedModel> getModels() {
        return null;
    }

    /**
     * Gets the VoxelShape culling shape for the baked model.
     * Returns null unless its set within the model json `cullshapes`
     * WeightedBakedModels cannot use this as we cannot determine which model will be used
     * @since 0.15.0
     */
    default @Nullable VoxelShape getCullingShape(BlockState state) {
        return null;
    }


    /**
     * Used to set the culling shape of the baked model
     * @since 0.17.0
     */
    @ApiStatus.Internal
    default void setCullingShape(@Nullable VoxelShape cullingShape) {}

    /**
     * Tells you if this model supports setting the culling shape
     * @since 0.17.0
     */
    @ApiStatus.Internal
    default boolean canSetCullingShape() {
        return false;
    }
}
