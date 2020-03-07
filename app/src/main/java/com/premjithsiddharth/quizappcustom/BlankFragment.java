package com.premjithsiddharth.quizappcustom;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.net.Uri;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    private static final String QUESTION = "question";
    private static final String ANSWER_1 = "answer_1";
    private static final String ANSWER_2 = "answer_2";
    private static final String ANSWER_3 = "answer_3";
    private static final String ANSWER_4 = "answer_4";

    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String quest, String a, String b, String c, String d) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION, quest);
        args.putString(ANSWER_1, a);
        args.putString(ANSWER_2, b);
        args.putString(ANSWER_3, c);
        args.putString(ANSWER_4, d);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(QUESTION);
            answerA = getArguments().getString(ANSWER_1);
            answerB = getArguments().getString(ANSWER_2);
            answerC = getArguments().getString(ANSWER_3);
            answerD = getArguments().getString(ANSWER_4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_questions, container, false);
        TextView quest_text = v.findViewById(R.id.question);
        quest_text.setText(question);
        TextView text_a = v.findViewById(R.id.answer1);
        text_a.setText(answerA);
        TextView text_b = v.findViewById(R.id.answer2);
        text_b.setText(answerB);
        TextView text_c = v.findViewById(R.id.answer3);
        text_c.setText(answerC);
        TextView text_d = v.findViewById(R.id.answer4);
        text_d.setText(answerD);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
