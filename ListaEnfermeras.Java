package Turnos;

//Clase que contiene la implementacion de la lista de enfermeras
//Contiene funciones para insertar ,modificar, imprimir,calcular tiempos,eliminar 
//
import java.util.Date;

import javax.swing.JOptionPane;

public class ListaEnfermeras {
	
    class Enfermera {
    	EnfermeraNodo info;
        Enfermera ant,sig;
    }
	
    private Enfermera raiz;
    
    public ListaEnfermeras () {
        raiz=null;
    }
              
    //Funcion para insertar un nodo al inicio de la lista
    public void insertarPrimero(EnfermeraNodo x) {
    	Enfermera nuevo=new Enfermera();
        nuevo.info=x;
        if (raiz==null) {
            nuevo.sig=nuevo;
            nuevo.ant=nuevo;            
            raiz=nuevo;
        } else {
        	Enfermera ultimo=raiz.ant;
            nuevo.sig=raiz;
            nuevo.ant=ultimo;
            raiz.ant=nuevo;
            ultimo.sig=nuevo;
            raiz=nuevo;
        }
    }
    
    //Funcion para insertar un nodo al final de lista
    public void insertarUltimo(EnfermeraNodo x) {
    	Enfermera nuevo=new Enfermera();
        nuevo.info=x;
        if (raiz==null) {
            nuevo.sig=nuevo;
            nuevo.ant=nuevo;            
            raiz=nuevo;
        } else {
        	Enfermera ultimo=raiz.ant;
            nuevo.sig=raiz;
            nuevo.ant=ultimo;
            raiz.ant=nuevo;
            ultimo.sig=nuevo;
        }
    }    
    
    // verificar si la lista esta vacia
    public boolean vacia ()
    {
        if (raiz == null)
            return true;
        else
            return false;
    }
    
    //Funcion inicialmente usada para mostrar la info cuando no estaba implentado el jtable
    public String imprimir()
    {
    	String tEnfermera="";
        if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
            do {
            	tEnfermera = tEnfermera + "ID: " + ReEnfermera.info.idEnfermera + " Nombre Completo:" + " " + ReEnfermera.info.nombreCompleto + "\n";
            	ReEnfermera = ReEnfermera.sig;                
            } while (ReEnfermera!=raiz);            
        }    
        return tEnfermera;
    }
    
    
    //Funcion para eliminar el nodo de enfermera de acuerdo a su ID, validando que no este enturno ni asignada
    public String EliminarEnfermera(int IDEnfernera)    {
    	String strEliminar="Err...";
        if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
        	
        	do {
        		if (ReEnfermera.info.idEnfermera==IDEnfernera){
	            	if (ReEnfermera.info.enTurno==false){
	            		if (ReEnfermera.info.asignada==false){
	            			borrar(IDEnfernera);            	 
	            			strEliminar = "Enfermera eliminada .. ID: " + ReEnfermera.info.idEnfermera + ", " + ReEnfermera.info.nombreCompleto  ;
	            		}else{
	            			strEliminar="Enfermera se encuentra en asignada turno no se puede eliminar";
	            		}
	            	}else{
	        			strEliminar="Enfermera se encuentra en turno no se puede eliminar";
	        		}
        		}
	            ReEnfermera = ReEnfermera.sig;
            } while (ReEnfermera!=raiz);
        }   	
    	return strEliminar;
    }

    //Funcion que retorna el nodo de enfermera, se usa para consultar y posteriormenete ser modificada
    public EnfermeraNodo TraerEnfermera(int IDEnfernera)    {
    	EnfermeraNodo Nodo=null;
        if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
        	
        	do {
        		if (ReEnfermera.info.idEnfermera==IDEnfernera){
        			Nodo=ReEnfermera.info;
        	    	return Nodo;
        		}
	            ReEnfermera = ReEnfermera.sig;
            } while (ReEnfermera!=raiz);
        }   	
    	return Nodo;
    }

    //Funcion para modificar es decir sobreescribir informacion del nodo de enfermera
    public boolean ModificaEnfermera(int IDEnfernera, EnfermeraNodo x)    {
    	if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
        	do {
        		if (ReEnfermera.info.idEnfermera==IDEnfernera){
        			ReEnfermera.info=x;
        	    	return true;
        		}
	            ReEnfermera = ReEnfermera.sig;
            } while (ReEnfermera!=raiz);
        }   	
    	return false;
    }

    //Funcion para asignar un paciente a una enfermera que se encuentre
    //upps se debio llamar asignarpaciente,, 
    public String AsignarTurno()    {
    	String IDAsignado="null";
        if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
            do {
            	if (ReEnfermera.info.enTurno==true){
            		if (ReEnfermera.info.asignada==false){
            			ReEnfermera.info.asignada=true;
            			IDAsignado = "ID: " + ReEnfermera.info.idEnfermera + ", " + ReEnfermera.info.nombreCompleto  ;
                		return IDAsignado;
            		}
            	}
            	ReEnfermera = ReEnfermera.sig;
            } while (ReEnfermera!=raiz);   
        }   	
    	return IDAsignado;
    }
    		
    //Funcion que recorre todos los nodos y calcula los tiempos para iniciar y finalizar turno
    public void ActualizarTurno(int pos)
    {
     	int iposNodos=1;
     	Date fechaActual = new Date();
        if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
			int diferenciaSeg;
            do {
            	if (pos==iposNodos){
            		if (ReEnfermera.info.enTurno==false){
            			ReEnfermera.info.asignada=false;
            			long diferenciamili =  fechaActual.getTime()-ReEnfermera.info.fechaAsignada.getTime();
            			diferenciaSeg=   (int) (diferenciamili/1000);
            			//Asignar turno
            			if ( diferenciaSeg  >= ReEnfermera.info.IniTurnoSegundos){
            				ReEnfermera.info.IniTurnoSegundos=ReEnfermera.info.turnoSegundos ;
            				ReEnfermera.info.enTurno=true;
            				ReEnfermera.info.fechaAsignada=fechaActual;
            			}else{
            				ReEnfermera.info.IniTurnoSegundos =ReEnfermera.info.IniTurnoSegundos  - diferenciaSeg  ;            				
            			}
            		}else{
            			long diferenciamili2 =  fechaActual.getTime()-ReEnfermera.info.fechaAsignada.getTime();
            			diferenciaSeg=   (int) (diferenciamili2/1000);
            			//Asignar turno
            			if ( diferenciaSeg  >= ReEnfermera.info.IniTurnoSegundos ){
            				ReEnfermera.info.turnoSegundos=ReEnfermera.info.IniTurnoSegundos ;
            				ReEnfermera.info.enTurno=false;
            				ReEnfermera.info.asignada=false;
            				ReEnfermera.info.fechaAsignada=fechaActual;
            			}else{
            				ReEnfermera.info.turnoSegundos = ReEnfermera.info.IniTurnoSegundos - diferenciaSeg;            				
                		}
            		}
            		return; 
            	}
            	ReEnfermera = ReEnfermera.sig;
            	iposNodos++;
            } while (ReEnfermera!=raiz);   
        }
    }
	
	//Esta primera funcion fue implementada para revisar la lista cuando no habia Jtable
    public Object[] imprimirTable(int pos)
    {
     	int iposNodos=1;
        if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
            do {
            	if (pos==iposNodos){
            		Object[] tEnfermera = {ReEnfermera.info.idEnfermera,ReEnfermera.info.nombreCompleto, ReEnfermera.info.telefonoContacto, 
            					ReEnfermera.info.turnoSegundos ,ReEnfermera.info.IniTurnoSegundos , ReEnfermera.info.enTurno , ReEnfermera.info.asignada};
            		return tEnfermera ;
            	}
            	ReEnfermera = ReEnfermera.sig;
            	iposNodos++;
            } while (ReEnfermera!=raiz);   
            return null;
        }else    
        	return null;
    }
    
    //Valida si existe una enfermera de acuerdo a su ID
    public boolean existeEnfermera(int IDbuscar)
    {
        if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
            do {
            	if (IDbuscar==ReEnfermera.info.idEnfermera){
            		return true ;
            	}
            	ReEnfermera = ReEnfermera.sig;
            } while (ReEnfermera!=raiz);   
            return false;
        }else    
        	return false;
    }
    
    // funcion para deerminar la catidad de enfermeras en el hospicio
    public int CantidadEnfermeras ()
    {
        int cant = 0;
        if (!vacia()) {
        	Enfermera ReEnfermera=raiz;
            do {
                cant++;
                ReEnfermera = ReEnfermera.sig;                
            } while (ReEnfermera!=raiz);
        }    
        return cant;
    }
    
    // funcion para borrar un nodo basado en su ID
    public void borrar (int pos)
    {
        if (pos <= CantidadEnfermeras ())    {
            if (pos == 1) {
                if (CantidadEnfermeras()==1) {
                    raiz=null;
                } else {
                	Enfermera ultimo=raiz.ant;    
                    raiz = raiz.sig;
                    ultimo.sig=raiz;
                    raiz.ant=ultimo;
                } 
            } else {
            	Enfermera ReEnfermera = raiz;
                for (int f = 1 ; f <= pos - 1 ; f++)
                	ReEnfermera = ReEnfermera.sig;
                Enfermera anterior = ReEnfermera.ant;
                ReEnfermera=ReEnfermera.sig;
                anterior.sig=ReEnfermera;
                ReEnfermera.ant=anterior;
            }
        }
    }    
    //primera implentacion este era un codigo de ejemplo para llenar la lista
    public static void main(String[] ar) {
    	ListaEnfermeras lc=new ListaEnfermeras();
    	EnfermeraNodo eN1=new EnfermeraNodo(1 , "Carolina Jimenez"   ,0,2,2,true,true,null);
        lc.insertarPrimero(eN1);
        EnfermeraNodo eN2=new EnfermeraNodo(2,"Andrea Siabato",0,2,20,true,true,null);
        lc.insertarPrimero(eN2);
        EnfermeraNodo eN3=new EnfermeraNodo(3,"Marina Granciera",0,2,20,true,true,null);
        lc.insertarPrimero(eN3);
        EnfermeraNodo eN4=new EnfermeraNodo(4,"Dubina Rubina",0,2,20,true,true,null);
        lc.insertarPrimero(eN4);
        System.out.println("Luego de insertar 4 nodos al principio");
        lc.imprimir();
        lc.insertarUltimo(eN1);
        lc.insertarUltimo(eN1);
        System.out.println("Luego de insertar 2 nodos al final");
        lc.imprimir();
        System.out.println("Cantidad de nodos:"+lc.CantidadEnfermeras());
        System.out.println("Luego de borrar el de la primer posición:");
        lc.borrar(1);
        lc.imprimir();
        System.out.println("Luego de borrar el de la cuarta posición:");
        lc.borrar(4);
        lc.imprimir();                
    }
}
