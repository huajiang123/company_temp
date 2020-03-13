package com.hondee.service_plan.domain;

import com.hondee.common.spring.domain.VersionBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "plan_version")
public class PlanVersion extends VersionBase {
}
