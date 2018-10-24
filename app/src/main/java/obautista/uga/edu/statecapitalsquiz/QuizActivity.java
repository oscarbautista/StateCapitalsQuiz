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

public class QuizActivity extends AppCompatActivity{

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    ActionBar mActionBar;
    private static QuizQuestion[] questions;
    DatabaseHelper stateCapitalsDb;

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

        // Add listener to view pager
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mActionBar.setTitle(mSectionsPagerAdapter.getPageTitle(position));

            }

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

        public SectionsPagerAdapter(FragmentManager fm, int size) {
            super(fm);
            this.mSize = size;
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return mSize;
        }

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
        private TextView questionNum;
        private RadioButton answerOne;
        private RadioButton answerTwo;
        private RadioButton answerThree;
        private RadioGroup group;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        public PlaceholderFragment() {
        }

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
            View rootView = inflater.inflate(R.layout.fragment_quiz_activity, container, false);
            quizPromptView = rootView.findViewById(R.id.quizPrompt);
            questionNum = rootView.findViewById(R.id.questionNumber);
            answerOne = rootView.findViewById(R.id.radioButton);
            answerTwo = rootView.findViewById(R.id.radioButton2);
            answerThree = rootView.findViewById(R.id.radioButton3);
            group = rootView.findViewById(R.id.radioGroup);

            // Get current question and save as a QuizQuestion object
            QuizQuestion currentQuestion = questions[questionNumber-1];

            // Set question number text
            questionNum.setText("Question " + questionNumber);

            // Set question prompt text
            quizPromptView.setText("What is the capital of " + currentQuestion.getState() + "?");

            // Set answers text
            answerOne.setText(currentQuestion.getCapital());
            answerTwo.setText(currentQuestion.getSecondCity());
            answerThree.setText(currentQuestion.getThirdCity());

            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if(i == R.id.radioButton){
                        
                    }
                    if(i == R.id.radioButton2){

                    }
                    if(i == R.id.radioButton3){

                    }
                }
            });
            return rootView;
        }
    }
}

