import { useState } from "react";
import axios from "axios";

function AddStudent({ refreshStudents }) {

  const [name,setName] = useState("");
  const [email,setEmail] = useState("");
  const [course,setCourse] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    await axios.post("http://localhost:8080/students", {
      name,
      email,
      course
    });

    setName("");
    setEmail("");
    setCourse("");

    refreshStudents();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input value={name} onChange={(e)=>setName(e.target.value)} placeholder="Name"/>
      <input value={email} onChange={(e)=>setEmail(e.target.value)} placeholder="Email"/>
      <input value={course} onChange={(e)=>setCourse(e.target.value)} placeholder="Course"/>

      <button type="submit">Add Student</button>
    </form>
  );
}

export default AddStudent;