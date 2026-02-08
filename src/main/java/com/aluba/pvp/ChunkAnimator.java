package com.aluba.pvp;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.WeakHashMap;

@OnlyIn(Dist.CLIENT)
public class ChunkAnimator {
    // Список чанков, которые начали анимацию
    private final WeakHashMap<ChunkRenderDispatcher.ChunkRender, Long> animatedChunks = new WeakHashMap<>();

    @SubscribeEvent
    public void onChunkRender(RenderChunkEvent.Pre event) {
        ChunkRenderDispatcher.ChunkRender chunk = event.getChunkRender();
        long now = System.currentTimeMillis();

        if (!animatedChunks.containsKey(chunk)) {
            animatedChunks.put(chunk, now);
        }

        long timePassed = now - animatedChunks.get(chunk);
        if (timePassed < 1000) { // Анимация идет 1 секунду
            // Считаем, насколько чанк должен быть опущен (от 20 блоков до 0)
            float offset = 20.0f - ((float)timePassed / 1000.0f * 20.0f);
            
            MatrixStack matrixStack = event.getMatrixStack();
            matrixStack.translate(0, -offset, 0); // Двигаем чанк вниз
        }
    }
}
