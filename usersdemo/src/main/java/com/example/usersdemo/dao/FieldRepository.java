package com.example.usersdemo.dao;
import com.example.usersdemo.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FieldRepository extends JpaRepository<Field,Long> {
}
