package dev.behindthescenery.sdm_ore_stages.mixin;

import dev.behindthescenery.sdm_ore_stages.utils.BlockHelper;
import net.minecraft.core.IdMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.VarInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.SingleValuePalette;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SingleValuePalette.class)
public class MixinSingleValuePalette<T> {


    @Shadow
    @Nullable
    private T value;

    @Shadow
    @Final
    private IdMap<T> registry;

    @Inject(method = "write", at = @At("HEAD"), cancellable = true)
    public void bts$onWrite(FriendlyByteBuf friendlyByteBuf, CallbackInfo ci) {
        if(this.value == null || !(this.value instanceof BlockState)) return;

        friendlyByteBuf.writeVarInt(BlockHelper.getReplacedBlock(this.registry.getId(this.value)));

        ci.cancel();
    }

    @Inject(method = "getSerializedSize", at = @At("HEAD"), cancellable = true)
    public void bts$getSerializedSize(CallbackInfoReturnable<Integer> cir) {
        if(!(this.value instanceof BlockState)) return;
        cir.setReturnValue(VarInt.getByteSize(BlockHelper.getReplacedBlock(this.registry.getId(this.value))));
    }
}
