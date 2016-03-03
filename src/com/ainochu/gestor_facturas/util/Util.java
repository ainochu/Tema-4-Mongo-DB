package com.ainochu.gestor_facturas.util;

import javax.swing.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ainoa on 30/01/2016.
 */
public class Util {

    public static void mensajeConfirmacion(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);

    }
    public static String convertirAMoneda(float cantidad){
        DecimalFormat decimalFormat = new DecimalFormat("#.00 €");
        return decimalFormat.format(cantidad);
    }

    public static Date parseFecha(String fecha) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.parse(fecha);
    }

    public static String parseFecha(Date fecha){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(fecha);
    }
}
