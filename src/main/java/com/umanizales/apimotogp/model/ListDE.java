package com.umanizales.apimotogp.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
public class    ListDE {

    private NodeDE head;
    private int size;



    public List<Motorcycle> getList(){
        List<Motorcycle> list = new ArrayList<>();     //Se crea lista
        if (head!=null){        //Si cabeza no está vacía
            NodeDE temp = head;     //Se crea temporal en cabeza
            while (temp!=null){     //Mientras temporal es diferente de nulo
                list.add(temp.getData());       //Se agrega en lista al niño
                temp = temp.getNext();      //Temporal avanza al siguiente
            }
        }
        return list;        //Se devuelve la lista
    }

    public void addBegginning(Motorcycle motorcycle) {
        if (this.head == null)      //Si la cabeza está vacía
        {
            this.head = new NodeDE(motorcycle);        //La cabeza toma el nuevo nodo
        }
        else        //Si no
        {
            NodeDE newNode = new NodeDE(motorcycle);       //Se mete niño en el nodo
            newNode.setNext(this.head);         //Se asigna como siguiente del nuevo nodo el niño que la cabeza tiene
            this.head.setPrevious(newNode);     //Se asigna como anterior del niño en cabeza al nuevo nodo
            this.head = newNode;            //La cabeza se vuelve el nuevo nodo

        }
        size ++;        //Se aumenta 1 el tamaño
    }

    public void addEnd(Motorcycle motorcycle) {
        if (head == null)       //Si la cabeza está vacía
        {
            head = new NodeDE(motorcycle);     //La cabeza toma al nuevo nodo
        }
        else        //Si no
        {
            NodeDE temp = head;     //Se crea temporal en cabeza
            while(temp.getNext()!=null)     //Mientras sel siguiente de temporal no sea nulo
            {
                temp = temp.getNext();      //Temporal avanza al siguiente
            }
            ///Ayudante está en el último
            NodeDE newNode = new NodeDE(motorcycle);   //Se mete al niño en el nodo
            temp.setNext(newNode);          //Se asigna como el siguiente del temporal el nuevo nodo
            newNode.setPrevious(temp);      //Se asigna como anterior del nodo al temporal
        }
        size ++;
    }

    public int deletePilot(String pilot){
        int count = 0;
        if (head!=null){        //Si la cabeza no está vacía
            if (Objects.equals(head.getData().getPilot(), pilot)){        //Si el piloto es igual a pilot
                head = head.getNext();      //La cabeza coge al siguiente niño
                size--;     //Se le disminuye 1 al tamaño
                count++;
            }
            else{       //Si no
                count = 1;
                NodeDE temp = head;     //Se crea temporal en cabeza
                while (temp!=null){     //Mientras temporal sea diferente de nulo
                    if (Objects.equals(temp.getNext().getData().getPilot(), pilot)){  //Si piloto igual a pilot
                        temp.setNext(temp.getNext().getNext());     //Temporal toma como siguiente al siguiente del siguiente
                        if (temp.getNext()!=null){      //Si el siguiente del temporal es diferente de nulo
                            temp.getNext().setPrevious(temp);       //Temporal hace que el anterior del siguiente sea temporal
                        }
                        count++;
                        size--;     //Se disminuye 1 al tamaño
                        break;
                    }
                    temp = temp.getNext();  //Temporal avanza al siguiente
                    count = count + 1;
                }
            }
        }
        return count;
    }

    public void addPosition(int position, Motorcycle motorcycle){
        if (position==1){       //Si posición igual a 1
            addBegginning(motorcycle);        //Se agrega la moto al inicio
        }
        else{       //Si no
            NodeDE temp = head;     //Se crea temporal en cabeza
            int place = 1;      //se crea contador en 1
            while (place<(position-1)){   //Si el contador es menor a posición-1
                temp = temp.getNext();       //Temporal pasa al siguiente
                place+=1;       //Se suma uno al contador
            }
            NodeDE newNode= new NodeDE(motorcycle);       //Se crea nodo con la moto
            newNode.setPrevious(temp);      //Se establece como anterior del nuevo nodo a temporal
            if (temp.getNext()!=null){      //Si el siguiente de temporal es diferente de nulo
                newNode.setNext(temp.getNext());        //Se establece como siguiente del nuevo nodo al siguiente de temporal
                temp.getNext().setPrevious(newNode);        //Temporal hace que el anterior del siguiente coja al nuevo nodo
            }
            temp.setNext(newNode);      //Temporal toma como siguiente al nuevo nodo
            size++;     //Se le aumenta 1 al tamaño
        }

    }

    public boolean advance(String pilot, int num){
        if (this.size>1){
            int count = 1;
            NodeDE temp = this.head;
            while (!Objects.equals(temp.getData().getPilot(), pilot)){
                count++;
                temp = temp.getNext();
            }
            if (num < count){
                addPosition((count-num),temp.getNext().getData());
                temp = temp.getPrevious();
                temp.setNext(temp.getNext().getNext());     //Temporal toma como siguiente al siguiente del siguiente
                if (temp.getNext()!=null){      //Si el siguiente del temporal es diferente de nulo
                    temp.getNext().setPrevious(temp);       //Temporal hace que el anterior del siguiente sea temporal
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean losePosition(String pilot, int num){
        if (this.size>1) {
            int count =  1;
            NodeDE temp = this.head;
            while (!Objects.equals(temp.getData().getPilot(), pilot)){
                count++;
                temp = temp.getNext();
            }
            if ((count+num)<=this.size){
                if ((count+num)==size){
                    addEnd(temp.getData());
                }
                else if ((count+num) < this.size) {
                    addPosition(count+num,temp.getData());
                }
                if (this.head==temp){
                    this.head = this.head.getNext();
                }
                else {
                    temp = temp.getPrevious();
                    temp.setNext(temp.getNext().getNext());     //Temporal toma como siguiente al siguiente del siguiente
                    temp.getNext().setPrevious(temp);       //Temporal hace que el anterior del siguiente sea temporal
                    return true;
                }
            }
        }
        return false;
    }
}
