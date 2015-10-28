package com.codetroopers.materialAndroidBootstrap.util;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StringsTest {
    @Test
    public void testJoinAnd() throws Exception {
        assertThat(Strings.joinAnd(", ", " and ", Arrays.asList("England", "France", "Germany")))
                .isEqualTo("England, France and Germany");
    }
}