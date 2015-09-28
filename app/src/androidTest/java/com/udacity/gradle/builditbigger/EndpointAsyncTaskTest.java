package com.udacity.gradle.builditbigger;

import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Manuel Sanchez on 9/27/15
 */
public class EndpointAsyncTaskTest extends InstrumentationTestCase {

    String taskResponse;
    final CountDownLatch countDownLatch = new CountDownLatch(1);

    public void testVerifyNonEmptyResponse() throws InterruptedException, ExecutionException, TimeoutException {
        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask();
        endpointAsyncTask.setListener(new EndpointAsyncTask.TaskListener() {
            @Override
            public void onCompleted(String response) {
                taskResponse = response;
                countDownLatch.countDown();
            }
        }).execute(new MockContext());
        countDownLatch.await();
        assertNotNull(taskResponse);

    }
}
