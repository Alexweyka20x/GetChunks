package net.getchunks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.argument.BlockPosArgumentType;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;

import net.getchunks.util.getBlocksStart;


public class getBlocksCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(literal("get")
                .then(literal("blocks").then(literal("start")
                        .then(argument("filename", StringArgumentType.greedyString())
                                .executes(context -> { return start(context, "blocks"); }))))
                .then(literal("biomes").then(literal("start")
                        .then(argument("filename", StringArgumentType.greedyString())
                                .executes(context -> { return start(context, "biomes"); }))))
        );
        dispatcher.register(literal("get")
                .then(literal("stop")
                    .executes(getBlocksCommand::stop)));
    }
    private static int start(CommandContext<ServerCommandSource> context, String type) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        String filenanme = StringArgumentType.getString(context, "filename");
        if (getBlocksStart.is_started) {
            context.getSource().sendFeedback(new LiteralText("Already started. Stop with '/get stop'"), true);
            return 0;
        }
        Runnable runnable = () -> {
            getBlocksStart.start_wrapped(player.world, context.getSource(), filenanme, type);
        };
        Thread res = new Thread(runnable);
        res.start();

        return 0;
    }
    private static int stop(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        getBlocksStart.is_started = false;
        context.getSource().sendFeedback(new LiteralText("Successfully stopped"), true);
        return 0;
    }
}
