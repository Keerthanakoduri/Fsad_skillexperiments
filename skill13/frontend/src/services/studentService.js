import axios from 'axios';

const BASE_URL = `${process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080'}/students`;

export const getAllStudents = () => axios.get(BASE_URL);
export const addStudent = (student) => axios.post(BASE_URL, student);
export const updateStudent = (id, student) => axios.put(`${BASE_URL}/${id}`, student);
export const deleteStudent = (id) => axios.delete(`${BASE_URL}/${id}`);
