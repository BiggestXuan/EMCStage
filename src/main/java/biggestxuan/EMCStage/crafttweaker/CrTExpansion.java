package biggestxuan.EMCStage.crafttweaker;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.openzen.zencode.java.ZenCodeType;

import java.math.BigInteger;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.player.MCPlayerEntity")
public class CrTExpansion {
    private static IKnowledgeProvider getKnowledge(PlayerEntity player){
        return ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(player.getUUID());
    }

    private static void sync(PlayerEntity player){
        if(player instanceof ServerPlayerEntity){
            getKnowledge(player).syncEmc((ServerPlayerEntity) player);
            getKnowledge(player).sync((ServerPlayerEntity) player);
        }
    }

    @ZenCodeType.Method
    public static long getEMC(PlayerEntity player){
        return getKnowledge(player).getEmc().longValue();
    }

    @ZenCodeType.Method
    public static BigInteger getBigIntegerPlayerEMC(PlayerEntity player){
        return getKnowledge(player).getEmc();
    }

    @ZenCodeType.Method
    public static void setEMC(PlayerEntity player,long value){
        getKnowledge(player).setEmc(BigInteger.valueOf(value));
        sync(player);
    }

    @ZenCodeType.Method
    public static boolean hasKnowledge(PlayerEntity player, IItemStack stack){
        return getKnowledge(player).hasKnowledge(stack.getImmutableInternal());
    }

    @ZenCodeType.Method
    public static boolean addKnowledge(PlayerEntity player,IItemStack stack){
        boolean flag = getKnowledge(player).addKnowledge(stack.getImmutableInternal());
        sync(player);
        return flag;
    }

    @ZenCodeType.Method
    public static boolean removeKnowledge(PlayerEntity player,IItemStack stack){
        boolean flag = getKnowledge(player).removeKnowledge(stack.getImmutableInternal());
        sync(player);
        return flag;
    }
}
