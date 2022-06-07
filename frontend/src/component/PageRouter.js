import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import React from 'react';
import HomePage from '../route/HomePage';
import PortfolioListPage from '../route/PortfolioListPage';
import BoardPage from '../route/BoardPage';
import BoardEditPage from '../route/BoardEditPage';
import BoardDetailPage from '../route/BoardDetailPage';
import PortfolioDetailPage from '../route/PortfolioDetailPage';
import PortfolioEditPage from '../route/PortfolioEditPage';
import LoginPage from '../route/LoginPage';
import SignUpPage from '../route/SignUpPage';
import ProfilePage from '../route/ProfilePage';

const PageRouter = () => {

    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/signup/terms" element={<SignUpPage currentPage="terms" />} />
                <Route path="/signup/form" element={<SignUpPage currentPage="form" />} />
                <Route path="/signup/auth" element={<SignUpPage currentPage="auth" />} />
                <Route path="/signup/result" element={<SignUpPage currentPage="result" />} />
                <Route path="/expire" element={<SignUpPage currentPage="expire" />} />

                <Route path="/profile" element={<ProfilePage />} />

                <Route path="/portfolios" element={<PortfolioListPage />} />
                <Route path="/portfolio/:id" element={<PortfolioDetailPage />} />
                <Route path="/portfolio/create" element={<PortfolioEditPage process="create" />} />
                <Route path="/portfolio/modify/:id" element={<PortfolioEditPage process="modify" />} />

                <Route path="/board/free" element={<BoardPage category="free" />} />
                <Route path="/board/free/:id" element={<BoardDetailPage category="free" />} />
                <Route path="/board/info" element={<BoardPage category="info" />} />
                <Route path="/board/info/:id" element={<BoardDetailPage category="info" />} />
                <Route path="/board/recruit" element={<BoardPage category="recruit" />} />
                <Route path="/board/recruit/:id" element={<BoardDetailPage category="recruit" />} />
                <Route path="/board/create" element={<BoardEditPage process="create" />} />
                <Route path="/board/modify/:id" element={<BoardEditPage process="modify" />} />
            </Routes>
        </Router>
    );
};

export default PageRouter;