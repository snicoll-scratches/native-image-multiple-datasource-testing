package org.matthenry87.nativetesting.api.db1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Db1Service {

    private final FooEntityRepository fooEntityRepository;

}
