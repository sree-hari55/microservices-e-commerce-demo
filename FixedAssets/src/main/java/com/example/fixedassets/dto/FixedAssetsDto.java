package com.example.fixedassets.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "fixed asset dto class")
public class FixedAssetsDto implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	protected Long id;
	
	@NotNull(message = "asset code required")
	@NotEmpty(message = "asset code should not be empty")
	protected String assetCode;
	
	@NotNull(message = "Item Name  required")
	@NotEmpty(message = "Item Name should not be empty")
	protected String newItemName;
	
	protected String newItemDescription;
	
	@Min(value = 0,message = "quantity should not be negative")
	protected int quantity;
	
	protected String stockRequestPendingQuantity;
	protected int currentStock;
	protected String stockIssuePendingQuantity;
	protected String remarks;
	protected int sanctionedQuantity;
	protected String sanctionProposalDetails;
	protected String action;
	
	
}
