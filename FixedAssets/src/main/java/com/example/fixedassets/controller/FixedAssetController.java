package com.example.fixedassets.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fixedassets.dto.FixedAssetResponse;
import com.example.fixedassets.dto.FixedAssetsDto;
import com.example.fixedassets.service.FixedAssetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author srihari.g
 *
 **/

@Slf4j
@AllArgsConstructor
@RestController

@RequestMapping("/api/v1/assests")
@Api(description = " Fixed Assets API's")
public class FixedAssetController {

	private FixedAssetService fixedAssetsService;
	private ObjectMapper objectMapper;

	@PostMapping(path ="/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "used to add fixed asset into system") 
	public @ResponseBody ResponseEntity<FixedAssetResponse> addAssets(@ApiParam(value = "FixedAssets Dto to adding FixedAsset into system") @RequestBody @Valid FixedAssetsDto fixedAssetsDto) throws JsonProcessingException {
		log.info("fixedAsset object from request :{}",objectMapper.writeValueAsString(fixedAssetsDto));
		FixedAssetResponse fixedAssetResponse=fixedAssetsService.addAssets(fixedAssetsDto);
		return new ResponseEntity<>(fixedAssetResponse,HttpStatus.CREATED);
	}


	@GetMapping(path="/list",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get list of fixed asset into the system") 
	public @ResponseBody ResponseEntity<List<FixedAssetsDto>> getAssestList(){
		List<FixedAssetsDto> asetsDtos=fixedAssetsService.getAssestList();
		return  new ResponseEntity<List<FixedAssetsDto>>(asetsDtos,HttpStatus.OK);
	}


	@GetMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get single or one  fixed asset based on the fixed asset id") 
	public @ResponseBody ResponseEntity<FixedAssetsDto> getAssestById( @ApiParam(" fixed asset Id to get one fixed asset ") @PathVariable(name = "id",required= true) Long id) {
		log.info("fixed asset id from request\t"+id);
		FixedAssetsDto fixedAssetsDto=fixedAssetsService.getAssest(id);
		return  new ResponseEntity<FixedAssetsDto>(fixedAssetsDto,HttpStatus.OK);
	}

	@GetMapping(path="/assestCode/{assestCode}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get list of fixed asset into the system based on asset code") 
	public @ResponseBody ResponseEntity< List<FixedAssetsDto>> getAssestByAssetCode( @ApiParam("assect code to get fixed asset ") @PathVariable(name = "assestCode",required= true) String assestCode) {
		log.info("assect code from request\t"+assestCode);
		List<FixedAssetsDto> fixedAssetsDto=fixedAssetsService.getAssest(assestCode);
		return  new ResponseEntity< List<FixedAssetsDto>>(fixedAssetsDto,HttpStatus.OK);
	}


	@PutMapping(path="/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "update exsiting assect to the system") 
	public @ResponseBody ResponseEntity<FixedAssetResponse> updateProduct(@ApiParam(value =" fixed assect dto to updating assest into system") @RequestBody @Valid FixedAssetsDto  assetsDto) throws JsonProcessingException {
		log.info("Fixsed assest  object from request{}"+objectMapper.writeValueAsString(assetsDto));
		FixedAssetResponse fixedAssetResponse=fixedAssetsService.updateAssest(assetsDto);
		return new ResponseEntity<FixedAssetResponse>(fixedAssetResponse,HttpStatus.OK);
	}

	@DeleteMapping(path="/delete/{id}")
	@ApiOperation(value = "Delete assect Based on ID") 
	public @ResponseBody ResponseEntity<FixedAssetResponse>	deleteProduct( @ApiParam("assect ID") @PathVariable(name="id",required = true) Long id){
		log.info("assect id from request\t"+id); 
		FixedAssetResponse fixedAssetResponse=fixedAssetsService.deleteAsset(id);
		return new ResponseEntity<FixedAssetResponse>(fixedAssetResponse,HttpStatus.OK);
	}

}
