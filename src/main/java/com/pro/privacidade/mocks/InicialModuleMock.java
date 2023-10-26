package com.pro.privacidade.mocks;

import com.pro.privacidade.entities.Inventory;
import com.pro.privacidade.entities.Quiz;

import java.util.Date;

public class InicialModuleMock {

    public static Quiz getQuizMock() {
        Quiz quiz = new Quiz();
        quiz.setAnswers("1,3,4,5");
        quiz.setDpoName("Teste");
        quiz.setResult("Moderado");
        quiz.setCreatedAt(new Date());
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
        inventory.setCreatedAt(new Date());
        inventory.setUpdatedAt(new Date());
        inventory.setStatus(true);
        return inventory;
    }
}
