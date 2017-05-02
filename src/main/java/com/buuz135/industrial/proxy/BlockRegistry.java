package com.buuz135.industrial.proxy;

import com.buuz135.industrial.fluid.IFCustomFluidBlock;
import com.buuz135.industrial.tile.animal.MobSlaughterFactoryTile;
import com.buuz135.industrial.tile.block.*;
import net.minecraft.block.material.Material;

public class BlockRegistry {

    public static PetrifiedFuelGeneratorBlock petrifiedFuelGeneratorBlock;
    public static EnchantmentRefinerBlock enchantmentRefinerBlock;
    public static EnchantmentExtractorBlock enchantmentExtractorBlock;
    public static EnchantmentAplicatorBlock enchantmentAplicatorBlock;
    public static MobRelocatorBlock mobRelocatorBlock;
    public static PotionEnervatorBlock potionEnervatorBlock;
    public static AnimalIndependenceSelectorBlock animalIndependenceSelectorBlock;
    public static AnimalStockIncreaserBlock animalStockIncreaserBlock;
    public static CropSowerBlock cropSowerBlock;
    public static CropEnrichMaterialInjectorBlock cropEnrichMaterialInjectorBlock;
    public static CropRecolectorBlock cropRecolectorBlock;
    public static BlackHoleUnitBlock blackHoleUnitBlock;
    public static WaterCondensatorBlock waterCondensatorBlock;
    public static WaterResourcesCollectorBlock waterResourcesCollectorBlock;
    public static AnimalResourceHarvesterBlock animalResourceHarvesterBlock;
    public static MobSlaughterFactoryBlock mobSlaughterFactoryBlock;

    public static IFCustomFluidBlock BLOCK_XP;
    public static IFCustomFluidBlock BLOCK_MILK;
    public static IFCustomFluidBlock BLOCK_MEAT;

    public static void registerBlocks() {
        (petrifiedFuelGeneratorBlock = new PetrifiedFuelGeneratorBlock()).register();
        (enchantmentRefinerBlock = new EnchantmentRefinerBlock()).register();
        (enchantmentExtractorBlock = new EnchantmentExtractorBlock()).register();
        (enchantmentAplicatorBlock = new EnchantmentAplicatorBlock()).register();
        (mobRelocatorBlock = new MobRelocatorBlock()).register();
        (potionEnervatorBlock = new PotionEnervatorBlock()).register();
        (animalIndependenceSelectorBlock = new AnimalIndependenceSelectorBlock()).register();
        (animalStockIncreaserBlock = new AnimalStockIncreaserBlock()).register();
        (cropSowerBlock = new CropSowerBlock()).register();
        (cropEnrichMaterialInjectorBlock = new CropEnrichMaterialInjectorBlock()).register();
        (cropRecolectorBlock = new CropRecolectorBlock()).register();
        (blackHoleUnitBlock = new BlackHoleUnitBlock()).register();
        (waterCondensatorBlock = new WaterCondensatorBlock()).register();
        (waterResourcesCollectorBlock = new WaterResourcesCollectorBlock()).register();
        (animalResourceHarvesterBlock = new AnimalResourceHarvesterBlock()).register();
        (mobSlaughterFactoryBlock = new MobSlaughterFactoryBlock()).register();

        (BLOCK_XP = new IFCustomFluidBlock(FluidsRegistry.XP, Material.WATER)).register();
        (BLOCK_MILK = new IFCustomFluidBlock(FluidsRegistry.MILK, Material.WATER)).register();
        (BLOCK_MEAT = new IFCustomFluidBlock(FluidsRegistry.MEAT, Material.WATER)).register();
    }
}
