package com.aluba.pvp;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("alubapvpmod")
public class AlubaPVPMod {
    public static final String MOD_ID = "alubapvpmod";
    private static final Logger LOGGER = LogManager.getLogger();

    public AlubaPVPMod() {
        // Регистрация события загрузки клиентской части
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        
        // Регистрация главного класса в шине событий Forge
        MinecraftForge.EVENT_BUS.register(this);
        
        LOGGER.info("AlubaPVPMod: Инициализация началась...");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // ВКЛЮЧАЕМ ВСЕ МОДУЛИ:
        
        // 1. Красное кольцо и сглаживание
        MinecraftForge.EVENT_BUS.register(new PvpGraphics());
        
        // 2. Статистика врага (HP и Броня)
        MinecraftForge.EVENT_BUS.register(new EnemyStats());
        
        // 3. Плавная анимация чанков
        MinecraftForge.EVENT_BUS.register(new ChunkAnimator());

        LOGGER.info("AlubaPVPMod: Все PVP системы (Кольцо, Статы, Чанки) запущены!");
    }
}
