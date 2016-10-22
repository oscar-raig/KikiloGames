package com.example.oscarraig.justjava;


import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.example.oscarraig.justjava.controller.MainActivity;

import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }
    @Override
      protected void setUp() throws Exception {
          super.setUp();
      }

    @SmallTest
    public void testFirst(){
        assertTrue(false);
    }

    public void incrementTextWhendisplayIsCalled() {
        MainActivity mainActivity = getActivity();
        mainActivity.submitOrder(null);
        TextView quantityTextView = (TextView) mainActivity
                .findViewById(R.id.quantity_text_view);
        Integer quantity = Integer.parseInt(quantityTextView.getText().toString());
        assertThat(quantity,is(1));

    }

    @UiThreadTest
    public void testSetTextWithAnnotation() throws Exception {

        MainActivity activity = getActivity();

        // search for the textView
        final TextView quantityTextView = (TextView) activity
                .findViewById(R.id.quantity_text_view);

        Integer quantity = Integer.parseInt(quantityTextView.getText().toString());
        assertThat(quantity,is(1));

    }

}