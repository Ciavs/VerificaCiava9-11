package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 3000);

        try {

            // per parlare
            PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

            // perfsf ascoltare
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // per la tastiera
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            boolean cycle = true;
            try {
                while (cycle) {
                    System.out.println(br.readLine()); // ricevo la stringa di richiesta
                    
                    String command = tastiera.readLine();
                        
                        if (command.equalsIgnoreCase("Q"))
                            break;

                    
                    pr.println(command); // invio comando
                    System.out.println(br.readLine()); // ricevo la risposta e la stampo
                    
                
                }
            
            } catch (Exception e) {

            }

            s.close();
        } catch (Exception e) {
        }
    }
}
