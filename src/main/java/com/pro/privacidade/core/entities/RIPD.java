package com.pro.privacidade.core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@SQLDelete(sql = "UPDATE ripd SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "ripd")
public class RIPD implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "criado_em")
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean status = true;

    public RIPD() {
    }

}
