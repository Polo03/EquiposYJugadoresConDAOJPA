package Application;

import Modelo.DAOGenerico;
import Modelo.Equipo;
import Modelo.Jugador;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class index {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        DAOGenerico<Equipo> daoEquipo=new DAOGenerico<>(Equipo.class);
        DAOGenerico<Jugador> daoJugador=new DAOGenerico<>(Jugador.class);
        daoEquipo.add(new Equipo("a","a"));
        int indice, idModificar, idEquipo, idJugador, tablaAOperar;
        float estatura, peso;
        String nombreEquipo, nombreEstadio, nombreJugador;
        do{
            System.out.println("1. Insertar");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Buscar");
            System.out.println("5. Ver todos");
            System.out.println("6. Salir");
            System.out.print("Ingrese la operación a realizar: ");
            indice = sc.nextInt();
            sc.nextLine();
            switch (indice) {
                case 1:

                    System.out.print("¿En cual de las dos tablas quiere insertar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){

                        System.out.print("Ingrese el nombre del equipo: ");
                        nombreEquipo = sc.nextLine();
                        System.out.print("Ingrese el nombre del estadio: ");
                        nombreEstadio = sc.nextLine();
                        daoEquipo.add(new Equipo(nombreEquipo,nombreEstadio));
                        System.out.println("Equipo insertado correctamente");
                    }else if(tablaAOperar==2){
                        System.out.print("Ingrese el nombre del jugador: ");
                        nombreJugador = sc.nextLine();
                        System.out.print("Ingrese la estatura del jugador en cm: ");
                        estatura = sc.nextFloat();
                        System.out.print("Ingrese el peso sin decimales del jugador en kg: ");
                        peso = sc.nextFloat();
                        System.out.print("Ingrese el id del equipo al que pertenece: ");
                        idEquipo = sc.nextInt();
                        daoJugador.add(new Jugador(nombreJugador, estatura, peso, daoEquipo.getById(idEquipo)));
                        System.out.println("Jugador insertado correctamente");
                    }else
                        System.out.print("No ha seleccionado ninguna de las dos tablas proporcionadas anteriormente.");
                    break;
                case 2:
                    System.out.print("¿En cual de las dos tablas quiere modificar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){
                        System.out.print("Ingrese el id del equipo a modificar: ");
                        idModificar = sc.nextInt();
                        System.out.print("Ingrese el nuevo nombre del equipo: ");
                        nombreEquipo = sc.nextLine();
                        System.out.print("Ingrese el nuevo nombre del estadio: ");
                        nombreEstadio = sc.nextLine();
                        daoEquipo.update(new Equipo(idModificar,nombreEquipo,nombreEstadio));
                    } else if (tablaAOperar==2) {
                        System.out.print("Ingrese el id del jugador a modificar: ");
                        idModificar = sc.nextInt();
                        System.out.print("Ingrese el nuevo nombre del jugador: ");
                        nombreJugador = sc.nextLine();
                        System.out.print("Ingrese la nueva estatura del jugador: ");
                        estatura = sc.nextFloat();
                        System.out.print("Ingrese el nuevo peso del jugador");
                        peso = sc.nextFloat();
                        System.out.println("Ingrese el id del nuevo equipo del jugador");
                        idEquipo = sc.nextInt();
                        daoJugador.update(new Jugador(idModificar, nombreJugador, estatura, peso, daoEquipo.getById(idEquipo)));
                    }else
                        System.out.println("No ha seleccionado ninguna de las dos tablas");
                    break;

                case 3:
                    System.out.print("¿En cual de las dos tablas quiere eliminar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){
                        System.out.print("Ingrese el id del equipo a eliminar: ");
                        idEquipo = sc.nextInt();
                        daoEquipo.deleteById(idEquipo);
                    } else if (tablaAOperar==2) {
                        System.out.print("Ingrese el id del jugador a eliminar: ");
                        idJugador = sc.nextInt();
                        daoJugador.deleteById(idJugador);
                    }else
                        System.out.println("No ha seleccionado ninguna de las dos tablas");
                    break;
                case 4:
                    System.out.print("¿En cual de las dos tablas quiere operar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){
                        System.out.print("Ingrese el id del equipo que quiere buscar: ");
                        idEquipo = sc.nextInt();
                        System.out.println("----------------------------------------------------------");
                        System.out.println(daoEquipo.getById(idEquipo));
                        System.out.println("----------------------------------------------------------");
                    } else if (tablaAOperar==2) {
                        System.out.print("Ingrese el id del jugador que quiere buscar: ");
                        idJugador = sc.nextInt();
                        System.out.println("----------------------------------------------------------");
                        System.out.println(daoJugador.getById(idJugador));
                        System.out.println("----------------------------------------------------------");
                    }else
                        System.out.println("No ha seleccionado ninguna de las dos tablas");
                    break;
                case 5:
                    System.out.print("¿En cual de las dos tablas quiere operar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){
                        System.out.println("----------------------------------------------------------");
                        for(Equipo e: daoEquipo.getAll()){
                            System.out.println(e.toString());
                        }
                        System.out.println("----------------------------------------------------------");
                    } else if (tablaAOperar==2) {
                        System.out.println("----------------------------------------------------------");
                        for(Jugador j: daoJugador.getAll()){
                            System.out.println(j.toString());
                        }
                        System.out.println("----------------------------------------------------------");
                    }else
                        System.out.println("No ha seleccionado ninguna de las dos tablas");
                    break;
                case 6:
                    System.out.println("Ha salido de la consola");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while(indice!=6);
    }
}
