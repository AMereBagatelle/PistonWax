package io.github.amerebagatelle.quietstone.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlock;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.amerebagatelle.quietstone.Quietstone.LOUD;

@Mixin(PistonBlock.class)
public class PistonBlockMixin {
    @Inject(method = "appendProperties", at = @At("TAIL"))
    private void addWaxed(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(LOUD);
    }

    @ModifyExpressionValue(
            method = "onSyncedBlockEvent",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PistonBlock;getDefaultState()Lnet/minecraft/block/BlockState;")
    )
    private BlockState modifyState(BlockState original, @Local(ordinal = 0, argsOnly = true) BlockState state) {
        return original.with(LOUD, state.get(LOUD));
    }

    @ModifyArg(
            method = "onSyncedBlockEvent",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"),
            index = 4
    )
    private float modifyVolume(float volume, @Local(ordinal = 0, argsOnly = true) BlockState state) {
        return state.get(LOUD) ? volume : volume * 0.0f;
    }
}
