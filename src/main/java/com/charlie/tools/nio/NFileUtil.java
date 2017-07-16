package com.charlie.tools.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by charlie on 04/04/2017.
 */
public class NFileUtil {

    public static void createFile(String filePath) {
        Path path = Paths.get(filePath);
//        Set<PosixFilePermission> set = EnumSet.of(PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_EXECUTE);
        Set<PosixFilePermission> set = PosixFilePermissions.fromString("rwxr-xr-x");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(set);
        try {
            Files.createFile(path, attr);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
