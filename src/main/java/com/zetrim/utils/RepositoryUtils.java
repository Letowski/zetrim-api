package com.zetrim.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryUtils {

    public static String escapeSpecialCharacters(String str) {
        if (StringUtils.isNotEmpty(str)) {
            return str.
                    replace("/", "\\/")
                    .replace("_", "\\_")
                    .replace("%", "\\%");
        } else {
            return "";
        }
    }
}
