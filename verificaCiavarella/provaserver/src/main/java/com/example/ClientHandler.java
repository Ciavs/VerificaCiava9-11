package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private PrintWriter pr = null;
    private BufferedReader br = null;
    Socket s;
    private static int biglietti = 5;

    public ClientHandler(Socket s){
        
        try {
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        try {
            
            boolean cycle = true;
            
            while(cycle){
                
                pr.println("Ciao client, scegli un comando fra D, A, Q :");
                String command = br.readLine(); // ricevo uno dei 3 comandi
                System.out.println("comando: " + command + " ricevuto");

                switch (command) {

                    case "D":
                        
                        pr.println(biglietti);  
                    break;
                    
                    case "A":
                        
                        if(biglietti == 0){
                            pr.println("Biglietti esauriti");
                        }else{
                            biglietti--;
                            pr.println("Biglietto acquistato");
                        }  
                    break;
                    
                    case "Q":
                    
                    pr.println("Arrivederci");
                    cycle = false;
                    s.close();
                    break;

                }
            }
        } catch (Exception e) {
            System.out.println("command not found");
        }
    }
}
