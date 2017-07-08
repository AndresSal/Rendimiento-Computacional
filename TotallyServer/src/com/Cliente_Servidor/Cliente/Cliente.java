/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cliente_Servidor.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author andres
 */
public class Cliente 
{
    public static void main (String args[]) throws IOException
    {
        if (args.length != 5)
        {
            System.err.println("Ingrese host name y puerto del servidor");
            System.exit(1);
        }
        
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String name = args[2];
        String password = args[3];
        String message= args[4];
        
        try
        (
            Socket clientsocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(clientsocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        )
        {
       
            out.println(name);
            out.println(password);
            out.println(message);
            
            System.out.println("respuesta: "+in.readLine());
       
        }
        catch (UnknownHostException e)
        {
            System.err.println("No hay contacto con puerto: "+portNumber);
            System.exit(1);   
        }
        catch (IOException e)
        {
            System.err.println("No se puede enviar o recibir contenido al servidor: "+hostName);
            System.exit(1);   
        }
        
    }
}