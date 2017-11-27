// IMyAidlInterface.aidl
package com.yhz.service;

// Declare any non-default types here with import statements

interface IRemoteService {

    /** Request the process ID of this service, to do evil things with it. */
    String getString();
}
