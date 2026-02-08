package com.aluba.pvp;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Название мода должно совпадать с тем, что в mods.toml
@Mod("alubapvpmod")
public class AlubaPVPMod {
    public static final String MOD_ID = "alubapvpmod";
    private static final Logger LOGGER = LogManager.getLogger();

    public AlubaPVPMod() {
        // Регистрируем наш мод в системе Forge
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        
        // Эта строчка заставляет работать все наши будущие функции
        MinecraftForge.EVENT_BUS.register(this);
        
        LOGGER.info("AlubaPVPMod: Система активирована!");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Вот эти две строчки активируют твои функции!
        MinecraftForge.EVENT_BUS.register(new PvpGraphics());
        MinecraftForge.EVENT_BUS.register(new EnemyStats());
        
        LOGGER.info("AlubaPVPMod: Все функции успешно запущены!");
    }
