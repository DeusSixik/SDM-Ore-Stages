package dev.behindthescenery.sdm_ore_stages.fabric;

import dev.behindthescenery.sdm_ore_stages.SdmOreStages;
import net.fabricmc.api.ModInitializer;

public final class SdmOreStagesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        SdmOreStages.init();
    }
}
