package com.example.proyectoerp.objects;

public class ContControler {
    private int id, mount, balance;

    public ContControler(int id, int mount, int balance) {
        this.id = id;
        this.mount = mount;
        this.balance = balance;
    }

    public ContControler(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
