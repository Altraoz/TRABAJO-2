import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner entrada =  new Scanner(System.in);

        //Inicialización del puerto
        ArrayList<LinkedList<Integer>> MatrizContenedores = new ArrayList<>();
        ArrayList<LinkedList<Integer>> MatrizAutos = new ArrayList<>();
        int contenedores = 0, autos = 0;
        for(int i = 0; i < 200; i++) {
            if (i < 25){
                MatrizAutos.add(new LinkedList<>());
                for (int j = 0; j < 15; j++){
                    if (autos < 371){
                        MatrizAutos.get(i).add(autos+1);
                        autos++;
                    }
                }
            }
            MatrizContenedores.add(new LinkedList<>());
            for (int j = 0; j < 5; j++) {
                if (contenedores<988){
                    MatrizContenedores.get(i).addFirst(contenedores+1);
                    contenedores++;}
            }
        }
        System.out.println(MatrizAutos);
        System.out.println(MatrizContenedores);
        //--------------------------------

        String solicitud = "1", owo;
        while (!solicitud.equals("0")){
            solicitud = entrada.nextLine();

            if (contenedores == 990){System.out.println("Alerta, umbral alcanzado, no se reciben más contenedores");}
            if (autos == 371){System.out.println("Alerta, umbral alcanzado, no se reciben más vehiculos");}

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
                owo = entrada.nextLine();
                for (int i = 0; i < MatrizAutos.size(); i++){ }
            }

            else if(solicitud.equals("capacidad")){} //Referencia a las cantidades de contenedores y vehiculos

            else if(solicitud.equals("elementos")){} //Consulta de elementos de una pila o columna

            else{}
        }
    }
}
