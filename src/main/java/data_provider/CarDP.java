package data_provider;

import dto.Car;
import org.testng.annotations.DataProvider;
import utils.Fuel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.RandomUtils.generateString;

public class CarDP {

    @DataProvider
    public Car[] addNewCarDP() {
        Car car1 = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.HYBRID.getValue())
                .seats(4)
                .carClass("C")
                .serialNumber("Opel" + generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .image("photoKatya.jpeg")
                .build();
        Car car2 = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.HYBRID.getValue())
                .seats(4)
                .carClass("C")
                .serialNumber("Opel" + generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .image("photoKatya.jpeg")
                .build();
        Car car3 = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.HYBRID.getValue())
                .seats(4)
                .carClass("C")
                .serialNumber("Opel" + generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .image("photoKatya.jpeg")
                .build();
        return new Car[]{car1, car2, car3};
    }

    @DataProvider
    public Iterator<Car> addNewCarDPFile() {
        List<Car> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/data_provider/data_car.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                list.add(Car.builder()
                        .city(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.valueOf(splitArray[5]))
                        .carClass(splitArray[6])
                        .serialNumber(splitArray[7])
                        .pricePerDay(Double.valueOf(splitArray[8]))
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.listIterator();
    }
}
