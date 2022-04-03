package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

    @Autowired
    AllocationRepository repository;

    @Test
    public void findAll() {
        // Act
        

        // Print
        
    }

    @Test
    public void findById() {
        // Arrange
        

        // Act
        

        // Print
        
    }

    @Test
    public void findByProfessorId() {
        // Arrange
        

        // Act
        

        // Print
        
    }

    @Test
    public void findByCourseId() {
        // Arrange
        

        // Act
        

        // Print
        
    }

    @Test
    public void save_create() throws ParseException {
        // Arrange
        Allocation allocation = new Allocation();
        allocation.setCourseId(1L);
        allocation.setProfessorId(1L);
        allocation.setDay(DayOfWeek.MONDAY);
        allocation.setStart(sdf.parse("19:00-0300"));
        allocation.setEnd(sdf.parse("21:00-0300"));

        // Act
        repository.save(allocation);

        // Print
        System.out.println(allocation);
    }

    @Test
    public void save_update() throws ParseException {
        // Arrange
        

        // Act
        

        // Print
        
    }

    @Test
    public void deleteById() {
        // Arrange
        

        // Act
        
    }

    @Test
    public void deleteAll() {
        // Act
        
    }
}
