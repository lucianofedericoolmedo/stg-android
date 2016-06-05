package com.ingsoft.magic_assistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ingsoft.magic_assistant.data.Card;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getBoolean(R.bool.dual_pane)){
            finish();
            return;
        }



        Card disciplina = (Card) getIntent().getParcelableExtra("disciplina");

        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String url = extras.getString(EXTRA_URL);

            CardDetailFragment detailFragment = (CardDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
            detailFragment.setDiscipline(disciplina);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
