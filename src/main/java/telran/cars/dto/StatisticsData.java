package telran.cars.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;

import static telran.cars.api.RentCompanyErrorMessage.*;

@ToString
public class StatisticsData implements Serializable
{
	private static final long serialVersionUID = -7843114314564227396L;

	@NotNull(message = DATE_IS_NULL)
//	@Past(message = DATE_IS_NOT_PAST)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fromDate;
	
	@NotNull(message = DATE_IS_NULL)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate toDate;
	
	@Min(value = 16, message = AGE_LESS_THAN_MIN)
	@Max(value = 80, message = AGE_GREATER_THAN_MAX)
	private int fromAge;
	
	@Min(value = 16, message = AGE_LESS_THAN_MIN)
	@Max(value = 80, message = AGE_GREATER_THAN_MAX)
	private int toAge;
	
	public StatisticsData()
	{
	}

	public StatisticsData(LocalDate fromDate, LocalDate toDate, int fromAge, int toAge)
	{
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromAge = fromAge;
		this.toAge = toAge;
	}

	public LocalDate getFromDate()
	{
		return fromDate;
	}

	public LocalDate getToDate()
	{
		return toDate;
	}

	public int getFromAge()
	{
		return fromAge;
	}

	public int getToAge()
	{
		return toAge;
	}
	
	
}
