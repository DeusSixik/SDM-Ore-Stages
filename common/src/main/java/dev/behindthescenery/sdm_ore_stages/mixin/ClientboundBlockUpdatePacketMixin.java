package dev.behindthescenery.sdm_ore_stages.mixin;

import dev.behindthescenery.sdm_ore_stages.data.BaseOreStage;
import dev.behindthescenery.sdm_ore_stages.utils.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientboundBlockUpdatePacket.class)
public class ClientboundBlockUpdatePacketMixin {

    @Mutable
    @Shadow
    @Final
    private BlockState blockState;

    @Inject(method = "<init>(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V", at = @At("RETURN"))
    public void bts$init(BlockPos blockPos, BlockState blockState, CallbackInfo ci) {
        final BaseOreStage stage = BlockHelper.getReplacedStage(BlockHelper.getBlockId(blockState));
        if(stage == null) return;
        this.blockState = stage.replace().block();
    }
}
