package dev.behindthescenery.sdm_ore_stages;

import dev.behindthescenery.sdm_ore_stages.path.ChunkMapPath;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OreStageUtils {

    private static ServerPlayer currentSendPlayer = null;

    public static void sendData(ServerPlayer serverPlayer) {
        final ServerLevel level = serverPlayer.serverLevel();

        currentSendPlayer = serverPlayer;
        try {
            final List<LevelChunk> chunks = getChunks(serverPlayer, level);
            for (LevelChunk chunk : chunks) {
                final ClientboundLevelChunkWithLightPacket packet = new ClientboundLevelChunkWithLightPacket(chunk, level.getLightEngine(), null, null);
                serverPlayer.connection.send(packet);
            }
        } finally {
            currentSendPlayer = null;
        }
    }

    protected static List<LevelChunk> getChunks(ServerPlayer player, ServerLevel level) {
        final int view = ((ChunkMapPath)level.getChunkSource().chunkMap).sdm$getPlayerViewDistance(player);
        final ChunkSource chunkSource = level.getChunkSource();
        final ChunkPos chunkPos = player.chunkPosition();

        final List<LevelChunk> chunks = new ArrayList<>();
        for (int dx = -view; dx < view; dx++) {
            for (int dz = -view; dz < view; dz++) {
                LevelChunk chunk = chunkSource.getChunkNow(chunkPos.x + dx, chunkPos.z + dz);
                if(chunk != null) chunks.add(chunk);
            }
        }
        return chunks;
    }

    @Nullable
    public static ServerPlayer getCurrentSendPlayer() {
        return currentSendPlayer;
    }

    public static void setCurrentSendPlayer(ServerPlayer currentSendPlayer) {
        OreStageUtils.currentSendPlayer = currentSendPlayer;
    }
}
