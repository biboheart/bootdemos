package com.biboheart.demos.service.impl;

import com.biboheart.demos.entity.ItemsContrast;
import com.biboheart.demos.repository.ItemsContrastRepository;
import com.biboheart.demos.repository.ValidConsumablesRepository;
import com.biboheart.demos.service.ItemsContrastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemsContrastServiceImpl implements ItemsContrastService {
    private final ItemsContrastRepository itemsContrastRepository;
    private final ValidConsumablesRepository validConsumablesRepository;

    @Override
    public List<Map<String, String>> compare() {
        // List<String> countryNumberList = itemsContrastRepository.findCountryNumber(3);
        List<ItemsContrast> itemsContrastList = itemsContrastRepository.findByType(3);
        System.out.println(itemsContrastList.size());
        Set<String> countryNumberList = new HashSet<>();
        for (ItemsContrast itemsContrast : itemsContrastList) {
            countryNumberList.add(itemsContrast.getCountryNumber());
        }
        System.out.println(countryNumberList.size());
        List<String> atCountryNumberList = validConsumablesRepository.findCountryNumber(countryNumberList);
        System.out.println(atCountryNumberList.size());
        List<Map<String, String>> list = new ArrayList<>();
        for (ItemsContrast itemsContrast : itemsContrastList) {
            if (atCountryNumberList.contains(itemsContrast.getCountryNumber())) {
                continue;
            }
            Map<String, String> items = new HashMap<>();
            items.put("本地编号", itemsContrast.getLocalSn());
            items.put("名称", itemsContrast.getName());
            items.put("规格", itemsContrast.getSpecifications());
            list.add(items);
        }
        return list;
    }
}
