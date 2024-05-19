package io.github.amerebagatelle.quietstone.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonExtensionBlock;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.amerebagatelle.quietstone.Quietstone.LOUD;

@Mixin(PistonExtensionBlock.class)
public class PistonExtensionBlockMixin {
   @Inject(method = "appendProperties", at = @At("TAIL"))
   private void addWaxed(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
       builder.add(LOUD);
   }
}
