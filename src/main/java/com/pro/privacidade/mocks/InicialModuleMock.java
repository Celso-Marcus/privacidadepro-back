package com.pro.privacidade.mocks;

import com.pro.privacidade.entities.Checklist;
import com.pro.privacidade.entities.Inventory;
import com.pro.privacidade.entities.Quiz;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InicialModuleMock {

    public static Quiz getQuizMock() {
        Quiz quiz = new Quiz();
        quiz.setAnswers("1,3,4,5");
        quiz.setDpoName("Teste Nome DPO");
        quiz.setResult("Moderado");
        quiz.setCreatedAt(LocalDateTime.now());
        quiz.setStatus(true);
        return quiz;
    }

    public static Inventory getInventoryMock() {
        Inventory inventory = new Inventory();
        inventory.setTagName("Tag123");
        inventory.setSector("Setor de Exemplo");
        inventory.setCollectedData("Dados coletados de exemplo");
        inventory.setSourceData("Fonte de dados de exemplo");
        inventory.setReasonData("Motivo dos dados de exemplo");
        inventory.setHowStorage("Como os dados são armazenados");
        inventory.setSecurityData("Nível de segurança dos dados");
        inventory.setDeadlineData("Prazo de retenção de dados");
        inventory.setJustificationData("Justificação dos dados de exemplo");
        inventory.setUnderAgeData(true);
        inventory.setSensitiveData("Dados sensíveis de exemplo");
        inventory.setController("Controlador de dados de exemplo");
        inventory.setCreatedAt(LocalDateTime.now());
        inventory.setUpdatedAt(LocalDateTime.now());
        inventory.setDpoName("Teste Nome DPO");
        inventory.setStatus(true);
        return inventory;
    }
}
