//Clase que contiene la implementacion de la lista de personal
//Contiene funciones para insertar ,modificar, imprimir,calcular tiempos,eliminar 
//
import java.util.Date;

import javax.swing.JOptionPane;

public class ListaPersonal {
	
    class Personal {
    	PersonalNodo info;
        Personal ant,sig;
    }
	
    private Personal raiz;
    
    public ListaPersonal () {
        raiz=null;
    }
              
    //Funcion para insertar un nodo al inicio de la lista
    public void insertarPrimero(PersonalNodo x) {
    	Personal nuevo=new Personal();
        nuevo.info=x;
        if (raiz==null) {
            nuevo.sig=nuevo;
            nuevo.ant=nuevo;            
            raiz=nuevo;
        } else {
        	Personal ultimo=raiz.ant;
            nuevo.sig=raiz;
            nuevo.ant=ultimo;
            raiz.ant=nuevo;
            ultimo.sig=nuevo;
            raiz=nuevo;
        }
    }
    
    //Funcion para insertar un nodo al final de lista
    public void insertarUltimo(PersonalNodo x) {
    	Personal nuevo=new Personal();
        nuevo.info=x;
        if (raiz==null) {
            nuevo.sig=nuevo;
            nuevo.ant=nuevo;            
            raiz=nuevo;
        } else {
        	Personal ultimo=raiz.ant;
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
    	String tPersonal="";
        if (!vacia()) {
        	Personal RePersonal=raiz;
            do {
            	tPersonal = tPersonal + "ID: " + RePersonal.info.idPersonal + " Nombre Completo:" + " " + RePersonal.info.nombreCompleto + "\n";
            	RePersonal = RePersonal.sig;                
            } while (RePersonal!=raiz);            
        }    
        return tPersonal;
    }
    
    
    //Funcion para eliminar el nodo de Personal de acuerdo a su ID, validando que no este enturno ni asignada
    public String EliminarPersonal(int IDEPersonal)    {
    	String strEliminar="Err...";
        if (!vacia()) {
        	Personal RePersonal=raiz;
        	
        	do {
        		if (RePersonal.info.idPersonal==IDEPersonal){
	            	if (RePersonal.info.enTurno==false){
	            		if (RePersonal.info.asignada==false){
	            			borrar(IDEPersonal);            	 
	            			strEliminar = "Personal eliminado .. ID: " + RePersonal.info.idPersonal + ", " + RePersonal.info.nombreCompleto  ;
	            		}else{
	            			strEliminar="Personal se encuentra en asignado turno no se puede eliminar";
	            		}
	            	}else{
	        			strEliminar="Personal se encuentra en turno no se puede eliminar";
	        		}
        		}
	            RePersonal = RePersonal.sig;
            } while (RePersonal!=raiz);
        }   	
    	return strEliminar;
    }

    //Funcion que retorna el nodo de Personal, se usa para consultar y posteriormenete ser modificada
    public PersonalNodo TraerPersonal(int IDPersonal)    {
    	PersonalNodo Nodo=null;
        if (!vacia()) {
        	Personal RePersonal=raiz;
        	
        	do {
        		if (RePersonal.info.idPersonal==IDEPersonal){
        			Nodo=RePersonal.info;
        	    	return Nodo;
        		}
	            RePersonal = RePersonal.sig;
            } while (RePersonal!=raiz);
        }   	
    	return Nodo;
    }

    //Funcion para modificar es decir sobreescribir informacion del nodo de Personal
    public boolean ModificaPersonal(int IDPersonal, PersonalNodo x)    {
    	if (!vacia()) {
    		Personal RePersonal=raiz;
        	do {
        		if (RePersonal.info.idPersonal==IDPersonal){
        			RePersonal.info=x;
        	    	return true;
        		}
	            RePersonal = RePersonal.sig;
            } while (RePersonal!=raiz);
        }   	
    	return false;
    }

    //Funcion para asignar un paciente a un Personal que se encuentre en turno
    //upps se debio llamar asignarpaciente,, 
    public String AsignarTurno()    {
    	String IDAsignado="null";
        if (!vacia()) {
        	Personal RePersonal=raiz;
            do {
            	if (RePersonal.info.enTurno==true){
            		if (RePersonal.info.asignada==false){
            			RePersonal.info.asignada=true;
            			IDAsignado = "ID: " + RePersonal.info.idPersonal + ", " + RePersonal.info.nombreCompleto  ;
                		return IDAsignado;
            		}
            	}
            	RePersonal = RePersonal.sig;
            } while (RePersonal!=raiz);   
        }   	
    	return IDAsignado;
    }
    		
    //Funcion que recorre todos los nodos y calcula los tiempos para iniciar y finalizar turno
    public void ActualizarTurno(int pos)
    {
     	int iposNodos=1;
     	Date fechaActual = new Date();
        if (!vacia()) {
        	Personal RePersonal=raiz;
			int diferenciaSeg;
            do {
            	if (pos==iposNodos){
            		if (RePersonal.info.enTurno==false){
            			RePersonal.info.asignada=false;
            			long diferenciamili =  fechaActual.getTime()-RePersonal.info.fechaAsignada.getTime();
            			diferenciaSeg=   (int) (diferenciamili/1000);
            			//Asignar turno
            			if ( diferenciaSeg  >= RePersonal.info.IniTurnoSegundos){
            				RePersonal.info.IniTurnoSegundos=RePersonal.info.turnoSegundos ;
            				RePersonal.info.enTurno=true;
            				RePersonal.info.fechaAsignada=fechaActual;
            			}else{
            				RePersonal.info.IniTurnoSegundos =RePersonal.info.IniTurnoSegundos  - diferenciaSeg  ;            				
            			}
            		}else{
            			long diferenciamili2 =  fechaActual.getTime()-RePersonal.info.fechaAsignada.getTime();
            			diferenciaSeg=   (int) (diferenciamili2/1000);
            			//Asignar turno
            			if ( diferenciaSeg  >= RePersonal.info.IniTurnoSegundos ){
            				RePersonal.info.turnoSegundos=RePersonal.info.IniTurnoSegundos ;
            				RePersonal.info.enTurno=false;
            				RePersonal.info.asignada=false;
            				RePersonal.info.fechaAsignada=fechaActual;
            			}else{
            				RePersonal.info.turnoSegundos = RePersonal.info.IniTurnoSegundos - diferenciaSeg;            				
                		}
            		}
            		return; 
            	}
            	RePersonal = RePersonal.sig;
            	iposNodos++;
            } while (RePersonal!=raiz);   
        }
    }
	
	//Esta primera funcion fue implementada para revisar la lista 
    public Object[] imprimirTable(int pos)
    {
     	int iposNodos=1;
        if (!vacia()) {
        	Personal RePersonal=raiz;
            do {
            	if (pos==iposNodos){
            		Object[] tPersonal= {RePersonal.info.idPersonal,RevPersonal.info.nombreCompleto, RePersonal.info.telefonoContacto, 
            					RePersonal.info.turnoSegundos ,RePersonal.info.IniTurnoSegundos , RePersonal.info.enTurno , RePersonal.info.asignada};
            		return tPersonal ;
            	}
            	RePersonal = RePersonal.sig;
            	iposNodos++;
            } while (RePersonal!=raiz);   
            return null;
        }else    
        	return null;
    }
    
    //Valida si existe  Personal de acuerdo a su ID
    public boolean existePersonal(int IDbuscar)
    {
        if (!vacia()) {
        	Personal RePersonal=raiz;
            do {
            	if (IDbuscar==RePersonal.info.idPersonal){
            		return true ;
            	}
            	RePersonal = RePersonal.sig;
            } while (RePersonal!=raiz);   
            return false;
        }else    
        	return false;
    }
    
    // funcion para deerminar la catidad de Personal en el servicio
    public int CantidadPersonal()
    {
        int cant = 0;
        if (!vacia()) {
        	Personal RePersonal=raiz;
            do {
                cant++;
                RePersonal = RePersonal.sig;                
            } while (RPersonal!=raiz);
        }    
        return cant;
    }
    
    // funcion para borrar un nodo basado en su ID
    public void borrar (int pos)
    {
        if (pos <= CantidadPersonal ())    {
            if (pos == 1) {
                if (CantidadPersonal()==1) {
                    raiz=null;
                } else {
                	Personal ultimo=raiz.ant;    
                    raiz = raiz.sig;
                    ultimo.sig=raiz;
                    raiz.ant=ultimo;
                } 
            } else {
            	Personal RePersonal = raiz;
                for (int f = 1 ; f <= pos - 1 ; f++)
                	RePersonal = RePersonal.sig;
                Personal anterior = RePersonal.ant;
                RePersonal=RePersonal.sig;
                anterior.sig=RePersonal;
                RePersonal.ant=anterior;
            }
        }
    }    
    //primera implentacion este era un codigo de ejemplo para llenar la lista
    public static void main(String[] ar) {
    	ListaPersonal lc=new ListaPersonal();
    	PersonalNodo eN1=new PersonalNodo(1 , "Jorge Gomez MD"   ,0,2,2,true,true,null);
        lc.insertarPrimero(eN1);
        PersonalNodo eN2=new PersonalNodo(2,"Andres Perez FT",0,2,20,true,true,null);
        lc.insertarPrimero(eN2);
        PersonalNodo eN3=new PersonalNodo(3,"Mario  Granja J:ENF",0,2,20,true,true,null);
        lc.insertarPrimero(eN3);
        PersonalNodo eN4=new PersonalNodo(4,"Maria Gracia AUX. ENF",0,2,20,true,true,null);
        lc.insertarPrimero(eN4);
        System.out.println("Luego de insertar 4 nodos al principio");
        lc.imprimir();
        lc.insertarUltimo(eN1);
        lc.insertarUltimo(eN1);
        System.out.println("Luego de insertar 2 nodos al final");
        lc.imprimir();
        System.out.println("Cantidad de nodos:"+lc.CantidadPersonal());
        System.out.println("Luego de borrar el de la primer posición:");
        lc.borrar(1);
        lc.imprimir();
        System.out.println("Luego de borrar el de la cuarta posición:");
        lc.borrar(4);
        lc.imprimir();                
    }
}
