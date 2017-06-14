package programmatic.listener;

import programmatic.domain.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

@Configurable
public class AuditingListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditingListener.class);

    @PrePersist
    public void touchForCreate(Object target) {

        if (target instanceof AbstractModel) {
            AbstractModel model = (AbstractModel) target;
            Instant now = Instant.now();
            model.setCreateInstant(now);
            model.setModifyInstant(now);
            model.setVersion(1);
        }
    }

    @PreUpdate
    public void touchForUpdate(Object target) {
        if (target instanceof AbstractModel) {
            AbstractModel model = (AbstractModel) target;
            model.setModifyInstant(Instant.now());
        }
    }
}
