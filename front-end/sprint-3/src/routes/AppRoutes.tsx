import { Route, Routes } from 'react-router-dom'
import Home from '../pages/Home/Home'
import Sobre from '../pages/Sobre/Sobre'
import NotFound from '../pages/NotFound/NotFound'
import QuemSomos from '../pages/QuemSomos/QuemSomos'
import Sistema from '../pages/Sistema/Sistema'
import Sistema2 from '../pages/Sistema2/Sistema2'
import Faq from '../pages/Faq/Faq'
import Contato from '../pages/Contato/Contato'

export default function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/quem-somos" element={<QuemSomos />} />
            <Route path="/sistema" element={<Sistema />} />
            <Route path="/sistema2" element={<Sistema2 />} />
            <Route path="/faq" element={<Faq />} />
            <Route path="/contato" element={<Contato />} />
            <Route path="/sobre" element={<Sobre />} />
            <Route path="/*" element={<NotFound/>}/>
        </Routes>
    )
}