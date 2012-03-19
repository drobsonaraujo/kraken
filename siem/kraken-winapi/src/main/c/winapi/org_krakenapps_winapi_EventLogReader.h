/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_krakenapps_winapi_EventLogReader */

#ifndef _Included_org_krakenapps_winapi_EventLogReader
#define _Included_org_krakenapps_winapi_EventLogReader
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_krakenapps_winapi_EventLogReader
 * Method:    readAllEventLogs
 * Signature: (Ljava/lang/String;I)Ljava/util/ArrayList;
 */
JNIEXPORT jobject JNICALL Java_org_krakenapps_winapi_EventLogReader_readAllEventLogs
  (JNIEnv *, jobject, jstring, jint);

/*
 * Class:     org_krakenapps_winapi_EventLogReader_EventLogIterator
 * Method:    readEventLog
 * Signature: (Ljava/lang/String;I)Lorg/krakenapps/winapi/EventLog;
 */
JNIEXPORT jobject JNICALL Java_org_krakenapps_winapi_EventLogReader_readEventLog
  (JNIEnv *, jobject, jstring, jint);

jobject getEventLogObject(JNIEnv *, LPTSTR, PEVENTLOGRECORD);
LPTSTR getEventType(WORD);
LPTSTR getMessageString(LPTSTR, LPTSTR, LPTSTR, DWORD, WORD, LPTSTR);
LPCVOID getResource(LPTSTR, LPTSTR, LPTSTR);

#ifdef __cplusplus
}
#endif
#endif