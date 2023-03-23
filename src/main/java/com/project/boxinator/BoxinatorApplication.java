package com.project.boxinator;

import com.project.boxinator.enums.ShipmentStatus;
import com.project.boxinator.enums.TypeOfUser;
import com.project.boxinator.enums.WeightOption;
import com.project.boxinator.models.Country;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.models.ShipmentStatusHistory;
import com.project.boxinator.models.User;
import com.project.boxinator.repositories.CountryRepository;
import com.project.boxinator.repositories.SSHRepository;
import com.project.boxinator.repositories.ShipmentRepository;
import com.project.boxinator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class BoxinatorApplication implements ApplicationRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShipmentRepository shipmentRepository;
	@Autowired
	private SSHRepository sshRepository;

	@Autowired
	private CountryRepository countryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BoxinatorApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		Country France = new Country(1, "France", 1.5);
		Country Germany = new Country(2, "Germany", 1.3);
		Country Iraq = new Country(3, "Iraq", 2.4);

		countryRepository.save(France);
		countryRepository.save(Germany);
		countryRepository.save(Iraq);

		Shipment one = new Shipment("Greger",
				WeightOption.PREMIUM, "Red", "France", new HashSet<>());
		Shipment two = new Shipment( "Sven",
				WeightOption.PREMIUM, "Blue", "Sweden", new HashSet<>());
		Shipment three = new Shipment("Glenn",
				WeightOption.HUMBLE, "Green", "Greece", new HashSet<>());

		Set<Shipment> shipmentList = new HashSet<>();
		shipmentList.add(one);
		shipmentList.add(two);
		shipmentList.add(three);


		User Milla = new User("3aa1169b-4646-4110-97de-38ec071fb58c ","Milovan", "Glisovic", "abc@dfg.com", "kpr",
				"1999-09-15", "France", 21231, "0743-23", TypeOfUser.Guest, new HashSet<>());
		User Seth = new User("945a2d59-16c9-4985-99e4-bf2f415cd0e9","Seth", "Öberg", "abc@dfg.com", "kpr",
				"1998-09-15", "Iraq", 21231, "0743-23", TypeOfUser.Registered, shipmentList);

		userRepository.save(Milla);
		userRepository.save(Seth);









//		yellowBox.addSSHToShipment(SSH1);
//		yellowBox.addSSHToShipment(SSH2);









	}
}
