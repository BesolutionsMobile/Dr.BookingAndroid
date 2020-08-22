package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioHome.Pattrens.IFOnBackPressed;
import com.besolutions.drbookingOriginal.local_data.send_data;


public class Search_Fragment extends Fragment implements IFOnBackPressed {

    private View view;
    Button search;
    TextView skip;
    EditText editSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.search_fragment, container, false);

        editSearch=(EditText)view.findViewById(R.id.editSearch);

        search=(Button)view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editSearch.getText().toString();
                Search_Result search_result=new Search_Result();
               // FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                /*Bundle bundle=new Bundle();
                bundle.putString("text_skip",text);
                //set Fragmentclass Arguments
                search_result.setArguments(bundle);*/
                send_data.SET_TEXT_SEARCH(getContext(),text);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, search_result).commit();
                /*fr.replace(R.id.fragment_container, search_result);
                fr.addToBackStack(null);
                fr.commit();*/

            }
        });
        skip=(TextView)view.findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search_Result search_result=new Search_Result();
               // FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                /*Bundle bundle=new Bundle();
                bundle.putString("text_skip","0");
                //set Fragmentclass Arguments
                search_result.setArguments(bundle);*/
                send_data.SET_TEXT_SEARCH(getContext(),"0");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, search_result).commit();
                /*fr.replace(R.id.fragment_container, search_result);
                fr.addToBackStack(null);
                fr.commit();*/

            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                final ViewSwitcher viewSwitcher=(ViewSwitcher)view.findViewById(R.id.viewswitch);
                if(editSearch.length()>0){
                    viewSwitcher.setDisplayedChild(1); //or switcher.showPrevious()
                }else if(editSearch.getText().toString().isEmpty())
                {
                    viewSwitcher.setDisplayedChild(0);//or switcher.showPrevious()

                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public boolean onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(a);
        getActivity().finish();
        return true;
    }
}
