/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainmodel;

import java.sql.Array;
import java.util.ArrayList;

/**
 *
 * @author 742227
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    
    private ArrayList<Integer> phone;
    private int address;
    private String email;
    
    private int hourlyRate;
    private int hours;
    
    public User(String username, int address,ArrayList<Integer> phone, String password, String firstName, String lastName,String role, String email, int hourlyRate, int hours) {
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Integer> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<Integer> phone) {
        this.phone = phone;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    
    
    
    
    
}
