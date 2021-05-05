import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner entrada =  new Scanner(System.in);
        String solicitud = "-";

        //Inicialización del puerto
        ArrayList<LinkedList<Integer>> MatrizContenedores = new ArrayList<>();
        ArrayList<LinkedList<Integer>> MatrizAutos = new ArrayList<>();
        ArrayList<Integer> serialesA = new ArrayList<>();
        ArrayList<Integer> serialesC = new ArrayList<>();
        int contenedores = 0, autos = 0;

        //Iniciar contendores
        for(int i = 0; i < 200; i++) {
            MatrizContenedores.add(new LinkedList<>());
            for (int j = 0; j < 5; j++) {
                if (contenedores<988){
                    MatrizContenedores.get(i).addFirst(contenedores+1);
                    serialesC.add(contenedores+1);
                    contenedores++;
                }
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


        System.out.println(MatrizAutos);
        System.out.println(MatrizContenedores);

        //--------------------------------


        while (!solicitud.equals("0")){
            System.out.print("Ingrese su solicitud: ");
            solicitud = entrada.nextLine();
            //------------------------------------
            if(solicitud.equals("recibir contenedor")){

                if (contenedores == 990){
                    System.out.println("!Alerta! (Umbral alcanzado) --> No se reciben más contenedores");
                    System.out.println("------------------------------");
                    continue;}

                System.out.print("Ingrese el serial del contenedor: ");
                String ec = entrada.nextLine();

                //verificamos que el serial del contenedor no se repita
                if (serialesA.contains(Integer.valueOf(ec))){
                    System.out.println("-Un contenedor ya tiene este serial!");
                    System.out.println("------------------------------");
                    continue; }

                //se realiza un recorrido por todos las pilas hasta hallar una incompleto
                //y se almacena este elemento dentro de esa pila
                for(int i=0; i<200; i++) {
                    if (MatrizContenedores.get(i).size() < 5) {
                        MatrizContenedores.get(i).addFirst(Integer.valueOf(ec));
                        System.out.println("-Tiempo estimado: 180 segundos");
                        System.out.println("-Contenedor almacenado en la pila: " + (i+1));
                        break;
                    }
                }
                contenedores++; //sumamos el numero de contenedores que tenemos
            }
            //------------------------------------
            else if (solicitud.equals("entregar contenedor")) {

                String serial_contenedor0;
                int tiempoC = 0, indice_contenedor = 0, aa= 0, aux, serial_contenedor, i;
                ArrayList<Integer> pil_incompletas = new ArrayList<>(), pil_usadas = new ArrayList<>();
                System.out.print("Ingrese el serial del contenedor: ");
                serial_contenedor0 = entrada.nextLine();
                serial_contenedor = Integer.parseInt(serial_contenedor0);

                //verificamos que el contenedor este en el listado de los seriales de contenedores
                if (!serialesC.contains(serial_contenedor)) {
                    System.out.println("-Automovil no encontrado");
                    System.out.println("------------------------------");
                    continue; }

                //Iteración encargado de buscar en que pila esta el serial del contenedor
                for (i = 0; i < 200; i++) {
                    LinkedList<Integer> pila1 = MatrizContenedores.get(i);
                    LinkedList<Integer> pila2 = new LinkedList<>();
                    aux = i;
                    while (pila1.peekFirst() != null) {
                        if (!pila1.peekFirst().equals(serial_contenedor)) {
                            pila2.add(pila1.peekFirst()); }
                        else { indice_contenedor = i;
                            i = 200;
                            pila2.add(pila1.peekFirst()); }
                        pila1.pollFirst(); }
                    MatrizContenedores.set(aux, pila2);
                }

                //Iteracion para encontrar las pilas que esten incompletas y almacenarlas en una lista
                for (i = 0; i < 200; i++) {
                    if (MatrizContenedores.get(i).size() != 5) { pil_incompletas.add(i); } }

                //bucle encargado de almacenar en otras pilas los contenedores mientras el ultimo elemento
                //no sea igual al serial del contenedor dado.
                while (MatrizContenedores.get(indice_contenedor).peekFirst() != serial_contenedor) {
                    if (MatrizContenedores.get(pil_incompletas.get(aa)).size() < 5) {
                        if (!pil_usadas.contains(pil_incompletas.get(aa))) {
                            pil_usadas.add(pil_incompletas.get(aa));
                        }
                        MatrizContenedores.get(pil_incompletas.get(aa)).addFirst(MatrizContenedores.get(indice_contenedor).peekFirst());
                        MatrizContenedores.get(indice_contenedor).pollFirst();
                        tiempoC += 60;
                    }else{ aa++; }
                }
                //eliminamos el ultimo elemento de la pila (el serial del contenedor)
                // aumentamos tiempo y restamos el numero de contenedores
                MatrizContenedores.get(indice_contenedor).pollFirst(); tiempoC += 240; contenedores --;
                serialesC.remove(Integer.valueOf(serial_contenedor)); //Borramos el serial de la lista

                System.out.println("-Elemento almacenado en la pila: " + (indice_contenedor+1));
                System.out.println("-Tiempo estimado: "+ tiempoC +" segundos");

                //imprime las los indices de las pilas si se almacenaron contendores en otras pilas
                if (!pil_usadas.isEmpty()){
                    if (pil_usadas.size() == 1){
                        System.out.print("-Contenedores reubicados en la pila: ");
                        System.out.println(pil_usadas.get(0));
                    }else {
                        System.out.print("-Contenedores reubicados en las pilas: ");
                        for (i = 0; i < pil_usadas.size(); i++) {
                            if (i == pil_usadas.size() - 1) {
                                System.out.println(pil_usadas.get(i) + ".");
                            } else { System.out.print(pil_usadas.get(i)+", "); }
                        }
                    }
                }
            }
            //------------------------------------
            else if(solicitud.equals("recibir auto")){

                //verifica que no se ha llegado al umbral
                if (autos == 371){
                    System.out.println("!Alerta! (Umbral alcanzado) --> No se reciben más autos");
                    System.out.println("------------------------------");
                    continue;}

                System.out.print("Ingrese el serial del auto: ");
                String of = entrada.nextLine();

                //verifica que el serial no exista en la lista de seriales de autos
                if (serialesA.contains(Integer.valueOf(of))){
                    System.out.println("-Un automóvil ya contiene este serial!");
                    System.out.println("------------------------------");
                    continue; }

                //busca una cola que no este llena, agrega el serial e imprime los requisitos
                for (int i = 0; i < 25; i++){
                    if (MatrizAutos.get(i).size() < 15){
                        MatrizAutos.get(i).add(Integer.valueOf(of));
                        serialesA.add(Integer.valueOf(of));
                        System.out.println("-Tiempo estimado: 120 segundos");
                        System.out.println("-Auto almacenado en la cola: " + (i + 1));
                        break;
                    }
                }
                autos++;
            }
            //------------------------------------
            else if(solicitud.equals("entregar auto")){

                int tiempo = 0, indice_auto = 0, aux, serial_auto, aa = 0;
                String serial_autoo;
                ArrayList<Integer> col_incompletas = new ArrayList<>(), colas_usadas = new ArrayList<>();


                System.out.print("Ingrese el serial del auto: ");
                serial_autoo = entrada.nextLine();
                serial_auto = Integer.parseInt(serial_autoo);
                if (!serialesA.contains(serial_auto)){
                    System.out.println("-Automóvil no encontrado");
                    System.out.println("------------------------------");
                    continue;
                }

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
                    } MatrizAutos.set(aux, cola2);
                }

                //Iteracion para encontrar las colas que esten incompletas
                for (int i = 0; i < 25; i++) {
                    if (MatrizAutos.get(i).size() != 15) {
                        col_incompletas.add(i);
                    }
                }

                //bucle encargado de ubicar autos en otras colas hasta que
                // el proximo elemento de una cola sea el necesitado
                while (MatrizAutos.get(indice_auto).peek() != serial_auto) {
                    if (MatrizAutos.get(col_incompletas.get(aa)).size() < 15){
                        //Si la cola incompleta aun no esta completa, encole el auto en esta cola
                        if (!colas_usadas.contains(col_incompletas.get(aa))){
                            colas_usadas.add(col_incompletas.get(aa));
                        }
                        MatrizAutos.get(col_incompletas.get(aa)).add(MatrizAutos.get(indice_auto).peek());
                        MatrizAutos.get(indice_auto).poll();
                        tiempo += 20;
                    }else if (aa == col_incompletas.size()-1){
                        //si se han lleno todas las colas, se encolan los elementos de la ultima cola en llenar
                        //en la cola donde se encuentra el auto, y viceversa;
                        MatrizAutos.get(indice_auto).add(MatrizAutos.get(col_incompletas.get(aa)).peek());
                        MatrizAutos.get(col_incompletas.get(aa)).poll();
                        MatrizAutos.get(col_incompletas.get(aa)).add(MatrizAutos.get(indice_auto).peek());
                        MatrizAutos.get(indice_auto).poll();
                        tiempo += 40;

                    } else{ aa++;} //si la cola esta completa, encole en la proxima cola incompleta
                }
                MatrizAutos.get(indice_auto).poll();// se desencola el auto
                serialesA.remove(Integer.valueOf(serial_auto));// se retira el serial del listado de seriales de autos
                tiempo += 140; autos --;

                System.out.println("-Elemento almacenado en la cola: " + (indice_auto + 1));
                System.out.println("-Tiempo estimado: "+tiempo+" segundos");

                //si se ha requerido mover autos a otras colas se imprime los indices de las colas
                if (!colas_usadas.isEmpty()){
                    if (colas_usadas.size() == 1){
                        System.out.print("-Autos reubicados en la cola: ");
                        System.out.println(colas_usadas.get(0));
                    }else {
                        System.out.print("-Autos reubicados en las colas: ");
                        for (int j = 0; j < colas_usadas.size(); j++) {
                            if (j == colas_usadas.size() - 1) {
                                System.out.println(colas_usadas.get(j) + ".");
                            }else{ System.out.print(colas_usadas.get(j)+", "); }
                        }
                    }
                }
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

            else if(solicitud.equals("0")){System.out.println("-Fin del programa _o7");}

            else{ System.out.println("-Entrada no valida"); }

            System.out.println("------------------------------");
        }
    }
}