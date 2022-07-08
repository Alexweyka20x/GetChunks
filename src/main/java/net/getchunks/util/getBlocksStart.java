package net.getchunks.util;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;


public class getBlocksStart {
    public static boolean is_started = false;

    public static void start_wrapped(World world, ServerCommandSource source, String filename, String type) {
        try { start(world, source, filename, type); }
        catch(IOException e) {
            source.sendFeedback(new LiteralText("Incorrect file"), true);
            getBlocksStart.is_started = false;
            throw new UncheckedIOException(e);
        }
    }

    public static void start(World world, ServerCommandSource source, String filename, String type) throws IOException {
        getBlocksStart.is_started = true;

        fileInteracter reader = new fileInteracter("getblocks/" + filename + ".txt", "r");
        fileInteracter writer = new fileInteracter("getblocks/" + filename + "-result.txt", "w");

        String[] control = reader.readLine().split(" ");
        int n = Integer.parseInt(control[0]);
        long cnt = 0;

        source.sendFeedback(new LiteralText("Starting"), true);
        for (int i = 1; i <= n; i++) {
            if (Objects.equals(type, "biomes")) {
                String[] pos = reader.readLine().split(" ");
                int x = Integer.parseInt(pos[0]), z = Integer.parseInt(pos[1]);
                String biome = Biome.getCategory(world.getBiome(new BlockPos(x, 255, z))).getName();
                writer.writeLine(biome);
            }
            else {
                String[] pos = reader.readLine().split(" ");
                int x = Integer.parseInt(pos[0]), y = Integer.parseInt(pos[1]), z = Integer.parseInt(pos[2]);
                String fluid = world.getFluidState(new BlockPos(x, y, z)).getBlockState().getBlock().getName().getString();
                writer.writeLine(fluid);
            }

            cnt++;
            int per = (int)(cnt * 5000 / n), was = (int)((cnt - 1) * 5000 / n);
            if (per != was) {
                source.sendFeedback(new LiteralText("" + per + "/5000"), true);
            }
            if (!getBlocksStart.is_started)
                break;
        }

        is_started = false;
        reader.close();
        writer.close();
        source.sendFeedback(new LiteralText("Done!"), true);
    }
}
