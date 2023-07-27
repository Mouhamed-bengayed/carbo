package com.example.co2.Entite;

import lombok.Data;


import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Userr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String adresse;
    private int number;
    private Boolean blocked;
    private boolean valid;
    private String token;
    @OneToMany(mappedBy = "user")
    private List<Solution> solutionList;
    @OneToMany(mappedBy = "user")
    private List<Activity>activityList;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public Userr(String name, String username, String email, String password, boolean blocked, String adresse, boolean valid) {

        this.name=name;
        this.username=username;
        this.email=email;
        this.password=password;
        this.blocked=blocked;
        this.adresse=adresse;
        this.valid=valid;

    }

    public Userr() {
    }
}
