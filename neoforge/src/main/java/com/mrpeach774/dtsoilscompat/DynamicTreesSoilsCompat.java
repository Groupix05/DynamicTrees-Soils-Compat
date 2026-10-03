package com.mrpeach774.dtsoilscompat;

import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.registry.NeoForgeRegistryHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.minecraft.resources.ResourceLocation;

@Mod(Constants.MOD_ID)
public final class DynamicTreesSoilsCompat {
  public DynamicTreesSoilsCompat(IEventBus eventBus) {
    eventBus.addListener(this::gatherData);
    NeoForgeRegistryHandler.setup(Constants.MOD_ID, eventBus);
  }

  private void gatherData(final GatherDataEvent event) {
    GatherDataHelper.gatherAllData(Constants.MOD_ID, event,
        SoilProperties.REGISTRY
    // Family.REGISTRY,
    // Species.REGISTRY,
    // LeavesProperties.REGISTRY
    );
  }
}
