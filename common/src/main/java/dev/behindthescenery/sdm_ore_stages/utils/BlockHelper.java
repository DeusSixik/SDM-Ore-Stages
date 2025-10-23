package dev.behindthescenery.sdm_ore_stages.utils;

import dev.behindthescenery.sdm_ore_stages.OreStageUtils;
import dev.behindthescenery.sdm_ore_stages.data.BaseOreStage;
import dev.behindthescenery.sdm_ore_stages.data.OreStagesContainer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockHelper {

    public static int getReplacedBlock(int originalBlock) {
        return getReplacedBlock(originalBlock, OreStageUtils.getCurrentSendPlayer());
    }

    public static int getReplacedBlock(int originalBlock, ServerPlayer player) {
        return OreStagesContainer.Instance.getReplacedBlock(originalBlock, player);
    }

    public static BaseOreStage getReplacedStage(int originalBlock) {
        return getReplacedStage(originalBlock, OreStageUtils.getCurrentSendPlayer());
    }

    public static BaseOreStage getReplacedStage(int originalBlock, ServerPlayer player) {
        return OreStagesContainer.Instance.getReplacedBlockStage(originalBlock, player);
    }

    public static int getBlockId(BlockState blockState) {
        return Block.BLOCK_STATE_REGISTRY.getId(blockState);
    }
}
