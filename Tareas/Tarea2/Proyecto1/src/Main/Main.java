package Main;

import vista.LoginView;

public class Main {
    public static void main(String[] args) {
        LoginView login = new LoginView();  //Aqui elige que vista ver: Cliente, Login, Menu o Producto
        login.setVisible(true);
    }
}
