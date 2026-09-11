import type { ReactNode } from 'react'
import Rodape from '../components/Rodape/Rodape'
import Cabecalho from '../components/Cabecalho/Cabecalho'

type MainLayoutProps = {
  children: ReactNode
}

export default function MainLayout({ children }: MainLayoutProps) {
    return (
        <div
        style={{
            minHeight: '100vh',
            display: 'flex',
            flexDirection: 'column',
        }}
        >
        <Cabecalho />

        <main style={{ flex: 1 }}>
            {children}
        </main>

        <Rodape/>
        </div>
    )
}