package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.DepartmentNotFoundException;
import com.etf.ppis.lambda.telenash.model.Department;
import com.etf.ppis.lambda.telenash.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "*")
public class DepartmentController
{
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository)
    {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/all")
    public List<Department> getAllDepartments()
    {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department getByIdDepartment(@PathVariable(value = "id") Integer id) throws DepartmentNotFoundException
    {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id.toString()));

        return department;
    }

    @PostMapping
    public void createDepartment(@RequestBody Department department)
    {
        departmentRepository.save(department);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteDepartment(@PathVariable(value = "id") Integer id) throws DepartmentNotFoundException
    {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id.toString()));

        departmentRepository.delete(department);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable(value = "id") Integer id, @RequestBody @Valid Department departmentUpdate) throws DepartmentNotFoundException
    {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id.toString()));

        department.setName(departmentUpdate.getName());
        department.setDescription(departmentUpdate.getDescription());
        department.setIncidents(departmentUpdate.getIncidents());
        department.setRequests(departmentUpdate.getRequests());

        Department o = departmentRepository.save(department);
        return o;
    }
}