import axios from 'axios';

const BASE_URL = 'http://localhost:8080/students';

export const getAllStudents    = ()         => axios.get(BASE_URL);
export const getStudentById   = (id)       => axios.get(`${BASE_URL}/${id}`);
export const addStudent        = (student)  => axios.post(BASE_URL, student);
export const updateStudent     = (id, s)    => axios.put(`${BASE_URL}/${id}`, s);
export const deleteStudent     = (id)       => axios.delete(`${BASE_URL}/${id}`);
