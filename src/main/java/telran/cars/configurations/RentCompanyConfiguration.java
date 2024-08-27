package telran.cars.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import telran.cars.service.IRentCompany;
import telran.cars.service.RentCompanyEmbedded;

@Configuration
public class RentCompanyConfiguration 
{
	@Value("${file-name: company.data}")
	private String fileName;
	
@Bean
IRentCompany getCompany()
	{
		return RentCompanyEmbedded.restoreFromFile(fileName);
	}
}
