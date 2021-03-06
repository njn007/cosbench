/** 
 
Copyright 2013 Intel Corporation, All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/ 

package com.intel.cosbench.driver.model;

import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

import com.intel.cosbench.api.auth.AuthAPI;
import com.intel.cosbench.api.storage.StorageAPI;
import com.intel.cosbench.bench.*;
import com.intel.cosbench.config.Mission;
import com.intel.cosbench.log.Logger;
import com.intel.cosbench.model.WorkerInfo;

/**
 * This class encapsulates worker related information.
 * 
 * @author ywang19, qzheng7
 * 
 */
public class WorkerContext implements WorkerInfo {

    private int index;
    private Mission mission;
    private transient Logger logger;
    private transient AuthAPI authApi;
    private transient StorageAPI storageApi;

    private volatile boolean error = false;
    private volatile boolean aborted = false;
    /* Each worker starts with an empty snapshot */
    private transient volatile Snapshot snapshot = new Snapshot();
    /* Each worker starts with an empty report */
    private volatile Report report = new Report();
    /* Each worker has its private random object so as to enhance performance */
    private transient Random random = new Random(RandomUtils.nextLong());

    public WorkerContext() {
        /* empty */
    }

    @Override
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public AuthAPI getAuthApi() {
        return authApi;
    }

    public void setAuthApi(AuthAPI authApi) {
        this.authApi = authApi;
    }

    public StorageAPI getStorageApi() {
        return storageApi;
    }

    public void setStorageApi(StorageAPI storageApi) {
        this.storageApi = storageApi;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isAborted() {
        return aborted;
    }

    public void setAborted(boolean aborted) {
        this.aborted = aborted;
    }

    @Override
    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    @Override
    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public void disposeRuntime() {
        logger = null;
        authApi.dispose();
        authApi = null;
        storageApi.dispose();
        storageApi = null;
        random = null;
        snapshot = new Snapshot();
    }

}
