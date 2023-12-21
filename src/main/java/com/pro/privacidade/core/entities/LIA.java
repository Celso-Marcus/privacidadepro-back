package com.pro.privacidade.core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SQLDelete(sql = "UPDATE lia SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "lia")
public class LIA implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "inventario")
    private String inventoryName;

    @Column(name = "respostas")
    private String answers;

    @Column(name = "justificativa")
    private String justification;

    @Column(name="nome_dpo")
    private String dpoName;

    @Column(name = "criado_em")
    private LocalDateTime createdAt;

    @Column(name = "atualizado_em")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean status = true;

    public LIA() {
    }

    public LIA(Long id, String inventoryName, String answers, String justification, String dpoName,
               LocalDateTime createdAt, LocalDateTime updatedAt, boolean status) {
        this.id = id;
        this.inventoryName = inventoryName;
        this.answers = answers;
        this.justification = justification;
        this.dpoName = dpoName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

        LIA lia = (LIA) o;

        if (!id.equals(lia.id)) return false;
        if (!Objects.equals(inventoryName, lia.inventoryName)) return false;
        if (!Objects.equals(answers, lia.answers)) return false;
        if (!Objects.equals(justification, lia.justification)) return false;
        if (!Objects.equals(dpoName, lia.dpoName)) return false;
        if (!Objects.equals(createdAt, lia.createdAt)) return false;
        return Objects.equals(status, lia.status);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (inventoryName != null ? inventoryName.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + (justification != null ? justification.hashCode() : 0);
        result = 31 * result + (dpoName != null ? dpoName.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LIA{" +
                "id=" + id +
                ", inventoryName='" + inventoryName + '\'' +
                ", answers='" + answers + '\'' +
                ", justification='" + justification + '\'' +
                ", dpoName='" + dpoName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                '}';
    }
}
