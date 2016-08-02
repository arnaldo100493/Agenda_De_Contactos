/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agenda.control;

import com.agenda.modelo.Contacto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FABAME
 */
public class ControlContactos {
   
    private ArrayList<Contacto> lista = new ArrayList<>();
  
    public void registrar(Contacto contacto) {
        lista.add(contacto);
    }

    public void eliminar(long tel) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTelefono() == tel) {
                lista.remove(i);
            }
        }
    }

    public void modificar(long tel,Contacto c) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTelefono() == tel) {
                lista.remove(i);
                lista.add(c);
            }
        }        
    }

    public boolean verificar(long tel) {
        boolean v = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTelefono() == tel) {
                v = true;                
            }
        }
        return v;
    }

    public void listar(JTable tb) {
        JTable tabla = tb;
        ((DefaultTableModel) tabla.getModel()).setNumRows(0);
        for(Contacto c: lista){
            ((DefaultTableModel) tabla.getModel()).addRow(new String[]{c.getNombre(),""+c.getTelefono(),c.getDireccion(),c.getEmail()});
        }
    }
    public ArrayList<Contacto> listar(){
        return lista;
    }
    //escribir en el fichero

    public void escribir() {
        FileWriter archivo = null;
        PrintWriter escribir = null;
        try {
            archivo = new FileWriter("datos/datos.txt");
            escribir = new PrintWriter(archivo);
            for (int i = 0; i < lista.size(); i++) {
                escribir.println(lista.get(i).toString());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fichero no Encontrado");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (Exception e) {
                
            }
        }
    }
    //cargar datos del fichero al array

    public void cargarDatos() {
        File archivo = null;
        FileReader leer = null;
        BufferedReader bufLeer;
        try {
            archivo = new File("datos/datos.txt");
            leer = new FileReader(archivo);
            bufLeer = new BufferedReader(leer);
            
            String linea;
            String aux;
            int posicion;
            
            String nombre;
            String direccion;
            long telefono;
            String email;
            
            while ((linea = bufLeer.readLine()) != null) {
                //nombre
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                nombre = aux;
                linea = linea.substring(posicion + 1);
                //telefono
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                telefono = Long.parseLong(aux);
                linea = linea.substring(posicion + 1);
                //direccion y email
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                direccion = aux;
                linea = linea.substring(posicion + 1);
                email = linea;
                
                Contacto c = new Contacto(nombre, direccion, telefono, email);
                registrar(c);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);            
        }finally{
            try {
                if(leer!=null){
                    leer.close();
                }
            } catch (Exception e) {
            }
        }
    }  
}
