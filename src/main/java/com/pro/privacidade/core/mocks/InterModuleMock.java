package com.pro.privacidade.core.mocks;

import com.pro.privacidade.core.entities.Checklist;
import com.pro.privacidade.core.entities.LIA;

import java.time.LocalDateTime;
import java.util.List;

public class InterModuleMock {

    public static Checklist getChecklistMock() {
        Checklist checklist = new Checklist();
        checklist.setCategory("categoria");
        checklist.setAnswers("Sim, Não, Não, Sim");
        checklist.setUpdatedAt(LocalDateTime.now());
        checklist.setFiles(List.of("17_11_2023_14_47_08_categoria_gutscat.jpeg", "17_11_2023_14_45_46_categoria_gutscat.jpeg"));
        checklist.setStatus(true);
        return checklist;
    }

    public static LIA getLIAMock(int i){
        LIA lia = new LIA();
        lia.setAnswers("Sim, Não, Não, Sim, " +
                "Sim, Não, Não, Sim," +
                "Sim, Não, Não, Sim, Sim");
        lia.setCreatedAt(LocalDateTime.now());
        lia.setUpdatedAt(LocalDateTime.now());
        lia.setDpoName("DPO");
        lia.setInventoryName("Inventario-TI" + i);
        lia.setJustification("Justificativaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        lia.setStatus(true);
        return lia;
    }
}
