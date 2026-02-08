package com.aluba.pvp;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnemyStats {

    @SubscribeEvent
    public void onRenderName(RenderNameplateEvent event) {
        // Проверяем, что перед нами именно игрок, а не зомби
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity target = (PlayerEntity) event.getEntity();
            
            // Получаем количество ХП (округляем) и броню
            int hp = (int) target.getHealth();
            int armor = target.getTotalArmorValue();

            // Создаем красивую надпись [❤ 20 | 🛡 10]
            String healthIcon = TextFormatting.RED + "❤ " + hp;
            String armorIcon = TextFormatting.BLUE + " 🛡 " + armor;
            String finalInfo = TextFormatting.GRAY + " [" + healthIcon + TextFormatting.GRAY + " | " + armorIcon + TextFormatting.GRAY + "]";

            // Приклеиваем инфу к нику игрока
            event.setContent(new StringTextComponent(event.getContent().getString() + finalInfo));
        }
    }
}
