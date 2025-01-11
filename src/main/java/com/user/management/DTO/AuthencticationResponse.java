package com.user.management.DTO;

import com.user.management.model.user;

public class AuthencticationResponse {
    
    private user User;
    private String token;
    private boolean sucess;

    public AuthencticationResponse(user User, String token,boolean sucess){
        this.User = User;
        this.token = token;
        this.sucess = sucess;
    }

    public user getUser(){
        return User;
    }

    public void setUser(user user){
        this.User = user;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public boolean getSuccess(){
        return sucess;
    }

    public void setSuccess(boolean sucess){
        this.sucess = sucess;
    }

}
