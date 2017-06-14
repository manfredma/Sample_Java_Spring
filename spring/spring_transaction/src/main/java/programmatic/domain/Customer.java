package programmatic.domain;


import programmatic.domain.enums.CustomerType;
import programmatic.domain.enums.Sex;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;


@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_ID", sequenceName = "seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ID")
    private Long id;

    private String name;

    private String code;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private CustomerType type;

    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Column(name = "DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    @Column(name = "BIRTH_DAY")
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Customer parent;

    private String mem;

    @Column(name = "ACTIVE_DATE_TIME")
    private ZonedDateTime activeDateTime;

    @Column(name = "ACTIVE_DATE_TIME2")
    private ZonedDateTime activeDateTime2;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getFirstName() {
        return firstName;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Customer getParent() {
        return parent;
    }

    public void setParent(Customer parent) {
        this.parent = parent;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public ZonedDateTime getActiveDateTime() {
        return activeDateTime;
    }

    public void setActiveDateTime(ZonedDateTime activeDateTime) {
        this.activeDateTime = activeDateTime;
    }

    public ZonedDateTime getActiveDateTime2() {
        return activeDateTime2;
    }

    public void setActiveDateTime2(ZonedDateTime activeDateTime2) {
        this.activeDateTime2 = activeDateTime2;
    }
}