import { Navigate, Outlet } from 'react-router-dom';

export function ProtectedRoute() {
    const isLoggedIn = sessionStorage.getItem('isLoggedIn');

    return isLoggedIn
        ? <Outlet />
        : <Navigate to="/login" replace />;
}