package com.hayro;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class App {

    public static void main( String[] args ) throws IOException {
        if (args.length<1){
            System.err.println("Usage: java App startingDir...");
            return;
        }

        HashMap<String, List<String>> hash = new HashMap<>();
        Checksum ch = new Checksum();
        Path p = Paths.get(args [0]);
        ch.filescheck(p, hash);

                hash.values().stream().sorted((o1, o2) -> o2.size() - o1.size())
                        .forEach((o) -> {
                    Stream<String> sorted = o.stream().sorted((o1, o2) -> {
                        try {
                            return Files.getLastModifiedTime(Paths.get(o2)).compareTo(Files.getLastModifiedTime(Paths.get(o1)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    });
                if (o.size() > 1){
                    sorted.forEach(s->{
                        System.out.print("  "+Paths.get(System.getProperty("user.dir")).relativize(Paths.get(s)));
                    });
                    System.out.println();
                    System.out.println(". . .");
                }
                });
        }

    }

