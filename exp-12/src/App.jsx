import { useState } from "react";
import AddStudent from "./AddStudent";
import StudentList from "./StudentList";

function App() {

  const [refresh,setRefresh] = useState(false);

  const refreshStudents = () => {
    setRefresh(!refresh);
  };

  return (
    <div>
      <h2>Student Management System</h2>

      <AddStudent refreshStudents={refreshStudents}/>

      <StudentList refresh={refresh}/>
    </div>
  );
}

export default App;