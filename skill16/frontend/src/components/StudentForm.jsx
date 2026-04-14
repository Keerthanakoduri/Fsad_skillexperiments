import React, { useState, useEffect } from 'react';
import { addStudent, updateStudent } from '../services/studentService';

const StudentForm = ({ editStudent, onDone, onCancel }) => {
  const [form, setForm] = useState({ name: '', email: '', course: '' });
  const [error, setError] = useState('');

  useEffect(() => {
    setForm(editStudent ? { name: editStudent.name, email: editStudent.email, course: editStudent.course } : { name: '', email: '', course: '' });
  }, [editStudent]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    if (!form.name.trim()) { setError('Name is required.'); return; }
    try {
      if (editStudent) {
        await updateStudent(editStudent.id, form);
      } else {
        await addStudent(form);
      }
      onDone();
    } catch (err) {
      setError(err.response?.data?.message || 'Operation failed.');
    }
  };

  return (
    <div className="form-card">
      <h2>{editStudent ? '✏️ Edit Student' : '➕ Add Student'}</h2>
      {error && <div className="error-box">{error}</div>}
      <form onSubmit={handleSubmit}>
        <input name="name"   placeholder="Full Name *" value={form.name}   onChange={handleChange} required />
        <input name="email"  placeholder="Email"       value={form.email}  onChange={handleChange} />
        <input name="course" placeholder="Course"      value={form.course} onChange={handleChange} />
        <div className="btn-row">
          <button type="submit" className="btn-primary">{editStudent ? 'Update' : 'Add Student'}</button>
          {editStudent && <button type="button" className="btn-cancel" onClick={onCancel}>Cancel</button>}
        </div>
      </form>
    </div>
  );
};

export default StudentForm;
