package org.bube.distributed.locks.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Table INT_LOCK is needed for the distributed lock to work.
 * This entity serves only 1 purpose, to auto-create this table.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntLock {

  @Id
  private String lockKey;
  private String region;
  private String clientId;
  private LocalDateTime createdDate;

}
