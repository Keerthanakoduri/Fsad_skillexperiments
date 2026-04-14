package com.kluniversity.swagger.service;

import com.kluniversity.swagger.model.Student;
import com.kluniversity.swagger.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student save(Student s)                      { return repo.save(s); }
    public List<Student> findAll()                      { return repo.findAll(); }
    public Optional<Student> findById(Long id)          { return repo.findById(id); }
    public boolean existsById(Long id)                  { return repo.existsById(id); }
    public void deleteById(Long id)                     { repo.deleteById(id); }

    public Student update(Long id, Student updated) {
        return repo.findById(id).map(s -> {
            s.setName(updated.getName());
            s.setEmail(updated.getEmail());
            s.setCourse(updated.getCourse());
            return repo.save(s);
        }).orElse(null);
    }
}
