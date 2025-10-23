package dev.behindthescenery.sdm_ore_stages.data;

import net.minecraft.world.level.block.state.BlockState;

public record BaseOreStage(Entry block, Entry replace, String stage) {


    public record Entry(BlockState block, int id) {}
}
