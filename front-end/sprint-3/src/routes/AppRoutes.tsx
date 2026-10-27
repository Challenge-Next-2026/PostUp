import { Route, Routes } from 'react-router-dom'
import Home from '../pages/Home/Home'
import Sobre from '../pages/Sobre/Sobre'
import NotFound from '../pages/NotFound/NotFound'
import Quemsomos from '../pages/QuemSomos/QuemSomos'
import Sistema from '../pages/Sistema/Sistema'
import Sistema2 from '../pages/Sistema2/Sistema2'
import Faq from '../pages/Faq/Faq'
import Contato from '../pages/Contato/Contato'
import Login from '../pages/Login/Login'
import Cadastro from '../pages/Cadastro/Cadastro'
//import Arquivos from '../pages/Arquivos/Arquivos'   <Route path="/arquivos" element={<Arquivos />} />  // Crie esta página depois
import { ProtectedRoute } from '../components/ProtectedRoute/ProtectedRoute'
import Postagens from '../pages/Postagens/Postagens'



export default function AppRoutes() {
    return (
        <Routes>
            {/* --- Rotas Públicas --- */}
            <Route path="/" element={<Home />} />
            <Route path="/quem-somos" element={<Quemsomos />} />
            <Route path="/faq" element={<Faq />} />
            <Route path="/contato" element={<Contato />} />
            <Route path="/login" element={<Login />} />
            <Route path="/cadastro" element={<Cadastro />} />
            <Route path="/sobre" element={<Sobre />} />
            <Route path="/sistema" element={<Sistema />} />
            <Route path="/sistema2" element={<Sistema2 />} />
            <Route element={<ProtectedRoute />}>
                <Route path="/postagens" element={<Postagens />} />
                
            </Route>
            <Route path="*" element={<NotFound />} />
        </Routes>
    )
}