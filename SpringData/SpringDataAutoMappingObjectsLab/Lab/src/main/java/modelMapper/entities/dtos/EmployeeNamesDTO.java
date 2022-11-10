package modelMapper.entities.dtos;

public class EmployeeNamesDTO {

    private String firstName;

    private String lastName;

    public EmployeeNamesDTO() {
    }

    public EmployeeNamesDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
