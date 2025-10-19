package com.github.monkeywie.proxyee.mongodb.model.xhs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    private String image_scene;
    private String url;
}
