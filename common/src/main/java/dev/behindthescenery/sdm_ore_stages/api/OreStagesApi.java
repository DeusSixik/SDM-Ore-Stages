package dev.behindthescenery.sdm_ore_stages.api;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import dev.behindthescenery.sdm_ore_stages.data.OreStagesContainer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.orestages.OreStages")
public class OreStagesApi {

    @ZenCodeType.Method
    public static void addOreStage(String stage, Block block) {
        addOreStage(stage, block.defaultBlockState());
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, Block block, Block replacedBlock) {
        addOreStage(stage, block.defaultBlockState(), replacedBlock.defaultBlockState());
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, BlockState block) {
        if(BuiltInRegistries.BLOCK.getKey(block.getBlock()).getPath().contains("deep_slate"))
            addOreStage(stage, block, Blocks.DEEPSLATE.defaultBlockState());
        else addOreStage(stage, block, Blocks.STONE.defaultBlockState());
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, BlockState block, BlockState replacedBlock) {
        OreStagesContainer.Instance.register(stage, block, replacedBlock);
    }
}
