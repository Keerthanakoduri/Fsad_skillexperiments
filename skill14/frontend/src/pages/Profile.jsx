import React, { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { getSession, clearSession, isLoggedIn, getProfile } from '../services/authService';

const Profile = () => {
  const navigate   = useNavigate();
  const [user, setUser]     = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError]   = useState('');

  useEffect(() => {
    if (!isLoggedIn()) { navigate('/login'); return; }
    const { userId } = getSession();
    getProfile(userId)
      .then(res => setUser(res.data))
      .catch(() => setError('Failed to load profile.'))
      .finally(() => setLoading(false));
  }, [navigate]);

  const handleLogout = () => { clearSession(); navigate('/login'); };

  if (loading) return <div className="page-wrapper"><p style={{padding:'40px'}}>Loading profile...</p></div>;

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
        <div className="profile-card">
          <h2>My Profile</h2>
          {error && <div className="error-msg">{error}</div>}
          {user && (
            <table className="profile-table">
              <tbody>
                <tr><td><strong>ID</strong></td><td>{user.id}</td></tr>
                <tr><td><strong>Full Name</strong></td><td>{user.fullName || '—'}</td></tr>
                <tr><td><strong>Username</strong></td><td>{user.username}</td></tr>
                <tr><td><strong>Email</strong></td><td>{user.email}</td></tr>
                <tr><td><strong>Phone</strong></td><td>{user.phone || '—'}</td></tr>
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
};

export default Profile;
