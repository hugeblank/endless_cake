package dev.elexi.hugeblank.endless_cake.mixin;

import dev.elexi.hugeblank.endless_cake.block.EndlessCandleCakeBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(CandleCakeBlock.class)
class CandleCakeBlockMixin {

	@Shadow
	@Final
	private static Map<Block, CandleCakeBlock> CANDLES_TO_CANDLE_CAKES;
	private CandleCakeBlock endless_cake$cake_original;

	@Inject(at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), method = "<init>")
	private void before(Block candle, AbstractBlock.Settings settings, CallbackInfo ci) {
		endless_cake$cake_original = CANDLES_TO_CANDLE_CAKES.get(candle);
	}

	@Inject(at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), method = "<init>")
	private void after(Block candle, AbstractBlock.Settings settings, CallbackInfo ci) {
		//noinspection ConstantConditions
		if ((Object)this instanceof EndlessCandleCakeBlock)
			CANDLES_TO_CANDLE_CAKES.put(candle, endless_cake$cake_original);
	}
}
