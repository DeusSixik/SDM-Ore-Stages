package dev.behindthescenery.sdm_ore_stages;

import dev.behindthescenery.sdmstages.StageApi;
import dev.behindthescenery.sdmstages.data.StageContainer;
import dev.behindthescenery.sdmstages.data.StageContainerType;
import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class OreStagesEvents {

    private static StageContainer ServerStageContainer;
    private static boolean isGlobal = false;
    private static MinecraftServer server;

    public static void onServerStarted(MinecraftServer _server) {
        server = _server;
        ServerStageContainer = StageApi.getServerStage();
        isGlobal = ServerStageContainer.getContainerType() == StageContainerType.GLOBAL;
    }

    public static MinecraftServer getServer() {
        return server;
    }

    public static boolean isGlobal() {
        return isGlobal;
    }

    public static StageContainer getServerStageContainer() {
        return ServerStageContainer;
    }

    public static void onStageAdd(String stage, Stage stageData, StageContainer container, @Nullable Object owner) {
        if(isGlobal()) {
            onGlobal();
            return;
        }
        onLocal(owner);
    }

    public static void onStageRemove(String stage, Stage stageData, StageContainer container, @Nullable Object owner) {
        if(isGlobal()) {
            onGlobal();
            return;
        }
        onLocal(owner);
    }

    protected static void onLocal(@Nullable Object owner) {
        if(owner instanceof ServerPlayer player) {
            OreStageUtils.sendData(player);
        } else if(owner instanceof UUID uuid) {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if(player.getGameProfile().getId().equals(uuid)) {
                    OreStageUtils.sendData(player);
                    return;
                }
            }
        }
    }

    protected static void onGlobal() {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            OreStageUtils.sendData(player);
        }
        return;
    }
}
