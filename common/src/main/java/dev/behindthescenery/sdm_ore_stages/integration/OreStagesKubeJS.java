package dev.behindthescenery.sdm_ore_stages.integration;

import dev.behindthescenery.sdm_ore_stages.api.OreStagesApi;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.minecraft.world.level.block.state.BlockState;

public class OreStagesKubeJS implements KubeJSPlugin {

    @Override
    public void registerBindings(BindingRegistry bindings) {
        if(bindings.type().isServer()) {
            bindings.add("OreStages", Methods.class);
        }
    }

    public interface Methods {

        static void addOreStageBlockState(String stage, BlockState blockState) {
            OreStagesApi.addOreStage(stage, blockState);
        }

        static void addOreStageBlockStateReplaced(String stage, BlockState blockState, BlockState newBlock) {
            OreStagesApi.addOreStage(stage, blockState, newBlock);
        }

        static void addOreStageBlock(String stage, BlockState blockState) {
            OreStagesApi.addOreStage(stage, blockState);
        }

        static void addOreStageBlockReplaced(String stage, BlockState blockState, BlockState newBlock) {
            OreStagesApi.addOreStage(stage, blockState, newBlock);
        }
    }
}

