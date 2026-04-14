import React, { useState } from 'react';
import { addStudent, updateStudent } from '../services/studentService';

const AddStudent = ({ onStudentAdded, editStudent, onCancelEdit }) => {
  const [form, setForm] = useState(
    editStudent || { name: '', email: '', course: '' }
  );

  React.useEffect(() => {
    if (editStudent) setForm(editStudent);
    else setForm({ name: '', email: '', course: '' });
  }, [editStudent]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.name || !form.email || !form.course) {
      alert('All fields are required!');
      return;
    }
    try {
      if (editStudent) {
        await updateStudent(editStudent.id, form);
        alert('Student updated successfully!');
      } else {
        await addStudent(form);
        alert('Student added successfully!');
      }
      setForm({ name: '', email: '', course: '' });
      onStudentAdded();
    } catch (err) {
      alert('Error: ' + err.message);
    }
  };

  return (
    <div className="form-container">
      <h2>{editStudent ? 'Edit Student' : 'Add New Student'}</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={form.name}
            onChange={handleChange}
            placeholder="Enter student name"
          />
        </div>
        <div className="form-group">
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={form.email}
            onChange={handleChange}
            placeholder="Enter email"
          />
        </div>
        <div className="form-group">
          <label>Course:</label>
          <input
            type="text"
            name="course"
            value={form.course}
            onChange={handleChange}
            placeholder="Enter course"
          />
        </div>
        <div className="btn-group">
          <button type="submit" className="btn-primary">
            {editStudent ? 'Update Student' : 'Add Student'}
          </button>
          {editStudent && (
            <button type="button" className="btn-secondary" onClick={onCancelEdit}>
              Cancel
            </button>
          )}
        </div>
      </form>
    </div>
  );
};

export default AddStudent;
