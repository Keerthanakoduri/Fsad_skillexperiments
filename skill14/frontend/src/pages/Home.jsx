import React, { useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { getSession, clearSession, isLoggedIn } from '../services/authService';

const Home = () => {
  const navigate  = useNavigate();
  const { username } = getSession();

  useEffect(() => {
    if (!isLoggedIn()) navigate('/login');
  }, [navigate]);

  const handleLogout = () => {
    clearSession();
    navigate('/login');
  };

  return (
    <div className="page-wrapper">
      <nav className="navbar">
        <span className="nav-brand">🎓 KL University</span>
        <div className="nav-links">
          <Link to="/home">Home</Link>
          <Link to="/profile">Profile</Link>
          <button className="btn-logout" onClick={handleLogout}>Logout</button>
        </div>
      </nav>
      <div className="page-content">
        <div className="welcome-card">
          <h1>Welcome, {username}! 👋</h1>
          <p>You are successfully logged in to the KL University Student Portal.</p>
          <div className="quick-links">
            <Link to="/profile" className="quick-card">
              <span>👤</span>
              <p>View Profile</p>
            </Link>
            <div className="quick-card" onClick={handleLogout} style={{cursor:'pointer'}}>
              <span>🔓</span>
              <p>Logout</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
