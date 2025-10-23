package dev.behindthescenery.sdm_ore_stages.mixin;

import dev.behindthescenery.sdm_ore_stages.OreStageUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.PlayerChunkSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerChunkSender.class)
public class PlayerChunkSenderMixin {

    @Inject(method = "sendNextChunks", at = @At("HEAD"))
    public void bts$sendChunk$pre(ServerPlayer serverPlayer, CallbackInfo ci) {
        OreStageUtils.setCurrentSendPlayer(serverPlayer);
    }

    @Inject(method = "sendNextChunks", at = @At("RETURN"))
    public void bts$sendChunk$post(ServerPlayer serverPlayer, CallbackInfo ci) {
        OreStageUtils.setCurrentSendPlayer(null);
    }
}
