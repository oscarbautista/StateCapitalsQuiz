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

    public final static Integer[] imageIds = new Integer[]{
            R.drawable.figs, R.drawable.grapes, R.drawable.heirloom_tomatoes,
            R.drawable.lemons, R.drawable.lime, R.drawable.oranges,
            R.drawable.peach, R.drawable.peppers, R.drawable.zucchini
    };

    public final static String[] imageDescriptions = new String[]{
            "Figs", "Grapes", "Heirloom Tomatoes",
            "Lemons", "Lime", "Oranges",
            "Peach", "Peppers", "Zucchini"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        mActionBar = getSupportActionBar();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), imageIds.length);
        mActionBar.setTitle(mSectionsPagerAdapter.getPageTitle(0));
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

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

    public void loadView(ImageView imageView, int resId, TextView textView, String description) {
        imageView.setImageResource(resId);
        textView.setText(description);
    }

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

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int mImageNum;
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
                mImageNum = getArguments().getInt(ARG_SECTION_NUMBER);
            } else {
                mImageNum = -1;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz_activity, container, false);
            quizPromptView = (TextView) rootView.findViewById(R.id.quizPrompt);
            questionNum = (TextView) rootView.findViewById(R.id.questionNumber);
            answerOne = (RadioButton) rootView.findViewById(R.id.radioButton);
            answerTwo = (RadioButton) rootView.findViewById(R.id.radioButton2);
            answerThree = (RadioButton) rootView.findViewById(R.id.radioButton3);
            group = (RadioGroup) rootView.findViewById(R.id.radioGroup);

            questionNum.setText("Question " + mImageNum);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if(i==R.id.radioButton){

                    }
                    if(i==R.id.radioButton2){

                    }
                    if(i==R.id.radioButton3){

                    }
                }
            });
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            if (QuizActivity.class.isInstance(getActivity())) {
                final int resId = QuizActivity.imageIds[mImageNum - 1];
                final String description = imageDescriptions[mImageNum - 1];
            }
        }
    }
}

