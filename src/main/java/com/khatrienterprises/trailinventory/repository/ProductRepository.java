package com.khatrienterprises.trailinventory.repository;

import com.khatrienterprises.trailinventory.documents.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Ankit Khatri
 */
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    // TODO to make it custom mongoOperations
}
