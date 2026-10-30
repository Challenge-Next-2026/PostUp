import { Navigate, Outlet } from 'react-router-dom';

const getCookie = (name: string): string | undefined => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop()?.split(';').shift();
};

export function ProtectedRoute() {
    const token = getCookie('authToken');
    return token ? <Outlet /> : <Navigate to="/login" replace />;
}