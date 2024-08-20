package edu.school21.sockets.models;
public class User{
    private Long id;
    private String userName;
    private String password;
    public User(){}

    public User(Long id, String userName, String password){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {return id;}

    public String getUserName() {return userName;}

    public String getPassword() {return password;}

    public void setUserName(String userName) {this.userName = userName;}

    public void setPassword(String password) {this.password = password;}

    public void setId(Long id) {this.id = id;}

    public String toString(){
        return "User{id = '" + id + "', userName = '" + userName + "', password = '" + password + "'}";
    }
}