package com.example.service;

import java.util.Objects;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.document.ProductSequence;

import lombok.AllArgsConstructor;

/**
 * @author srihari.g
 * id generation in mongodb
 * based on the sequences name we can get 
 * sequences no and increment by 1
 * and update into collection as well as
 * 
 * **/
@Service
@AllArgsConstructor
public class ProductSequenceGeneratorService {

	private MongoOperations mongoOperations;
	
	public int generateSequenceNo(String sequenceName) {
		
		 //get sequence no
		Query query=new Query(Criteria.where("_id").is(sequenceName));
		
		//update the sequence no
		Update update=new Update().inc("sequenceNo", 1);
		
		// options for updating 
		FindAndModifyOptions options=new FindAndModifyOptions().returnNew(true).upsert(true);
		
		//update the sequence no in document
		ProductSequence productSequence=mongoOperations.findAndModify(query, update, options,ProductSequence.class);
		
		 return !Objects.isNull(productSequence) ? productSequence.getSequenceNo() : 1;			
	}
}
