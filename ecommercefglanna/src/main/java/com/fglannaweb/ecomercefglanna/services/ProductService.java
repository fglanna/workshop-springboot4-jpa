package com.fglannaweb.ecomercefglanna.services;

import com.fglannaweb.ecomercefglanna.entities.Product;
import com.fglannaweb.ecomercefglanna.repositories.ProductRepository;
import com.fglannaweb.ecomercefglanna.services.exceptions.DatabaseException;
import com.fglannaweb.ecomercefglanna.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }
    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }

    // Método Insert
    public Product insert(Product obj) {
        return repository.save(obj);
    }

    // Método Delete
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    // Método Update
    public Product update(Long id, Product obj) {
        try {
            Product entity = repository.getReferenceById(id); // ou getOne(id) dependendo da versão do Spring
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    // Método auxiliar para o Update (atualiza os dados da entidade antes de salvar)
    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setImgUrl(obj.getImgUrl());
    }
}
