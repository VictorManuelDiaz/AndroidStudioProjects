package com.example.objectlistapp.objects;

public class Estudiante {
    private String nombres;
    private String apellidos;
    private String carnet;
    private String correo;
    private String telefono;

    public Estudiante() {
    }

    public Estudiante(String nombres, String apellidos, String carnet, String correo, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.carnet = carnet;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
