package net.getchunks;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.getchunks.util.registerClass;


public class mainClass implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("getChunks");

    @Override
    public void onInitialize() {
        registerClass.register();
    }
}
