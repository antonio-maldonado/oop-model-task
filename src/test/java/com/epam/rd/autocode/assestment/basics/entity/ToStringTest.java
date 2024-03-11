package com.epam.rd.autocode.assestment.basics.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {
    static final Locale DEFAULT_LOCALE = Locale.getDefault();
    static final TimeZone DEFAULT_TIMEZONE = TimeZone.getDefault();

    @BeforeAll
    static void setUp() {
        Locale.setDefault(new Locale("en"));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @AfterAll
    static void tearDown() {
        Locale.setDefault(DEFAULT_LOCALE);
        TimeZone.setDefault(DEFAULT_TIMEZONE);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/employee.csv", numLinesToSkip = 1)
    void testEmployeeToString(long id, String email, String password, String name,
                              String phone, String dateOfBirth, String expected) {
        LocalDate date = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String actual = new Employee(id, email, password, name, phone, date).toString();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/client.csv", numLinesToSkip = 1)
    void testClientToString(long id, String email, String password, String name,
                            BigDecimal balance, String driverLicense, String expected) {

        String actual = new Client(id, email, password, name, balance, driverLicense).toString();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/vehicle.csv", numLinesToSkip = 1)
    void testVehicleToString(long id, String make, String model, String characteristics,
                             int yearOfProduction, long odometer, String color,
                             String licensePlate, int numberOfSeats, BigDecimal price,
                             char requiredLicense, BodyType bodyType, String expected) {

        String actual = new Vehicle(id, make, model, characteristics, yearOfProduction,
                odometer, color, licensePlate, numberOfSeats, price, requiredLicense,
                bodyType).toString();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/order.csv", numLinesToSkip = 1)
    void testOrderToString(long id, long clientId, long employeeId, long vehicleId,
                           String startTime, String endTime, BigDecimal price,
                           String expected) {

        LocalDateTime sd = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm"));
        LocalDateTime ed = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm"));
        String actual = new Order(id, clientId, employeeId, vehicleId, sd, ed, price)
                .toString();
        assertEquals(expected, actual);
    }
}
