package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product.Product;
import com.udacity.course3.reviews.entity.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    // TODO: Wire JPA repositories here
    @Autowired
    ProductRepository productRepo;

    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody Product product) {
        productRepo.save(product);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Product prod;
        try {
            prod = productRepo.findById(id).orElseThrow(() -> new Exception("Product not found " + id));
        } catch (Exception ex) {
            return new ResponseEntity(new ArrayList(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(prod, HttpStatus.FOUND);
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listProducts() {
        return new ResponseEntity(productRepo.findAll(), HttpStatus.OK);
    }
}