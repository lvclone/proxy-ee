package com.github.monkeywie.proxyee.mongodb.model.xhs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfo {

    @Id
    private String cursor;

    private String xS;

}
