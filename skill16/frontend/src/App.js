import React, { useState } from 'react';
import StudentForm  from './components/StudentForm';
import StudentTable from './components/StudentTable';
import './App.css';

function App() {
  const [refresh,     setRefresh]     = useState(false);
  const [editStudent, setEditStudent] = useState(null);

  const handleDone = () => {
    setRefresh(r => !r);
    setEditStudent(null);
  };

  return (
    <div className="app">
      <header className="app-header">
        <h1>🎓 Student Management — Skill 16</h1>
        <p>KL University | FSAD | Swagger/OpenAPI Documentation</p>
        <a
          href="http://localhost:8080/swagger-ui.html"
          target="_blank"
          rel="noreferrer"
          className="swagger-link"
        >
          📄 Open Swagger UI
        </a>
      </header>

      <main className="app-main">
        <StudentForm
          editStudent={editStudent}
          onDone={handleDone}
          onCancel={() => setEditStudent(null)}
        />
        <StudentTable
          refresh={refresh}
          onEdit={setEditStudent}
        />
      </main>
    </div>
  );
}

export default App;
