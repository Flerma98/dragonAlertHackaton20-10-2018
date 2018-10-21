package com.itlm.fernandoalberto.firedragonalerthackaton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterRVDatos extends RecyclerView.Adapter<AdapterRVDatos.DatosviewHolder>{

    ArrayList<Datos> datos;

    public AdapterRVDatos(ArrayList<Datos> datos) {
        this.datos = datos;
    }

    @Override
    public DatosviewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_recycler_datos, viewGroup, false);
        DatosviewHolder holder = new DatosviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DatosviewHolder datosviewHolder, int i) {
        Datos dato= datos.get(i);
        datosviewHolder.txtID.setText("ID: " + dato.getID());
        if(dato.getFuego()==1){
            datosviewHolder.txtFuego.setText("Hay Fuego");
        }else{
            datosviewHolder.txtFuego.setText("No hay Fuego");
        }
        datosviewHolder.txtCantidad.setText("Cantidad: " + dato.getCantidad());
        datosviewHolder.txtGradosC.setText(dato.getGradosC() + "°C");
        datosviewHolder.txtGradosF.setText(dato.getGradosF() + "°Fh");
        datosviewHolder.txtHumedad.setText("H: " + dato.getHumedad());
        datosviewHolder.txtFecha.setText(dato.getFecha());
        datosviewHolder.txtHora.setText(dato.getHora());

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class DatosviewHolder extends RecyclerView.ViewHolder{

        TextView txtID, txtFuego, txtCantidad, txtGradosC, txtGradosF, txtHumedad, txtFecha, txtHora;

        public DatosviewHolder(View itemView) {
            super(itemView);
            txtID= (TextView)itemView.findViewById(R.id.txtRVID);
            txtFuego= (TextView)itemView.findViewById(R.id.txtRVFuego);
            txtCantidad= (TextView)itemView.findViewById(R.id.txtRVCantidad);
            txtGradosC= (TextView)itemView.findViewById(R.id.txtRVGradosC);
            txtGradosF= (TextView)itemView.findViewById(R.id.txtRVGradosF);
            txtHumedad= (TextView)itemView.findViewById(R.id.txtRVHumedad);
            txtFecha= (TextView)itemView.findViewById(R.id.txtRVFecha);
            txtHora= (TextView)itemView.findViewById(R.id.txtRVHora);
        }
    }
}
