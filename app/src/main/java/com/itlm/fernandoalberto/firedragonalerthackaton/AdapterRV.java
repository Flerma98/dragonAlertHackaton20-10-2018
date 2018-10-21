package com.itlm.fernandoalberto.firedragonalerthackaton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.UsuariosviewHolder>{

    ArrayList<Usuarios> usuarios;

    public AdapterRV(ArrayList<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public UsuariosviewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_recycler_usuarios, viewGroup, false);
        UsuariosviewHolder holder = new UsuariosviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UsuariosviewHolder usuariosviewHolder, int i) {
        Usuarios usuario= usuarios.get(i);
        usuariosviewHolder.txtNombre.setText(usuario.getNombre());
        usuariosviewHolder.txtCorreo.setText(usuario.getCorreo());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class UsuariosviewHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtCorreo;

        public UsuariosviewHolder(View itemView) {
            super(itemView);
            txtNombre= (TextView)itemView.findViewById(R.id.txtRVNombre);
            txtCorreo= (TextView)itemView.findViewById(R.id.txtRVCorreo);
        }
    }
}
