//package com.sgtmcclain;
//
//
//
//import com.mpatric.mp3agic.InvalidDataException;
//import com.mpatric.mp3agic.Mp3File;
//import com.mpatric.mp3agic.UnsupportedTagException;
//
//import java.io.*;
//
//public class ReadFiles {
//
//    public static int numberOfFiles(File directory) {
//
//
//        if (directory.isFile())
//            return(1);
//        else {
//            File[] list = directory.listFiles();
//
//            int count = 0;
//
//            if (list != null)
//                for (File file : list) {
//                    String filePath = file.getPath();
//                    count += (file.isFile()) ? 1 : numberOfFiles(file);
//                    try {
//                        Mp3File mp3File = new Mp3File(file);
//                        if (mp3File.hasId3v2Tag()){
//                            System.out.println(mp3File.getId3v2Tag());
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (InvalidDataException e) {
//                            e.printStackTrace();
//                    } catch (UnsupportedTagException e) {
//                        e.printStackTrace();
//                    }
//                    return(count);
//        }
//    }
//
//    public static void main(String[] args) {
//        System.out.println(numberOfFiles(new File("E:\\Music")));
//    }
//        return Integer.parseInt(null);
//    }
//}
