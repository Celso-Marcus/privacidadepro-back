package com.pro.privacidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Validated
public class InventoryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    @NotNull
    private String tagName;
    @NotBlank
    @NotNull
    private String sector;
    @NotBlank
    @NotNull
    private String collectedData;
    @NotBlank
    @NotNull
    private String sourceData;
    @NotBlank
    @NotNull
    private String reasonData;
    @NotBlank
    @NotNull
    private String howStorage;
    @NotBlank
    @NotNull
    private String securityData;
    @NotBlank
    @NotNull
    private String deadlineData;
    @NotBlank
    @NotNull
    private String justificationData;
    @NotNull
    private boolean underAgeData;
    @NotBlank
    @NotNull
    private String sensitiveData;
    @NotBlank
    @NotNull
    private String controller;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public InventoryDTO() {
    }

    public InventoryDTO(Long id, String tagName, String sector, String collectedData, String sourceData, String reasonData,
                        String howStorage, String securityData, String deadlineData, String justificationData, boolean underAgeData,
                        String sensitiveData, String controller, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.tagName = tagName;
        this.sector = sector;
        this.collectedData = collectedData;
        this.sourceData = sourceData;
        this.reasonData = reasonData;
        this.howStorage = howStorage;
        this.securityData = securityData;
        this.deadlineData = deadlineData;
        this.justificationData = justificationData;
        this.underAgeData = underAgeData;
        this.sensitiveData = sensitiveData;
        this.controller = controller;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCollectedData() {
        return collectedData;
    }

    public void setCollectedData(String collectedData) {
        this.collectedData = collectedData;
    }

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String getReasonData() {
        return reasonData;
    }

    public void setReasonData(String reasonData) {
        this.reasonData = reasonData;
    }

    public String getHowStorage() {
        return howStorage;
    }

    public void setHowStorage(String howStorage) {
        this.howStorage = howStorage;
    }

    public String getSecurityData() {
        return securityData;
    }

    public void setSecurityData(String securityData) {
        this.securityData = securityData;
    }

    public String getDeadlineData() {
        return deadlineData;
    }

    public void setDeadlineData(String deadlineData) {
        this.deadlineData = deadlineData;
    }

    public String getJustificationData() {
        return justificationData;
    }

    public void setJustificationData(String justificationData) {
        this.justificationData = justificationData;
    }

    public boolean isUnderAgeData() {
        return underAgeData;
    }

    public void setUnderAgeData(boolean underAgeData) {
        this.underAgeData = underAgeData;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
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

        InventoryDTO that = (InventoryDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", sector='" + sector + '\'' +
                ", collectedData='" + collectedData + '\'' +
                ", sourceData='" + sourceData + '\'' +
                ", reasonData='" + reasonData + '\'' +
                ", howStorage='" + howStorage + '\'' +
                ", securityData='" + securityData + '\'' +
                ", deadlineData='" + deadlineData + '\'' +
                ", justificationData='" + justificationData + '\'' +
                ", underAgeData=" + underAgeData +
                ", sensitiveData='" + sensitiveData + '\'' +
                ", controller='" + controller + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
