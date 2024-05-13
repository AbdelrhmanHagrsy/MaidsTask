package com.example.Sales.Management.System.jwtDto;

import lombok.Getter;
import lombok.Setter;


public class UserResponseDto {
    private String name;
    private String email;
//    private String password;
    private String aboutMe;
    private long  id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
