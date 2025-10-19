package com.github.monkeywie.proxyee.mongodb.model.xhs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InteractInfo {
    private String liked_count;
    private boolean liked;
}
