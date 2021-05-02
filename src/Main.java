import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner entrada =  new Scanner(System.in);

        //Inicialización de contenedores
        ArrayList<LinkedList<Integer>> MatrizContenedores = new ArrayList<>();
        int contenedores = 0;
        for(int i = 0; i < 200; i++) {
            MatrizContenedores.add(new LinkedList<>());
            for (int j = 0; j < 5; j++) {
                if (contenedores<988){
                    MatrizContenedores.get(i).addFirst(contenedores+1);
                    contenedores++;}
            }
        }
        //--------------------------------

        int finalizador = 0;
        while (finalizador == 0){
            String solicitud = entrada.nextLine();

            if (contenedores == 990){System.out.println("Alerta, umbral alcanzado, no se reciben más contenedores");}
            // if (vehiculos == 371){System.out.println("Alerta, umbral alcanzado, no se reciben más vehiculos");}

            if(solicitud.equals("recibir contenedor")){}

            else if (solicitud.equals("entregar contenedor")){}

            else if(solicitud.equals("recibir vehiculo")){}

            else if(solicitud.equals("entregar vehiculo")){}

            else if(solicitud.equals("capacidad")){} //Referencia a las cantidades de contenedores y vehiculos

            else if(solicitud.equals("elementos")){} //Consulta de elementos de una pila o columna

            else{finalizador = -1;}
        }
    }
}
