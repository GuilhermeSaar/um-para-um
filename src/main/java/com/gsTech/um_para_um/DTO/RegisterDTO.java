package com.gsTech.um_para_um.DTO;

import com.gsTech.um_para_um.enums.Role;

public class RegisterDTO {


    private String name;
    private String email;
    private int age;
    private String password;
    private Role role;

    public RegisterDTO(String name, String email, int age, String password, Role role) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.role = role;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
