import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { registerUser } from '../services/authService';

const Register = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState({ username: '', password: '', email: '', fullName: '', phone: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    if (!form.username || !form.password || !form.email) {
      setError('Username, password and email are required!');
      return;
    }
    setLoading(true);
    try {
      const res = await registerUser(form);
      if (res.data.success) {
        alert('Registration successful! Please login.');
        navigate('/login');
      } else {
        setError(res.data.message);
      }
    } catch (err) {
      setError('Registration failed. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-wrapper">
      <div className="auth-card">
        <h2>Create Account</h2>
        <p className="auth-subtitle">KL University Student Portal</p>
        {error && <div className="error-msg">{error}</div>}
        <form onSubmit={handleSubmit}>
          <input name="fullName"  placeholder="Full Name"  value={form.fullName}  onChange={handleChange} />
          <input name="username"  placeholder="Username *" value={form.username}  onChange={handleChange} required />
          <input name="password"  placeholder="Password *" type="password" value={form.password} onChange={handleChange} required />
          <input name="email"     placeholder="Email *"    type="email"  value={form.email}    onChange={handleChange} required />
          <input name="phone"     placeholder="Phone"      value={form.phone}    onChange={handleChange} />
          <button type="submit" disabled={loading}>{loading ? 'Registering...' : 'Register'}</button>
        </form>
        <p className="auth-link">Already have an account? <Link to="/login">Login</Link></p>
      </div>
    </div>
  );
};

export default Register;
