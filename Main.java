package com.company;

import java.sql.*;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        IDB db = new Database();
        Restaraunt restaraunt = new Restaraunt(db);
        MyApplication app = new MyApplication(restaraunt);
        app.start();
    }
}

