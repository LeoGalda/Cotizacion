import React from 'react';
import { AppBar, Toolbar, Typography } from '@material-ui/core';
import Cotizacion, { TIPO } from './components/Cotizacion';

function App() {
    return (
        <div>
            <AppBar position="fixed">
                <Toolbar>
                    <Typography variant="h6">Cotizaciones</Typography>
                </Toolbar>
            </AppBar>
            <div
                style={{
                    marginTop: 80,
                    flex: 1,
                    display: 'flex',
                    flexDirection: 'row',
                }}
            >
                <Cotizacion moneda={TIPO.DOLAR} />
                <Cotizacion moneda={TIPO.EURO} />
                <Cotizacion moneda={TIPO.REAL} />
            </div>
        </div>
    );
}

export default App;
