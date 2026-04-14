import React, { useEffect, useState } from 'react';
import { getAllStudents, deleteStudent } from '../services/studentService';

const StudentList = ({ refresh, onEdit }) => {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchStudents = async () => {
    setLoading(true);
    try {
      const res = await getAllStudents();
      setStudents(res.data);
    } catch (err) {
      console.error('Error fetching students:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, [refresh]);

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this student?')) {
      try {
        await deleteStudent(id);
        setStudents(students.filter((s) => s.id !== id));
        alert('Student deleted successfully!');
      } catch (err) {
        alert('Error deleting student: ' + err.message);
      }
    }
  };

  if (loading) return <p>Loading students...</p>;

  return (
    <div className="list-container">
      <h2>Student List</h2>
      {students.length === 0 ? (
        <p className="empty-msg">No students available</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Course</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.email}</td>
                <td>{student.course}</td>
                <td>
                  <button className="btn-edit" onClick={() => onEdit(student)}>
                    Edit
                  </button>
                  <button className="btn-delete" onClick={() => handleDelete(student.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default StudentList;
