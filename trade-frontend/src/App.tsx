import React from 'react';
import FundList from './FundList'; 
import Signup from './Signup';
import Login from './Login';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

const App: React.FC = () => {
  return (
    <Router>
      <div className="App">
        <Routes>
          {/* Signup route */}
          <Route path="/signup" element={<Signup />} />

          {/* Fund list page route */}
          <Route path="/funds" element={<FundList />} />

          <Route path="/login" element={<Login />} />

          {/* Default route: redirect to signup */}
          <Route path="*" element={<Navigate to="/signup" replace />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
