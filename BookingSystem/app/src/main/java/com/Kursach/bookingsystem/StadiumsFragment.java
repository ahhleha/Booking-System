package com.Kursach.bookingsystem;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;


public class StadiumsFragment extends Fragment{
    private final ArrayList<Stadium> stadiumsArrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    private StadiumAdapter stadiumAdapter;

    public StadiumsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stadiums, container, false);
        recyclerView = view.findViewById(R.id.stadiums_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        stadiumAdapter = new StadiumAdapter(getContext(), stadiumsArrayList);
        recyclerView.setAdapter(stadiumAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        stadiumsArrayList.clear();
        dataInitialize();
    }

    private void dataInitialize() {

        stadiumsArrayList.add(new Stadium("\"Динамо\"", "Футбольный клуб: \"Динамо Минск\"\n" +
                "Город: Минск\n" +
                "Вместимость: 22324\n" +
                "Адрес: ул. Кирова, 8\n" +
                "\n", "dinamo_photo"));
        stadiumsArrayList.add(new Stadium("\"Борисов Арена\"", "Футбольный клуб: БАТЭ\n" +
                "Город: Борисов\n" +
                "Вместимость: 13121\n" +
                "Адрес: Борисов, ул. Гагарина, 119\n" +
                "\n", "borisov_photo"));
        stadiumsArrayList.add(new Stadium("\"ОСК Брестский\"", "Футбольный клуб: \"Динамо-Брест\"\n" +
                "Город: Брест\n" +
                "Вместимость: 10169\n" +
                "Адрес: Брест, ул. Гоголя, 9\n" +
                "\n","osk_photo"));
        stadiumsArrayList.add(new Stadium("\"Трактор\"", "Город: Минск\n" +
                "Вместимость: 17600\n" +
                "Адрес: Минск, ул. Ванеева, 3\n" +
                "\n", "traktor_photo"));
        stadiumsArrayList.add(new Stadium("\"Центральный стадион\"", "Футбольный клуб: \"Гомель\"\n" +
                "Город: Гомель\n" +
                "Вместимость: 14307\n" +
                "Адрес: Гомель, площадь Восстания, 1\n" +
                "\n", "centralniy_photo"));
        stadiumsArrayList.add(new Stadium("\"Неман\"", "Футбольный клуб: \"Неман\"\n" +
                "Город: Гродно\n" +
                "Вместимость: 8479\n" +
                "Адрес: Гродно, ул. Коммунальная, 3\n" +
                "\n", "neman_photo"));
        stadiumsArrayList.add(new Stadium("\"ЦСК Витбеский\"", "Футбольный клуб: \"Витебск\"\n" +
                "Город: Витебск\n" +
                "Вместимость: 8200\n" +
                "Адрес: Витебск, пр-т Генерала Людникова, 12\n" +
                "\n", "cks_photo"));
        stadiumsArrayList.add(new Stadium("\"Спартак\"", "Футбольный клуб: \"Днепр\"\n" +
                "Город: Могилев\n" +
                "Вместимость: 7350\n" +
                "Адрес: Могилев, ул. Ленинская, 50б\n" +
                "\n", "spartak_photo"));
        stadiumsArrayList.add(new Stadium("\"Торпедо\"", "Футбольный клуб: \"Торпедо-БелАЗ\"\n" +
                "Город: Жодино\n" +
                "Вместимость: 6524\n" +
                "Адрес: Жодино, ул. Деревянко, 5\n" +
                "\n", "torpedo_photo"));
        stadiumAdapter.notifyDataSetChanged();
    }



}