package edu.northeastern.stickers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.adapters.StickerPackAdapter;
import edu.northeastern.stickers.models.StickerPack;
import edu.northeastern.stickers.models.StickerSection;

public class StickerStoreFragment extends Fragment {

    ListView listView;
    StickerPackAdapter adapter;
    ArrayList<StickerSection> stickerSections = new ArrayList<>();
    Map<String, List<StickerPack>> stickerSectionMap = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sticker_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ImageView iconImageView = view.findViewById(R.id.downloadImageView);

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StickerPackAdapter(getContext(), stickerSections, stickerSectionMap);
        recyclerView.setAdapter(adapter);
//        progressBar = view.findViewById(R.id.progressBar);


        int desiredIconSize = 100; // in pixels

        Glide.with(this)
                .asGif()
                .load(R.drawable.download)
                .apply(new RequestOptions().override(desiredIconSize, desiredIconSize))
                .into(iconImageView);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Sticker").child("StickerPack");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stickerSections.clear();
                stickerSectionMap.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    List<StickerPack> stickerPackList = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        StickerPack stickerPack = new StickerPack(snapshot1.getKey(),
                                snapshot1.child("Name").getValue(String.class),
                                snapshot1.child("StickerPath").getValue(String.class));
                        stickerPackList.add(stickerPack);
                    }
                    stickerSections.add(new StickerSection(snapshot.getKey()));
                    stickerSectionMap.put(snapshot.getKey(), stickerPackList);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
