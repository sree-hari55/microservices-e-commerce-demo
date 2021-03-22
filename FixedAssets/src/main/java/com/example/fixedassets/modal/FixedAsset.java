package com.example.fixedassets.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fixed_assest")
public class FixedAsset implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",insertable = false,unique = true,updatable = false,nullable = false)
	protected Long id;
	
	@Column(name="asset_code",nullable = false,length = 256)
	protected String assetCode;
	
	@Column(name="new_item_name",length = 256)
	protected String newItemname;
	
	@Column(name="new_item_description",length = 256)
	protected String newItemDescription;
	
	@Column(name="quantity")
	protected int quantity;
	
	@Column(name="stockRequest_pending_qty")
	protected String stockRequestPendingQuantity;
	
	@Column(name="current_stock")
	protected int currentStock;
	
	@Column(name="stockissue_pending_qty")
	protected String stockIssuePendingQuantity;
	
	
	@Column(name="remarks")
	protected String remarks;
	
	
	@Column(name="sanctioned_qty")
	protected int sanctionedQuantity;
	
	@Column(name="sanction_proposal_details")
	protected String sanctionProposalDetails;
	
	
	@Column(name="action")
	protected String action;
	

}
