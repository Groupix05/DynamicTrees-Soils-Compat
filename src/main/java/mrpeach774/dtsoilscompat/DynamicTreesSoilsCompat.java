package mrpeach774.dtsoilscompat;

import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.MixinEnvironment;
import net.minecraft.resources.ResourceLocation;

@Mod(DynamicTreesSoilsCompat.MOD_ID)
public final class DynamicTreesSoilsCompat {

    public static final String MOD_ID = "dtsoilscompat";

    public DynamicTreesSoilsCompat() {
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
        RegistryHandler.setup(MOD_ID);
    }

    public static ResourceLocation location (String name){
        return new ResourceLocation(MOD_ID, name);
    }
}
