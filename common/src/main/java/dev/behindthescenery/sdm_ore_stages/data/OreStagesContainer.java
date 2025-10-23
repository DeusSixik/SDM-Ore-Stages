package dev.behindthescenery.sdm_ore_stages.data;

import dev.behindthescenery.sdm_ore_stages.OreStagesEvents;
import dev.behindthescenery.sdm_ore_stages.utils.BlockHelper;
import dev.behindthescenery.sdmstages.data.containers.Stage;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class OreStagesContainer extends SimplePreparableReloadListener<Void> {

    public static OreStagesContainer Instance = new OreStagesContainer();

    protected Object2ObjectMap<Integer, BaseOreStage> StagesData = new Object2ObjectArrayMap<>();

    public void register(String stage, BlockState original, BlockState newBlock) {
        register(original, new BaseOreStage(new BaseOreStage.Entry(original, BlockHelper.getBlockId(original)), new BaseOreStage.Entry(newBlock, BlockHelper.getBlockId(newBlock)), stage));
    }

    public void register(BlockState state, BaseOreStage stage) {
        register(BlockHelper.getBlockId(state), stage);
    }

    public void register(int originalBlock, BaseOreStage stage) {
        StagesData.computeIfAbsent(originalBlock, s -> stage);
    }

    @Nullable
    public BaseOreStage getStage(int originalBlock) {
        return StagesData.get(originalBlock);
    }

    public int getReplacedBlock(int originalBlock, ServerPlayer player) {
        if(StagesData.isEmpty()) return originalBlock;

        BaseOreStage stage = StagesData.get(originalBlock);
        if(stage == null) return originalBlock;

        if(OreStagesEvents.isGlobal()) {
            return getReplacedBlock(originalBlock, OreStagesEvents.getServerStageContainer().getStage(null), stage);
        }

        if(player == null) return originalBlock;

        final Stage player_stage = OreStagesEvents.getServerStageContainer().getStage(player);
        return getReplacedBlock(originalBlock, player_stage, stage);
    }

    @Nullable
    public BaseOreStage getReplacedBlockStage(int originalBlock, ServerPlayer player) {
        if(StagesData.isEmpty()) return null;

        BaseOreStage stage = StagesData.get(originalBlock);
        if(stage == null) return null;

        if(OreStagesEvents.isGlobal()) {
            return getReplacedBlockStage(originalBlock, OreStagesEvents.getServerStageContainer().getStage(null), stage);
        }

        if(player == null) return null;

        final Stage player_stage = OreStagesEvents.getServerStageContainer().getStage(player);
        return getReplacedBlockStage(originalBlock, player_stage, stage);
    }

    protected int getReplacedBlock(int original, Stage stage, BaseOreStage oreStage) {
        return stage.contains(oreStage.stage()) ? original : oreStage.replace().id();
    }

    @Nullable
    protected BaseOreStage getReplacedBlockStage(int original, Stage stage, BaseOreStage oreStage) {
        return stage.contains(oreStage.stage()) ? null : oreStage;
    }

    @Override
    protected Void prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        StagesData.clear();
        return null;
    }

    @Override
    protected void apply(Void object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

    }
}
