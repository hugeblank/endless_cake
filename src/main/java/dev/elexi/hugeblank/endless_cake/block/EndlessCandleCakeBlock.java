package dev.elexi.hugeblank.endless_cake.block;

import dev.elexi.hugeblank.endless_cake.Init;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class EndlessCandleCakeBlock extends CandleCakeBlock {
    private final EndlessCakeBlock cake;
    private static final Map<EndlessCakeBlock, Map<CandleBlock, EndlessCandleCakeBlock>> COMBINER = new HashMap<>();

    public EndlessCandleCakeBlock(Block candle, EndlessCakeBlock cake, Settings settings) {
        super(candle, settings);
        this.cake = cake;
        if (COMBINER.containsKey(cake)) {
            COMBINER.get(cake).put((CandleBlock) candle, this);
        } else {
            Map<CandleBlock, EndlessCandleCakeBlock> candleCakeBlockMap = new HashMap<>();
            candleCakeBlockMap.put((CandleBlock) candle, this);
            COMBINER.put(cake, candleCakeBlockMap);
        }

    }

    public static BlockState getCandleCakeFromCandle(EndlessCakeBlock cake, CandleBlock candle) {
        return COMBINER.get(cake).get(candle).getDefaultState();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(Items.FLINT_AND_STEEL) && !itemStack.isOf(Items.FIRE_CHARGE)) {
            if (isHittingCandle(hit) && player.getStackInHand(hand).isEmpty()) {
                extinguish(player, state, world, pos);
                return ActionResult.success(world.isClient);
            } else {
                return EndlessCakeBlock.tryEat(world, pos, player);
            }
        } else {
            return ActionResult.PASS;
        }
    }

    public static void extinguish(@Nullable PlayerEntity player, @NotNull BlockState state, World world, BlockPos pos) {
        if (!state.get(AbstractCandleBlock.LIT)) {
            dropStacks(state, world, pos);
            world.setBlockState(pos, Init.ENDLESS_CAKE.getDefaultState());
        } else {
            AbstractCandleBlock.extinguish(player, state, world, pos);
        }
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(cake);
    }

    protected static boolean isHittingCandle(BlockHitResult hitResult) {
        return hitResult.getPos().y - (double)hitResult.getBlockPos().getY() > 0.5D;
    }
}

