package com.ab.springbootreactbackend.model;

import com.ab.springbootreactbackend.annotation.UniqueUsername;
import com.ab.springbootreactbackend.util.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @NotNull(message = "{username.NotNull.message}")
    @Size(min = 4,max = 10)
    @UniqueUsername
    @JsonView(Views.Base.class)
    private String username;
    @NotNull(message = "{display.NotNull.message}")
    @Size(min = 4,max = 20)
    @JsonView(Views.Base.class)
    private String displayName;
    @NotNull
    @Size(min = 8,max = 20)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message = "{password.Pattern.message}")
    private String password;

    @JsonView(Views.Base.class)
    private String image;
}
