package org.example;

import org.example.Domain.Cliente;
import org.example.Domain.ClienteDaoImplement;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        ClienteDaoImplement clienteDAO = new ClienteDaoImplement();

        Cliente cliente1 = new Cliente("Juan",10L,"Activo");
        Cliente cliente2 = new Cliente("Maria",20L,"Inactivo");
        Cliente cliente3 = new Cliente("Jose",30L,"Activo");
        Cliente cliente4 = new Cliente("Pepe",40L,"Inactivo");
        Cliente cliente5 = new Cliente("Alberto",50L,"Inactivo");




        clienteDAO.getCliente(2);
        clienteDAO.getCliente(3);



        System.out.println(clienteDAO.getClientesActivosConVentasSuperiores(10L));


        clienteDAO.estadisticas();


    }



}