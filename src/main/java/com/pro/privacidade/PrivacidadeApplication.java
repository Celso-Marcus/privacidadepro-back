package com.pro.privacidade;

import com.pro.privacidade.core.entities.ReasonData;
import com.pro.privacidade.core.mocks.InicialModuleMock;
import com.pro.privacidade.core.mocks.IntermediarioModuleMock;
import com.pro.privacidade.infra.repositories.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class PrivacidadeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivacidadeApplication.class, args);
    }

    @GetMapping("/hello")
    @Operation(summary = "Endpoint de Teste", tags = {"Teste"})
    public String hello() {
        return "Olá Mundo!";
    }

    //Mocks para o módulo Inicial
    //Retirar pra quando for pra produção
    @Bean
    CommandLineRunner initDatabase
    (QuizRepository quizRepository, InventoryRepository inventoryRepository,
     InterviewRepository interviewRepository, ChecklistRepository checklistRepository, LIARepository liaRepository,
     ReasonDataRepository reasonDataRepository) {
        return args -> {
            quizRepository.deleteAll();
            inventoryRepository.deleteAll();
            checklistRepository.deleteAll();
            liaRepository.deleteAll();
            interviewRepository.deleteAll();
            reasonDataRepository.deleteAll();

            List<String> reasonDataList = new ArrayList<>();
            reasonDataList.add("Consentimento - Mendiante consetimento do titular");
            reasonDataList.add("Regulatório - Para cumprimento de obrigação legal ou regulatória pelo controlador");
            reasonDataList.add("Governo - Pela administração pública, para tratamento de dados necessários a política pública");
            reasonDataList.add("Pesquisa - Para realização de estudos por órgão de pesquisa, sendo garantida a anonimização dos dados");
            reasonDataList.add("Contratos - Quando necessário para a execução de contrato");
            reasonDataList.add("Judicial - Exercício regular de direitos, inclusive em contrato e em processo judicial, administrativo e arbitral");
            reasonDataList.add("Vida - Para a proteção da vida ou incolumidade física do títular ou terceiros");
            reasonDataList.add("Saúde - Para a tutela da pádida, com procedimento realizado por profissionais da área da pessoa ou por entidades sanitárias");
            reasonDataList.add("Legítimo interesse - Interesses legítimos do controlador ou de terceiro");
            reasonDataList.add("Crédito - Para proteção do crédito");
            reasonDataList.add("Dados sensíveis - Para garantia da prevenção à fraude e à segurança do títular");

            reasonDataList.forEach(reasonData -> {
                ReasonData reasonDataEntity = new ReasonData();
                reasonDataEntity.setName(reasonData);
                reasonDataRepository.save(reasonDataEntity);
            });

            var reasonData = reasonDataRepository.findById(1L).get();

            for (int i = 0; i < 3; i++) {
                var quiz = InicialModuleMock.getQuizMock();
                var inventory = InicialModuleMock.getInventoryMock(reasonData);
                var lia = IntermediarioModuleMock.getLIAMock(i);
                var interview = InicialModuleMock.getInterviewMock();

                liaRepository.save(lia);

                quizRepository.save(quiz);
                inventoryRepository.save(inventory);
                interviewRepository.save(interview);
            }

            for (int i = 0; i < 9; i++) {
                var checklist = IntermediarioModuleMock.getChecklistMock(i);
                checklistRepository.save(checklist);
            }
        };
    }

}
