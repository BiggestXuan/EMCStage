package biggestxuan.EMCStage.event;

import biggestxuan.EMCStage.EMCStage;
import biggestxuan.EMCStage.recipes.EMCStageRecipe;
import biggestxuan.EMCStage.utils.StageUtils;
import moze_intel.projecte.api.event.PlayerAttemptCondenserSetEvent;
import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCStage.MODID,bus= Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvent {
    @SubscribeEvent
    public static void playerTryToGetKnowledgeEvent(PlayerAttemptLearnEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide) return;
        for(EMCStageRecipe recipe:player.level.getRecipeManager().getAllRecipesFor(EMCStageRecipe.EMCStageLimitType.INSTANCE)){
            if(recipe.getItem().equals(event.getSourceInfo().getItem()) && StageUtils.playerStage(recipe,player).size() != 0){
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public static void playerSetCondenserEvent(PlayerAttemptCondenserSetEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide) return;
        for(EMCStageRecipe recipe:player.level.getRecipeManager().getAllRecipesFor(EMCStageRecipe.EMCStageLimitType.INSTANCE)){
            if(recipe.getItem().equals(event.getSourceInfo().getItem()) && StageUtils.playerStage(recipe,player).size() != 0){
                event.setCanceled(true);
            }
        }
    }
}
