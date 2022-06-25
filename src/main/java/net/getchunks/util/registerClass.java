package net.getchunks.util;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.getchunks.commands.getBlocksCommand;

public class registerClass {
    public static void register() {
        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(getBlocksCommand::register);
    }

}

