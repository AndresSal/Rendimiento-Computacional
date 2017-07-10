/*
 * To change this license header, choose License Headers inBE Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template inBE the editor.
 */
package com.FE_BE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author andres
 */
public class FrontEnd {
    
    public static void main (String args[])
    {
      
      if (args.length < 2) 
      {
        System.err.println("Usage: java EchoServer <port number>");
        System.exit(1);
      }
    
      int portNumberThread = Integer.parseInt(args[0]);
      //String hostname = args[1];
      int portNumberBE = Integer.parseInt(args[1]);
      
      boolean listening = true;
      try 
      (        
        ServerSocket threadServerSocket = new ServerSocket(portNumberThread);
        //Socket clientSocketBE = new Socket(hostname, portNumberBE);
        ServerSocket clientSocketBE = new ServerSocket(portNumberBE);  
      )
      {
          String inputlineBE, inputlineTh;
          while (listening)
          {
//------------------------------------------------------------------------------
//Comunicacion con Thread 
              Socket threadClientSocket = threadServerSocket.accept();
              PrintWriter outTh = new PrintWriter(threadClientSocket.getOutputStream(),true);
              BufferedReader inTh = new BufferedReader(new InputStreamReader(threadClientSocket.getInputStream()));
              Queue colainput = new Queue();
              inputlineTh = inTh.readLine();
              colainput.agregarQueue(inputlineTh);
//------------------------------------------------------------------------------              
//Comunicacion con Servidor BE
              
//              ReadingThread reader =  new ReadingThread(clientSocketBE);
//              reader.start();
//              PrintWriter outBE = new PrintWriter(clientSocketBE.getOutputStream(),true);
//              BufferedReader inBE = new BufferedReader(new InputStreamReader(clientSocketBE.getInputStream()));
//              outBE.println(colainput.sacarQueue());
//              inputlineBE = inBE.readLine();
//              outTh.println(inputlineBE);
                
                Socket BEsocket=clientSocketBE.accept();
                
                PrintWriter outBE = new PrintWriter(BEsocket.getOutputStream(),true);
                BufferedReader inBE = new BufferedReader(new InputStreamReader(BEsocket.getInputStream()));
                System.out.println(inBE.readLine());
                
                outBE.println(colainput.sacarQueue());
                inputlineBE = inBE.readLine();
                outTh.println(inputlineBE);
                

//------------------------------------------------------------------------------              
          }
          clientSocketBE.close();
          threadServerSocket.close();
	} 
        
        catch (Exception e) 
        {
            e.getStackTrace();
       	}
    }
}
