package dev.behindthescenery.sdm_ore_stages.mixin;

import dev.behindthescenery.sdm_ore_stages.utils.BlockHelper;
import net.minecraft.core.IdMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.VarInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LinearPalette;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LinearPalette.class)
public abstract class MixinLinearPalette<T> {
    @Shadow
    @Final
    private T[] values;
    @Shadow
    private int size;

    @Shadow
    @Final
    private IdMap<T> registry;

    @Shadow
    public abstract int getSize();

    @Inject(method = "write", at = @At("HEAD"), cancellable = true)
    public void bts$onWrite(FriendlyByteBuf friendlyByteBuf, CallbackInfo ci) {
        if(this.values.length == 0 || !(this.values[0] instanceof BlockState)) return;

        friendlyByteBuf.writeVarInt(this.size);

        for (int i = 0; i < this.size; ++i) {
            friendlyByteBuf.writeVarInt(BlockHelper.getReplacedBlock(this.registry.getId(this.values[i])));
        }

        ci.cancel();
    }


    @Inject(method = "getSerializedSize", at = @At("HEAD"), cancellable = true)
    public void bts$getSerializedSize(CallbackInfoReturnable<Integer> cir) {
        if(this.values.length == 0 || !(this.values[0] instanceof BlockState)) return;
        int i = VarInt.getByteSize(this.getSize());
        for (int j = 0; j < this.getSize(); ++j) {
            i += VarInt.getByteSize(BlockHelper.getReplacedBlock(this.registry.getId(this.values[j])));
        }
        cir.setReturnValue(i);
    }
}
