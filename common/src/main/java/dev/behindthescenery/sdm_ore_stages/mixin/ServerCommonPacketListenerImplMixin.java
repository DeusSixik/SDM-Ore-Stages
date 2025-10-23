package dev.behindthescenery.sdm_ore_stages.mixin;

import dev.behindthescenery.sdm_ore_stages.OreStageUtils;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerCommonPacketListenerImpl.class)
public class ServerCommonPacketListenerImplMixin {

    @Inject(method = "send(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketSendListener;)V", at = @At("HEAD"))
    public void bts$send(Packet<?> packet, PacketSendListener packetSendListener, CallbackInfo ci) {
        if(((Object)this) instanceof ServerGamePacketListenerImpl packetListener) {
            OreStageUtils.setCurrentSendPlayer(packetListener.player);
        }
    }
}
