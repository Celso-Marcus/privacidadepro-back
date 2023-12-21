package com.pro.privacidade.core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@SQLDelete(sql = "UPDATE quiz SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "quiz")
public class Quiz implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "resultado")
    private String result;

    @Column(name = "respostas")
    private String answers;

    @Column(name = "nome_dpo")
    private String dpoName;

    @Column(name = "criado_em")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean status = true;

    public Quiz() {
    }

    public Quiz(Long id, String result, String answers, String dpoName, LocalDateTime createdAt, boolean status) {
        this.id = id;
        this.result = result;
        this.answers = answers;
        this.dpoName = dpoName;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getDpoName() {
        return dpoName;
    }

    public void setDpoName(String dpoName) {
        this.dpoName = dpoName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        return id.equals(quiz.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", result='" + result + '\'' +
                ", answers='" + answers + '\'' +
                ", dpoName='" + dpoName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
