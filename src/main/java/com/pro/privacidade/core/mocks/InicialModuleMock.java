package com.pro.privacidade.core.mocks;

import com.pro.privacidade.core.entities.Interview;
import com.pro.privacidade.core.entities.Inventory;
import com.pro.privacidade.core.entities.Quiz;
import com.pro.privacidade.core.entities.ReasonData;

import java.time.LocalDateTime;
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

    public static Inventory getInventoryMock(ReasonData reasonData) {
        Inventory inventory = new Inventory();
        inventory.setTagName("Inventario-TI-123");
        inventory.setSector("Setor de Exemplo");
        inventory.setCollectedData("Dados coletados de exemplo");
        inventory.setSourceData("Fonte de dados de exemplo");
        inventory.setReasonData(reasonData);
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
        inventory.setForeignData("Nenhum");
        inventory.setShareData("Nenhum");
        inventory.setSystemNames(List.of("System1, System2, System3"));
        inventory.setOperators(List.of("Operador1, Operador2, Operador3"));
        inventory.setStatus(true);
        return inventory;
    }

    public static Interview getInterviewMock() {
        Interview interview = new Interview();
        interview.setFilePath("audio_dota2.ogg");
        interview.setCreatedAt(LocalDateTime.now());
        interview.setStatus(true);
        return interview;
    }
}
