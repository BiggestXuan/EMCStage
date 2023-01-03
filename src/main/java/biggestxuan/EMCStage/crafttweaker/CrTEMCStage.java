package biggestxuan.EMCStage.crafttweaker;

import biggestxuan.EMCStage.EMCStage;
import biggestxuan.EMCStage.recipes.EMCStageRecipe;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ZenRegister
@ZenCodeType.Name("mods.EMCStage.recipe")
public class CrTEMCStage implements IRecipeManager {
    @ZenCodeType.Method
    public void add(IItemStack stack,String[] stage,boolean require){
        ResourceLocation rl = stack.getImmutableInternal().getItem().getRegistryName();
        if(rl != null){
            String name = rl.getNamespace()+"_"+rl.getPath();
            add(name,stack,stage,require);
        }else CraftTweakerAPI.logWarning("This item hasn't registry name: "+stack.getDisplayName());
    }

    //@ZenCodeType.Method
    public void add(String name,IItemStack stack,String[] stage,boolean require){
        Set<String> stages = new HashSet<>(Arrays.asList(stage));
        EMCStageRecipe recipe = new EMCStageRecipe(EMCStage.rl(name),stack.getImmutableInternal().getItem(),stages,require);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<EMCStageRecipe> getRecipeType() {
        return EMCStageRecipe.EMCStageLimitType.INSTANCE;
    }
}
