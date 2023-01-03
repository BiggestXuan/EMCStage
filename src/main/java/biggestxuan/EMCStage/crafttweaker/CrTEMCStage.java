package biggestxuan.EMCStage.crafttweaker;

import biggestxuan.EMCStage.EMCStage;
import biggestxuan.EMCStage.recipes.EMCStageRecipe;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.IRecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.EMCStage.recipe")
public class CrTEMCStage implements IRecipeManager {
    @ZenCodeType.Method
    public void add(IItemStack stack,String stage){
        String name = stack.getImmutableInternal().getItem().toString();
        add(name,stack,stage);
    }

    @ZenCodeType.Method
    public void add(String name,IItemStack stack,String stage){
        EMCStageRecipe recipe = new EMCStageRecipe(EMCStage.rl(name),stack.getImmutableInternal().getItem(),stage);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<EMCStageRecipe> getRecipeType() {
        return EMCStageRecipe.EMCStageLimitType.INSTANCE;
    }
}
