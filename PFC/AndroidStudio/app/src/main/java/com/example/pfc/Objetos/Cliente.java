package com.example.pfc.Objetos;

public class Cliente {
    int id;
    String dni , tlf, nombre , apellido, email , direccion, usuario, contrasenha;
    boolean admin , sesion;

    public Cliente(){

    }
    //Constructor para registrarse
    public Cliente( String dni, String tlf, String nombre, String apellido, String email, String direccion, String usuario, String contrasenha) {

        this.dni = dni;
        this.tlf = tlf;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.admin = false;
        this.sesion = false;
    }
    public Cliente( int id ,String dni, String tlf, String nombre, String apellido, String email, String direccion, String usuario, String contrasenha, boolean admin , boolean sesion) {
        this.id = id;
        this.dni = dni;
        this.tlf = tlf;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.admin = admin;
        this.sesion = sesion;
    }

    //Constructor para añadir admin
    public Cliente(String dni, String tlf, String nombre, String apellido, String email, String direccion, String usuario, String contrasenha, boolean admin) {
        this.dni = dni;
        this.tlf = tlf;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.admin = admin;
        this.sesion = false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contrasenha;
    }

    public void setContraseña(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public boolean isSesion() {
        return sesion;
    }

    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }
}
