package com.pro.privacidade.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@SQLDelete(sql = "UPDATE inventario SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "inventario")
public class Inventory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String tagName;

    @Column(name = "setor")
    private String sector;

    @Column(name = "dados_coletados")
    private String collectedData;

    @Column(name = "origem_dados")
    private String sourceData;

    @Column(name = "motivo_coleta")
    private String reasonData;

    @Column(name = "como_armazenado")
    private String howStorage;

    @Column(name = "segurança_dados")
    private String securityData;

    @Column(name = "prazo_eliminacao")
    private String deadlineData;

    @Column(name = "justificativa")
    private String justificationData;

    @Column(name = "menor_idade")
    private boolean underAgeData;

    @Column(name = "dados_sensiveis")
    private String sensitiveData;

    @Column(name = "controlador")
    private String controller;

    @Column(name = "criado_em")
    private Date createdAt;

    @Column(name = "atualizado_em")
    private Date updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;

    public Inventory() {
    }

    public Inventory(String tagName, String sector, String collectedData, String sourceData, String reasonData,
                     String howStorage, String securityData, String deadlineData, String justificationData, boolean underAgeData,
                     String sensitiveData, String controller, Date createdAt, Date updatedAt) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory inventory = (Inventory) o;

        return id.equals(inventory.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Inventory{" +
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
