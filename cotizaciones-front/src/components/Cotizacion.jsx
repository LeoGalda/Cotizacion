import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Card, Typography } from '@material-ui/core';

export const TIPO = {
    DOLAR: 'dolar',
    EURO: 'euro',
    REAL: 'real',
};

const obtenerCotizacion = async ({
    moneda,
    conectApi: { yaPedido, cargando },
    setConectApi,
    refresh,
}) => {
    if ((!yaPedido && !cargando) || refresh) {
        setConectApi((actual) => ({ ...actual, cargando: true }));
        const respuesta = await fetch(process.env.REACT_APP_BASE_URL + moneda);
        var fechaActual = new Date().toLocaleString();
        if (respuesta.status === 200) {
            const json = await respuesta.json();
            setConectApi((actual) => ({
                ...actual,
                respuesta: { error: null, precio: json.precio, fecha: fechaActual },
            }));
        } else {
            setConectApi((actual) => ({
                ...actual,
                respuesta: { error: 'Errot Back', precio: 0, fecha: fechaActual },
            }));
        }
        setConectApi((actual) => ({ ...actual, yaPedido: true, cargando: false }));
    }
};

// eslint-disable-next-line
const renderContenido = ({ error, precio, fecha, moneda, cargando }) => {
    const cotizacion = `Cotizacion del ${moneda}`;    
    const fechaActualizacion = `Fecha de actualizacion: ${fecha}`;             
    if (error){
        const errorMostrar = "Información no disponible en este momento";
        return (
            <>
            <Typography variant="h6">{cotizacion}</Typography>            
            <Typography variant="inherit">{errorMostrar}</Typography>            
            <Typography variant="body2">{fechaActualizacion}</Typography>
        </>
        )
    } 
    const precioString = `$${precio}`;
    return (
        <>
            <Typography variant="h6">{cotizacion}</Typography>            
            <Typography variant="h3" color="primary">{precioString}</Typography>            
            <Typography variant="body2">{fechaActualizacion}</Typography>
        </>
    );
};

const Cotizacion = ({ moneda }) => {
    const [conectApi, setConectApi] = useState({
        cargando: false,
        respuesta: { precio: 0, error: null, fecha: null },
        yaPedido: false,
    });

    useEffect(() => {
        obtenerCotizacion({ moneda, conectApi, setConectApi });
        const intervalo = setInterval(
            () => obtenerCotizacion({
                moneda,
                conectApi,
                setConectApi,
                refresh: true,
            }),
            5000,
        );
        return () => {
            clearInterval(intervalo);
        };
    }, [moneda, conectApi]);

    return (
        <Card bg="prymary" text="white" 
            style={{
                margin: 50,
                padding: 8,
                width: 300,
                heigh: 300, 
            }}
        >
            {renderContenido({
                moneda,
                ...conectApi.respuesta,
                cargando: conectApi.cargando,
            })}
        </Card>
    );
};

Cotizacion.propTypes = {
    moneda: PropTypes.string.isRequired,
};

export default Cotizacion;
