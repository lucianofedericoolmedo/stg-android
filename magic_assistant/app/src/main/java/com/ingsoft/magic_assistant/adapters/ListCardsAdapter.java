package com.ingsoft.magic_assistant.adapters;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.ingsoft.magic_assistant.CardsListFragment;
import com.ingsoft.magic_assistant.R;
import com.ingsoft.magic_assistant.data.Card;

import java.util.List;

public class ListCardsAdapter extends BaseAdapter {

    private final Fragment context;
    private final List<Card> disciplinas;
    private CardsListFragment listener;

    public ListCardsAdapter(Fragment context, List<Card> disciplinas){

        this.context = context;
        this.disciplinas = disciplinas;
        this.listener =(CardsListFragment) context;

    }

    @Override
    public int getCount() {
        return disciplinas.size();
    }

    @Override
    public Card getItem(int position) {
        return disciplinas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context.getContext()).inflate(R.layout.itemdelista, parent, false);
        final Card disciplina = getItem(position);
        setContenido(view, disciplina);
        setOnClick(view, disciplina);
        return view;
    }

    private void setOnClick(View view, final Card disciplina) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.tellTheListenerThatADisciplineWasSelected(disciplina);
            }
        });
    }

    private void setContenido(View view, Card disciplina) {
        TextView cardNameView = (TextView) view.findViewById(R.id.list_item_title);
        cardNameView.setText(disciplina.getName());
        TextView cardlevelView = (TextView) view.findViewById(R.id.list_item_subtitle);
        cardlevelView.setText("Level: " + disciplina.getLevel());
        TextView cardSchemaView = (TextView) view.findViewById(R.id.list_item_right_text);
        cardSchemaView.setText(disciplina.getSchedule());
        ImageView cardImagenView = (ImageView) view.findViewById(R.id.imagendedeporte);
        cardImagenView.setImageResource(disciplina.getLogoResId());
    }

}

