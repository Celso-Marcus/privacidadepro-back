package com.pro.privacidade.core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE checklist SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "checklist")
public class Checklist implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "categoria")
    private String category;

    @Column(name = "respostas")
    private String answers;

    @Column(name = "arquivos")
    private List<String> files;

    @Column(name = "atualizado_em")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;

    public Checklist() {}

    public Checklist(Long id, String category, String answers, List<String> files, Boolean status, LocalDateTime updatedAt) {
        this.id = id;
        this.category = category;
        this.answers = answers;
        this.files = files;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

        Checklist that = (Checklist) o;

        if (!id.equals(that.id)) return false;
        return category.equals(that.category);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChecklistItem{");
        sb.append("id=").append(id);
        sb.append(", category='").append(category).append('\'');
        sb.append(", answers='").append(answers).append('\'');
        sb.append(", files=").append(files);
        sb.append(", status=").append(status);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
