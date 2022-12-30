package org.matthenry87.nativetesting.persistence.db2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Db2Service {

    private final BarEntityRepository barEntityRepository;

}
