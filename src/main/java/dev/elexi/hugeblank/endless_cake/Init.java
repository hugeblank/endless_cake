package dev.elexi.hugeblank.endless_cake;

import dev.elexi.hugeblank.endless_cake.block.EndlessCandleCakeBlock;
import dev.elexi.hugeblank.endless_cake.block.EndlessCakeBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Init implements ModInitializer {
    public static final String ID = "endless_cake";
    public static final EndlessCakeBlock ENDLESS_CAKE = new EndlessCakeBlock();

    private static void registerBlock(String name, Block block, ItemGroup group) {
        Identifier id = new Identifier(ID, name);
        Registry.register(Registry.BLOCK, id, block);
        if (group != null) {
            Item item = new BlockItem(block, new Item.Settings().group(group).rarity(Rarity.EPIC));
            Registry.register(Registry.ITEM, id, item);
        }
    }

    private static void registerCakeType(String name, EndlessCakeBlock cake) {
        registerBlock(name, cake, ItemGroup.FOOD);
        registerBlock("candle_" + name, new EndlessCandleCakeBlock(Blocks.CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CAKE).luminance((state) -> (Boolean)state.get(Properties.LIT) ? 3 : 0)), null);
        registerBlock("white_candle_" + name, new EndlessCandleCakeBlock(Blocks.WHITE_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("orange_candle_" + name, new EndlessCandleCakeBlock(Blocks.ORANGE_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("magenta_candle_" + name, new EndlessCandleCakeBlock(Blocks.MAGENTA_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("light_blue_candle_" + name, new EndlessCandleCakeBlock(Blocks.LIGHT_BLUE_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("yellow_candle_" + name, new EndlessCandleCakeBlock(Blocks.YELLOW_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("lime_candle_" + name, new EndlessCandleCakeBlock(Blocks.LIME_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("pink_candle_" + name, new EndlessCandleCakeBlock(Blocks.PINK_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("gray_candle_" + name, new EndlessCandleCakeBlock(Blocks.GRAY_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("light_gray_candle_" + name, new EndlessCandleCakeBlock(Blocks.LIGHT_GRAY_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("cyan_candle_" + name, new EndlessCandleCakeBlock(Blocks.CYAN_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("purple_candle_" + name, new EndlessCandleCakeBlock(Blocks.PURPLE_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("blue_candle_" + name, new EndlessCandleCakeBlock(Blocks.BLUE_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("brown_candle_" + name, new EndlessCandleCakeBlock(Blocks.BROWN_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("green_candle_" + name, new EndlessCandleCakeBlock(Blocks.GREEN_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("red_candle_" + name, new EndlessCandleCakeBlock(Blocks.RED_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("black_candle_" + name, new EndlessCandleCakeBlock(Blocks.BLACK_CANDLE, cake, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
    }

    @Override
    public void onInitialize() {
        // Just a single cake, for now...
        registerCakeType("endless_cake", ENDLESS_CAKE);
    }
}
