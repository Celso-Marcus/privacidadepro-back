package com.pro.privacidade.core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SQLDelete(sql = "UPDATE incidentes SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "incidentes")
public class Incident implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "comunicacao")
    private String communication;
    @Column(name = "ocorrencia")
    private String occurrence;
    @Column(name = "criado_em")
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean status = true;

    public Incident() {
    }

    public Incident(Long id, String communication, String occurrence, LocalDateTime createdAt, boolean status) {
        this.id = id;
        this.communication = communication;
        this.occurrence = occurrence;
        this.createdAt = createdAt;
        this.status = status;
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

        Incident incidents = (Incident) o;

        if (!Objects.equals(id, incidents.id)) return false;
        if (!Objects.equals(communication, incidents.communication))
            return false;
        if (!Objects.equals(occurrence, incidents.occurrence)) return false;
        if (!Objects.equals(createdAt, incidents.createdAt)) return false;
        return Objects.equals(status, incidents.status);
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
        return "Incidents{" +
                "id=" + id +
                ", communication='" + communication + '\'' +
                ", occurrence='" + occurrence + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
