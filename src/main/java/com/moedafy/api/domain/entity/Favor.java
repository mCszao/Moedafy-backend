package com.moedafy.api.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "favors")
@Getter
@Setter
@NoArgsConstructor
public class Favor {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 255)
    private String description;

    private Integer valor;

    //Hibernate cria uma tabela intermediaria com o id usuario e favor para gerenciar o mapeamento many to many
    @ManyToMany
    @JoinTable(
            name = "collaborators",
            joinColumns = @JoinColumn(name = "favor_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> collaborators;
}
