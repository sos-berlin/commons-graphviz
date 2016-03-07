package com.sos.graphviz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.graphviz.enums.FileType;

public class GraphIO {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphIO.class);
    public static final String NAME = System.getProperty("os.name");
    public static final boolean IS_WINDOWS = NAME.startsWith("Windows");
    private static String DOT = IS_WINDOWS ? "dot.exe" : "dot";
    private String tempDir = System.getProperty("java.io.tmpdir") + "/graphviz/";
    private String dotDir = null;
    private final Graph graph;

    public GraphIO(Graph graph) {
        this.graph = graph;
    }

    public void setDotExecFileName(final String pstrDotExecFileName) {
        if (!(new File(pstrDotExecFileName).exists())) {
            throw new JobSchedulerException(String.format("Dot-executable '%1$s' not found", pstrDotExecFileName));
        }
        DOT = pstrDotExecFileName;
    }

    public String getDotExecFileName() {
        return DOT;
    }

    /** Writes the graph's image in a file.
     * 
     * @param type The type of file.
     * @param file A Filename to where we want to write. */
    public void writeGraphToFile(FileType type, String file) throws IOException {
        File to = new File(file);
        writeGraphToFile(type, to);
    }

    /** Writes the graph's image in a file.
     *
     * @param type The type of file.
     * @param to A File object to where we want to write. */
    public void writeGraphToFile(FileType type, File to) throws IOException {
        LOGGER.trace("Write graph to file " + to.getAbsolutePath() + ".");
        FileOutputStream fos = new FileOutputStream(to);
        File dot = writeDotSourceToTemporaryFile(graph.getSource());
        fos.write(getGraph(dot, type));
        fos.flush();
        fos.close();
        if (getDotDir() != null) {
            // getFileExtension nicht in Guava 10,0
            String targetName = replaceLast(to.getName(), getFileExtension(to.getName()), "dot");
            File targetFile = new File(getDotDir(), targetName);
            LOGGER.trace("try to move {} to {}.", dot.getAbsolutePath(), targetFile.getAbsolutePath());
            Files.move(dot, targetFile);
        } else {
            deleteDotFile(dot);
        }
    }

    private static String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos) + replacement + string.substring(pos + toReplace.length(), string.length());
        }
        return string;
    }

    private String getFileExtension(String file) {
        int i = file.lastIndexOf(".");
        return (i > 0) ? file.substring(i + 1) : "???";
    }

    private void deleteDotFile(File dotFile) {
        if (dotFile != null && !dotFile.delete()) {
            LOGGER.warn(dotFile.getAbsolutePath() + " could not be deleted!");
        }
    }

    /** Returns the graph as an image in binary format.
     *
     * @param dotFile The file with the dotContent of the graph.
     * @param type Type of the output image to be produced, e.g.: gif, dot, svg.
     * @return A byte array containing the image of the graph. */
    private byte[] getGraph(File dotFile, FileType type) {
        byte[] imgStream = null;
        if (dotFile != null) {
            imgStream = getImgStream(dotFile, type.name());
        }
        return imgStream;
    }

    /** It will call the external dot program, and return the image in binary
     * format.
     * 
     * @param dot Source of the graph (in dot language).
     * @param type Type of the output image to be produced, e.g.: gif, dot, fig,
     *            pdf, ps, svg, png.
     * @return The image of the graph in .gif format. */
    private byte[] getImgStream(File dot, String type) {
        File img;
        byte[] imgStream = null;
        try {
            img = File.createTempFile("graph_", "." + type, new File(getTempDir()));
            img.deleteOnExit();
            Runtime rt = Runtime.getRuntime();
            // patch by Mike Chenault
            String[] args = { DOT, "-T" + type, dot.getAbsolutePath(), "-o", img.getAbsolutePath(), "-Gcharset=latin1" };
            String cmd = getCommandString(args);
            LOGGER.trace("About to execute: " + cmd);
            Process p = rt.exec(args);
            InputStream stderrIs = p.getErrorStream();
            InputStreamReader stderrReader = new InputStreamReader(stderrIs);
            BufferedReader stderr = new BufferedReader(stderrReader);
            String line = null;
            while ((line = stderr.readLine()) != null) {
                if (line.startsWith("Warning:")) {
                    LOGGER.warn(line);
                } else {
                    LOGGER.error(line);
                }
            }
            int exitVal = p.waitFor();
            if (exitVal != 0) {
                LOGGER.error(String.format("Process '%1$s' ends with cc=%2$s", cmd, exitVal));
                throw new RuntimeException(cmd + " ends with cc=" + exitVal);
            }
            FileInputStream in = new FileInputStream(img.getAbsolutePath());
            imgStream = new byte[in.available()];
            in.read(imgStream);
            // Close it if we need to
            if (in != null) {
                in.close();
            }
        } catch (IOException ioe) {
            LOGGER.error("Error: in I/O processing of tempfile in dir " + getTempDir() + "\n       or in calling external command", ioe);
        } catch (InterruptedException ie) {
            LOGGER.error("Error: the execution of the external program was interrupted", ie);
        }
        return imgStream;
    }

    private String getCommandString(String[] args) {
        StringBuilder b = new StringBuilder();
        for (String s : args) {
            b.append(s + " ");
        }
        return b.toString();
    }

    /** Writes the source of the graph in a file, and returns the written file as
     * a File object.
     * 
     * @param content Source of the graph (in dot language).
     * @return The file (as a File object) that contains the source of the
     *         graph. */
    private File writeDotSourceToTemporaryFile(String content) throws java.io.IOException {
        File objF = new File(getTempDir());
        if (!objF.exists()) {
            objF.mkdirs();
        }
        File temp = File.createTempFile("graphviz_", ".dot.tmp", objF);
        try {
            FileWriter fout = new FileWriter(temp);
            fout.write(content);
            fout.close();
        } catch (Exception e) {
            LOGGER.error("Error: I/O error while writing the dot source to temp file {}.", temp.getAbsolutePath(), e);
            return null;
        }
        return temp;
    }

    public String getTempDir() {
        return this.tempDir;
    }

    public String getDotDir() {
        return this.dotDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public void setDotDir(String dotDir) {
        this.dotDir = dotDir;
    }
}
