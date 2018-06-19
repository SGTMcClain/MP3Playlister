package com.sgtmcclain;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

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

        ConcurrentLinkedQueue<File> queue = new ConcurrentLinkedQueue<File>();

        System.out.println("File ArraylistSize = " + fileArrayList.size());
        System.out.println("Did the blocking queue get anything: " + queue.addAll(fileArrayList));

        System.out.println("Queue size after put loop: " + queue.size());


        // Mark the start time
        startTime = new Date();
        timeStart = timeFormatter.format(startTime);

        //Get the Songs and display the titles and Artists
        //getSongs(queue);
        MultiThreader getMusicThread = new MultiThreader(queue);
        MultiThreader getMusicThread2 = new MultiThreader(queue);
        MultiThreader getMusicThread3 = new MultiThreader(queue);

        getMusicThread.run();
        getMusicThread2.run();
        getMusicThread3.run();


        //Mark the end time
        endTime = new Date();
        timeEnd = timeFormatter.format(endTime);

        //Print out the start and end times
        System.out.println("Started at " + timeStart);
        System.out.println("Ended at " + timeEnd);
        System.out.println("ArrayList size = " + fileArrayList.size());

    } // end main

    // Worker Functions

    public static void getSongs(ConcurrentLinkedQueue<File> queue){
        int count = 1;
        do {
            try {
                File file = queue.poll();
                AudioFile audioFile = AudioFileIO.read(file);
                Tag tag = audioFile.getTag();

                System.out.printf("--------------------------------------------\n %s %s \n--------------------------------------------\n", count, file.getPath());
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
            }
        } while (!queue.isEmpty());

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
