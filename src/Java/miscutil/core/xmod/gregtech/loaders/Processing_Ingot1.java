package miscutil.core.xmod.gregtech.loaders;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import miscutil.core.util.Utils;
import miscutil.core.xmod.gregtech.api.enums.GregtechOrePrefixes;
import miscutil.core.xmod.gregtech.api.enums.GregtechOrePrefixes.GT_Materials;
import miscutil.core.xmod.gregtech.api.interfaces.internal.Interface_OreRecipeRegistrator;
import miscutil.core.xmod.gregtech.api.util.GregtechRecipeRegistrator;
import net.minecraft.item.ItemStack;

public class Processing_Ingot1 implements Interface_OreRecipeRegistrator {
    public Processing_Ingot1() {
        GregtechOrePrefixes.ingot.add(this);
    }

    @Override
	public void registerOre(GregtechOrePrefixes aPrefix, GT_Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
    	Utils.LOG_INFO("Processing Ingots");
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(GT_Utility.copyAmount(1L, new Object[]{aStack}), null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        GT_Values.RA.addBoxingRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), ItemList.Crate_Empty.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.crateGtIngot, aMaterial, 1L), 100, 8);
        GT_Values.RA.addUnboxingRecipe(GT_OreDictUnificator.get(OrePrefixes.crateGtIngot, aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 16L), ItemList.Crate_Empty.get(1L, new Object[0]), 800, 1);

        if (aMaterial.mStandardMoltenFluid != null)
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ingot.get(0L, new Object[0]), aMaterial.getMolten(144L), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L), 32, 8);
        GregtechRecipeRegistrator.registerReverseFluidSmelting(aStack, aMaterial, aPrefix.mMaterialAmount, null);
        GregtechRecipeRegistrator.registerReverseMacerating(aStack, aMaterial, aPrefix.mMaterialAmount, null, null, null, false);
        if (aMaterial.mSmeltInto.mArcSmeltInto != aMaterial) {
            GregtechRecipeRegistrator.registerReverseArcSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), aMaterial, aPrefix.mMaterialAmount, null, null, null);
        }
        if (!aMaterial.contains(SubTag.NO_SMASHING)) {
            GT_Values.RA.addWiremillRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Utility.copy(new Object[]{GT_OreDictUnificator.get(OrePrefixes.wireGt01, aMaterial, 2L), GT_OreDictUnificator.get(OrePrefixes.wireFine, aMaterial, 8L)}), 100, 4);
            GT_Values.RA.addForgeHammerRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L), (int) Math.max(aMaterial.getMass(), 1L), 16);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L), (int) Math.max(aMaterial.getMass() * 1L, 1L), 24);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateDouble, aMaterial, 1L), (int) Math.max(aMaterial.getMass() * 2L, 1L), 96);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateTriple, aMaterial, 1L), (int) Math.max(aMaterial.getMass() * 3L, 1L), 96);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateQuadruple, aMaterial, 1L), (int) Math.max(aMaterial.getMass() * 4L, 1L), 96);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(5L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateQuintuple, aMaterial, 1L), (int) Math.max(aMaterial.getMass() * 5L, 1L), 96);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1L), (int) Math.max(aMaterial.getMass() * 9L, 1L), 96);
        }


        if (!GregtechOrePrefixes.block.isIgnored(aMaterial))
            GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L));
        if (!aMaterial.contains(SubTag.NO_WORKING))
            GT_Values.RA.addLatheRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial.mMacerateInto, 2L), (int) Math.max(aMaterial.getMass() * 5L, 1L), 16);
        if (!aMaterial.contains(SubTag.NO_SMELTING)) {
            GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), ItemList.Shape_Mold_Nugget.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 9L), 100, 1);
            if ((GT_ModHandler.getSmeltingOutput(aStack, false, null) == null) && (GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial.mSmeltInto, 1L) != null) && (!GT_ModHandler.addSmeltingRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial.mSmeltInto, 9L))))
                GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial.mSmeltInto, 9L), new Object[]{aOreDictName});
        }
        ItemStack tStack;
        if ((null != (tStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L))) && (
                (aMaterial.mBlastFurnaceRequired) || (aMaterial.contains(SubTag.NO_SMELTING)))) {
            GT_ModHandler.removeFurnaceSmelting(tStack);
        }

        GregtechRecipeRegistrator.registerUsagesForGT_Materials(GT_Utility.copyAmount(1L, new Object[]{aStack}), OrePrefixes.plate.get(aMaterial).toString(), !aMaterial.contains(SubTag.NO_SMASHING));

    }
}