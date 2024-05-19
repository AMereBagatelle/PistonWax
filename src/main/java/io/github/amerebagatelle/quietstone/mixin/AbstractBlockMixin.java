package io.github.amerebagatelle.quietstone.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.amerebagatelle.quietstone.Quietstone.LOUD;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "onUseWithItem", at = @At("HEAD"), cancellable = true)
    private void waxPiston(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ItemActionResult> cir) {
        if(state.isOf(Blocks.PISTON) || state.isOf(Blocks.STICKY_PISTON)) {
            if(stack.isOf(Items.HONEYCOMB) && state.get(LOUD)) {
                state = state.with(LOUD, false);
                System.out.println(state.get(LOUD));
                world.setBlockState(pos, state);
                cir.setReturnValue(ItemActionResult.SUCCESS);
            } else {
                cir.setReturnValue(ItemActionResult.FAIL);
            }
        }
    }
}
