package telran.cars.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.dto.RemovedCarData;
import telran.cars.dto.RentCarData;
import telran.cars.dto.ReturnCarData;
import telran.cars.service.IRentCompany;

@RestController
@Slf4j
public class RentCompanyClercController 
{
	@Autowired
	IRentCompany service;
	
	private<T> void logging(String methodName, T data)
	{
		log.debug(String.format("%s: receiver data:{}", methodName));
	}
	
	@PostMapping("/driver/add")
	CarsReturnCode addDriver(@RequestBody @Valid Driver driver)
	{
		logging("addDriver", driver);
		return service.addDriver(driver);
	}
	
	@GetMapping("/car")
	Car getCar(@RequestParam String regNumber)
	{
		logging("getCar", regNumber);
		return service.getCar(regNumber);
	}
	
	@GetMapping("/model")
	Model getModel(@RequestParam String modelName)
	{
		logging("getModel", modelName);
		return service.getModel(modelName);
	}
	
	@GetMapping("/driver")
	Driver getDriver(@RequestParam long licenseId)
	{
		logging("getDriver", licenseId);
		return service.getDriver(licenseId);
	}
	
	@PostMapping("/car/rent")
	CarsReturnCode rentCar(@RequestBody @Valid RentCarData rcd)
	{
		logging("rentCar", rcd);
		return service.rentCar(rcd.getRegNumber(), rcd.getLicenseId(), rcd.getRentDate(), rcd.getRentDays());
	}
	
	@PostMapping("/car/return")
	RemovedCarData returnCar(@RequestBody ReturnCarData rcd)
	{
		logging("returnCar", rcd);
		return service.returnCar(rcd.getRegNumber(), rcd.getLicenseId(), rcd.getReturnDate(), rcd.getDamagesPercent(), rcd.getTankPercent());
	}
	
	@GetMapping("/models")
	List<String> getModelNames()
	{
		logging("getModelNames", null);
		return service.getModelNames();
	}
	
	@GetMapping("/cars/{modelName}")
	List<Car> getCarsByModel(@PathVariable String modelName)
	{
		logging("getCarsByModel", modelName);
		return service.getCarsByModel(modelName);
	}
	
}
