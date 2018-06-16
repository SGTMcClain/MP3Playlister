package com.sgtmcclain;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.IOException;
import java.util.*;

import java.text.DateFormat;


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


            if (list != null)


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


        endTime = new Date();
        timeEnd = timeFormatter.format(endTime);


        return count;
    }

    public static Stack getMusicFiles(File directory){
        File[] list = directory.listFiles();
        Stack<File> musicStack = new Stack<File>();
        int i = 0;
        for(File file : list){

            System.out.println(i + " - " + file.getName());
            i++;
        }

        musicStack.addAll(Arrays.asList(list));

        return musicStack;
    }

    public static void printMp3s(File directory) {
        int i = 1; //iterator value
        Stack<File> musicStack = getMusicFiles(directory);
        do {
            getMp3s(musicStack, i);
            i++;
            //System.out.println(musicStack.pop());
        } while(!musicStack.isEmpty());

    }

    public static void getMp3s(Stack<File> musicStack, int iterator){
        File file = musicStack.pop();
        String mp3Title, mp3Artist, tagType;
        try {
            Mp3File mp3File = new Mp3File(file);
            System.out.printf("--------------------------------------------\n %s %s \n--------------------------------------------\n", iterator, file.getName());
            if (mp3File.hasId3v2Tag()){
                mp3Title = mp3File.getId3v2Tag().getTitle();
                mp3Artist = mp3File.getId3v2Tag().getArtist();
                tagType = "ID3v2";

                System.out.printf("Title: %s \n Artist: %s\n MP3 Tag Type: %s\n\n", mp3Title, mp3Artist, tagType);

            } else if (mp3File.hasId3v1Tag()){
                mp3Title = mp3File.getId3v1Tag().getTitle();
                mp3Artist = mp3File.getId3v1Tag().getArtist();
                tagType = "ID3v1Tag";

                System.out.printf("Title: %s \n Artist: %s\n MP3 Tag Type: %s\n\n", mp3Title, mp3Artist, tagType);
            } else {
                System.out.printf("No ID3 tag found\n\n");
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


