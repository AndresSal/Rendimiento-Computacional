/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE_BE;

/**
 *
 * @author andres
 */
public class Queue {
    public static final int DEFAULT_SIZE = 5;
    
    private String data[];
    private int index;

    public Queue() {
        this.data = new String[DEFAULT_SIZE];
    }
    
    public boolean isEmpty()
    {
        return index == 0;
    }
    
    public void agregarQueue(String dato) throws Exception
    {
        if (index==DEFAULT_SIZE-1)
        {
            throw new Exception("Cola llena");
        }
        data[index]=dato;
        index++;
    }
    
    public String sacarQueue() throws Exception
    {
        if (isEmpty())
        {
            throw new Exception("Cola vacia");
        }
        String aux=data[0];
        for (int i=0;i<index-1;i++)
        {
            data[i]=data[i+1];
        }
        index--;
        return aux;
    }
}
