package com.pro.privacidade.infra.http.dtos;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Validated
public class LIADTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull
    private String inventoryName;
    @NotNull
    private String answers;
    @NotNull
    private String justification;
    @NotNull
    private String dpoName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LIADTO() {
    }

    public LIADTO(Long id, String inventoryName, String answers, String justification, String dpoName,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.inventoryName = inventoryName;
        this.answers = answers;
        this.justification = justification;
        this.dpoName = dpoName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LIADTO liadto = (LIADTO) o;

        if (!Objects.equals(id, liadto.id)) return false;
        if (!Objects.equals(inventoryName, liadto.inventoryName))
            return false;
        if (!Objects.equals(answers, liadto.answers)) return false;
        if (!Objects.equals(justification, liadto.justification))
            return false;
        if (!Objects.equals(dpoName, liadto.dpoName)) return false;
        return Objects.equals(createdAt, liadto.createdAt);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
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
                '}';
    }
}
