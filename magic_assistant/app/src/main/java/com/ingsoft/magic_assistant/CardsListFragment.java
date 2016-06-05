package com.ingsoft.magic_assistant;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ingsoft.magic_assistant.adapters.ListCardsAdapter;
import com.ingsoft.magic_assistant.data.Card;
import com.ingsoft.magic_assistant.data.CardStore;

import java.util.List;


public class CardsListFragment extends Fragment {

    private OnDisciplineSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disciplines_list,
                container, false);

        ListView lista = (ListView) view.findViewById(R.id.disciplinasLista);
        ListCardsAdapter listaAdaptada = new ListCardsAdapter(this, CardStore.getAll(this.getContext()));
        lista.setAdapter(listaAdaptada);
        return view;
    }

    public void armarLista(List<Card> disciplinas) {

        ListView lista = (ListView) getView().findViewById(R.id.disciplinasLista);
        ListCardsAdapter listaAdaptada = new ListCardsAdapter(this, disciplinas);
        lista.setAdapter(listaAdaptada);
    }

    public interface OnDisciplineSelectedListener {
        void onDisciplineSelected(Card card);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDisciplineSelectedListener) {
            listener = (OnDisciplineSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement CardsListFragment.OnDisciplineSelectedListener");
        }
    }

    public void tellTheListenerThatADisciplineWasSelected(Card card) {
        listener.onDisciplineSelected(card);
    }

}
