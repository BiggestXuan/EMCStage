package biggestxuan.EMCStage.mixin;

import moze_intel.projecte.events.ToolTipEvent;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ToolTipEvent.class)
public class EMCToolTipMixin {
    @Redirect(method = "tTipEvent",
            at = @At(value = "INVOKE",target = "Lmoze_intel/projecte/utils/EMCHelper;getEmcValue(Lnet/minecraft/item/ItemStack;)J"),
            remap = false)
    private static long setZero(ItemStack stack){
        return 0L;
    }
}
