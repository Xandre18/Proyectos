package com.example.proyectoerp.objects;

public class User {

    private String name , paswd;
    private boolean admin;

    public User(String name, String paswd, Boolean admin) {
        this.name = name;
        this.paswd = paswd;
        this.admin = admin;

    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaswd() {
        return paswd;
    }

    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }
}
