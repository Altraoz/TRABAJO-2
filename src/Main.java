import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner entrada =  new Scanner(System.in);

        //Inicialización del puerto
        ArrayList<LinkedList<Integer>> MatrizContenedores = new ArrayList<>();
        ArrayList<LinkedList<Integer>> MatrizAutos = new ArrayList<>();
        ArrayList<Integer> serialesA = new ArrayList<>();
        int contenedores = 0, autos = 0;

        //Iniciar contendores
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
                if (autos < 369){
                    MatrizAutos.get(i).add(autos+1);
                    serialesA.add(autos+1);
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

                for(int i=0; i<200; i++) {
                    if (MatrizContenedores.get(i).size() < 5) {
                        MatrizContenedores.get(i).addFirst(Integer.valueOf(entrada.nextLine()));
                        System.out.println(180 + " segundos");
                        System.out.println("Contenedor almacenado en la pila: " + i);
                    }
                }
                contenedores++;
            }
            //------------------------------------
            else if (solicitud.equals("entregar contenedor")){
                int tiempoC = 0; int indice_contenedor = 0; int aux, serial_contenedor;
                serial_contenedor = entrada.nextInt();

                //Iteración para buscar en que pila esta el elemento serial_contenedor
                for (int i = 0; i < 200; i++) {
                    LinkedList<Integer> pila1 = MatrizContenedores.get(i);
                    LinkedList<Integer> pila2 = new LinkedList<>();
                    aux = i;
                    while (pila1.peekFirst() != null) {
                        if (!pila1.peekFirst().equals(serial_contenedor)) {
                            pila2.addFirst(pila1.peekFirst());
                            pila1.pollFirst();
                        }else {
                            indice_contenedor = i;
                            i = 200;
                            pila2.addFirst(pila1.peekFirst());
                            pila1.pollFirst();
                        }
                    }
                    MatrizContenedores.set(aux, pila2);
                }

                //Iteracion para encontrar las pilas que esten incompletas
                ArrayList<Integer> pil_incompletas = new ArrayList<>();
                int pila_incompleta = 0;
                int aa = 0;
                for (int i = 0; i < 200; i++) {
                    if (MatrizContenedores.get(i).size() != 5) {
                        pil_incompletas.add(i);
                    }
                }

                //bucle que se encarga de acomodar la lista hasta que el proximo elemento de una pila sea el necesitado
                while (MatrizContenedores.get(indice_contenedor).peekFirst() != serial_contenedor) {
                    if (MatrizContenedores.get(pil_incompletas.get(aa)).size() < 6){
                        if (MatrizContenedores.get(pil_incompletas.get(aa)).size() < 5) {
                            MatrizContenedores.get(pil_incompletas.get(aa)).addFirst(MatrizContenedores.get(indice_contenedor).peekFirst());
                            MatrizContenedores.get(indice_contenedor).pollFirst();
                            tiempoC += 60;
                        }
                        else {
                            MatrizContenedores.get(pil_incompletas.get(aa)).addFirst(MatrizContenedores.get(indice_contenedor).peekFirst());
                            MatrizContenedores.get(indice_contenedor).pollFirst();
                            MatrizContenedores.get(indice_contenedor).addFirst(MatrizContenedores.get(pil_incompletas.get(aa)).peekFirst());
                            MatrizContenedores.get(pil_incompletas.get(aa)).pollFirst();
                            tiempoC += 120;
                        }
                    }
                    else{ aa++; }
                }

                MatrizContenedores.get(indice_contenedor).pollFirst();
                tiempoC += 420;

                System.out.println("Elemento almacenado en la pila: " + indice_contenedor);
                System.out.println("Tiempo estimado: "+ tiempoC +" segundos");
                contenedores --;
                solicitud = entrada.nextLine();

            }
            //------------------------------------
            else if(solicitud.equals("recibir auto")){
                if (autos == 371){
                    System.out.println("!Alerta! (Umbral alcanzado) --> No se reciben más autos");
                    System.out.println("------------------------------");
                    continue;}
                System.out.print("Ingrese el serial del auto: ");
                String of = entrada.nextLine();
                if (serialesA.contains(Integer.valueOf(of))){
                    System.out.println("-Un automóvil ya contiene este serial!");
                    System.out.println("------------------------------");
                    continue; }
                for (int i = 0; i < 25; i++){
                    if (MatrizAutos.get(i).size() < 15){
                        MatrizAutos.get(i).add(Integer.valueOf(of));
                        serialesA.add(Integer.valueOf(of));
                        System.out.println(120 + " segundos");
                        System.out.println("-Auto almacenado en la cola: " + (i + 1));
                        break;
                    }
                }
                autos++;
            }
            //------------------------------------
            else if(solicitud.equals("entregar auto")){

                int tiempo = 0; int indice_auto = 0; int aux, serial_auto;
                String serial_autoo;
                System.out.print("Ingrese el serial del auto: ");
                serial_autoo = entrada.nextLine();

                serial_auto = Integer.parseInt(serial_autoo);
                if (!serialesA.contains(serial_auto)){
                    System.out.println("-Automóvil no encontrado");
                    System.out.println("------------------------------");
                    continue;
                }
                serialesA.remove(Integer.valueOf(serial_auto));
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
                ArrayList<Integer> colas_usadas = new ArrayList<>();

                //bucle que se encarga de acomodar la lista hasta que el proximo elemento de una cola sea el necesitado
                while (MatrizAutos.get(indice_auto).peek() != serial_auto) {
                    if (MatrizAutos.get(col_incompletas.get(aa)).size() < 15){
                        if (!colas_usadas.contains(col_incompletas.get(aa))){
                            colas_usadas.add(col_incompletas.get(aa));
                        }
                        MatrizAutos.get(col_incompletas.get(aa)).add(MatrizAutos.get(indice_auto).peek());
                        MatrizAutos.get(indice_auto).poll();
                        tiempo += 20;
                    }else if (aa == col_incompletas.size()-1){

                        MatrizAutos.get(indice_auto).add(MatrizAutos.get(col_incompletas.get(aa)).peek());
                        MatrizAutos.get(col_incompletas.get(aa)).poll();
                        MatrizAutos.get(col_incompletas.get(aa)).add(MatrizAutos.get(indice_auto).peek());
                        MatrizAutos.get(indice_auto).poll();
                        tiempo += 40;
                    } else{ aa++; }
                }
                MatrizAutos.get(indice_auto).poll();
                tiempo += 140;

                System.out.println("-Elemento almacenado en la cola: " + (indice_auto + 1));
                System.out.println("-Tiempo estimado: "+tiempo+" segundos");
                if (!colas_usadas.isEmpty()){
                    if (colas_usadas.size() == 1){
                        System.out.print("-Autos ubicados en la cola: ");
                        System.out.println(colas_usadas.get(0));
                    }else {
                        System.out.print("-Autos ubicados en las colas: ");
                        for (int j = 0; j < colas_usadas.size(); j++) {
                            if (j == colas_usadas.size() - 1) {
                                System.out.println(colas_usadas.get(j) + ".");
                            }else{ System.out.print(colas_usadas.get(j)+", "); }
                        }
                    }
                }
                autos --;

            }
            //-------------------------------------------
            else if(solicitud.equals("capacidad")){
                System.out.println("-Contenedores: " + contenedores);
                System.out.println("-Autos: " + autos);
            }
            //-------------------------------------------
            else if(solicitud.equals("consultar")){
                System.out.print("Ingrese el tipo de elemento que desea consultar: ");
                String elemento = entrada.nextLine();
                System.out.print("Ingrese el indice del elemento que desea consultar: ");
                String indicativo = entrada.nextLine();
                if(elemento.equals("pila") && Integer.parseInt(indicativo) > 0 && Integer.parseInt(indicativo) <= 200){
                    LinkedList<Integer> pila = MatrizContenedores.get(Integer.parseInt(indicativo)-1);
                    for (Integer elem: pila) {System.out.println("-[" + elem + "]");}
                }
                else if (elemento.equals("cola") && Integer.parseInt(indicativo) > 0 && Integer.parseInt(indicativo) <= 25) {
                    LinkedList<Integer> cola = MatrizAutos.get(Integer.parseInt(indicativo)-1);
                    for (Integer elem: cola) {System.out.println("-[" + elem + "]");}
                }
                else{System.out.println("-Consulta invalida");}
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
                            System.out.println("-Pila: " + MatrizContenedores.indexOf(pila)); verificador++; break;}
                    }
                    if (verificador == 0){ System.out.println("-No se encuentra en el puerto");}
                }
                else if (elemento.equals("auto") && Integer.parseInt(serial) > 0 && Integer.parseInt(serial) <= 375) {
                    for (LinkedList cola: MatrizAutos ) {
                        if(cola.contains(Integer.parseInt(serial))){
                            System.out.println("-Cola: " + MatrizAutos.indexOf(cola)); verificador++; break;}
                    }
                    if (verificador == 0){ System.out.println("-No se encuentra en el puerto");}
                }
                else{System.out.println("-Busqueda invalida");}
            }
            //-------------------------------------------

            else if(solicitud.equals("0")){System.out.println("-Fin del programa ;)");}

            else{ System.out.println("-Entrada no valida"); }

            System.out.println("------------------------------");
        }
    }
}