package com.mrpeach774.dtsoilscompat;

import com.dtteam.dynamictrees.registry.*;
import com.dtteam.dynamictrees.api.*;
import com.dtteam.dynamictrees.api.resource.loading.StagedApplierResourceLoader;
import com.dtteam.dynamictrees.api.resource.loading.preparation.JsonRegistryResourceLoader;
import com.dtteam.dynamictrees.deserialization.PropertyAppliers;

import net.fabricmc.api.*;

public class DTEntryPoint implements DynamicTreesAddonEntrypoint {
  @Override
  public void onDynamicTreesPreSetup() {
    FabricRegistryHandler.setup(Constants.MOD_ID);
  }
}
