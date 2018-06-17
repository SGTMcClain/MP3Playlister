package com.sgtmcclain;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import javax.swing.text.DateFormatter;
import java.io.File;

import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MusicScanner {

    public static void main(String[] args) {
        // Needed variables to track elapsed time
        Date startTime = null;
        Date endTime = null;
        Locale currentLocale;
        DateFormatter dateFormatter;
        DateFormat timeFormatter;
        String timeStart;
        String timeEnd;


        currentLocale = new Locale("en");
        timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, currentLocale);


        ArrayList<File> fileArrayList = new ArrayList<File>();

        // Parse the filesystem to find all audio files and place them into a blocking Queue
        listAudioFiles(new File("E:\\Music"), fileArrayList);
        //Create Blocking Queue to store files in
        BlockingQueue<File> blockingQueue = new ArrayBlockingQueue<File>(fileArrayList.size()) {
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

        System.out.println("File ArraylistSize = " + fileArrayList.size());
        System.out.println("Did the blocking queue get anything: " + blockingQueue.addAll(fileArrayList));

        System.out.println("Queue size: " + blockingQueue.size() + " " + blockingQueue.remainingCapacity());

        for (File file : fileArrayList){
                blockingQueue.offer(file);
        }
        System.out.println("Queue size after put loop: " + blockingQueue.size());


        // Mark the start time
        startTime = new Date();
        timeStart = timeFormatter.format(startTime);

        //Get the Songs and display the titles and Artists
        //getSongs(blockingQueue);

        //Mark the end time
        endTime = new Date();
        timeEnd = timeFormatter.format(endTime);

        //Print out the start and end times
        System.out.println("Started at " + timeStart);
        System.out.println("Ended at " + timeEnd);
        System.out.println("ArrayList size = " + fileArrayList.size());

    } // end main

    // Worker Functions

    public static void getSongs(BlockingQueue<File> blockingQueue){
        int count = 1;
        do {
            try {
                File file = blockingQueue.take();
                AudioFile audioFile = AudioFileIO.read(file);
                Tag tag = audioFile.getTag();


                System.out.printf("--------------------------------------------\n %s %s \n--------------------------------------------\n", file.getPath());
                String songTitle = tag.getFirst(FieldKey.TITLE);
                String songArtist = tag.getFirst(FieldKey.ARTIST);

                System.out.printf("Title: %s\n", songTitle);
                System.out.printf("Artist: %s\n\n", songArtist);
                count++;

            } catch (CannotReadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TagException e) {
                e.printStackTrace();
            } catch (ReadOnlyFileException e) {
                e.printStackTrace();
            } catch (InvalidAudioFrameException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!blockingQueue.isEmpty());

    }

    public static void listAudioFiles(File file, ArrayList<File> fileArrayList){


        try {
            //check to see if the file is a file or a directory
            if (file.isFile()) {
                //if it is a file add it to the blocking queue
                System.out.println("File: " + file.getPath());
                fileArrayList.add(file);
            } else {
                //send the files back through the listAudioFiles method
                File[] listOfFiles = file.listFiles();
                for(File singleFile : listOfFiles) {
                    listAudioFiles(singleFile, fileArrayList);
                }
            }
        } catch (StackOverflowError e){
            e.printStackTrace();
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
        }
    }

}
