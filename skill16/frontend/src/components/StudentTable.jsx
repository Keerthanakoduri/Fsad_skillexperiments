import React, { useEffect, useState } from 'react';
import { getAllStudents, deleteStudent } from '../services/studentService';

const StudentTable = ({ refresh, onEdit }) => {
  const [students, setStudents] = useState([]);
  const [loading, setLoading]   = useState(false);

  useEffect(() => {
    setLoading(true);
    getAllStudents()
      .then(res => setStudents(res.data))
      .catch(err => console.error(err))
      .finally(() => setLoading(false));
  }, [refresh]);

  const handleDelete = async (id) => {
    if (!window.confirm('Delete this student?')) return;
    try {
      await deleteStudent(id);
      setStudents(prev => prev.filter(s => s.id !== id));
    } catch (err) {
      alert(err.response?.data?.message || 'Delete failed.');
    }
  };

  if (loading) return <p className="info-text">Loading students...</p>;

  return (
    <div className="table-card">
      <h2>📋 Student Records</h2>
      {students.length === 0 ? (
        <p className="empty-msg">No students found.</p>
      ) : (
        <table>
          <thead>
            <tr><th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Actions</th></tr>
          </thead>
          <tbody>
            {students.map(s => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.name}</td>
                <td>{s.email}</td>
                <td>{s.course}</td>
                <td>
                  <button className="btn-edit"   onClick={() => onEdit(s)}>Edit</button>
                  <button className="btn-delete" onClick={() => handleDelete(s.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default StudentTable;
