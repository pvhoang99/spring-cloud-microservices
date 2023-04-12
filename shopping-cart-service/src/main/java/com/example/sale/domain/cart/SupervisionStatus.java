package com.example.sale.domain.cart;

import com.example.common.domain.ValueObject;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class SupervisionStatus implements ValueObject {

    private Status status;

    private Long supervisorId;

    public static SupervisionStatus create() {
        final SupervisionStatus supervisionStatus = new SupervisionStatus();
        supervisionStatus.status = Status.PENDING;
        supervisionStatus.supervisorId = null;

        return supervisionStatus;
    }

    public static SupervisionStatus approve() {
        SupervisionStatus supervisionStatus = new SupervisionStatus();
        supervisionStatus.status = Status.APPROVED;
        supervisionStatus.supervisorId = 0L;

        return supervisionStatus;
    }

    public static SupervisionStatus deny() {
        SupervisionStatus supervisionStatus = new SupervisionStatus();
        supervisionStatus.status = Status.DENIED;
        supervisionStatus.supervisorId = 0L;

        return supervisionStatus;
    }

}
