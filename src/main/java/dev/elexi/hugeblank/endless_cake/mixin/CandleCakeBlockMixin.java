package dev.elexi.hugeblank.endless_cake.mixin;

import dev.elexi.hugeblank.endless_cake.block.EndlessCandleCakeBlock;
import net.minecraft.block.CandleCakeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(CandleCakeBlock.class)
class CandleCakeBlockMixin {

	@Redirect(at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), method = "<init>")
	private <K, V> V avoidMapInsertion(Map<K, V> map, K key, V value) {
		return value instanceof EndlessCandleCakeBlock ? null : map.put(key, value);
	}
}
