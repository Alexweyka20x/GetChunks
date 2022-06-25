package net.getchunks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;


public class getBlocksCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {

        dispatcher.register(CommandManager.literal("getblocks")
                .then(CommandManager.argument("location", BlockPosArgumentType.blockPos())
                        .then(CommandManager.argument("dist", IntegerArgumentType.integer())
                                .executes(getBlocksCommand::execute))));
    }
    private static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        BlockPos block = BlockPosArgumentType.getBlockPos(context, "location");
        int dist = IntegerArgumentType.getInteger(context, "dist");
        int x = block.getX(), y = block.getY(), z = block.getZ();
        String pos = "(" + x + ", " + y + ", " + z + ")";
        context.getSource().sendFeedback(new LiteralText("Starting load from " + pos + " with distance " + dist), true);

        return 0;
    }
}
