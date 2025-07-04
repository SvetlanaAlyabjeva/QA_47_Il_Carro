package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class Car {
    private String serialNumber;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private Integer seats;
    private String carClass;
    private Double pricePerDay;
    private String about;
    private String city;
    private String image;


}
