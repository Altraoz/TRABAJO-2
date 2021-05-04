import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner entrada =  new Scanner(System.in);

        //Inicialización del puerto
        ArrayList<LinkedList<Integer>> MatrizContenedores = new ArrayList<>();
        ArrayList<LinkedList<Integer>> MatrizAutos = new ArrayList<>();
        int contenedores = 0, autos = 0;

        //Iniciar contendeores
        for(int i = 0; i < 200; i++) {
            MatrizContenedores.add(new LinkedList<>());
            for (int j = 0; j < 5; j++) {
                if (contenedores<988){
                    MatrizContenedores.get(i).addFirst(contenedores+1);
                    contenedores++;}
            }
        }

        //Iniciar autos
        for (int i = 0; i < 25; i++){
            MatrizAutos.add(new LinkedList<>());
            for (int j = 0; j < 15; j++){
                if (autos < 371){
                    MatrizAutos.get(i).add(autos+1);
                    autos++;
                }
            }
        }
        //salidas de verificación que se deben retirar al final
        System.out.println(MatrizAutos);
        System.out.println(MatrizContenedores);
        //--------------------------------
        String solicitud = "1";

        while (!solicitud.equals("0")){
            System.out.print("Ingrese su solicitud: ");
            solicitud = entrada.nextLine();

            //------------------------------------
            if(solicitud.equals("recibir contenedor")){
                if (contenedores == 988){
                    System.out.println("!Alerta! (Umbral alcanzado) --> No se reciben más contenedores");
                    System.out.println("------------------------------");
                    continue;}
            }
            //------------------------------------
            else if (solicitud.equals("entregar contenedor")){}
            //------------------------------------
            else if(solicitud.equals("recibir auto")){
                if (autos == 371){
                    System.out.println("!Alerta! (Umbral alcanzado) --> No se reciben más autos");
                    System.out.println("------------------------------");
                    continue;}
                for (int i = 0; i < 25; i++){
                    if (MatrizAutos.get(i).size() < 15){
                        MatrizAutos.get(i).add(Integer.valueOf(entrada.nextLine()));
                        System.out.println(120 + " segundos");
                        System.out.println("Auto almacenado en la cola: " + (i + 1));
                    }
                }
                autos++;
            }
            //------------------------------------
            else if(solicitud.equals("entregar auto")){

                int tiempo = 0; int indice_auto = 0; int aux, serial_auto;
                System.out.print("Ingrese el serial del auto: ");
                serial_auto = entrada.nextInt();

                //Iteración para buscar en que cola esta el elemento serial_auto
                for (int i = 0; i < 25; i++) {
                    LinkedList<Integer> cola1 = MatrizAutos.get(i);
                    LinkedList<Integer> cola2 = new LinkedList<>();
                    aux = i;
                    while (cola1.peek() != null) {
                        if (!cola1.peek().equals(serial_auto)) {
                            cola2.add(cola1.peek());
                            cola1.poll();
                        } else {
                            indice_auto = i;
                            i = 25;
                            cola2.add(cola1.peek());
                            cola1.poll();
                        }
                    }
                    MatrizAutos.set(aux, cola2);
                }
                //Iteracion para encontrar las colas que esten incompletas
                ArrayList<Integer> col_incompletas = new ArrayList<>();
                int cola_incompleta = 0;
                int aa = 0;
                for (int i = 0; i < 25; i++) {
                    if (MatrizAutos.get(i).size() != 15) {
                        col_incompletas.add(i);
                    }
                }

                //bucle que se encarga de acomodar la lista hasta que el proximo elemento de una cola sea el necesitado
                while (MatrizAutos.get(indice_auto).peek() != serial_auto) {
                    if (MatrizAutos.get(col_incompletas.get(aa)).size() < 16){
                        if (MatrizAutos.get(col_incompletas.get(aa)).size() < 15) {
                            MatrizAutos.get(col_incompletas.get(aa)).add(MatrizAutos.get(indice_auto).peek());
                            MatrizAutos.get(indice_auto).poll();
                            tiempo += 20;
                        }
                        else {
                            MatrizAutos.get(col_incompletas.get(aa)).add(MatrizAutos.get(indice_auto).peek());
                            MatrizAutos.get(indice_auto).poll();
                            MatrizAutos.get(indice_auto).add(MatrizAutos.get(col_incompletas.get(aa)).peek());
                            MatrizAutos.get(col_incompletas.get(aa)).poll();
                            tiempo += 40;
                        }
                    }
                    else{ aa++; }
                }
                MatrizAutos.get(indice_auto).poll();
                tiempo += 140;

                System.out.println("Elemento almacenado en la cola: " + (indice_auto + 1));
                System.out.println("tiempo estimado: "+tiempo+" segundos");
                autos --;
                solicitud = entrada.nextLine();

            }
            //-------------------------------------------
            else if(solicitud.equals("capacidad")){
                System.out.println("contenedores: " + contenedores);
                System.out.println("autos: " + autos);
            }
            //-------------------------------------------
            else if(solicitud.equals("consultar")){
                System.out.print("Ingrese el tipo de elemento que desea consultar: ");
                String elemento = entrada.nextLine();
                System.out.print("Ingrese el indice del elemento que desea consultar: ");
                String indicativo = entrada.nextLine();
                if(elemento.equals("pila") && Integer.parseInt(indicativo) > 0 && Integer.parseInt(indicativo) <= 200){
                    LinkedList<Integer> pila = MatrizContenedores.get(Integer.parseInt(indicativo)-1);
                    for (Integer elem: pila) {System.out.println("[" + elem + "]");}
                }
                else if (elemento.equals("cola") && Integer.parseInt(indicativo) > 0 && Integer.parseInt(indicativo) <= 25) {
                    LinkedList<Integer> cola = MatrizAutos.get(Integer.parseInt(indicativo)-1);
                    for (Integer elem: cola) {System.out.println("[" + elem + "]");}
                }
                else{System.out.println("Consulta invalida");}
            }
            //-------------------------------------------
            else if(solicitud.equals("buscar")){
                System.out.print("Ingrese el tipo de elemento que desea buscar: ");
                String elemento = entrada.nextLine();
                System.out.print("Ingrese el serial del elemento que desea buscar: ");
                String serial = entrada.nextLine();
                int verificador = 0;
                if(elemento.equals("contenedor") && Integer.parseInt(serial) > 0 && Integer.parseInt(serial) <= 1000){
                    for (LinkedList pila: MatrizContenedores ) {
                        if(pila.contains(Integer.parseInt(serial))){
                            System.out.println("pila: " + MatrizContenedores.indexOf(pila)); verificador++; break;}
                    }
                    if (verificador == 0){ System.out.println("No se encuentra en el puerto");}
                }
                else if (elemento.equals("auto") && Integer.parseInt(serial) > 0 && Integer.parseInt(serial) <= 375) {
                    for (LinkedList cola: MatrizAutos ) {
                        if(cola.contains(Integer.parseInt(serial))){
                            System.out.println("cola: " + MatrizAutos.indexOf(cola)); verificador++; break;}
                    }
                    if (verificador == 0){ System.out.println("No se encuentra en el puerto");}
                }
                else{System.out.println("Busqueda invalida");}
            }
            //-------------------------------------------

            else if(solicitud.equals("0")){System.out.println("Fin del programa");}

            else{ System.out.println("Entrada no valida"); }

            System.out.println("------------------------------");
        }
    }
}