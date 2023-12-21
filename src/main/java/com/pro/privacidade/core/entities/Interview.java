package com.pro.privacidade.core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SQLDelete(sql = "UPDATE interview SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "interview")
public class Interview implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "arquivo")
    private String filePath;

    @Column(name = "criado_em")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean status = true;

    public Interview() {}

    public Interview(Long id, String filePath, LocalDateTime createdAt, boolean status) {
        this.id = id;
        this.filePath = filePath;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

        Interview interview = (Interview) o;

        if (!Objects.equals(id, interview.id)) return false;
        if (!Objects.equals(filePath, interview.filePath)) return false;
        if (!Objects.equals(createdAt, interview.createdAt)) return false;
        return Objects.equals(status, interview.status);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Interview{");
        sb.append("id=").append(id);
        sb.append(", filePath='").append(filePath).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
