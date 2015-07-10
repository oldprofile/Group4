package com.exadel.training.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by HP on 09.07.2015.
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "roles")
    private List<User> listUser;

}
