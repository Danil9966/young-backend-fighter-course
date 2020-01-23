package groupId.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


public class Dog {
    String name;
    double height;
    LocalDate dateOfBirth;
    double weight;
    int id;

    public String getName() {
        return name;
    }

    public String getId() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }



    public Dog(String name, LocalDate dateOfBirth, double weight, double height, int id) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public void setId() {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        Dog dog = (Dog) o;
        return Double.compare(dog.height, height) == 0 &&
                Double.compare(dog.weight, weight) == 0 &&
                Objects.equals(name, dog.name) &&
                Objects.equals(dateOfBirth, dog.dateOfBirth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, height, dateOfBirth, weight);
    }

}
