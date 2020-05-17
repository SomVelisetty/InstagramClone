package com.somvelisetty.instagramclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileNmae, edtBio, edtProfession, edtHobbie, edtFavSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileNmae = view.findViewById(R.id.edtProfileName);
        edtBio = view.findViewById(R.id.edtBio);
        edtProfession = view.findViewById(R.id.edtProfession);
        edtHobbie = view.findViewById(R.id.edtHobbies);
        edtFavSport = view.findViewById(R.id.edtFavSport);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateinfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null)
        {
            edtProfileNmae.setText("");
            edtBio.setText("");
            edtProfession.setText("");
            edtHobbie.setText("");
            edtFavSport.setText("");
        } else {
            edtProfileNmae.setText(parseUser.get("profileName") + "");
            edtBio.setText(parseUser.get("profileBio") + "");
            edtProfession.setText(parseUser.get("profileProfession") + "");
            edtHobbie.setText(parseUser.get("profileHobbies") + "");
            edtFavSport.setText(parseUser.get("proFavSport") + "");
        }
        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName", edtProfileNmae.getText().toString());
                parseUser.put("profileBio", edtBio.getText().toString());
                parseUser.put("profileProfession", edtProfession.getText().toString());
                parseUser.put("profileHobbies", edtHobbie.getText().toString());
                parseUser.put("proFavSport", edtFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)
                        {
                            FancyToast.makeText(getContext(), "Info Updated", Toast.LENGTH_LONG, FancyToast.INFO,true).show();
                        } else
                        {
                            FancyToast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR,true).show();
                        }
                    }
                });

            }
        });

        return view;
    }
}
