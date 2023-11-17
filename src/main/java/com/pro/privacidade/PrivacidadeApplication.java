package com.pro.privacidade;

import com.pro.privacidade.mocks.InicialModuleMock;
import com.pro.privacidade.mocks.InterModuleMock;
import com.pro.privacidade.repositories.ChecklistRepository;
import com.pro.privacidade.repositories.InventoryRepository;
import com.pro.privacidade.repositories.QuizRepository;
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
	CommandLineRunner initDatabase(QuizRepository quizRepository, InventoryRepository inventoryRepository, ChecklistRepository checklistRepository) {
		return args -> {
			quizRepository.deleteAll();
			inventoryRepository.deleteAll();
			checklistRepository.deleteAll();

			for (int i = 0; i < 3; i++) {
				var quiz = InicialModuleMock.getQuizMock();
				var inventory = InicialModuleMock.getInventoryMock();
				var checklist = InterModuleMock.getChecklistMock();

				checklistRepository.save(checklist);
				quizRepository.save(quiz);
				inventoryRepository.save(inventory);
			}
		};
	}

}
