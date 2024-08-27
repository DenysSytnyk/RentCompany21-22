package telran.cars.controllers;

import static telran.cars.api.RentCompanyErrorMessage.DATE_IS_NOT_PAST;
import static telran.cars.api.RentCompanyErrorMessage.DATE_IS_NULL;
import static telran.cars.api.RentCompanyErrorMessage.DATE_WRONG_FORMAT;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import telran.cars.dto.Driver;
import telran.cars.dto.StatisticsData;
import telran.cars.service.IRentCompany;

@RestController
@Slf4j
public class RentCompanyStatisticsController 
{
	@Autowired
	IRentCompany service;
	
	private<T> void logging(String methodName, T data)
	{
		log.debug(String.format("%s: receiver data:{}", methodName));
	}
	
	@GetMapping("/drivers/active")
	List<Driver> getMostActiveDrivers()
	{
		logging("getMostActiveDrivers", null);
		return service.getMostActiveDrivers();
	}
	
	@PostMapping("/models/popular")
	List<String> getMostPopularCarModels(@RequestBody @Valid StatisticsData data)
	{
		logging("getMostPopularCarModels", data);
		return service.getMostPopularCarModels(data.getFromDate(), data.getToDate(), data.getFromAge(), data.getToAge());
	}
	
	@GetMapping("/models/profitable/{dateFrom}/{dateTo}")
	List<String> getMostProfitableCarModels(@PathVariable @NotNull(message = DATE_IS_NULL)
	String dateFrom,
	
	@PathVariable
	@NotNull(message = DATE_IS_NULL)
	 String dateTo)
	{
		LocalDate fromDate = LocalDate.parse(dateFrom);
		LocalDate toDate = LocalDate.parse(dateTo);
		logging("getMostProfitableCarModels", new StatisticsData(fromDate, toDate, 0, 0));
		
		return service.getMostProfitableCarModels(fromDate, toDate);
	}
}
