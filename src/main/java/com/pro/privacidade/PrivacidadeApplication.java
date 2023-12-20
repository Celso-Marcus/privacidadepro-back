package com.pro.privacidade;

import com.pro.privacidade.core.mocks.InicialModuleMock;
import com.pro.privacidade.core.mocks.InterModuleMock;
import com.pro.privacidade.infra.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PrivacidadeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivacidadeApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Olá Mundo!";
    }

    //Mocks para o módulo Inicial
    //Retirar pra quando for pra produção
    @Bean
    CommandLineRunner initDatabase
    (QuizRepository quizRepository, InventoryRepository inventoryRepository,
     InterviewRepository interviewRepository, ChecklistRepository checklistRepository, LIARepository liaRepository) {
        return args -> {
            quizRepository.deleteAll();
            inventoryRepository.deleteAll();
            checklistRepository.deleteAll();
            liaRepository.deleteAll();
            interviewRepository.deleteAll();

            for (int i = 0; i < 3; i++) {
                var quiz = InicialModuleMock.getQuizMock();
                var inventory = InicialModuleMock.getInventoryMock();
                var lia = InterModuleMock.getLIAMock(i);
                var interview = InicialModuleMock.getInterviewMock();

                liaRepository.save(lia);

                quizRepository.save(quiz);
                inventoryRepository.save(inventory);
                interviewRepository.save(interview);
            }

            for (int i = 0; i < 9; i++) {
                var checklist = InterModuleMock.getChecklistMock(i);
                checklistRepository.save(checklist);
            }
        };
    }

}
