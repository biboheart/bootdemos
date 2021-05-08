package com.biboheart.demos.service;


import com.biboheart.demos.entity.Area;
import com.biboheart.demos.entity.Sdrugdictionary;
import com.biboheart.demos.entity.Type;
import com.biboheart.demos.entity.Unit;
import com.biboheart.demos.mapper.SdrugdictionaryMapper;

import java.util.List;

public interface SdrugdictionaryService {
    List<SdrugdictionaryMapper> findAllSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int addSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int editSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int deleteSdrugdictionary(Integer drugId);
    List<Unit> findAllUnit();
    List<Area> findAllArea();
    List<Type> findAllType();
    int count(Sdrugdictionary sdrugdictionary);
}
