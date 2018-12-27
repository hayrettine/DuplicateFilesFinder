package com.hayro;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static java.nio.file.FileVisitResult.CONTINUE;

public class Checksum {

    public String sum(Path p) throws IOException {

        FileInputStream fis = new FileInputStream(String.valueOf(p));
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] dataBytes = new byte[1024];

        long nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, (int) nread);
        }
        byte[] mdbytes = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString().toUpperCase();
    }

    public void filescheck(Path p, HashMap<String, List<String>> map) throws IOException {

        Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(attrs.isRegularFile() && !Files.isHidden(file)) {
                    List<String> list = new ArrayList<>();

                    if (map.get(sum(file)) != null){
                        map.get(sum(file)).add(file.toString());
                    }
                    else {
                        list.add(file.toString());
                        map.put(sum(file), list);
                    }

                }
                return CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return CONTINUE;
            }});

    }




}
