package programmatic.domain;


import programmatic.listener.AuditingListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingListener.class)
public class AbstractModel implements Serializable {

    private static final long serialVersionUID = 5403519630540522858L;
    @Version
    private int version;

    @Column(name = "CREATE_ID", updatable = false)
    private Long createId;

    @Column(name = "CREATE_INSTANT", updatable = false)
    private Instant createInstant;

    @Column(name = "MODIFY_ID")
    private Long modifyId;

    @Column(name = "MODIFY_INSTANT")
    private Instant modifyInstant;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "SERVER_NAME")
    private String serverName;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Instant getCreateInstant() {
        return createInstant;
    }

    public void setCreateInstant(Instant createInstant) {
        this.createInstant = createInstant;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public Instant getModifyInstant() {
        return modifyInstant;
    }

    public void setModifyInstant(Instant modifyInstant) {
        this.modifyInstant = modifyInstant;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
