/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE_BE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;


/**
 *
 * @author andres
 */
public class BackEnd {

        public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 1) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        
        
        
        int portNumber = Integer.parseInt(args[0]);
        
        try 
        (
            ServerSocket serverBE = new ServerSocket(portNumber);
            Socket clientserver = serverBE.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientserver.getInputStream()));
            PrintWriter writer = new PrintWriter(clientserver.getOutputStream(),true);    
        ) 
        
        {                
            SendingThread sending = new SendingThread(clientserver);
            sending.start();
            
            String entrada, nombre="", contrasena="", mensaje="", respuesta;
            
            entrada=reader.readLine();
            
            StringTokenizer tokens = new StringTokenizer(entrada, "/");
            while(tokens.hasMoreTokens())
            {
                nombre=tokens.nextToken();
                contrasena= tokens.nextToken();
                mensaje= tokens.nextToken();
            }
            
            LogIn logeo = new LogIn();
            
            boolean validez = logeo.Conectar(nombre, contrasena);
            if(validez==true)
            {
                CeasarEncryption cesar = new CeasarEncryption();
                respuesta=cesar.Encriptar(mensaje);
            }
            else
            {
                respuesta="lo sentimos su mensaje no pudo ser encriptado.";
            }
            
            respuesta=nombre+"/"+contrasena+"/"+respuesta;
            writer.println(respuesta);
            
            clientserver.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
}
