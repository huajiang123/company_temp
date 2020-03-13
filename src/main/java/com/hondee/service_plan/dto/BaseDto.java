package com.hondee.service_plan.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    Boolean validity;
    String createdBy;
    String createdByName;
    Long createdByHead;
    Date createdTime;
    String updatedBy;
    String updatedByName;
    Long updatedByHead;
    Date updatedTime;
}
