package dev.behindthescenery.sdm_ore_stages;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.behindthescenery.sdmstages.events.StagesEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public final class SdmOreStages {
    public static final String MOD_ID = "sdm_orestages";

    public static void init() {


        LifecycleEvent.SERVER_STARTED.register(OreStagesEvents::onServerStarted);
        PlayerEvent.DROP_ITEM.register(SdmOreStages::onDrop);

        StagesEvents.ON_STAGE_ADD.register(OreStagesEvents::onStageAdd);
        StagesEvents.ON_STAGE_REMOVE.register(OreStagesEvents::onStageRemove);
    }

    public static boolean b1 = false;

    private static EventResult onDrop(Player player, ItemEntity itemEntity) {
        if(player.level().isClientSide) return  EventResult.interruptDefault();

        OreStageUtils.sendData((ServerPlayer) player);

        b1 = !b1;

        return EventResult.interruptDefault();
    }
}
