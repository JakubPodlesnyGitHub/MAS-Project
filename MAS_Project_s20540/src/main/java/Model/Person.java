package Model;

import Enums.EmployeeGender;
import Enums.PersonType;
import RequirementsInfo.StaticProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "Person")
@Access(AccessType.FIELD)
public class Person {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idPerson;
    @Column(name = "Person First Name", nullable = false)
    private String firstName;

    @Column(name = "Person Last Name", nullable = false)
    private String lastName;

    //@Pattern(regexp =  sta)
    @Column(name = "Person Phone Number", nullable = false)
    private String phoneNumber;
    @Column(name = "Person Birth Date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "Person Email", nullable = false, length = 100)
    private String email;


    @Column(name = "Person Login", nullable = false, length = 100, unique = true)
    private String login;

    //@Pattern(regexp = Requirements.PASSWORD_PATTERN)
    @Column(name = "Person Password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PersonType.class,fetch = FetchType.EAGER)
    @Column(name = "Person Type", nullable = false)
    private Set<PersonType> personTypes;

    //Employee Attributes
    //@Pattern(regexp = Requirements.PESEL_PATTERN)
    @Column(name = "Employee Pesel", nullable = true)
    @Length(min = 11, max = 11)
    //@Pattern(regexp = Requirements.PESEL_PATTERN)
    private String pesel;
    @Embedded
    @Column(name = "Employee Address", nullable = true)
    private Address employeeAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "Employee Gender", nullable = true)
    private EmployeeGender employeeGender;

    @Column(name = "Employee Middle Name", nullable = true)
    private String middleName;

    @Column(name = "Employee Work Start Date", nullable = true)
    private LocalDate workStartDate;
    @Column(name = "Employee Work End Date", nullable = true)
    private LocalDate workEndDate;

    //Client Attributes
    @Column(name = "Client Number", nullable = true, unique = true)
    private Integer clientNumber;

    //connections
    @ManyToMany(mappedBy = "employeeList",fetch = FetchType.EAGER)
    private List<CinemaScreening> cinemaScreeningsService = new ArrayList<>();

    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private List<Booking> clientBookings = new ArrayList<>();

    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private List<Subscription> clientSubscriptions = new ArrayList<>();

    public Person() {

    }

    public Person(String firstName, String lastName, String phoneNumber, LocalDate birthDate, String email, String login, String password, Set<PersonType> personTypes) throws Exception {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setBirthDate(birthDate);
        setEmail(email);
        setLogin(login);
        setPassword(password);
        setPersonTypes(personTypes);
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName == null)
            throw new Exception("Person First Name cannot be null. This filed is mandatory");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (lastName == null)
            throw new Exception("Person Last Name cannot be null. This filed is mandatory");
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        if (phoneNumber == null)
            throw new Exception("Person Phone Number cannot be null. This filed is mandatory");
        /*Pattern pattern = Pattern.compile(StaticProperties.getStaticPropertiesInstance().PHONE_NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches())
            throw new Exception("The phone number provided does not meet the requirements.\nThe phone number should contains 9 numbers");*/
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws Exception {
        if (phoneNumber == null)
            throw new Exception("Person Local Date cannot be null. This filed is mandatory");
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email == null)
            throw new Exception("Person Email cannot be null. This filed is mandatory");
        if (email.length() > StaticProperties.getStaticPropertiesInstance().MAX_LENGTH_LOGIN)
            throw new Exception("Email is too long -> " + email.length() + "Max Length is: " + StaticProperties.getStaticPropertiesInstance().MAX_LENGTH_LOGIN);
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws Exception {
        if (login == null)
            throw new Exception("Person Login cannot be null. This filed is mandatory");
        if (login.length() > StaticProperties.getStaticPropertiesInstance().MAX_LENGTH_LOGIN)
            throw new Exception("Login is too long -> " + login.length() + "Max Length is: " + StaticProperties.getStaticPropertiesInstance().MAX_LENGTH_LOGIN);
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (password == null)
            throw new Exception("Person Password cannot be null. This filed is mandatory");
       /* Pattern pattern = Pattern.compile(StaticProperties.getStaticPropertiesInstance().PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new Exception("The password provided does not meet the requirements.\nThe password should be: at least 10 characters long, contains min. 1 capital letter, min. 1 smallLetter, min. 1 number");
        */
        this.password = password;
    }

    public Set<PersonType> getPersonTypes() {
        return personTypes;
    }

    public void setPersonTypes(Set<PersonType> personTypes) {
        this.personTypes = personTypes;
    }

    public String getPesel() throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to PESEL. Only employee has access");
        return pesel;
    }

    public void setPesel(String pesel) throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to PESEL. Only employee has access");
        if (pesel == null)
            throw new Exception("Employee Pesel cannot be null. This filed is mandatory");
        /*Pattern pattern = Pattern.compile(StaticProperties.getStaticPropertiesInstance().PESEL_PATTERN);
        Matcher matcher = pattern.matcher(pesel);
        if (!matcher.matches())
            throw new Exception("The pesel provided does not meet the requirements. The pesel should have 11 numbers");*/
        this.pesel = pesel;
    }

    public Address getEmployeeAddress() throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to Address. Only employee has access");
        return employeeAddress;
    }

    public void setEmployeeAddress(Address employeeAddress) throws Exception {
        if (!personTypes.contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to Address. Only employee has access");
        if (employeeAddress == null)
            throw new Exception("Employee Address cannot be null. This filed is mandatory");
        this.employeeAddress = employeeAddress;
    }

    public EmployeeGender getEmployeeGender() throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to Gender. Only employee has access");
        return employeeGender;
    }

    public void setEmployeeGender(EmployeeGender employeeGender) throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to Gender. Only employee has access");
        this.employeeGender = employeeGender;
    }

    public String getMiddleName() throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to MiddleName. Only employee has access");
        return middleName;
    }

    public void setMiddleName(String middleName) throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to MiddleName. Only employee has access");
        this.middleName = middleName;
    }

    public LocalDate getWorkStartDate() throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to Employee Work Start Date. Only employee has access");
        return workStartDate;
    }

    public void setWorkStartDate(LocalDate workStartDate) throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to Employee Work Start Date. Only employee has access");
        if (workStartDate == null)
            throw new Exception("Employee Work Start Date cannot be null. This filed is mandatory");

        this.workStartDate = LocalDate.parse(StaticProperties.getStaticPropertiesInstance().DATE_FORMAT.format(workStartDate));
    }

    public LocalDate getWorkEndDate() throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to Employee Work Start Date. Only employee has access");
        return workEndDate;
    }

    public void setWorkEndDate(LocalDate workEndDate) throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to Employee Work Start Date. Only employee has access");
        this.workEndDate = LocalDate.parse(StaticProperties.getStaticPropertiesInstance().DATE_FORMAT.format(workEndDate));
    }

    public Integer getClientNumber() throws Exception {
        if (!getPersonTypes().contains(PersonType.CLIENT))
            throw new Exception("You do not have access to Client Work Start Date. Only client has access");
        return clientNumber;
    }

    public void setClientNumber(Integer clientNumber) throws Exception {
        if (!getPersonTypes().contains(PersonType.CLIENT))
            throw new Exception("You do not have access to Client Work Start Date. Only client has access");
        this.clientNumber = clientNumber;
    }

    //wyliczalne
    @Transient
    public int getPersonAge() {
        return (int) ChronoUnit.YEARS.between(getBirthDate(), LocalDate.now());
    }

    @Transient
    public int getMoviesWatchedNumber() throws Exception {
        if (!getPersonTypes().contains(PersonType.CLIENT))
            throw new Exception("You do not have access to Client Movies Watched Number. Only client has access");
        return clientBookings.size();
    }

    @Transient
    public int getNumberOfMemberShipPoints() throws Exception {
        if (!getPersonTypes().contains(PersonType.CLIENT))
            throw new Exception("You do not have access to Client Movies Watched Number. Only client has access");
        return StaticProperties.getStaticPropertiesInstance().POINTS_FOR_MOVIE * clientBookings.size();
    }

    //connections
    public List<CinemaScreening> getCinemaScreeningsService() {
        return cinemaScreeningsService;
    }

    public void setCinemaScreeningsService(List<CinemaScreening> cinemaScreeningsService) {
        this.cinemaScreeningsService = cinemaScreeningsService;
    }

    public List<Booking> getClientBookings() {
        return clientBookings;
    }

    public void setClientBookings(List<Booking> clientBookings) {
        this.clientBookings = clientBookings;
    }

    public List<Subscription> getClientSubscriptions() {
        return clientSubscriptions;
    }

    public void setClientSubscriptions(List<Subscription> clientSubscriptions) {
        this.clientSubscriptions = clientSubscriptions;
    }

    public void addNewSubscriptionToClient(Subscription newSubscription) throws Exception {
        if (!getPersonTypes().contains(PersonType.CLIENT))
            throw new Exception("You do not have access to method: addNewSubscriptionToClient. Only client has access.");

        if (!getClientSubscriptions().contains(newSubscription)) {
            getClientSubscriptions().add(newSubscription);
            newSubscription.setClient(this);
        }
    }

    public void removeSubscriptionFromClient(Subscription subscriptionToRemove) throws Exception {
        if (!getPersonTypes().contains(PersonType.CLIENT))
            throw new Exception("You do not have access to method: removeSubscriptionFromClient. Only client has access.");

        if (getClientSubscriptions().contains(subscriptionToRemove)) {
            getClientSubscriptions().remove(subscriptionToRemove);
            subscriptionToRemove.setClient(null);
        }
    }

    public void addNewBookingToClient(Booking newBooking) throws Exception {
        if (!getPersonTypes().contains(PersonType.CLIENT))
            throw new Exception("You do not have access to method: addNewBookingToClient. Only client has access.");

        if (!getClientBookings().contains(newBooking)) {
            getClientBookings().add(newBooking);
            newBooking.setClient(this);
        }
    }

    public void removeBookingFromClient(Booking bookingToRemove) throws Exception {
        if (!getPersonTypes().contains(PersonType.CLIENT))
            throw new Exception("You do not have access to method: removeBookingFromClient. Only client has access.");

        if (getClientBookings().contains(bookingToRemove)) {
            getClientBookings().remove(bookingToRemove);
            bookingToRemove.setClient(null);
        }
    }

    public void addScreeningServiceToEmployee(CinemaScreening newCinemaScreening) throws Exception {
        if (!getPersonTypes().contains(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to method: addScreeningServiceToEmployee. Only employee has access.");

        if (!getCinemaScreeningsService().contains(newCinemaScreening)) {
            getCinemaScreeningsService().add(newCinemaScreening);
            newCinemaScreening.addEmployeeServiceToScreening(this);
        }
    }

    //auxiliary
    public boolean checkPersonType(PersonType personType) {
        return getPersonTypes().contains(personType);
    }

    //use case methods
    public void changePassword(String newPassword) throws Exception {
        setPassword(newPassword);
    }

    public boolean login(String login, String password) {
        //czy brac to z bazy
        return false;
    }

    public void register() {
        //tutaj dane inne dla pracownika i inne dla klienta oprocz czesci wspolnej
    }

    public static void showPersonsData() {
        // czy brac z bazy przez sql czy z listy statycznej cos jak ekstensja
    }

    private void displayInformationAboutEmployee(StringBuilder sb) {
        if (personTypes.contains(PersonType.EMPLOYEE)) {
            sb.append("\nEmployee Attributes").append("\n");
            try {
                sb.append("Employee Pesel: ").append(getPesel());
                sb.append("Employee Gender: ").append((getEmployeeGender() != null) ? employeeGender : "None Gender Of The Employee").append("\n");
                sb.append("Employee Address: ").append(employeeAddress).append("\n");
                sb.append("Employee Middle Name: ").append((middleName != null) ? middleName : "None Middle Name Of The Employee").append("\n");
                sb.append("Employee Work Start Date: ").append(workStartDate).append("\n");
                sb.append("Employee Work End Date: ").append((workEndDate != null) ? middleName : "None Work End Date Of The Employee").append("\n");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void displayInformationAboutClient(StringBuilder sb) {
        if (personTypes.contains(PersonType.CLIENT)) {
            sb.append("\nClient Attributes").append("\n");
            try {
                sb.append("Client Number: ").append(getClientNumber()).append("\n");
                sb.append("Client Watched Movies: ").append(getMoviesWatchedNumber()).append("\n");
                sb.append("Client Membership Points: ").append(getNumberOfMemberShipPoints()).append("\n");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First Name: ").append(getFirstName()).append("\n");
        sb.append("Last Name: ").append(getLastName()).append("\n");
        sb.append("Birth Date: ").append(getBirthDate()).append("\n");
        sb.append("Age: ").append(getPersonAge()).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("Login: ").append(getLogin()).append("\n");
        sb.append("Password: ").append(getPassword()).append("\n");
        sb.append("Person Types: ").append(String.join(",", personTypes.toString()));
        displayInformationAboutClient(sb);
        displayInformationAboutEmployee(sb);
        return sb.toString();
    }

}
