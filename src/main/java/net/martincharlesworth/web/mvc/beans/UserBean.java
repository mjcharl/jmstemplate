package net.martincharlesworth.web.mvc.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserBean {

    @NotNull
    @Size(min = 5, max = 20, message = "Username must be between 2 and 20 characters")
    private String username;

    @NotNull
    @Size(min = 2, max = 100, message = "Forename must be between 2 and 100 characters")
    private String forename;

    @NotNull
    @Size(min = 5, max = 100, message = "Surname must be between 2 and 100 characters")
    private String surname;

    @NotNull
    @Size(min = 5, max = 20, message = "Password must be between 2 and 20 characters")
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
