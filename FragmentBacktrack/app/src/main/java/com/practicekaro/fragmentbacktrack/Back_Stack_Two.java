package com.practicekaro.fragmentbacktrack;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Back_Stack_Two extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_back__stack__two, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        Button gotoFragmentThreeBtn = (Button)retView.findViewById(R.id.stack_two_button);
        gotoFragmentThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back_Stack_Three fragmentThree = new Back_Stack_Three();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentUtil.printActivityFragmentList(fragmentManager);

                // Get fragment two if exist.
                Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");
                if(fragmentTwo!=null) {
                    Log.d(FragmentUtil.NAME_FRAGMENT, "Fragment Two exist in back stack, will hide it now.");
                    // Hide fragment two. Only hide not destroy.
                    // When user type back menu in Fragment three,
                    // this hidden Fragment will be shown again with input text saved.
                    fragmentTransaction.hide(fragmentTwo);
                }
                // Add Fragment with special tag name.
                fragmentTransaction.add(R.id.fragment_back_stack_frame_layout, fragmentThree, "Fragment Three");
                // Add fragment two in back stack.
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return retView;
    }

}
