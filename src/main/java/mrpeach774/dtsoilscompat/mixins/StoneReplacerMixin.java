package mrpeach774.dtsoilscompat.mixins;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.data.DTBlockTags;
import com.llamalad7.mixinextras.sugar.Local;
import lilypuree.hyle.world.feature.StoneReplacer;
import lilypuree.hyle.world.feature.gen.StoneType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Objects;

@Mixin(StoneReplacer.class)
public abstract class StoneReplacerMixin {
    @Inject(remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/LevelChunkSection;getBlockState(III)Lnet/minecraft/world/level/block/state/BlockState;", shift = At.Shift.AFTER), method = "replaceAll", locals = LocalCapture.CAPTURE_FAILHARD)
    private void onSetBlockState(
            ChunkAccess chunkAccess, int minY, int[][] heights, StoneType[][][] results,
            CallbackInfo ci,
            LevelChunkSection chunkSection, int x, int z, int height, int posY)
    {
        BlockState block = chunkSection.getBlockState(x, posY & 15, z);
        BlockState replaced = results[x][z][posY - minY].replace(block);
        if (Objects.equals(ForgeRegistries.BLOCKS.getKey(block.getBlock()), DynamicTrees.location("rooty_grass_block"))) {
            BlockPos pos = new BlockPos(chunkAccess.getPos().getMinBlockX() + x,posY, chunkAccess.getPos().getMinBlockZ() + z);
            CompoundTag tileData = chunkAccess.getBlockEntityNbtForSaving(pos);
            if (block != replaced) {
                BlockEntity newEntity = null;
                if (tileData != null){
                    newEntity = BlockEntity.loadStatic(pos, replaced, tileData);
                }
                chunkSection.setBlockState(x, posY & 15, z, replaced, false);
                System.out.println("[DTSoilsCompat] TileEntity : " + tileData);
                if (newEntity != null) {
                    chunkAccess.setBlockEntity(newEntity);
                    newEntity.setChanged();
                    System.out.println("[DTSoilsCompat] Created new TileEntity at " + pos);
                }
            }
        } else {
            if (block != replaced) {
                chunkSection.setBlockState(x, posY & 15, z, replaced, false);
            }
        }
    }
}