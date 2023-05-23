package cm.ls.inventoryservice;

import cm.ls.inventoryservice.Model.Inventory;
import cm.ls.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = Inventory.builder()
					.skuCode("inphone_11")
					.qte(10)
					.build();

			Inventory inventory2 = Inventory.builder()
					.skuCode("inphone_11_pro")
					.qte(10)
					.build();

			//inventoryRepository.save(inventory);
			//inventoryRepository.save(inventory2);

		};
	}

}
