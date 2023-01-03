package biggestxuan.EMCStage.utils;

import biggestxuan.EMCStage.recipes.EMCStageRecipe;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashSet;
import java.util.Set;

public class StageUtils {
    public static Set<String> playerStage(EMCStageRecipe recipe, PlayerEntity player){
        Set<String> return_stages = new HashSet<>();
        Set<String> recipe_stages = recipe.getStage();
        if(recipe.isRequire()){
            recipe_stages.forEach((stage)->{
                if(!GameStageHelper.hasStage(player,stage)){
                    return_stages.add(stage);
                }
            });
            return return_stages;
        }else{
            for(String s : recipe_stages){
                if(GameStageHelper.hasStage(player,s)){
                    return return_stages;
                }
            }
            return recipe_stages;
        }
    }
}
