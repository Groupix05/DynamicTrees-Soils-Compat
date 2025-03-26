package mrpeach774.dtsoilscompat.mixins;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import lilypuree.hyle.core.HyleTags;
import lilypuree.hyle.world.feature.gen.StoneType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import com.ferreusveritas.dynamictrees.data.DTBlockTags;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import mrpeach774.dtsoilscompat.DynamicTreesSoilsCompat;

import java.util.Objects;

@Mixin(StoneType.class)
public abstract class StoneTypeMixin {

    @Shadow(remap = false)
    private BlockState grassReplace;

    @Inject(remap = false, at = @At("HEAD"), method = "replace", cancellable = true)
    public void onReplace(BlockState original, CallbackInfoReturnable<BlockState> cir) {
        if (Objects.equals(ForgeRegistries.BLOCKS.getKey(original.getBlock()), DynamicTrees.location("rooty_grass_block")))
        {
            ResourceLocation grassReplaceName = ForgeRegistries.BLOCKS.getKey(grassReplace.getBlock());
            assert grassReplaceName != null;
            String rootyBlockName = "rooty_" + grassReplaceName.getPath();
            Block rootyBlock = ForgeRegistries.BLOCKS.getValue(DynamicTreesSoilsCompat.location(rootyBlockName));
            if (rootyBlock == null || rootyBlock.defaultBlockState().isAir()){
                rootyBlock=null;
            }
            if(rootyBlock!=null){
                cir.setReturnValue(rootyBlock.defaultBlockState());
                cir.cancel();
            }
        }
    }
}