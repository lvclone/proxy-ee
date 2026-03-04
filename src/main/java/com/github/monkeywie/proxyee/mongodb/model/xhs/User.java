package com.github.monkeywie.proxyee.mongodb.model.xhs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String user_id;
    private String nickname;
    private String avatar;
}
