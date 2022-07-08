package net.getchunks.util;

import net.getchunks.mainClass;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class fileInteracter {
    PrintWriter writer;
    Scanner reader;
    String mode;
    fileInteracter(String path, String mode) throws IOException {
        this.mode = mode;
        mainClass.LOGGER.info(path);
        if (Objects.equals(mode, "w"))
            writer = new PrintWriter(path, StandardCharsets.UTF_8);
        if (Objects.equals(mode, "r"))
            reader = new Scanner(new File(path));
    }
    public void writeLine(String s) throws IOException {
        writer.println(s);
    }
    public String readLine() throws IOException {
        return reader.nextLine();
    }
    public void close() {
        if (Objects.equals(mode, "w"))
            writer.close();
        if (Objects.equals(mode, "r"))
            reader.close();
    }
}
