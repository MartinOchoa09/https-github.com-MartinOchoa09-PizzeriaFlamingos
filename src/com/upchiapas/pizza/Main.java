package com.upchiapas.pizza;

import com.upchiapas.pizza.models.CatalogoPizza;
import com.upchiapas.pizza.models.ItemCompra;
import com.upchiapas.pizza.models.OrdenCompra;
import com.upchiapas.pizza.models.Pizza;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;

public class Main {
    public static void main(String[] args){

        int idPizza = 0;
        int idCompra = 0;
        ArrayList<Pizza> catalogo = new ArrayList<>();
        CatalogoPizza catalogoPizza = new CatalogoPizza(catalogo);
        ArrayList<OrdenCompra> pedidos = new ArrayList<>();


        boolean isExit = false;
        int opcion = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("--------------------------");
            System.out.println("--------------------------");
            System.out.println("\tBienvenido");
            System.out.println("1) Agregar pizza al catalogo");
            System.out.println("2) Generar el pedido");
            System.out.println("3) Imprimir las ventas realizadas");
            System.out.println("4) Salir");
            System.out.println("--------------------------");
            System.out.println("--------------------------");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    sc = new Scanner(System.in);
                    Pizza pizza = new Pizza();
                    pizza.setId(idPizza+"");
                    idPizza++;
                    System.out.println("Ingresa la especialidad de la pizza: ");
                    String especialidad;
                    especialidad = sc.nextLine();
                    pizza.setEspecialidad(especialidad);
                    System.out.println("Ingresa el precio de la pizza: ");
                    double precio = sc.nextDouble();
                    pizza.setPrecio(precio);
                    catalogoPizza.getCatalogo().add(pizza);
                    break;
                case 2:
                    sc = new Scanner(System.in);
                    OrdenCompra orden = new OrdenCompra();
                    orden.setId(idCompra+"");
                    System.out.println("Ingrese su nombre: ");
                    String nombreCliente = sc.nextLine();
                    orden.setNombreCliente(nombreCliente);
                    boolean isAddPizza = false;
                    do {
                        System.out.println("Añadir pizza a la orden:");
                        System.out.println(idPizza+" ) Terminar orden");
                        System.out.println(catalogoPizza.toString());
                        int menuOpcion = sc.nextInt();
                        if(menuOpcion != idPizza) {
                            try {
                                ItemCompra itemCompra = new ItemCompra();
                                System.out.println("Cuantas desea añadir de " + catalogoPizza.getCatalogo().get(menuOpcion).getEspecialidad());
                                itemCompra.setCantidad(sc.nextInt());
                                itemCompra.setProducto(catalogoPizza.getCatalogo().get(menuOpcion));
                                itemCompra.setSubtotal(itemCompra.getCantidad() * catalogoPizza.getCatalogo().get(menuOpcion).getPrecio());
                                orden.getProductos().add(itemCompra);
                            } catch (Exception e) {
                                System.out.println("Seleccione una opción correcta");
                            }
                        }else {
                            isAddPizza = true;
                            if(orden.getProductos().size() > 0) {
                                pedidos.add(orden);
                            }
                        }
                        System.out.println("\n\nSubtotal: " + orden.getTotal());
                    }while(!isAddPizza);
                    System.out.println("\nSubtotal: " + orden.getTotal()+"\n\n");
                    break;
                case 3:
                    System.out.println("'Reporte de ventas'");
                    for (Iterator iterator = pedidos.iterator(); iterator.hasNext();) {
                        System.out.println("-----------------------------------------------------------------------------");
                        System.out.println("-----------------------------------------------------------------------------");
                        OrdenCompra ordenReporte = (OrdenCompra) iterator.next();
                        Random random = new Random(System.nanoTime());
                        System.out.println("Numero de Orden: " + random.nextFloat());
                        System.out.println("Nombre del cliente: " + ordenReporte.getNombreCliente());
                        System.out.println("Numero de telefono del cliente: " + ordenReporte.getNoTelefonico());
                        System.out.println("Productos: ");
                        for (Iterator iterator2 = ordenReporte.getProductos().iterator(); iterator2.hasNext();) {
                            ItemCompra itemCompra = (ItemCompra) iterator2.next();
                            System.out.println("Especialidad: " + itemCompra.getProducto().getEspecialidad() + " - Cantidad: "
                                    + itemCompra.getCantidad() + " - Subtotal: " + itemCompra.getSubtotal());
                        }
                        System.out.println("Total: " + ordenReporte.getTotal());
                        System.out.println("-----------------------------------------------------------------------------");
                        System.out.println("-----------------------------------------------------------------------------");
                    }
                    break;
                case 4:
                    System.out.println("Fue un gusto atenderle, vuelva pronto!");
                    isExit = true;
                    break;
                default:
                    System.out.println("Selecciona una opcion valida");
                    break;
            }

        }while(!isExit);
    }
}
