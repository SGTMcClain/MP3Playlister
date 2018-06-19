package com.sgtmcclain;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MultiThreader extends MusicScanner implements Runnable {
    ConcurrentLinkedQueue<File> queue = new ConcurrentLinkedQueue<File>();

    MultiThreader(ConcurrentLinkedQueue<File> queue){
        this.queue = queue;
    }

    public void run() {
        try{
            MusicScanner.getSongs(queue);
        } catch (NullPointerException e){

        }
    }
}
