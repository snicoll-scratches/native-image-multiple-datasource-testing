package org.matthenry87.nativetesting.persistence.db2;

import org.matthenry87.nativetesting.persistence.db1.FooEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarEntityRepository extends JpaRepository<BarEntity, Long> {

}
