package biggestxuan.EMCStage.recipes;

import biggestxuan.EMCStage.EMCStage;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class EMCRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EMCStage.MODID);

    public static final RegistryObject<IRecipeSerializer<?>> EMC_STAGE_LIMIT_RECIPE = RECIPES.register("stage_limit",() -> EMCStageRecipe.EMCStageLimitSerializer.serializer);

    public static <T extends IRecipe<?>> IRecipeType<T> register(String p_222147_0_) {
        return Registry.register(Registry.RECIPE_TYPE, EMCStage.rl(p_222147_0_), new IRecipeType<T>() {
            public String toString() {
                return p_222147_0_;
            }
        });
    }
}
