package telran.cars.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Model;
import telran.cars.dto.RemovedCarData;
import telran.cars.service.IRentCompany;

@RestController
@Slf4j
public class RentCompanyAdministratorController 
{
	@Autowired
	IRentCompany service;
	
	private<T> void logging(String methodName, T data)
	{
		log.debug(String.format("%s: receiver data:{}", methodName));
	}
	
	@PostMapping("/car/add")
	CarsReturnCode addCar(@RequestBody @Valid Car car)
	{
		logging("addCar", car);
		return service.addCar(car);
	}
	
	@PostMapping("/model/add")
	CarsReturnCode addModel(@RequestBody @Valid Model model)
	{
		logging("addModel", model);
		return service.addModel(model);
	}
	
	@DeleteMapping("/car/remove")
	RemovedCarData removeCar(@RequestParam String regNumber)
	{
		logging("removeCar", regNumber);
		return service.removeCar(regNumber);
	}
	
	@DeleteMapping("/model/remove")
	List<RemovedCarData> removeModel(@RequestParam String modelName)
	{
		logging("removeModel", modelName);
		return service.removeModel(modelName);
	}

}
