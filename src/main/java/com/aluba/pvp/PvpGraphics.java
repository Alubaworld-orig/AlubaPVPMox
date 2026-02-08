package com.aluba.pvp;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class PvpGraphics {

    @SubscribeEvent
    public void onRenderCrosshair(RenderGameOverlayEvent.Pre event) {
        // Проверяем, что сейчас должен рисоваться прицел
        if (event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            
            // 1. Отменяем стандартный крестик
            event.setCanceled(true); 

            // 2. Включаем сглаживание (Anti-aliasing) для линий
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);

            // 3. Берем инструменты для рисования
            MatrixStack stack = event.getMatrixStack();
            int x = Minecraft.getInstance().getMainWindow().getScaledWidth() / 2;
            int y = Minecraft.getInstance().getMainWindow().getScaledHeight() / 2;

            // 4. Рисуем красное кольцо (состоит из 4 палочек вокруг центра)
            // Цвет 0xFFFF0000 (Ярко-красный)
            int c = 0xFFFF0000;
            
            // Рисуем квадратное "кольцо" (верх, низ, лево, право)
            AbstractGui.fill(stack, x - 4, y - 1, x - 2, y + 1, c); // Лево
            AbstractGui.fill(stack, x + 2, y - 1, x + 4, y + 1, c); // Право
            AbstractGui.fill(stack, x - 1, y - 4, x + 1, y - 2, c); // Верх
            AbstractGui.fill(stack, x - 1, y + 2, x + 1, y + 4, c); // Низ
        }
    }
                             }
