package com.pro.privacidade.core.mocks;

import com.pro.privacidade.core.entities.Checklist;
import com.pro.privacidade.core.entities.LIA;

import java.time.LocalDateTime;
import java.util.List;

public class IntermediarioModuleMock {

    public static Checklist getChecklistMock(int i) {
        Checklist checklist = new Checklist();
        checklist.setCategory(getCategory(i));
        checklist.setAnswers("false,false,false,false,false,false,false");
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

    private static String getCategory(int i){
        return switch (i) {
            case 0 -> "Controle de Acesso";
            case 1 -> "Dados Armazenados";
            case 2 -> "Segurança das Comunicações";
            case 3 -> "Gerenciamento de Vulnerabilidades";
            case 4 -> "Dispositivos Móveis";
            case 5 -> "Serviços em Nuvem";
            case 6 -> "Cookies";
            case 7 -> "Política de Segurança da Informação";
            case 8 -> "Conscientização e Treinamento";
            case 9 -> "Contratos";
            default -> null;
        };
    }
}
