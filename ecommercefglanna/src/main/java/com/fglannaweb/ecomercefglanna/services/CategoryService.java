package com.fglannaweb.ecomercefglanna.services;

import com.fglannaweb.ecomercefglanna.entities.Category;
import com.fglannaweb.ecomercefglanna.repositories.CategoryRepository;
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
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }
    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

    // O Insert e o Delete usam a mesma estrutura
    public Category insert(Category obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            // ESSENCIAL: Se você tentar deletar uma categoria que tem produtos vinculados,
            // esse catch vai capturar o erro e lançar a sua exceção customizada de banco.
            throw new DatabaseException(e.getMessage());
        }
    }

    public Category update(Long id, Category obj) {
        try {
            Category entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // ATENÇÃO: Aqui muda! Categoria só tem o campo 'name' no seu script.sql
    private void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
    }


}
