package com.pro.privacidade.infra.http.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
public class RipdDTO {

    @NotNull
    @Positive
    private Long inventoryId;
    @NotNull
    @NotBlank
    private String ownerName;
    @NotNull
    @NotBlank
    private String ownerEmail;
    @NotNull
    @NotBlank
    private String ownerPhone;
    @NotNull
    @NotBlank
    private String principleRisk;
    @NotNull
    @NotBlank
    private String userRisk;
    @NotNull
    @NotBlank
    private String dataRisk;
    @NotNull
    @NotBlank
    private String reactionRisk;

    public RipdDTO(Long inventoryId, String ownerName, String ownerEmail, String ownerPhone,
                   String principleRisk, String userRisk, String dataRisk, String reactionRisk) {
        this.inventoryId = inventoryId;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.ownerPhone = ownerPhone;
        this.principleRisk = principleRisk;
        this.userRisk = userRisk;
        this.dataRisk = dataRisk;
        this.reactionRisk = reactionRisk;
    }

    public RipdDTO() {
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getPrincipleRisk() {
        return principleRisk;
    }

    public void setPrincipleRisk(String principleRisk) {
        this.principleRisk = principleRisk;
    }

    public String getUserRisk() {
        return userRisk;
    }

    public void setUserRisk(String userRisk) {
        this.userRisk = userRisk;
    }

    public String getDataRisk() {
        return dataRisk;
    }

    public void setDataRisk(String dataRisk) {
        this.dataRisk = dataRisk;
    }

    public String getReactionRisk() {
        return reactionRisk;
    }

    public void setReactionRisk(String reactionRisk) {
        this.reactionRisk = reactionRisk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RipdDTO ripdDTO = (RipdDTO) o;

        if (!Objects.equals(inventoryId, ripdDTO.inventoryId)) return false;
        if (!Objects.equals(ownerName, ripdDTO.ownerName)) return false;
        if (!Objects.equals(ownerEmail, ripdDTO.ownerEmail)) return false;
        if (!Objects.equals(ownerPhone, ripdDTO.ownerPhone)) return false;
        if (!Objects.equals(principleRisk, ripdDTO.principleRisk))
            return false;
        if (!Objects.equals(userRisk, ripdDTO.userRisk)) return false;
        if (!Objects.equals(dataRisk, ripdDTO.dataRisk)) return false;
        return Objects.equals(reactionRisk, ripdDTO.reactionRisk);
    }

    @Override
    public int hashCode() {
        int result = inventoryId != null ? inventoryId.hashCode() : 0;
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        result = 31 * result + (ownerEmail != null ? ownerEmail.hashCode() : 0);
        result = 31 * result + (ownerPhone != null ? ownerPhone.hashCode() : 0);
        result = 31 * result + (principleRisk != null ? principleRisk.hashCode() : 0);
        result = 31 * result + (userRisk != null ? userRisk.hashCode() : 0);
        result = 31 * result + (dataRisk != null ? dataRisk.hashCode() : 0);
        result = 31 * result + (reactionRisk != null ? reactionRisk.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RipdDTO{");
        sb.append("inventoryId=").append(inventoryId);
        sb.append(", ownerName='").append(ownerName).append('\'');
        sb.append(", ownerEmail='").append(ownerEmail).append('\'');
        sb.append(", ownerPhone='").append(ownerPhone).append('\'');
        sb.append(", principleRisk='").append(principleRisk).append('\'');
        sb.append(", userRisk='").append(userRisk).append('\'');
        sb.append(", dataRisk='").append(dataRisk).append('\'');
        sb.append(", reactionRisk='").append(reactionRisk).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
