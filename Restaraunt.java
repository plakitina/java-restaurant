package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Restaraunt {
    private final IDB db;
    private double sum = 0;
    private ArrayList<Dish> dishes = new ArrayList<>();
    private List<String> dishesName = new ArrayList<String>();
    public Restaraunt(IDB db) {
        this.db = db;
    }

    public void showTables() {
        Connection con = null;

        try {
            con = db.getConnection();

            String sql = "SELECT table_id, free FROM tables";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            int count = 0;
            while(rs.next()) {
                System.out.print(rs.getString("table_id") + ": " + rs.getString("free") + "\t\t");
                count++;
                if(count == 4) {
                    System.out.println();
                    count = 0;
                }
            }
            System.out.println();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean chooseTable(int table_id) {
        Connection con = null;
        boolean isFree = false;
        try {
            con = db.getConnection();

            String sql = "SELECT table_id, free FROM tables WHERE table_id=?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, table_id);

            ResultSet rs = st.executeQuery();


            if (rs.next()) {
                if((rs.getString("free")).compareTo("yes") == 0) {
                    isFree = true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(isFree)
                return true;
            return false;
        }
    }

    public void showMenu(){
        Connection con = null;
        try {
            con = db.getConnection();

            String sql = "SELECT dish_id, dish_name, dish_category, dish_price FROM menu";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getString("dish_id") + " | " + rs.getString("dish_name") + " | " + rs.getString("dish_category") + " | " + rs.getString("dish_price"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void addDish(int dish_id) {
        Connection con = null;
        try {
            con = db.getConnection();

            String sql = "SELECT dish_id, dish_name, dish_category, dish_price FROM menu WHERE dish_id=?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, dish_id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                sum += rs.getDouble("dish_price");
                Dish dish = new Dish(rs.getString("dish_name"), rs.getDouble("dish_price"));
                dishes.add(dish);
                dishesName.add(dishes.get(dishes.size()-1).getName());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void showOrder() {
        for(int i = 0; i != dishes.size(); i++) {
            if(Collections.frequency(dishesName, dishes.get(i).getName()) != 1) {
                System.out.println((i + 1) + ") " + dishes.get(i) + " x" + Collections.frequency(dishesName, dishes.get(i).getName()));
                i++;
                break;
            } else {
                System.out.println((i+1) + ") " + dishes.get(i) + " x" + 1);
            }
            for(int k = i; k != dishes.size(); k++) {
                if(Collections.frequency(dishesName, dishes.get(i).getName()) != 1) {
                    System.out.println((i+1) + ") " + dishes.get(i) + " x" + Collections.frequency(dishesName, dishes.get(i).getName()));
                }
            }
        }
    }

    public double getSum() {
        return this.sum;
    }
}
