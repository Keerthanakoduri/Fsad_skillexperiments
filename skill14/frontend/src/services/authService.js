import axios from 'axios';

const BASE = 'http://localhost:8080/api/auth';

export const registerUser = (data) => axios.post(`${BASE}/register`, data);
export const loginUser   = (data) => axios.post(`${BASE}/login`, data);
export const getProfile  = (id)   => axios.get(`${BASE}/profile/${id}`);

// Session helpers
export const saveSession = (userId, username) => {
  sessionStorage.setItem('userId', userId);
  sessionStorage.setItem('username', username);
};

export const getSession = () => ({
  userId:   sessionStorage.getItem('userId'),
  username: sessionStorage.getItem('username'),
});

export const clearSession = () => {
  sessionStorage.removeItem('userId');
  sessionStorage.removeItem('username');
};

export const isLoggedIn = () => !!sessionStorage.getItem('userId');
