package obautista.uga.edu.statecapitalsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Daniel on 10/22/18.
 */
public class QuizActivity extends AppCompatActivity{

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    ActionBar mActionBar;
    private static QuizQuestion[] questions;
    private static int[] answers;
    DatabaseHelper stateCapitalsDb;

    /*
      This onCreate method is called everytime the app boots up. It contains most of the methods used in the program
      @param savedInstanceState is used to save the state
      @return there is nothing to return so it is void
      */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        // Initialize views, action bar, and database
        mActionBar = getSupportActionBar();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), 6);
        mActionBar.setTitle(mSectionsPagerAdapter.getPageTitle(0));
        stateCapitalsDb = new DatabaseHelper(this);

        // Initialize viewpager and set the adapter
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Get an array of six random quiz questions
        questions = stateCapitalsDb.getSixQuestions();

        // Create an array that will hold the answers to each question
        answers = new int[6];
        Arrays.fill(answers, 0);

        // Add listener to view pager
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /*
          This method would be used if we wanted to do something in particular when the page is scrolled
          @param position is the position of the page
          @param positionOffset is the offset one would set
          @param positionOffsetPixels is the offset pixels we would want to set
          @return there is nothing to return so it is void
          */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            /*
          This method sets the title of the page view when one slides to it
          @param position is the position of the page
          @return there is nothing to return so it is void
          */
            @Override
            public void onPageSelected(int position) {
                mActionBar.setTitle(mSectionsPagerAdapter.getPageTitle(position));

            }
            /*
          This method would be used if we wanted to start a state change on page scroll
          @param state is the state number
          @return there is nothing to return so it is void
          */
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * Class for viewpager
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final int mSize;
        /*
      This method is a constructor for the pager adapter
      @param fm is the fragment manager for the fragment
      @param size is the size that we want the page to have
      */
        public SectionsPagerAdapter(FragmentManager fm, int size) {
            super(fm);
            this.mSize = size;
        }

        /*
      This method gets the item fragment at a position
      @param position is the certain position in the pager that we want
      @return Fragment is just a fragment we want to return to show in the view pager
      */
        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }
        /*
      This method returns the size of the view pager
      @return we return an int because that is what the size would be outputted as
      */
        @Override
        public int getCount() {
            return mSize;
        }

        /*
      This method gets the page title of the view pager position
      @param position is the position of the page in the sequence
      @return CharSequence is just the string we want to return at the top for the title
      */
        @Override
        public CharSequence getPageTitle(int position) {
            int imageNum = position + 1;
            return String.valueOf("Question " + imageNum);
        }
    }

    /**
     * Class for viewpager fragments
     */
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int questionNumber;
        private TextView quizPromptView;
        private RadioButton answerOne;
        private RadioButton answerTwo;
        private RadioButton answerThree;
        private RadioGroup group;
        private RadioButton selectedAnswer;
        private Button submitBtn;


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        public PlaceholderFragment() {
        }

        /*
          This onCreate method is called everytime the app boots up. It contains most of the methods used in the program
          @param savedInstanceState is used to save the state
          @return there is nothing to return so it is void
      */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                questionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            } else {
                questionNumber = -1;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Initialize fragment views
            final View rootView = inflater.inflate(R.layout.fragment_quiz_activity, container, false);
            quizPromptView = rootView.findViewById(R.id.quizPrompt);
            answerOne = rootView.findViewById(R.id.radioButton);
            answerTwo = rootView.findViewById(R.id.radioButton2);
            answerThree = rootView.findViewById(R.id.radioButton3);
            group = rootView.findViewById(R.id.radioGroup);
            submitBtn = rootView.findViewById(R.id.submitBtn);

            // Get current question and save as a QuizQuestion object
            final QuizQuestion currentQuestion = questions[questionNumber-1];

            // Set question prompt text
            quizPromptView.setText("What is the capital of " + currentQuestion.getState() + "?");

            // Add possible answer choices to ArrayList and shuffle them
            final ArrayList<String> choices = new ArrayList<>();

            choices.add(currentQuestion.getCapital());
            choices.add(currentQuestion.getSecondCity());
            choices.add(currentQuestion.getThirdCity());

            Collections.shuffle(choices);

            // Set answers text
            answerOne.setText(choices.get(0));
            answerTwo.setText(choices.get(1));
            answerThree.setText(choices.get(2));

            // Adds a listener the choices radio group and keeps score
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    selectedAnswer = rootView.findViewById(i);
                    if(selectedAnswer.getText().equals(currentQuestion.getCapital())) {
                        answers[questionNumber-1] = 1;
                    }
                    else {
                        answers[questionNumber-1] = 0;
                    }
                }
            });

            // Checks to see if user is at the end of the quiz and shows submit button
            if(questionNumber == 6) {
                submitBtn.setVisibility(View.VISIBLE);

                // Submit the quiz
                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int totalCorrect = 0;

                        for(int i = 0; i < answers.length; i++) {
                            totalCorrect += answers[i];
                        }

                        Intent intent = new Intent(view.getContext(), Results.class);
                        intent.putExtra("totalCorrect", totalCorrect);
                        startActivity(intent);
                    }
                });

            }

            return rootView;
        }
    }
}

