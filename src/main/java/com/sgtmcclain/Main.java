package com.sgtmcclain;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.IOException;
import java.util.*;

import java.text.DateFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        Date startTime = null;
        Date endTime = null;
        Locale currentLocale;
        DateFormatter dateFormatter;
        DateFormat timeFormatter;
        String timeStart;
        String timeEnd;



        startTime = new Date();
        currentLocale = new Locale("en");
        timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, currentLocale);
        timeStart = timeFormatter.format(startTime);
        System.out.println("Started at " + timeStart);

        System.out.println(numberOfFiles(new File("E:\\Music")));
        //printMp3s(new File("E:\\Music"));

        endTime = new Date();
        timeEnd = timeFormatter.format(endTime);
        System.out.println("Started at " + timeStart);
        System.out.println("Ended at " + timeEnd);
    }

    public static int numberOfFiles(File directory) {
        Date startTime = null;
        Date endTime = null;
        Locale currentLocale;
        DateFormatter dateFormatter;
        DateFormat timeFormatter;
        String timeStart;
        String timeEnd;

        startTime = new Date();
        currentLocale = new Locale("en");
        timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, currentLocale);

        timeStart = timeFormatter.format(startTime);

        int count;

        if (directory.isFile())
            return (1);
        else {
            File[] list = directory.listFiles();

            count = 0;
            System.out.println("List size is " + list.length);

            if (list != null) {


                for (File file : list) {
                    String filePath = file.getPath();
                    count += (file.isFile()) ? 1 : numberOfFiles(file);
                    try {
                        Mp3File mp3File = new Mp3File(file);
                        System.out.println(mp3File.getId3v2Tag().getTitle());
//                        if (mp3File.hasId3v2Tag()) {
//                            System.out.println(mp3File.getId3v2Tag().getTitle());
//                        }

                    } catch (IOException e) {
                        //e.printStackTrace();
                    } catch (InvalidDataException e) {
                        //e.printStackTrace();
                    } catch (UnsupportedTagException e) {
                        //e.printStackTrace();
                    }

                }
            }
        }


        endTime = new Date();
        timeEnd = timeFormatter.format(endTime);


        return count;
    }

    public static BlockingQueue getMusicFiles(File directory){
        File[] list = directory.listFiles();
        BlockingQueue<File> musicQueue = new BlockingQueue<File>() {
            public boolean add(File file) {
                return false;
            }

            public boolean offer(File file) {
                return false;
            }

            public void put(File file) throws InterruptedException {

            }

            public boolean offer(File file, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            public File take() throws InterruptedException {
                return null;
            }

            public File poll(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            public int remainingCapacity() {
                return 0;
            }

            public boolean remove(Object o) {
                return false;
            }

            public boolean contains(Object o) {
                return false;
            }

            public int drainTo(Collection<? super File> c) {
                return 0;
            }

            public int drainTo(Collection<? super File> c, int maxElements) {
                return 0;
            }

            public File remove() {
                return null;
            }

            public File poll() {
                return null;
            }

            public File element() {
                return null;
            }

            public File peek() {
                return null;
            }

            public int size() {
                return 0;
            }

            public boolean isEmpty() {
                return false;
            }

            public Iterator<File> iterator() {
                return null;
            }

            public Object[] toArray() {
                return new Object[0];
            }

            public <T> T[] toArray(T[] a) {
                return null;
            }

            public boolean containsAll(Collection<?> c) {
                return false;
            }

            public boolean addAll(Collection<? extends File> c) {
                return false;
            }

            public boolean removeAll(Collection<?> c) {
                return false;
            }

            public boolean retainAll(Collection<?> c) {
                return false;
            }

            public void clear() {

            }
        } ;
        Stack<File> musicStack = new Stack<File>();
        int i = 0;
        System.out.println("List size is " + list.length);
        for(File file : list){

            System.out.println(i + " - " + file.getName());
            i++;
        }

        musicStack.setSize(list.length + 1);
        musicStack.addAll(Arrays.asList(list));

        musicQueue.addAll(Arrays.asList(list));

//        return musicStack;
        return musicQueue;
    }

    public static void printMp3s(File directory) {
        int i = 1; //iterator value
//        Stack<File> musicStack = getMusicFiles(directory);
//        do {
//            getMp3s(musicStack, i);
//            i++;
//            //System.out.println(musicStack.pop());
//        } while(!musicStack.isEmpty());
        File[] list = directory.listFiles();
        BlockingQueue<File> musicQueue = new BlockingQueue<File>() {
            public boolean add(File file) {
                return false;
            }

            public boolean offer(File file) {
                return false;
            }

            public void put(File file) throws InterruptedException {

            }

            public boolean offer(File file, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            public File take() throws InterruptedException {
                return null;
            }

            public File poll(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            public int remainingCapacity() {
                return 0;
            }

            public boolean remove(Object o) {
                return false;
            }

            public boolean contains(Object o) {
                return false;
            }

            public int drainTo(Collection<? super File> c) {
                return 0;
            }

            public int drainTo(Collection<? super File> c, int maxElements) {
                return 0;
            }

            public File remove() {
                return null;
            }

            public File poll() {
                return null;
            }

            public File element() {
                return null;
            }

            public File peek() {
                return null;
            }

            public int size() {
                return 0;
            }

            public boolean isEmpty() {
                return false;
            }

            public Iterator<File> iterator() {
                return null;
            }

            public Object[] toArray() {
                return new Object[0];
            }

            public <T> T[] toArray(T[] a) {
                return null;
            }

            public boolean containsAll(Collection<?> c) {
                return false;
            }

            public boolean addAll(Collection<? extends File> c) {
                return false;
            }

            public boolean removeAll(Collection<?> c) {
                return false;
            }

            public boolean retainAll(Collection<?> c) {
                return false;
            }

            public void clear() {

            }
        };
        musicQueue.addAll(Arrays.asList(list));
        System.out.println("List size is " + list.length);
        System.out.println("MusicQueue size is " + musicQueue.size());

//        do {
//            getMp3s(musicQueue, i);
//            i++;
//        } while (!musicQueue.isEmpty());

    }

    public static void getMp3s(BlockingQueue<File> musicQueue, int iterator){
        File file = null;
        try {
            file = musicQueue.take();
            //System.out.println(file.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String mp3Title, mp3Artist, tagType;
        try {
            if (file != null) {
                Mp3File mp3File = new Mp3File(file);
                System.out.printf("--------------------------------------------\n %s %s \n--------------------------------------------\n", iterator, file.getName());
                if (mp3File.hasId3v2Tag()) {
                    mp3Title = mp3File.getId3v2Tag().getTitle();
                    mp3Artist = mp3File.getId3v2Tag().getArtist();
                    tagType = "ID3v2";

                    System.out.printf("Title: %s \n Artist: %s\n MP3 Tag Type: %s\n\n", mp3Title, mp3Artist, tagType);

                } else if (mp3File.hasId3v1Tag()) {
                    mp3Title = mp3File.getId3v1Tag().getTitle();
                    mp3Artist = mp3File.getId3v1Tag().getArtist();
                    tagType = "ID3v1Tag";

                    System.out.printf("Title: %s \n Artist: %s\n MP3 Tag Type: %s\n\n", mp3Title, mp3Artist, tagType);
                } else {
                    System.out.printf("No ID3 tag found\n\n");
                }
            } else {
                System.out.println("File was null!");

            }

        } catch (IOException e) {
            //e.printStackTrace();
            e.getCause();
        } catch (UnsupportedTagException e) {
            //e.printStackTrace();
            e.getCause();
        } catch (InvalidDataException e) {
            //e.printStackTrace();
            e.getCause();
        }
    }
}


