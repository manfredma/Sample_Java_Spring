package programmatic.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Subscriber.findAll", query = "SELECT s FROM Subscriber s")
public class Subscriber extends AbstractModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "BUSINESS_ID")
    private Long businessId;

    private String password;

    private String mem;

    public Subscriber() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }
}