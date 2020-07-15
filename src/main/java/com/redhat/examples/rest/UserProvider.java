package com.redhat.examples.rest;
public class UserProvider {

    private String globaluserid;
    private String firstName;
    private String lastName;

    public UserProvider() {

    }
 
    public UserProvider(String globaluserid, String firstName, String lastName) {
        this.globaluserid = globaluserid;
        this.firstName = firstName;
        this.lastName = lastName;
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
    public  void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGlobalUserID() {
        return globaluserid;
    }
    public void setGlobalUserID(String id) {
        this.globaluserid = globaluserid;
    }
 }
