package dev.behindthescenery.sdm_ore_stages.mixin;

import dev.behindthescenery.sdm_ore_stages.utils.BlockHelper;
import net.minecraft.core.IdMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.VarInt;
import net.minecraft.util.CrudeIncrementalIntIdentityHashBiMap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.HashMapPalette;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HashMapPalette.class)
public abstract class MixinHashMapPalette<T> {

    @Shadow
    @Final
    private CrudeIncrementalIntIdentityHashBiMap<T> values;


    @Shadow public abstract int getSize();

    @Shadow
    @Final
    private IdMap<T> registry;

    @Inject(method = "write", at = @At("HEAD"), cancellable = true)
    public void bts$onWrite(FriendlyByteBuf friendlyByteBuf, CallbackInfo ci) {
        if(this.values.size() == 0 || !(values.byId(0) instanceof BlockState)) return;

        final int i = this.getSize();
        friendlyByteBuf.writeVarInt(i);

        for (int j = 0; j < i; ++j) {
            friendlyByteBuf.writeVarInt(BlockHelper.getReplacedBlock(this.registry.getId(this.values.byId(j))));
        }

        ci.cancel();
    }

    @Inject(method = "getSerializedSize", at = @At("HEAD"), cancellable = true)
    public void bts$getSerializedSize(CallbackInfoReturnable<Integer> cir) {
        if(this.values.size() == 0 || !(values.byId(0) instanceof BlockState)) return;

        int i = VarInt.getByteSize(this.getSize());
        for (int j = 0; j < this.getSize(); ++j) {
            i += VarInt.getByteSize(BlockHelper.getReplacedBlock(this.registry.getId(this.values.byId(j))));
        }
        cir.setReturnValue(i);
    }
}
