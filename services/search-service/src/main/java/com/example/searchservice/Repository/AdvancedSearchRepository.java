package com.example.searchservice.Repository;

import com.example.searchservice.Model.AdvancedSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdvancedSearchRepository extends JpaRepository<AdvancedSearch,Long> {
}
