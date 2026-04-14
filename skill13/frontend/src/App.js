import React, { useState } from 'react';
import AddStudent from './components/AddStudent';
import StudentList from './components/StudentList';
import './App.css';

function App() {
  const [refresh, setRefresh] = useState(false);
  const [editStudent, setEditStudent] = useState(null);

  const handleStudentAdded = () => {
    setRefresh(!refresh);
    setEditStudent(null);
  };

  return (
    <div className="app">
      <header className="app-header">
        <h1>Student Management System</h1>
        <p>KL University - FSAD Skill 12</p>
      </header>
      <main className="app-main">
        <AddStudent
          onStudentAdded={handleStudentAdded}
          editStudent={editStudent}
          onCancelEdit={() => setEditStudent(null)}
        />
        <StudentList
          refresh={refresh}
          onEdit={(student) => setEditStudent(student)}
        />
      </main>
    </div>
  );
}

export default App;
