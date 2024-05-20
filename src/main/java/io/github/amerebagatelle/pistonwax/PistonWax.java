package io.github.amerebagatelle.pistonwax;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.state.property.BooleanProperty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class PistonWax implements ModInitializer {
    public static final BooleanProperty LOUD = BooleanProperty.of("loud");
    public static float loudnessMultiplier = 0.1f;

    @Override
    public void onInitialize() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("pistonwax.txt");
        try {
            if(!Files.exists(configPath)) {
                Files.createFile(configPath);
                Files.write(configPath, Float.toString(loudnessMultiplier).getBytes());
            }
            loudnessMultiplier = Float.parseFloat(Files.readAllLines(configPath).getFirst());
        } catch (IOException e) {
            throw new RuntimeException("Loudness file could not be read properly.  Proper format is a text file containing a number in decimal form.");
        }
    }
}
