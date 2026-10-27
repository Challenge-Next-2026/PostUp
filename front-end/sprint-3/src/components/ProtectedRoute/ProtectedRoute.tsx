import { Navigate, Outlet } from 'react-router-dom';

export function ProtectedRoute() {
    const token = localStorage.getItem('authToken');

    return token ? <Outlet /> : <Navigate to="/login" replace />;
}