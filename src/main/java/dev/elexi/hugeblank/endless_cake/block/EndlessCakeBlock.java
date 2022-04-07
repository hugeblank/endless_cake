package dev.elexi.hugeblank.endless_cake.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class EndlessCakeBlock extends Block {

    public static final VoxelShape CAKE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
    public EndlessCakeBlock() {
        super(Settings.of(Material.CAKE).strength(0.5F).sounds(BlockSoundGroup.WOOL));
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CAKE_SHAPE;
    }

    // Mojang bad, hugeblank good.
    public static ActionResult tryEat(WorldAccess world, BlockPos pos, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.incrementStat(Stats.EAT_CAKE_SLICE);
            player.getHungerManager().add(2, 0.1F);
            world.emitGameEvent(player, GameEvent.EAT, pos);

            return ActionResult.SUCCESS;
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (itemStack.isIn(ItemTags.CANDLES)) {
            Block block = Block.getBlockFromItem(item);
            if (block instanceof CandleBlock) {
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }

                world.playSound(null, pos, SoundEvents.BLOCK_CAKE_ADD_CANDLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos, EndlessCandleCakeBlock.getCandleCakeFromCandle(this, (CandleBlock) block));
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                return ActionResult.SUCCESS;
            }
        }

        if (world.isClient) {
            if (tryEat(world, pos, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, player);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {}

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 14;
    }
}

