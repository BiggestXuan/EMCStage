package biggestxuan.EMCStage.recipes;

import com.google.gson.JsonObject;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class EMCStageRecipe implements IRecipe<RecipeWrapper> {
    private final ResourceLocation rl;
    private final Item item;
    private final String stage;

    public EMCStageRecipe(ResourceLocation rl,Item item,String stage){
        this.rl = rl;
        this.item = item;
        this.stage = stage;
    }

    @Override
    public boolean matches(RecipeWrapper p_77569_1_, World p_77569_2_) {
        return true;
    }

    @Override
    public ItemStack assemble(RecipeWrapper p_77572_1_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return rl;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return EMCRecipeTypes.EMC_STAGE_LIMIT_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return EMCStageLimitType.INSTANCE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.create();
    }

    public String getStage() {
        return stage;
    }

    public Item getItem() {
        return item;
    }

    public static class EMCStageLimitType implements IRecipeType<EMCStageRecipe>{
        public static final IRecipeType<EMCStageRecipe> INSTANCE = EMCRecipeTypes.register("stage_limit");
    }

    public static class EMCStageLimitSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<EMCStageRecipe> {
        public static IRecipeSerializer<EMCStageRecipe> serializer = new EMCStageLimitSerializer();

        @Override
        public EMCStageRecipe fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_) {
            Item item = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(p_199425_2_,"limit_item")).getItem();
            String stage = JSONUtils.getAsString(p_199425_2_,"stage");
            return new EMCStageRecipe(p_199425_1_,item,stage);
        }

        @Nullable
        @Override
        public EMCStageRecipe fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_) {
            Item item = p_199426_2_.readItem().getItem();
            String stage = p_199426_2_.readUtf();
            return new EMCStageRecipe(p_199426_1_,item,stage);
        }

        @Override
        public void toNetwork(PacketBuffer p_199427_1_, EMCStageRecipe p_199427_2_) {
            ItemStack stack = new ItemStack(p_199427_2_.item,1);
            String stage = p_199427_2_.stage;
            p_199427_1_.writeItem(stack);
            p_199427_1_.writeUtf(stage);
        }
    }
}
