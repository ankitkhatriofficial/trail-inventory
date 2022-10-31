package com.khatrienterprises.trailinventory.repository;

import com.khatrienterprises.trailinventory.documents.ProductTransaction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Ankit Khatri
 */
public interface ProductTransactionRepository extends MongoRepository<ProductTransaction, ObjectId> {

    List<ProductTransaction> findByProductId(String productId);

}
