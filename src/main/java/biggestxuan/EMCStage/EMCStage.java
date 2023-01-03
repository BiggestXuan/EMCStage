package biggestxuan.EMCStage;

import biggestxuan.EMCStage.recipes.EMCRecipeTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EMCStage.MODID)
public class EMCStage {
    public static final String MODID = "emc_stage";
    private static final String prefix = "tooltip."+MODID+".";

    public EMCStage() {
        EMCRecipeTypes.RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation rl(String value){
        return new ResourceLocation(MODID,value);
    }

    public static TranslationTextComponent tc(String id){
        return new TranslationTextComponent(prefix+id);
    }

    public static TranslationTextComponent tc(String id,Object... name){
        return new TranslationTextComponent(prefix+id,name);
    }
}
