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

        System.out.println(MatrizAutos);
        System.out.println(MatrizContenedores);

        //--------------------------------
        String solicitud = "1";

        while (!solicitud.equals("0")){
            if (contenedores == 990){System.out.println("Alerta, umbral alcanzado, no se reciben más contenedores");}
            if (autos == 371){System.out.println("Alerta, umbral alcanzado, no se reciben más vehiculos");}
            solicitud = entrada.nextLine();
            if(solicitud.equals("recibir contenedor")){}

            else if (solicitud.equals("entregar contenedor")){}

            else if(solicitud.equals("recibir auto")){
                for (int i = 0; i < 25; i++){
                    if (MatrizAutos.get(i).size() < 15){
                        MatrizAutos.get(i).add(Integer.valueOf(entrada.nextLine()));
                        System.out.println(120 + " segundos");
                        System.out.println("Auto almacenado en la cola: " + i);
                    }
                }
            }

            else if(solicitud.equals("entregar auto")){

                int tiempo = 0; int indice_auto = 0; int aux, serial_auto;
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
                        } else {
                            MatrizAutos.get(col_incompletas.get(aa)).add(MatrizAutos.get(indice_auto).peek());
                            MatrizAutos.get(indice_auto).poll();
                            MatrizAutos.get(indice_auto).add(MatrizAutos.get(col_incompletas.get(aa)).peek());
                            MatrizAutos.get(col_incompletas.get(aa)).poll();
                            tiempo += 40;
                        }
                    }else{ aa++; }
                }
                MatrizAutos.get(indice_auto).poll();
                tiempo += 140;

                System.out.println("Elemento almacenado en la cola: " + indice_auto);
                System.out.println("tiempo estimado: "+tiempo+" segundos");
                autos --;
                solicitud = entrada.nextLine();

            }

            else if(solicitud.equals("capacidad")){} //Referencia a las cantidades de contenedores y vehiculos

            else if(solicitud.equals("elementos")){} //Consulta de elementos de una pila o columna

            else{ System.out.println("Entrada no valida"); }
        }
    }
}
