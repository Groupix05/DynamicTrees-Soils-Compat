package mrpeach774.dtsoilscompat.mixins;

import lilypuree.hyle.world.feature.gen.StoneType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import com.ferreusveritas.dynamictrees.data.DTBlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StoneType.class)
public abstract class StoneTypeMixin {

    @Shadow(remap = false)
    private BlockState grassReplace;

    @Inject(remap = false, at = @At("HEAD"), method = "replace", cancellable = true)
    public void onReplace(BlockState original, CallbackInfoReturnable<BlockState> cir) {
        if (original.is(DTBlockTags.ROOTY_SOIL)) {
            if (grassReplace != null) {
                ResourceLocation grassReplaceName = BuiltInRegistries.BLOCK.getKey(grassReplace.getBlock());
                String rootyBlockName = "rooty_" + grassReplaceName.getPath();
                Block rootyBlock = BuiltInRegistries.BLOCK.get(new ResourceLocation("dtsoilscompat", rootyBlockName));
                if (rootyBlock != null) {
                    cir.setReturnValue(rootyBlock.defaultBlockState());
                    cir.cancel();
                }
            }
        }
    }
}