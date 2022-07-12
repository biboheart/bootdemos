import org.junit.jupiter.api.Test;

import java.util.*;

public class Demo {
    @Test
    public void tttt() {
        List<Object> list = new ArrayList<>();
        Map<String, Object> hash = new HashMap<>();
        Map<String, Boolean> stateHash = new HashMap<>();
        Set<String> itemsSnSet = new HashSet<>();
        for (Object o : list) {
            hash.put("systemSn" + "_" + "sourceSn", o);
            stateHash.put("systemSn" + "_" + "sourceSn", true);
            itemsSnSet.add("itemsSn");
        }
        // 查询库存
        Map<String, Object> storeHash = new HashMap<>();
        // 查询库存
        List<Object> storeCountList = new ArrayList<>();
        for (Object o : storeCountList) {
            storeHash.put("itemsSn", o);
        }
        // 比对库存，设置状态
        for (Object o : storeCountList) {

        }
        // 判断状态
        // 记录流水ID
        Map<String, Long> idHash = new HashMap<>();
        hash.forEach((s, o) -> {
            // 锁定项目
            // 增加流水
            // 加减库存
            idHash.put("systemSn" + "_" + "sourceSn", 0L);
            // 如果异常，删除idHash中的流水
            // 回滚库存
        });
    }
}
