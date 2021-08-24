package com.biboheart.ditems;

import org.junit.Test;

public class ScopeTest {
    @Test
    public void generateScope() {
        // String[] scopes = {"收费", "体检", "临床", "护理", "手麻", "治疗", "检验", "检查", "皮试", "输液", "输血", "西药", "中药", "材料", "仓库"};
        // String[] classify = {"门诊", "住院", "体检", "急诊"};
        Long scope = 1L << 12 | 1L << 14;
        // Long scope = 1L << 2;
        System.out.println(scope);
        Integer cls = 1 | 1 << 1 | 1 << 3;
        System.out.println(cls);
    }

    @Test
    public void testStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("0");
        }
        sb.replace(5, 6, "1");
        System.out.println(sb.toString());
    }
}
