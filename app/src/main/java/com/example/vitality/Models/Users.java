package com.example.vitality.Models;

public class Users {

    String name;
    String profilepic;
    String mail;
    String userId;


    String lastMessage;




    public Users(String profilepic, String name, String mail, String userId, String lastMessage) {
        this.profilepic = profilepic;
        this.name=name;
        this.mail = mail;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }
    

//
    public String getProfilepic() {
        return profilepic;
    }



    public String getMail() {
        return mail;
    }

    public String getUserId() {
        return userId;
    }

   public String getLastMessage() {
        return lastMessage;
    }
    public String getName() {
        return name;
    }


    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    public void setName(String name) {
        this.name = name;
    }


}


