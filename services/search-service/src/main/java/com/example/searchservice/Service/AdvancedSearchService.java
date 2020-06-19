package com.example.searchservice.Service;

import com.example.searchservice.Model.AdvancedSearch;
import com.example.searchservice.Model.Dto.SearchDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface AdvancedSearchService {

    public AdvancedSearch addAdvancedReplica(AdvancedSearch vozilo);
    public void removeAdvancedReplica(Long idVozila);
    public List<Long> findByAdvancedSearch(SearchDTO search);



}
