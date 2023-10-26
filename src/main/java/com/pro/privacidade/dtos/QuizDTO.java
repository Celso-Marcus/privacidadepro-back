package com.pro.privacidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Objects;

@Validated
public class QuizDTO {

    private Long id;

    @NotBlank
    @NotNull
    private String result;

    @NotBlank
    @NotNull
    private String answers;

    @NotBlank
    @NotNull
    private String dpoName;

    private Date createdAt;

    public QuizDTO() {}

    public QuizDTO(Long id, String result, String answers, String dpoName, Date createdAt) {
        this.id = id;
        this.result = result;
        this.answers = answers;
        this.dpoName = dpoName;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizDTO quizDTO = (QuizDTO) o;

        return id.equals(quizDTO.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
                "id=" + id +
                ", result='" + result + '\'' +
                ", answers='" + answers + '\'' +
                ", dpoName='" + dpoName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
