package com.mrpeach774.dtsoilscompat;

import com.dtteam.dynamictrees.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class DTSoilsCompat implements ModInitializer {

  @Override
  public void onInitialize() {
  }

  public static ResourceLocation location(final String path) {
    return ResourceLocation.tryBuild(Constants.MOD_ID, path);
  }
}
