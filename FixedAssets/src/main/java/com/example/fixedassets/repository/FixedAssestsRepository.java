package com.example.fixedassets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fixedassets.modal.FixedAsset;

@Repository
public interface FixedAssestsRepository extends JpaRepository<FixedAsset, Long>{

	List<FixedAsset> findByAssetCode(String assestCode);
}
