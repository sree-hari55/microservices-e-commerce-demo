package com.example.fixedassets.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.fixedassets.dto.FixedAssetResponse;
import com.example.fixedassets.dto.FixedAssetsDto;
import com.example.fixedassets.exception.AssetNotFoundException;
import com.example.fixedassets.modal.FixedAsset;
import com.example.fixedassets.repository.FixedAssestsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



/**
 * @author srihari.g
 *
 */


@Service
@AllArgsConstructor
@Slf4j
public class FixedAssetServiceImpl implements FixedAssetService {

	private FixedAssestsRepository fixedAssestsRepository;
	private ModelMapper modelMapper;
	private ObjectMapper objectMapper;

	@Override
	public FixedAssetResponse addAssets(FixedAssetsDto fixedAssetsDto) throws JsonProcessingException {
		FixedAsset fixedAsset=modelMapper.map(fixedAssetsDto, FixedAsset.class);
		log.info("after converting dto into modal{}"+objectMapper.writeValueAsString(fixedAsset));
		fixedAsset =fixedAssestsRepository.save(fixedAsset);
		return new FixedAssetResponse("SUCCESS","fixed asset is inserted successfully with Id:"+fixedAsset.getId());
	}

	@Override
	public List<FixedAssetsDto> getAssestList() {
		List<FixedAsset> assetsList=fixedAssestsRepository.findAll();

		if(assetsList.isEmpty()) {
			throw new AssetNotFoundException("no assets found");
		}
		List<FixedAssetsDto> asetsDtos=assetsList.stream().map(asset -> modelMapper.map(asset, FixedAssetsDto.class)).collect(Collectors.toList());
		log.info("all fixed assests {}"+asetsDtos.size());
		return asetsDtos;
	}

	@Override
	public FixedAssetsDto getAssest(Long id) {
		FixedAsset fixedAsset= fixedAssestsRepository.findById(id).orElseThrow(() -> new AssetNotFoundException("asset not found for id:"+id));
		FixedAssetsDto fixedAssetsDto=modelMapper.map(fixedAsset,FixedAssetsDto.class );
		return fixedAssetsDto;
	}

	@Override
	public List<FixedAssetsDto> getAssest(String assetCode) {
		List<FixedAsset> fixedAssetList= fixedAssestsRepository.findByAssetCode(assetCode);
		if(fixedAssetList.isEmpty()) {
			throw new AssetNotFoundException("no assets found");
		}
		List<FixedAssetsDto> asetsDtos=fixedAssetList.stream().map(asset -> modelMapper.map(asset, FixedAssetsDto.class)).collect(Collectors.toList());
		log.info("all fixed assests {}"+asetsDtos.size());
		return asetsDtos;
	}

	@Override
	public FixedAssetResponse updateAssest(FixedAssetsDto fixedAssetsDto) throws JsonProcessingException {

		Optional<FixedAsset> optionalFixedAsset=fixedAssestsRepository.findById(fixedAssetsDto.getId());
		if(!optionalFixedAsset.isPresent()) {
			return new FixedAssetResponse("FAILED","asset to be updated not found with Id:{}"+fixedAssetsDto.getId());
		}
		FixedAsset fixedAsset=optionalFixedAsset.get();
		fixedAsset=modelMapper.map(fixedAssetsDto, FixedAsset.class);
		/*
		 * fixedAsset.setAssetCode(fixedAssetsDto.getAssetCode());
		 * fixedAsset.setNewItemname(fixedAsset.getNewItemname());
		 * fixedAsset.setNewItemDescription(fixedAsset.getNewItemDescription());
		 * fixedAsset.setQuantity(fixedAssetsDto.getQuantity());
		 * fixedAsset.setAction(fixedAssetsDto.getAction());
		 * fixedAsset.setCurrentStock(fixedAssetsDto.getCurrentStock());
		 * fixedAsset.setRemarks(fixedAssetsDto.getRemarks());
		 * fixedAsset.setSanctionedQuantity(fixedAssetsDto.getSanctionedQuantity());
		 * fixedAsset.setSanctionProposalDetails(fixedAssetsDto.
		 * getSanctionProposalDetails());
		 * fixedAsset.setStockIssuePendingQuantity(fixedAssetsDto.
		 * getStockIssuePendingQuantity());
		 * fixedAsset.setStockRequestPendingQuantity(fixedAssetsDto.
		 * getStockRequestPendingQuantity());
		 */

		fixedAsset =fixedAssestsRepository.save(fixedAsset);
		log.info("after updating assests in db{}"+objectMapper.writeValueAsString(fixedAsset));
		modelMapper.map(fixedAsset, FixedAssetsDto.class);
		return new FixedAssetResponse("SUCCESS","asset updated sucesfully with Id:{}"+fixedAssetsDto.getId());
	}

	@Override
	public FixedAssetResponse deleteAsset(Long id) {
		Optional<FixedAsset> optionalFixedAsset=fixedAssestsRepository.findById(id);
		if(!optionalFixedAsset.isPresent()) {
			return new FixedAssetResponse("FAILED","asset to be Deleted not found with Id:{}"+id);
		}
		fixedAssestsRepository.deleteById(id);
		return new FixedAssetResponse("SUCCESS","asset deleted succesfully with Id:{}"+id);
	}

}