package com.kluniversity.swagger.controller;

import com.kluniversity.swagger.model.ErrorResponse;
import com.kluniversity.swagger.model.Student;
import com.kluniversity.swagger.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
@Tag(name = "Student Management", description = "APIs for managing student records - FSAD Skill 16")
public class StudentController {

    @Autowired
    private StudentService service;

    // ─────────────────────────────────────────
    // POST /students - Add a new student
    // ─────────────────────────────────────────
    @Operation(
        summary     = "Add a new student",
        description = "Creates a new student record and saves it to the database. Returns the created student with its generated ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Student created successfully",
            content = @Content(schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        if (student.getName() == null || student.getName().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Student name cannot be empty", 400));
        }
        Student saved = service.save(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ─────────────────────────────────────────
    // GET /students - Get all students
    // ─────────────────────────────────────────
    @Operation(
        summary     = "Retrieve all students",
        description = "Returns a list of all student records stored in the database."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of students retrieved successfully",
            content = @Content(schema = @Schema(implementation = Student.class)))
    })
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(service.findAll());
    }

    // ─────────────────────────────────────────
    // GET /students/{id} - Get student by ID
    // ─────────────────────────────────────────
    @Operation(
        summary     = "Get student by ID",
        description = "Returns a single student record identified by the given ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Student found",
            content = @Content(schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(
            @Parameter(description = "ID of the student to retrieve", example = "1")
            @PathVariable Long id) {
        return service.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Student not found with id: " + id, 404)));
    }

    // ─────────────────────────────────────────
    // PUT /students/{id} - Update student
    // ─────────────────────────────────────────
    @Operation(
        summary     = "Update an existing student",
        description = "Updates the details of an existing student identified by the given ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Student updated successfully",
            content = @Content(schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @Parameter(description = "ID of the student to update", example = "1")
            @PathVariable Long id,
            @RequestBody Student student) {
        Student updated = service.update(id, student);
        if (updated != null) return ResponseEntity.ok(updated);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Student not found with id: " + id, 404));
    }

    // ─────────────────────────────────────────
    // DELETE /students/{id} - Delete student
    // ─────────────────────────────────────────
    @Operation(
        summary     = "Delete a student",
        description = "Deletes the student record identified by the given ID from the database."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(
            @Parameter(description = "ID of the student to delete", example = "1")
            @PathVariable Long id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.ok("Student with id " + id + " deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Student not found with id: " + id, 404));
    }
}
