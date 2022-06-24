import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import React from 'react';
import HomePage from '../route/HomePage';
import PortfolioListPage from '../route/PortfolioListPage';
import BoardPage from '../route/BoardPage';
import PostEditPage from '../route/PostEditPage';
import PostPage from '../route/PostPage';
import PortfolioDetailPage from '../route/PortfolioDetailPage';
import PortfolioEditPage from '../route/PortfolioEditPage';
import LoginPage from '../route/LoginPage';
import SignUpPage from '../route/SignUpPage';
import ProfilePage from '../route/ProfilePage';
import ProjectListPage from '../route/ProjectListPage';
import ProjectDetailPage from '../route/ProjectDetailPage';
import ProjectEditPage from '../route/ProjectEditPage';

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
                <Route path="/portfolios/:id" element={<PortfolioDetailPage />} />
                <Route path="/portfolios/create" element={<PortfolioEditPage process="create" />} />
                <Route path="/portfolios/modify/:id" element={<PortfolioEditPage process="modify" />} />

                <Route path="/projects" element={<ProjectListPage />} />
                <Route path="/projects/complete/:id" element={<ProjectDetailPage category="complete" />} />
                <Route path="/projects/progress/:id" element={<ProjectDetailPage category="progress" />} />
                <Route path="/projects/complete/modify/:id" element={<ProjectEditPage category="complete" />} />
                <Route path="/projects/progress/modify/:id" element={<ProjectEditPage category="progress" />} />

                <Route path="/board/free" element={<BoardPage category="free" />} />
                <Route path="/board/free/:id" element={<PostPage category="free" />} />
                <Route path="/board/info" element={<BoardPage category="info" />} />
                <Route path="/board/info/:id" element={<PostPage category="info" />} />
                <Route path="/board/recruit" element={<BoardPage category="recruit" />} />
                <Route path="/board/recruit/:id" element={<PostPage category="recruit" />} />
                <Route path="/board/create" element={<PostEditPage process="create" />} />
                <Route path="/board/modify/:id" element={<PostEditPage process="modify" />} />
            </Routes>
        </Router>
    );
};

export default PageRouter;