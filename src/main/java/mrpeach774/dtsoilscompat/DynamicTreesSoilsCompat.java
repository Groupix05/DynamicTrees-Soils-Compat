package mrpeach774.dtsoilscompat;

import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(DynamicTreesSoilsCompat.MOD_ID)
public final class DynamicTreesSoilsCompat {

    public static final String MOD_ID = "dtsoilscompat";

    public DynamicTreesSoilsCompat() {
        RegistryHandler.setup(MOD_ID);
    }
}
