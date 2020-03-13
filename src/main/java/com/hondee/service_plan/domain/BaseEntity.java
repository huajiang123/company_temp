package com.hondee.service_plan.domain;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    Boolean validity;
    String createdBy;
    Date createdTime;
    String updatedBy;
    Date updatedTime;
}
