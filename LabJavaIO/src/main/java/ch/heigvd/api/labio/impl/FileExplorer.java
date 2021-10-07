package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Arrays;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger, Francesco Monti
 */
public class FileExplorer {

    public void explore(File rootDirectory) {
        FileTransformer transformer = new FileTransformer();
        exploreDirectory(rootDirectory, transformer);
    }

    /**
     * Recursively explores a directory. Lists all the children of a give File if they exist
     * and for each one applies the transformer if it's a file or explores it if it's a directory.
     * @param directory The directory to explore recursively
     * @param transformer The transformer to apply to files
     */
    private void exploreDirectory(File directory, FileTransformer transformer) {
        File[] content = directory.listFiles();
        if (content != null) {
            Arrays.sort(content);

            for (File f : content) {
                if(f.isDirectory()){
                    exploreDirectory(f, transformer);
                }
                else if (f.isFile()) {
                    transformer.transform(f);
                }
            }
        }
    }
}