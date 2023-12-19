package com.pro.privacidade.infra.http.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Validated
public class IncidentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull
    private String communication;
    @NotNull
    private String occurrence;
    private LocalDateTime createdAt;

    public IncidentDTO() {}

    public IncidentDTO(Long id, String communication, String occurrence, LocalDateTime createdAt) {
        this.id = id;
        this.communication = communication;
        this.occurrence = occurrence;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncidentDTO that = (IncidentDTO) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(communication, that.communication))
            return false;
        if (!Objects.equals(occurrence, that.occurrence)) return false;
        return Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (communication != null ? communication.hashCode() : 0);
        result = 31 * result + (occurrence != null ? occurrence.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IncidentDTO{" +
                "id=" + id +
                ", communication='" + communication + '\'' +
                ", occurrence='" + occurrence + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
