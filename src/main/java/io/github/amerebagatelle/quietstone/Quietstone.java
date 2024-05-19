package io.github.amerebagatelle.quietstone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.fabricmc.fabric.api.block.v1.FabricBlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.BooleanProperty;


public class Quietstone implements ModInitializer {
    public static final BooleanProperty LOUD = BooleanProperty.of("loud");

    @Override
    public void onInitialize() {
    }
}
