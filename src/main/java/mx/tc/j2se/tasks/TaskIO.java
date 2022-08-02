package mx.tc.j2se.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class TaskIO {

    public TaskIO(){}

    /**
     * Writes the tasks from the list to the stream in a binary format.
     * @param tasks a list of tasks.
     * @param out the binary stream.
     */
    public static void writeBinary(AbstractTaskList tasks, OutputStream out) {
        try {
            DataOutputStream d = new DataOutputStream(out);
            d.writeInt(tasks.size());
            for (Task task : tasks) {
                d.writeInt(task.getTitle().length());
                d.writeUTF(task.getTitle());
                d.writeBoolean(task.isActive());
                d.writeLong(task.getRepeatInterval());
                if (task.isRepeated()) {
                    d.writeLong(task.getStartTime().toInstant(ZoneOffset.UTC).toEpochMilli());
                    d.writeLong(task.getEndTime().toInstant(ZoneOffset.UTC).toEpochMilli());
                } else {
                    d.writeLong(task.getTime().toInstant(ZoneOffset.UTC).toEpochMilli());
                }
            }
            d.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the binary stream to the current task list.
     * @param tasks a list of tasks
     * @param in the binary stream to read.
     */
    public static void readBinary(AbstractTaskList tasks, InputStream in) {
        try {
            DataInputStream d = new DataInputStream(in);
            int size = d.readInt();
            while(d.available()>0) {
                int titleLength = d.readInt();
                String title = d.readUTF();
                boolean active = d.readBoolean();
                Long interval = d.readLong();
                LocalDateTime startTime;
                LocalDateTime endTime;
                LocalDateTime executionTime;
                Task task;
                if (interval > 0) {
                    startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(d.readLong()), ZoneOffset.UTC);
                    endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(d.readLong()), ZoneOffset.UTC);
                    task = new TaskImpl(title, startTime, endTime, interval);
                } else {
                    executionTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(d.readLong()), ZoneOffset.UTC);
                    task = new TaskImpl(title, executionTime);
                }
                task.setActive(active);
                tasks.add(task);
            }
            d.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks from the list to the binary file.
     * @param tasks a list of tasks
     * @param file a file to write to.
     */
    public static void write(AbstractTaskList tasks, File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
            OutputStream os = new BufferedOutputStream(fos)) {
            writeBinary(tasks, os);
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Error while writing to file" + ioe);
        }
    }

    /**
     * Reads tasks from the binary file to the task list.
     * @param tasks a list of tasks.
     * @param file a file to read.
     */
    public static void read(AbstractTaskList tasks, File file) {
        try(FileInputStream fis = new FileInputStream(file);
            InputStream is = new BufferedInputStream(fis)) {
            readBinary(tasks, is);
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Error while reading file" + ioe);
        }
    }

    /**
     * Writes tasks from the list to the stream in the JSON format.
     * @param tasks a list of tasks.
     * @param out the destination to write character streams.
     * @throws IOException
     */
    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gson = new Gson();
        ArrayTaskListImpl arrayTaskList = new ArrayTaskListImpl();
        tasks.getStream().filter(Objects::nonNull).forEach(arrayTaskList::add);
        gson.toJson(arrayTaskList, out);
        out.close();
    }

    /**
     * Reads tasks from the JSON stream to the list.
     * @param tasks a list of tasks.
     * @param in object for reading character streams
     * @throws IOException
     */
    public static void read(AbstractTaskList tasks, Reader in) throws IOException{
        Gson gson = new Gson();
        ArrayTaskListImpl arrayTaskList = gson.fromJson(in, ArrayTaskListImpl.class);
        arrayTaskList.getStream().filter(Objects::nonNull).forEach(tasks::add);
        in.close();
    }

    /**
     * Writes tasks from the list to the file in JSON format.
     * @param tasks a list of tasks.
     * @param file a file to store the tasks.
     * @throws IOException
     */
    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        Gson gson = new Gson();
        ArrayTaskListImpl arrayTaskList = new ArrayTaskListImpl();
        tasks.getStream().filter(Objects::nonNull).forEach(arrayTaskList::add);
        gson.toJson(arrayTaskList, new FileWriter(file));
    }

    /**
     * Reads tasks from the JSON file.
     * @param tasks a list of tasks
     * @param file a file to read.
     * @throws FileNotFoundException
     */
    public static void readText(AbstractTaskList tasks, File file) throws FileNotFoundException{
        Gson gson = new Gson();
        ArrayTaskListImpl arrayTaskList = gson.fromJson(new FileReader(file), ArrayTaskListImpl.class);
        arrayTaskList.getStream().filter(Objects::nonNull).forEach(tasks::add);
    }
}
