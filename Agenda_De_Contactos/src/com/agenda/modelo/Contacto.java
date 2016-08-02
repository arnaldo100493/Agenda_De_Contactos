/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agenda.modelo;

/**
 *
 * @author FABAME
 */
public class Contacto {

    private String nombre;
    private long telefono;
    private String direccion;
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contacto() {
        this.nombre = null;
        this.direccion = null;
        this.telefono = 0;
        this.email = null;
    }

    public Contacto(String nombre, long telefono, String direccion, String email) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + "|" + telefono + "|" + direccion + "|" + email;
    }

}
