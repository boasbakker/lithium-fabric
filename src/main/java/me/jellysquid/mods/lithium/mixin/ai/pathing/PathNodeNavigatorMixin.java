package me.jellysquid.mods.lithium.mixin.ai.pathing;

import me.jellysquid.mods.lithium.common.ai.pathing.PathNodeCache;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.TargetPathNode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(PathNodeNavigator.class)
public class PathNodeNavigatorMixin {
    @Inject(method = "findPathToAny(Lnet/minecraft/util/profiler/Profiler;Lnet/minecraft/entity/ai/pathing/PathNode;Ljava/util/Map;FIF)Lnet/minecraft/entity/ai/pathing/Path;", at = @At("HEAD"))
    private void preFindPathToAny(Profiler profiler, PathNode pathNode, Map<TargetPathNode, BlockPos> map, float f, int i, float g, CallbackInfoReturnable<Path> cir) {
        PathNodeCache.enableChunkCache();
    }

    @Inject(method = "findPathToAny(Lnet/minecraft/util/profiler/Profiler;Lnet/minecraft/entity/ai/pathing/PathNode;Ljava/util/Map;FIF)Lnet/minecraft/entity/ai/pathing/Path;", at = @At("RETURN"))
    private void postFindPathToAny(Profiler profiler, PathNode pathNode, Map<TargetPathNode, BlockPos> map, float f, int i, float g, CallbackInfoReturnable<Path> cir) {
        PathNodeCache.disableChunkCache();
    }
}
