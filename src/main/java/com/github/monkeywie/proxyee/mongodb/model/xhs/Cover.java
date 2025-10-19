package com.github.monkeywie.proxyee.mongodb.model.xhs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cover {
    private String url_pre;
    private int width;
    private List<Info> info_list;
    private String url_default;
    private int height;
}
