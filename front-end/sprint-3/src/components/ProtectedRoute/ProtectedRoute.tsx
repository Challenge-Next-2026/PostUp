import { Navigate, Outlet } from 'react-router-dom';

export function ProtectedRoute() {
    const token = localStorage.getItem('authToken');
    // Se houver token, renderiza a rota (Outlet), caso contrário manda para o login
    return token ? <Outlet /> : <Navigate to="/login" replace />;
}