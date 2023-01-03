package biggestxuan.EMCStage.event;

import biggestxuan.EMCStage.EMCStage;
import biggestxuan.EMCStage.recipes.EMCStageRecipe;
import biggestxuan.EMCStage.utils.FormatterUtils;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.utils.EMCHelper;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = EMCStage.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = {Dist.CLIENT}
)
public class ToolTipEvent {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void tooltipEvent(ItemTooltipEvent event){
        if(!ProjectEConfig.client.emcToolTips.get()) return;
        ItemStack stack = event.getItemStack();
        if(event.getPlayer() == null || stack.equals(ItemStack.EMPTY) || Minecraft.getInstance().level == null) return;
        long value;
        String stage = "";
        long sellValue = EMCHelper.getEmcSellValue(stack);
        value = EMCHelper.getEmcValue(stack);
        boolean isTrans = true;
        if(value > 0L){
            RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
            for(EMCStageRecipe recipe:manager.getAllRecipesFor(EMCStageRecipe.EMCStageLimitType.INSTANCE)){
                if(recipe.getItem().equals(stack.getItem())){
                    if(GameStageHelper.hasStage(event.getPlayer(),recipe.getStage())){
                        break;
                    }
                    isTrans = false;
                    stage = recipe.getStage();
                }
            }
            IFormattableTextComponent normal = EMCStage.tc("emc",FormatterUtils.format(value));
            IFormattableTextComponent stack_tip = EMCStage.tc("emc_stack",FormatterUtils.format(value * stack.getCount()));
            if(Screen.hasShiftDown()){
                normal = EMCStage.tc("emc", FormatterUtils.thousandSign(value));
                stack_tip = EMCStage.tc("emc_stack",FormatterUtils.thousandSign(value * stack.getCount()));
            }
            if(value != sellValue){
                if(Screen.hasShiftDown()){
                    normal.append(EMCStage.tc("sell_value",FormatterUtils.thousandSign(sellValue)));
                    stack_tip.append(EMCStage.tc("sell_value",FormatterUtils.thousandSign(sellValue * stack.getCount())));
                }else{
                    normal.append(EMCStage.tc("sell_value",FormatterUtils.format(sellValue)));
                    stack_tip.append(EMCStage.tc("sell_value",FormatterUtils.format(sellValue * stack.getCount())));
                }
            }
            if(!isTrans){
                normal.setStyle(Style.EMPTY.setStrikethrough(true));
            }
            event.getToolTip().add(normal);
            if(stack.getCount() > 1){
                event.getToolTip().add(stack_tip);
            }
            if(!isTrans && stage.equals("disabled")){
                event.getToolTip().add(EMCStage.tc("emc_disable"));
                return;
            }
            if(!isTrans && !stage.equals("")){
                event.getToolTip().add(EMCStage.tc("emc_locked",stage));
            }
        }
    }
}
