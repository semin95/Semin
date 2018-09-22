package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.ProductNotFoundException;
import com.etf.ppis.lambda.telenash.model.Product;
import com.etf.ppis.lambda.telenash.model.Request;
import com.etf.ppis.lambda.telenash.repository.ProductRepository;
import com.etf.ppis.lambda.telenash.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController
{
    private final ProductRepository productRepository;
    private final RequestRepository requestRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, RequestRepository requestRepository)
    {
        this.productRepository = productRepository;
        this.requestRepository = requestRepository;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getByIdProduct(@PathVariable(value = "id") Integer id) throws ProductNotFoundException
    {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        return product;
    }

    @GetMapping("/user/{id}")
    public List<Product> getByUserIdProduct(@PathVariable(value = "id") Integer id) throws ProductNotFoundException
    {
        List<Request> requests = requestRepository.findAll();
        Set<Product> productsSet = new HashSet<>();

        for (Request r : requests) {
            if (r.getUser().getId().equals(id) && r.getStatus().getName().equals("Zatvoren"))
                productsSet.add(r.getProduct());
        }
        return  new ArrayList<>(productsSet);
    }

    @GetMapping("/userNot/{id}") //prikaz svih usluga koje korisnik nema
    public List<Product> getByUserIdThatDoesntHaveProduct(@PathVariable(value = "id") Integer id) throws ProductNotFoundException
    {
        List<Request> requests = requestRepository.findAll();
        Set<Product> productsSet = new HashSet<>();

        for (Request r : requests) {
            if (r.getUser().getId().equals(id)){}
            else{
                productsSet.add(r.getProduct());}
        }
        return  new ArrayList<>(productsSet);
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product)
    {
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable(value = "id") Integer id) throws ProductNotFoundException
    {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") Integer id, @RequestBody @Valid Product productUpdate) throws ProductNotFoundException
    {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        product = productUpdate;

        return productRepository.save(product);
    }
}