package it.unibo.oop17.ga_game.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.google.common.io.ByteStreams;

/**
 * Helper class for zip files.
 */
public final class ZipUtils {

    private ZipUtils() {

    }

    /**
     * Extracts the InputStream as if it's a zipped input to the desired path.
     * 
     * @param is
     *            The InputStream
     * @param tempDir
     *            The base path of the decompressed files.
     * @throws IOException
     *             If something goes bad during reading and writing.
     */
    public static void extract(final InputStream is, final File tempDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(is)) {
            for (ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
                if (!entry.isDirectory()) {
                    final File dest = new File(tempDir, entry.getName());
                    final File parentDir = dest.getParentFile();
                    if (!parentDir.exists() && !parentDir.mkdirs() && !parentDir.isDirectory()) {
                        throw new IOException("Couldn't create parent directory " + parentDir);
                    }
                    try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(dest))) {
                        if (ByteStreams.copy(zis, fos) < 0) {
                            throw new IOException("This is not possible and it's here to make checkstyle happy");
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }
}
