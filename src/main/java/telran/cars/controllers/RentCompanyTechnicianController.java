package telran.cars.controllers;

import static telran.cars.api.RentCompanyErrorMessage.DATE_IS_NOT_PAST;
import static telran.cars.api.RentCompanyErrorMessage.DATE_IS_NULL;
import static telran.cars.api.RentCompanyErrorMessage.DATE_WRONG_FORMAT;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
import telran.cars.dto.Car;
import telran.cars.dto.Driver;
import telran.cars.dto.RentRecord;
import telran.cars.dto.StatisticsData;
import telran.cars.service.IRentCompany;

@RestController
@Slf4j
public class RentCompanyTechnicianController 
{
	@Autowired
	IRentCompany service;
	
	private<T> void logging(String methodName, T data)
	{
		log.debug(String.format("%s: receiver data:{}", methodName));
	}
	
	
	@GetMapping("/driver/{licenseId}/cars")
	Set<Car> getCarsByDriver(@PathVariable long licenseId)
	{
		logging("getCarsByDriver", licenseId);
		return service.getCarsByDriver(licenseId);
	}
	
	@GetMapping("/car/{regNumber}/drivers")
	Set<Driver> getDriversByCar(@PathVariable String regNumber)
	{
		logging("getDriversByCar", regNumber);
		return service.getDriversByCar(regNumber);
	}
	
	@GetMapping("/records/{from}/{to}")
	 List<RentRecord> getRentRecordsAtDates(@PathVariable String from,@PathVariable String to)
	{
		String temp = from + " - " + to;
		LocalDate fromDate = LocalDate.parse(from);
		LocalDate toDate = LocalDate.parse(to);
		
		logging("getRentRecordsAtDates", temp);
		return service.getRentRecordsAtDates(fromDate, toDate);
	}

}
