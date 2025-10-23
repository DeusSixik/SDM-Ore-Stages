package dev.behindthescenery.sdm_ore_stages.mixin;

import dev.behindthescenery.sdm_ore_stages.path.ChunkMapPath;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChunkMap.class)
public abstract class ChunkMapMixin implements ChunkMapPath {
    @Shadow
    abstract int getPlayerViewDistance(ServerPlayer arg);

    @Override
    public int sdm$getPlayerViewDistance(ServerPlayer player) {
        return getPlayerViewDistance(player);
    }
}
