package com.example.fixedassets.service;

import java.util.List;

import com.example.fixedassets.dto.FixedAssetResponse;
import com.example.fixedassets.dto.FixedAssetsDto;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * @author srihari.g
 *
 */

public interface FixedAssetService {

	FixedAssetResponse addAssets(FixedAssetsDto fixedAssetsDto) throws JsonProcessingException;
	List<FixedAssetsDto> getAssestList();
	FixedAssetsDto getAssest(Long id);
	List<FixedAssetsDto> getAssest(String assetCode);
	FixedAssetResponse updateAssest(FixedAssetsDto fixedAssetsDto) throws JsonProcessingException;
	FixedAssetResponse deleteAsset(Long id);
}
