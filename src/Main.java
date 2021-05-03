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
        	LinkedList<Integer> pila = new LinkedList<>();
			MatrizContenedores.add(pila);
            for (int j = 0; j < 5; j++) {
                if (contenedores<988){
                    MatrizContenedores.get(i).addFirst(contenedores+1);
                    contenedores++;
                    }
            }
        }
        //--------------------------------

        int finalizador = 0;
        while (finalizador == 0){
            String solicitud = entrada.nextLine();

            if (contenedores == 990){System.out.println("Alerta, umbral alcanzado, no se reciben más contenedores");}
            // if (vehiculos == 371){System.out.println("Alerta, umbral alcanzado, no se reciben más vehiculos");}

            if(solicitud.equals("recibir contenedor")){
            	int c=0;
            	System.out.print("Ingrese el serial del contenedor ");
            	int contenedor= entrada.nextInt();
            	for (int i = 0; i < 200; i++){
					if (MatrizContenedores.get(i).contains(contenedor)) {
            			System.out.print("Este contenedor ya esta en la pila");
            			break;
					}
					
					else if (MatrizContenedores.get(i).size() < 5 ) {
						if(MatrizContenedores.get(i).contains(contenedor)) {
	            			c=1;}
            			MatrizContenedores.get(i).addFirst(contenedor);
                        System.out.println(180 + " segundos");
                        System.out.println("Contenedor almacenado en la pila: " + i);
                            }
            
                }
            }
            		
                
            else if (solicitud.equals("entregar contenedor")){
            	
            }

            else if(solicitud.equals("recibir vehiculo")){
  
            }

            else if(solicitud.equals("entregar vehiculo")){}

            else if(solicitud.equals("capacidad")){} //Referencia a las cantidades de contenedores y vehiculos

            else if(solicitud.equals("elementos")){} //Consulta de elementos de una pila o columna

            else{finalizador = -1;}
        }
    }
}