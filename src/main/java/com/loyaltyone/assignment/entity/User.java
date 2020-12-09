package com.loyaltyone.assignment.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "INPUT_USER")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "USER_NAME")
    private String userName;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InputText> inputTexts;
}
