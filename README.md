1��srcĿ¼ 

��  apis.app --ϵͳ ����֪ʶ

��  apis.appwidget --���ڿؼ����� ���

��  apis.location --λ�����

��  apis.net --�������

��  apis.phone --�ֻ�����

��  apis.project --��ĿDemo

��  apis.thread --���߳�

��  apis.ui --�������

	�� resources --��Դ�ļ�

	�� views --ϵͳ�ؼ�

	�� widget --�Զ���View



2��libsĿ¼ 
��  android-support-v4.jar --v4���ݰ�



==
AnimatedVectorDrawable 5.0����path

============================������һЩAndroidȨ��============================
 
����Ȩ�ޣ�


��д�洢��

װ�غ�ж���ļ�ϵͳ
android.permission.WRITE_EXTERNAL_STORAGE
android.permission.READ_EXTERNAL_STORAGE
android.permission.MOUNT_UNMOUNT_FILESYSTEMS
��������
android.permission.INTERNET
android.permission.ACCESS_NETWORK_STATE
android.permission.ACCESS_WIFI_STATE
android.permission.CHANGE_WIFI_STATE
��������
android.permission.RECEIVE_BOOT_COMPLETED
��׼��GPSλ��
��������Ĵ��Ե�λ��
��ȡģ�ⶨλ��Ϣ�����ԣ�
android.permission.ACCESS_FINE_LOCATION
android.permission.ACCESS_COARSE_LOCATION
android.permission.ACCESS_MOCK_LOCATION
����
android.permission.BROADCAST_SMS
android.permission.READ_SMS
android.permission.SEND_SMS
android.permission.RECEIVE_SMS
android.permission.WRITE_SMS
����绰
������򲦴�绰 ���κε绰��
android.permission.CALL_PHONE
android.permission.CALL_PRIVILEGED
��ȡ��ϵ��
��ȡͨ����¼
android.permission.READ_CONTACTS
android.permission.WRITE_CONTACTS
android.permission.READ_CALL_LOG
android.permission.WRITE_CALL_LOG
��װ/ж�ؿ�ݷ�ʽ
com.android.launcher.permission.INSTALL_SHORTCUT
com.android.launcher.permission.UNINSTALL_SHORTCUT
¼��
�޸���������
android.permission.RECORD_AUDIO
android.permission.MODIFY_AUDIO_SETTINGS
��
android.permission.VIBRATE
Ĭ��
android.permission.BAIDU_LOCATION_SERVICE
��д�ֻ�״̬�����
android.permission.READ_PHONE_STATE
װ�غ�ж���ļ�ϵͳ
android.permission.MOUNT_UNMOUNT_FILESYSTEMS
����������־����
android.permission.READ_LOGS
���ͳ־ù㲥
android.permission.BROADCAST_STICKY
�޸�ȫ��ϵͳ����
android.permission.WRITE_SETTINGS
����
android.permission.WAKE_LOCK
�رճ���
android.permission.RESTART_PACKAGES
android.permission.KILL_BACKGROUND_PROCESSES
android������������
android.webkit.permission.PLUGIN
���ü�����
android.permission.DISABLE_KEYGUARD
д����������ղؼк���ʷ��¼
��
com.android.browser.permission.WRITE_HISTORY_BOOKMARKS
com.android.browser.permission.READ_HISTORY_BOOKMARKS
����Camera
android.permission.CAMERA

android.permission.WRITE_APN_SETTINGS

android.permission.GET_TASKS
 	 

�Զ���Ȩ�ޣ�
http://berdy.iteye.com/blog/1782854
http://blog.csdn.net/myhu730/article/details/6816880

Ȩ�ޣ�http://blog.csdn.net/dianyueneo/article/details/7224756

���ʵǼ�����	android.permission.ACCESS_CHECKIN_PROPERTIES����ȡ��д��Ǽ�check-in���ݿ����Ա��Ȩ��
��ȡ����λ��	android.permission.ACCESS_COARSE_LOCATION��ͨ��WiFi���ƶ���վ�ķ�ʽ��ȡ�û����Եľ�γ����Ϣ����λ���ȴ�������30~1500��
��ȡ��ȷλ��	android.permission.ACCESS_FINE_LOCATION��ͨ��GPSоƬ�������ǵĶ�λ��Ϣ����λ���ȴ�10������
���ʶ�λ��������	android.permission.ACCESS_LOCATION_EXTRA_COMMANDS�����������ʶ���Ķ�λ�ṩ��ָ��
��ȡģ�ⶨλ��Ϣ	android.permission.ACCESS_MOCK_LOCATION����ȡģ�ⶨλ��Ϣ��һ�����ڰ��������ߵ���Ӧ��
��ȡ����״̬	android.permission.ACCESS_NETWORK_STATE����ȡ������Ϣ״̬���統ǰ�����������Ƿ���Ч
����Surface Flinger	android.permission.ACCESS_SURFACE_FLINGER��Androidƽ̨�ϵײ��ͼ����ʾ֧�֣�һ��������Ϸ�������Ԥ������͵ײ�ģʽ����Ļ��ͼ
��ȡWiFi״̬	android.permission.ACCESS_WIFI_STATE����ȡ��ǰWiFi�����״̬�Լ�WLAN�ȵ����Ϣ
�˻�����	android.permission.ACCOUNT_MANAGER����ȡ�˻���֤��Ϣ����ҪΪGMail�˻���Ϣ��ֻ��ϵͳ�����̲��ܷ��ʵ�Ȩ��
��֤�˻�	android.permission.AUTHENTICATE_ACCOUNTS������һ������ͨ���˻���֤��ʽ�����˻�����ACCOUNT_MANAGER�����Ϣ
����ͳ��	android.permission.BATTERY_STATS����ȡ��ص���ͳ����Ϣ
��С���	android.permission.BIND_APPWIDGET������һ���������appWidget������Ҫ����С��������ݿ⣬ֻ�зǳ��ٵ�Ӧ�ò��õ���Ȩ��
���豸����	android.permission.BIND_DEVICE_ADMIN������ϵͳ����Ա������receiver��ֻ��ϵͳ����ʹ��
�����뷨	android.permission.BIND_INPUT_METHOD ������InputMethodService����ֻ��ϵͳ����ʹ��
��RemoteView	android.permission.BIND_REMOTEVIEWS������ͨ��RemoteViewsService����������ֻ��ϵͳ������
�󶨱�ֽ	android.permission.BIND_WALLPAPER������ͨ��WallpaperService����������ֻ��ϵͳ������
ʹ������	android.permission.BLUETOOTH���������������Թ��������豸
��������	android.permission.BLUETOOTH_ADMIN�����������з��ֺ�����µ������豸
���שͷ	android.permission.BRICK���ܹ������ֻ����ǳ�Σ�գ�����˼��������ֻ����שͷ
Ӧ��ɾ��ʱ�㲥	android.permission.BROADCAST_PACKAGE_REMOVED����һ��Ӧ����ɾ��ʱ����һ���㲥
�յ�����ʱ�㲥	android.permission.BROADCAST_SMS�����յ�����ʱ����һ���㲥
�����㲥	android.permission.BROADCAST_STICKY������һ�������յ��㲥������յ���һ���㲥
WAP PUSH�㲥	android.permission.BROADCAST_WAP_PUSH��WAP PUSH�����յ��󴥷�һ���㲥
����绰	android.permission.CALL_PHONE���������ӷ�ϵͳ������������绰����
ͨ��Ȩ��	android.permission.CALL_PRIVILEGED��������򲦴�绰���滻ϵͳ�Ĳ���������
����Ȩ��	android.permission.CAMERA�������������ͷ��������
�ı����״̬	android.permission.CHANGE_COMPONENT_ENABLED_STATE���ı�����Ƿ�����״̬
�ı�����	android.permission.CHANGE_CONFIGURATION������ǰӦ�øı����ã��綨λ
�ı�����״̬	android.permission.CHANGE_NETWORK_STATE���ı�����״̬���Ƿ�������
�ı�WiFi�ಥ״̬	android.permission.CHANGE_WIFI_MULTICAST_STATE���ı�WiFi�ಥ״̬
�ı�WiFi״̬	android.permission.CHANGE_WIFI_STATE���ı�WiFi״̬
���Ӧ�û���	android.permission.CLEAR_APP_CACHE�����Ӧ�û���
����û�����	android.permission.CLEAR_APP_USER_DATA�����Ӧ�õ��û�����
�ײ����Ȩ��	android.permission.CWJ_GROUP������CWJ�˻�����ʵײ���Ϣ
�ֻ��Ż���ʦ��չȨ��	android.permission.CELL_PHONE_MASTER_EX���ֻ��Ż���ʦ��չȨ��
���ƶ�λ����	android.permission.CONTROL_LOCATION_UPDATES���������ƶ����綨λ��Ϣ�ı�
ɾ�������ļ�	android.permission.DELETE_CACHE_FILES������Ӧ��ɾ�������ļ�
ɾ��Ӧ��	android.permission.DELETE_PACKAGES���������ɾ��Ӧ��
��Դ����	android.permission.DEVICE_POWER��������ʵײ��Դ����
Ӧ�����	android.permission.DIAGNOSTIC���������RW�������Դ
���ü�����	android.permission.DISABLE_KEYGUARD�����������ü�����
ת��ϵͳ��Ϣ	android.permission.DUMP����������ȡϵͳdump��Ϣ��ϵͳ����
״̬������	android.permission.EXPAND_STATUS_BAR�����������չ������״̬��
��������ģʽ	android.permission.FACTORY_TEST������������й�������ģʽ
ʹ�������	android.permission.FLASHLIGHT��������������
ǿ�ƺ���	android.permission.FORCE_BACK���������ǿ��ʹ��back���˰���������Activity�Ƿ��ڶ���
�����˻�Gmail�б�	android.permission.GET_ACCOUNTS������GMail�˻��б�
��ȡӦ�ô�С	android.permission.GET_PACKAGE_SIZE����ȡӦ�õ��ļ���С
��ȡ������Ϣ	android.permission.GET_TASKS����������ȡ��ǰ��������е�Ӧ��
����ȫ������	android.permission.GLOBAL_SEARCH���������ʹ��ȫ����������
Ӳ������	android.permission.HARDWARE_TEST������Ӳ�������豸������Ӳ������
ע���¼�	android.permission.INJECT_EVENTS��������ʱ�����ĵײ��¼�����ȡ�������켣����¼���
��װ��λ�ṩ	android.permission.INSTALL_LOCATION_PROVIDER����װ��λ�ṩ
��װӦ�ó���	android.permission.INSTALL_PACKAGES���������װӦ��
�ڲ�ϵͳ����	android.permission.INTERNAL_SYSTEM_WINDOW�����������ڲ����ڣ����Ե�����Ӧ�ó��򿪷Ŵ�Ȩ��
��������	android.permission.INTERNET�������������ӣ����ܲ���GPRS����
������̨����	android.permission.KILL_BACKGROUND_PROCESSES������������killBackgroundProcesses(String).����������̨����
�����˻�	android.permission.MANAGE_ACCOUNTS������������AccountManager�е��˻��б�
�����������	android.permission.MANAGE_APP_TOKENS�����������ݻ١�Z��˳�򣬽�����ϵͳ
�߼�Ȩ��	android.permission.MTWEAK_USER������mTweak�û����ʸ߼�ϵͳȨ��
����Ȩ��	android.permission.MTWEAK_FORUM������ʹ��mTweak����Ȩ��
���ʽ��	android.permission.MASTER_CLEAR���������ִ�����ʽ����ɾ��ϵͳ������Ϣ
�޸���������	android.permission.MODIFY_AUDIO_SETTINGS���޸�����������Ϣ
�޸ĵ绰״̬	android.permission.MODIFY_PHONE_STATE���޸ĵ绰״̬�������ģʽ�����������滻ϵͳ����������
��ʽ���ļ�ϵͳ	android.permission.MOUNT_FORMAT_FILESYSTEMS����ʽ�����ƶ��ļ�ϵͳ�������ʽ�����SD��
�����ļ�ϵͳ	android.permission.MOUNT_UNMOUNT_FILESYSTEMS�����ء��������ⲿ�ļ�ϵͳ
����NFCͨѶ	android.permission.NFC���������ִ��NFC������ͨѶ�����������ƶ�֧��
����Activity	android.permission.PERSISTENT_ACTIVITY������һ�����õ�Activity���ù��ܱ��Ϊ���������Ƴ�
�������绰	android.permission.PROCESS_OUTGOING_CALLS�����������ӣ��޸Ļ���������绰
��ȡ�ճ�����	android.permission.READ_CALENDAR����������ȡ�û����ճ���Ϣ
��ȡ��ϵ��	android.permission.READ_CONTACTS������Ӧ�÷�����ϵ��ͨѶ¼��Ϣ
��Ļ��ͼ	android.permission.READ_FRAME_BUFFER����ȡ֡����������Ļ��ͼ
��ȡ�ղؼк���ʷ��¼	com.android.browser.permission.READ_HISTORY_BOOKMARKS����ȡ������ղؼк���ʷ��¼
��ȡ����״̬	android.permission.READ_INPUT_STATE����ȡ��ǰ��������״̬��������ϵͳ
��ȡϵͳ��־	android.permission.READ_LOGS����ȡϵͳ�ײ���־
��ȡ�绰״̬	android.permission.READ_PHONE_STATE�����ʵ绰״̬
��ȡ��������	android.permission.READ_SMS����ȡ��������
��ȡͬ������	android.permission.READ_SYNC_SETTINGS����ȡͬ�����ã���ȡGoogle����ͬ������
��ȡͬ��״̬	android.permission.READ_SYNC_STATS����ȡͬ��״̬�����Google����ͬ��״̬
�����豸	android.permission.REBOOT������������������豸
�����Զ�����	android.permission.RECEIVE_BOOT_COMPLETED��������򿪻��Զ�����
���ղ���	android.permission.RECEIVE_MMS�����ղ���
���ն���	android.permission.RECEIVE_SMS�����ն���
����Wap Push	android.permission.RECEIVE_WAP_PUSH������WAP PUSH��Ϣ
¼��	android.permission.RECORD_AUDIO��¼������ͨ���ֻ�����������
����ϵͳ����	android.permission.REORDER_TASKS����������ϵͳZ�������е�����
����ϵͳ����	android.permission.RESTART_PACKAGES����������ͨ��restartPackage(String)�������÷�ʽ������������
���Ͷ���	android.permission.SEND_SMS�����Ͷ���
����Activity�۲���	android.permission.SET_ACTIVITY_WATCHER������Activity�۲���һ������monkey����
������������	com.android.alarm.permission.SET_ALARM��������������
���������˳�	android.permission.SET_ALWAYS_FINISH�����ó����ں�̨�Ƿ������˳�
���ö�������	android.permission.SET_ANIMATION_SCALE������ȫ�ֶ�������
���õ��Գ���	android.permission.SET_DEBUG_APP�����õ��Գ���һ�����ڿ���
������Ļ����	android.permission.SET_ORIENTATION��������Ļ����Ϊ�������׼��ʽ��ʾ����������ͨӦ��
����Ӧ�ò���	android.permission.SET_PREFERRED_APPLICATIONS������Ӧ�õĲ������Ѳ��ٹ�������鿴addPackageToPreferred(String) ����
���ý�������	android.permission.SET_PROCESS_LIMIT����������������Ľ�������������
����ϵͳʱ��	android.permission.SET_TIME������ϵͳʱ��
����ϵͳʱ��	android.permission.SET_TIME_ZONE������ϵͳʱ��
���������ֽ	android.permission.SET_WALLPAPER�����������ֽ
���ñ�ֽ����	android.permission.SET_WALLPAPER_HINTS�����ñ�ֽ����
�������ý����ź�	android.permission.SIGNAL_PERSISTENT_PROCESSES������һ�����õĽ����ź�
״̬������	android.permission.STATUS_BAR���������򿪡��رա�����״̬��
���ʶ�������	android.permission.SUBSCRIBED_FEEDS_READ�����ʶ�����Ϣ�����ݿ�
д�붩������	android.permission.SUBSCRIBED_FEEDS_WRITE��д����޸Ķ������ݵ����ݿ�
��ʾϵͳ����	android.permission.SYSTEM_ALERT_WINDOW����ʾϵͳ����
�����豸״̬	android.permission.UPDATE_DEVICE_STATS�������豸״̬
ʹ��֤��	android.permission.USE_CREDENTIALS���������������֤��AccountManager
ʹ��SIP��Ƶ	android.permission.USE_SIP���������ʹ��SIP��Ƶ����
ʹ����	android.permission.VIBRATE��������
��������	android.permission.WAKE_LOCK������������ֻ���Ļ�رպ��̨������Ȼ����
д��GPRS���������	android.permission.WRITE_APN_SETTINGS��д������GPRS���������
д���ճ�����	android.permission.WRITE_CALENDAR��д���ճ̣������ɶ�ȡ
д����ϵ��	android.permission.WRITE_CONTACTS��д����ϵ�ˣ������ɶ�ȡ
д���ⲿ�洢	android.permission.WRITE_EXTERNAL_STORAGE���������д���ⲿ�洢����SD����д�ļ�
д��Google��ͼ����	android.permission.WRITE_GSERVICES���������д��Google Map��������
д���ղؼк���ʷ��¼	com.android.browser.permission.WRITE_HISTORY_BOOKMARKS��д���������ʷ��¼���ղؼУ������ɶ�ȡ
��дϵͳ��������	android.permission.WRITE_SECURE_SETTINGS����������дϵͳ��ȫ���е�������
��дϵͳ����	android.permission.WRITE_SETTINGS�������дϵͳ������
��д����	android.permission.WRITE_SMS�������д����
д������ͬ������	android.permission.WRITE_SYNC_SETTINGS��д��Google����ͬ������
         

============================������һЩjava�쳣��============================


�����쳣�ࣺArithmeticExecption

��ָ���쳣�ࣺNullPointerException

����ǿ��ת���쳣��ClassCastException

���鸺�±��쳣��NegativeArrayException

�����±�Խ���쳣��ArrayIndexOutOfBoundsException

Υ����ȫԭ���쳣��SecturityException

�ļ��ѽ����쳣��EOFException

�ļ�δ�ҵ��쳣��FileNotFoundException

�ַ���ת��Ϊ�����쳣��NumberFormatException
�������ݿ��쳣��SQLException
��������쳣��IOException
����δ�ҵ��쳣��NoSuchMethodException

java.lang.AbstractMethodError

���󷽷����󡣵�Ӧ����ͼ���ó��󷽷�ʱ�׳���

java.lang.AssertionError

���Դ�����ָʾһ������ʧ�ܵ������

java.lang.ClassCircularityError

��ѭ�����������ڳ�ʼ��һ����ʱ������⵽��֮��ѭ���������׳����쳣��

java.lang.ClassFormatError

���ʽ���󡣵�Java�������ͼ��һ���ļ��ж�ȡJava�࣬����⵽���ļ������ݲ����������Ч��ʽʱ�׳���

java.lang.Error

���������д���Ļ��࣬���ڱ�ʶ���صĳ����������⡣��Щ����ͨ������һЩ��Ӧ��Ӧ�ó��򲶻�ķ��������

java.lang.ExceptionInInitializerError

��ʼ��������󡣵�ִ��һ����ľ�̬��ʼ������Ĺ����У��������쳣ʱ�׳�����̬��ʼ��������ֱָ�Ӱ��������е�static���Ρ�

java.lang.IllegalAccessError

Υ�����ʴ��󡣵�һ��Ӧ����ͼ���ʡ��޸�ĳ�������Field�����ߵ����䷽����������Υ����򷽷��Ŀɼ������������׳����쳣��

java.lang.IncompatibleClassChangeError

�����ݵ���仯���󡣵�����ִ�еķ������������ඨ�巢���˲����ݵĸı�ʱ���׳����쳣��һ�����޸���Ӧ���е�ĳЩ������������û�ж�����Ӧ�����±����ֱ�����е�����£����������ô���

java.lang.InstantiationError

ʵ�������󡣵�һ��Ӧ����ͼͨ��Java��new����������һ����������߽ӿ�ʱ�׳����쳣.

java.lang.InternalError

�ڲ���������ָʾJava������������ڲ�����

java.lang.LinkageError

���Ӵ��󡣸ô�������������ָʾĳ��������������һЩ�࣬�ڸ������֮�󣬱���������ı������ඨ���û�����±������е��࣬������������������

java.lang.NoClassDefFoundError

δ�ҵ��ඨ����󡣵�Java�����������װ������ͼʵ����ĳ���࣬���Ҳ�������Ķ���ʱ�׳��ô���

java.lang.NoSuchFieldError

�򲻴��ڴ��󡣵�Ӧ����ͼ���ʻ����޸�ĳ���ĳ���򣬶�����Ķ�����û�и���Ķ���ʱ�׳��ô���

java.lang.NoSuchMethodError

���������ڴ��󡣵�Ӧ����ͼ����ĳ���ĳ��������������Ķ�����û�и÷����Ķ���ʱ�׳��ô���

java.lang.OutOfMemoryError

�ڴ治����󡣵������ڴ治������Java����������һ������ʱ�׳��ô���

java.lang.StackOverflowError

��ջ������󡣵�һ��Ӧ�õݹ���õĲ��̫������¶�ջ���ʱ�׳��ô���

java.lang.ThreadDeath

�߳̽�����������Thread���stop����ʱ�׳��ô�������ָʾ�߳̽�����

java.lang.UnknownError

δ֪��������ָʾJava�����������δ֪���ش���������

java.lang.UnsatisfiedLinkError

δ��������Ӵ��󡣵�Java�����δ�ҵ�ĳ���������Ϊnative�����ı������Զ���ʱ�׳���

java.lang.UnsupportedClassVersionError

��֧�ֵ���汾���󡣵�Java�������ͼ�Ӷ�ȡĳ�����ļ������Ƿ��ָ��ļ��������ΰ汾�Ų�����ǰJava�����֧�ֵ�ʱ���׳��ô���

java.lang.VerifyError

��֤���󡣵���֤����⵽ĳ�����ļ��д����ڲ������ݻ��߰�ȫ����ʱ�׳��ô���

java.lang.VirtualMachineError

�������������ָʾ��������ƻ����߼���ִ�в����������Դ����������
java.lang.ArithmeticException

���������쳣��Ʃ�磺��������ȡ�

java.lang.ArrayIndexOutOfBoundsException

��������Խ���쳣���������������ֵΪ��������ڵ��������Сʱ�׳���

java.lang.ArrayStoreException

����洢�쳣�����������д�ŷ������������Ͷ���ʱ�׳���

java.lang.ClassCastException

�������쳣����������A��B��A����B�ĸ�������ࣩ��O��A��ʵ������ô��ǿ�ƽ�O����Ϊ��B��ʵ��ʱ�׳����쳣�����쳣��������Ϊǿ������ת���쳣��

java.lang.ClassNotFoundException

�Ҳ������쳣����Ӧ����ͼ�����ַ�����ʽ�����������࣬���ڱ���CLASSPAH֮���Ҳ�����Ӧ���Ƶ�class�ļ�ʱ���׳����쳣��

java.lang.CloneNotSupportedException

��֧�ֿ�¡�쳣����û��ʵ��Cloneable�ӿڻ��߲�֧�ֿ�¡����ʱ,������clone()�������׳����쳣��

java.lang.EnumConstantNotPresentException

ö�ٳ����������쳣����Ӧ����ͼͨ�����ƺ�ö�����ͷ���һ��ö�ٶ��󣬵���ö�ٶ��󲢲���������ʱ���׳����쳣��

java.lang.Exception

���쳣����������Ӧ�ó���ϣ������������

java.lang.IllegalAccessException

Υ���ķ����쳣����Ӧ����ͼͨ�����䷽ʽ����ĳ�����ʵ�������ʸ������ԡ����ø��෽��������ʱ���޷�������ġ����Եġ������Ļ��췽���Ķ���ʱ�׳����쳣��

java.lang.IllegalMonitorStateException

Υ���ļ��״̬�쳣����ĳ���߳���ͼ�ȴ�һ���Լ�����ӵ�еĶ���O���ļ��������֪ͨ�����̵߳ȴ��ö���O���ļ����ʱ���׳����쳣��

java.lang.IllegalStateException

Υ����״̬�쳣������Java������Ӧ����δ����ĳ�������ĺϷ�����״̬���������˸÷���ʱ���׳����쳣��

java.lang.IllegalThreadStateException

Υ�����߳�״̬�쳣�����س���δ����ĳ�������ĺϷ�����״̬���������˸÷���ʱ���׳��쳣��

java.lang.IndexOutOfBoundsException

����Խ���쳣��������ĳ�����е�����ֵС��0����ڵ������д�Сʱ���׳����쳣��

java.lang.InstantiationException

ʵ�����쳣������ͼͨ��newInstance()��������ĳ�����ʵ������������һ���������ӿ�ʱ���׳����쳣��

java.lang.InterruptedException

����ֹ�쳣����ĳ���̴߳��ڳ�ʱ��ĵȴ������߻�������ͣ״̬������ʱ�������߳�ͨ��Thread��interrupt������ֹ���߳�ʱ�׳����쳣��

java.lang.NegativeArraySizeException

�����СΪ��ֵ�쳣����ʹ�ø�����Сֵ��������ʱ�׳����쳣��

java.lang.NoSuchFieldException

���Բ������쳣��������ĳ����Ĳ����ڵ�����ʱ�׳����쳣��

java.lang.NoSuchMethodException

�����������쳣��������ĳ����Ĳ����ڵķ���ʱ�׳����쳣��

java.lang.NullPointerException

��ָ���쳣����Ӧ����ͼ��Ҫ��ʹ�ö���ĵط�ʹ����nullʱ���׳����쳣��Ʃ�磺����null�����ʵ������������null��������ԡ�����null����ĳ��ȡ�ʹ��throw����׳�null�ȵȡ�

java.lang.NumberFormatException

���ָ�ʽ�쳣������ͼ��һ��Stringת��Ϊָ�����������ͣ������ַ���ȷ��������������Ҫ��ĸ�ʽʱ���׳����쳣��

java.lang.RuntimeException

����ʱ�쳣��������Java��������������ڼ���Ա��׳����쳣�ĸ��ࡣ

java.lang.SecurityException

��ȫ�쳣���ɰ�ȫ�������׳�������ָʾΥ����ȫ������쳣��

java.lang.StringIndexOutOfBoundsException

�ַ�������Խ���쳣����ʹ������ֵ����ĳ���ַ����е��ַ�����������ֵС��0����ڵ������д�Сʱ���׳����쳣��

java.lang.TypeNotPresentException

���Ͳ������쳣����Ӧ����ͼ��ĳ���������Ƶ��ַ�����﷽ʽ���ʸ����ͣ����Ǹ��ݸ������������Ҳ������������׳����쳣�����쳣��ClassNotFoundException���������ڸ��쳣��unchecked��������飩�쳣����ClassNotFoundException��checked������飩�쳣��

java.lang.UnsupportedOperationException

��֧�ֵķ����쳣��ָ������ķ�������֧��������쳣��

�쳣
javax.servlet.jsp.JspException: Cannot retrieve mapping for action /Login ��/Login�����action���֣�

����ԭ��
actionû����struts-config.xml �ж��壬��û���ҵ�ƥ���action��������JSP�ļ���ʹ�� <html:form action=��Login.do��.�����ύ��Login.do����������������쳣����鿴struts-config.xml�еĶ��岿�֣���ʱ�����Ǵ�����ַ�������ĳЩ�����Ϲ��򣬿���ʹ��strutsconsole��������顣
���������������������������������������������������������������������������C
�쳣
org.apache.jasper.JasperException: Cannot retrieve definition for form bean null

����ԭ��

����쳣����ΪStruts����struts-config.xml�е�mappingû���ҵ�action������form bean���󲿷ֵ������������Ϊ��form-bean�����õ�name���Ժ�action�����õ�name���Բ�ƥ�����¡����仰˵��action��form��Ӧ�ø�����һ��name���ԣ�����Ҫ��ȷƥ�䣬������Сд���������û��name���Ժ�action����ʱҲ�ᷢ�������û����action��ָ��name���ԣ���ô��û��name���Ժ�action���������Ȼ��action����ĳЩ����ʱ��Ʃ����ݲ���ֵ��ת����Ӧ��jspҳ�棬�����Ǵ�������ݣ����ǾͲ���name���ԣ���Ҳ��action��ʹ�÷���֮һ��
���������������������������������������������������������������������������C
�쳣
No action instance for path /xxxx could be created

����ԭ��
�ر���ʾ����Ϊ�кܶ�������ᵼ���������ķ����������Ƽ���ҵ������web����������־/���Լ����������ԴӸ������Ϣ�п���Ǳ�ڵġ�����ͼ����action��ʱ�����Ĵ������action�����Ѿ���struts-config.xml�������˹������������<action>��ǩ����

��struts-config.xml��ͨ��action��ǩ��class����ָ����action�಻�ܱ��ҵ��кܶ���ԭ�����磺��λ������.class�ļ�ʧ�ܡ�Failure to place compiled .class file for the action in the classpath (��web�����У�class�ĵ�λ����r WEB-INF/classes���������action class����Ҫ�����Ŀ¼�¡��������action��λ��WEB-INF/classes/action/Login.class,��ô��struts-config.xml������action������typeʱ����action.Login).
ƴд�������Ҳʱ�з��������Ҳ����ҵ����ر�ע���һ����ĸ�Ĵ�Сд�Ͱ������ơ�
���������������������������������������������������������������������������C
�쳣
javax.servlet.jsp.JspException: No getter method for property username of bean org.apache.struts.taglib.html.BEAN

����ԭ��
û��λform bean�е�ĳ����������getter ����

���������Ҫ�����ڱ��ύ��FormBean�У���struts���<html:text property=��username��>ʱ����FormBean�б�����һ��getUsername()������ע����ĸ��U����
���������������������������������������������������������������������������C
�쳣
java.lang.NoClassDefFoundError: org/apache/struts/action/ActionForm

����ԭ��
���������Ҫ��������classpath���Ҳ�����Ӧ��Java .class�ļ�����������������webӦ�ó��������ʱ����Ҫ����Ϊָ����class�ļ�����web server��classpath�У�/WEB-INF/classes �� /WEB-INF/lib����������Ĵ����У�ԭ�����Ҳ���ActionForm�ࡣ
���������������������������������������������������������������������������C
�쳣
javax.servlet.jsp.JspException: Exception creating bean of class org.apache.struts.action.ActionForm: {1}

����ԭ��
Instantiating Struts-provided ActionForm class directly instead of instantiating a class derived off ActionForm. This mightoccur implicitly if you specify that a form-bean is this Struts ActionForm class rather than specifying a child of this classfor the form-bean.

Not associating an ActionForm-descended class with an action can also lead to this error.
���������������������������������������������������������������������������C
�쳣
javax.servlet.jsp.JspException: Cannot find ActionMappings or ActionFormBeans collection

����ԭ��
���Ǳ�ʶStruts actionServlet��<servlet>��Ǿ���ӳ��.do��չ����<sevlet-mapping>��ǻ������߶�û����web.xml��������

��struts-config.xml�еĴ��ֻ���ƴд����Ҳ�ɵ�������쳣�ķ���������ȱ��һ����ǵĹرշ���/>�����ʹ��struts console���߼��һ�¡�

���⣬load-on-startup������web.xml����������Ҫô��һ���ձ�ǣ�Ҫôָ��һ����ֵ�������ֵ������servlet���е����ȼ�����ֵԽ�����ȼ�Խ�͡�

����һ����ʹ��load-on-startup�йص���ʹ��StrutsԤ����JSP�ļ�ʱҲ���ܵ�������쳣��
���������������������������������������������������������������������������C
�쳣
java.lang.NullPointerException at org.apache.struts.util.RequestUtils.forwardURL(RequestUtils.java:1223)

����ԭ��
��struts-config.xml�е�forwardԪ��ȱ��path���ԡ�����Ӧ����������ʽ��
<forward name=��userhome�� path=��/user/userhome.jsp��/>
���������������������������������������������������������������������������C
�쳣
javax.servlet.jsp.JspException: Cannot find bean org.apache.struts.taglib.html.BEAN in any scope
Probable Causes
��ͼ��Struts��form�����ʹ��form����Ԫ�ء��ⳣ������������</html:form>����ʹ��Struts��html��ǡ�����Ҫע������㲻����ʹ�õ�������ı�ǣ���<html:form �� />������web ����������ʱ�͵���һ��������ı�ǣ����ʹ�õ�����<html>��Ƕ�����Ϊ����������֮��ģ�����ʹ����<html:text property=��id��>���о�����ʹ��taglib����HTML��ǿ�ʱ����ʹ�õ�prefix��ֵ����html��
���������������������������������������������������������������������������C
�쳣
javax.servlet.jsp.JspException: Missing message for key xx.xx.xx

Probable Causes
���key��ֵ��û������Դ�ļ�ApplicationResources.properties�ж��塣�����ʹ��eclipseʱ�����������������������Ŀ���±���ʱ��eclipse���Զ���classesĿ¼�µ���Դ�ļ�ɾ����

��Դ�ļ�ApplicationResources.properties ����classpath��Ӧ����Դ�ļ��ŵ� WEB-INF/classes Ŀ¼�£���ȻҪ��struts-config.xml�ж���)
���������������������������������������������������������������������������C
�쳣
Cannot find message resources under key org.apache.struts.action.MESSAGE

����ԭ��
����Ȼ����������Ƿ�����ʹ����Դ�ļ�ʱ����Strutsû���ҵ���Դ�ļ���

Implicitly trying to use message resources that are not available (such as using empty html:options tag instead of specifyingthe options in its body �� this assumes options are specified in ApplicationResources.properties file)

XML parser issues �� too many, too few, incorrect/incompatible versions
���������������������������������������������������������������������������C
�쳣
Strange and seemingly random characters in HTML and on screen, but not in original JSP or servlet.

����ԭ��
���ʹ��Struts��html:form��Ǻͱ�׼��HTML��ǲ���ȷ��

ʹ�õı�����ʽ�ڱ�ҳ�в�֧�֡�
���������������������������������������������������������������������������C
�쳣
��Document contained no data�� in Netscape

No data rendered (completely empty) page in Microsoft Internet Explorer

����ԭ��
ʹ��һ��Action���������û��ʵ��perform()������execute()��������Struts1.0��ʵ�ֵ���perform()��������Struts1.1��ʵ�ֵ���execute()��������Struts1.1������perform()����������ʹ��Struts1.1����һ��Action�������࣬����ʵ����execute()������������Struts1.0�����еĻ����ͻ�õ���Document contained nodata�� error message in Netscape or a completely empty (no HTML whatsoever) page rendered in Microsoft Internet Explorer.���Ĵ�����Ϣ��

����������������������������������������������������������������������������������
�쳣
ServletException: BeanUtils.populate
�������
����Struts�ϴ��ļ�ʱ,������javax.servlet.ServletException: BeanUtils.populate�쳣��
�ҵ�ActionServlet��û���õ�BeanUtils��Щ�����ࡣ������ϸ�����뷢������jsp�ļ����form���˼�enctype=&quot;multipart/form-data&quot; �ˡ�����д��������������쳣Ӧ�ôӶ෽�濼��������ڵĿ����ԣ��뵽ϵͳ��ʾ��Ϣ����Ķ�����
����������������������������������������������������������������������������������-
1. ����Action��, ���ָ����name, ��ô����Ҫ����һ������ͬ����FormBean���ܽ���formӳ��.2. �������Action��, �ύҳ��ʱ���� ��No input attribute for mapping path���� ����, ����Ҫ����input�����ж���ת���ҳ��.3. ��������µ�����ʱ���� ��Batch update row count wrong:���� ����, ��˵��XXX.hbm.xml��ָ����key������Ϊԭʼ����(int, long),��Ϊ�������ͻ��Զ�����ֵ, �����ֵ��������ϵͳ��Ϊ�Ѿ����ڸü�¼, ��ȷ�ķ�����ʹ��java.lang.Integer��java.lang.Long����.4. �����������ʱ���� ��argument type mismatch�� ����, ��������ʹ����Date���������, ��Ϊstruts�����Զ���String��ת����Date��,����, ����Ҫ��Action���ֶ���String��ת����Date��.5. Hibernate��, Query��iterator()��list()������ܶ�.6. ������� ��equal symbol expected�� ����, ˵�����strtus��ǩ�а�����һ����ǩ���߱���, ����:
<html:select property=��test�� onchange=��<%=test%>��/>
����
<html:hidden property=��test�� value=��<bean:write name=��t�� property=��p��/>��/>
�����������
����������������������������������������������������������������������������������
����Exception in thread ��main�� org.hibernate.exception.SQLGrammarException: Could not execute JDBC batch updateԭ��������      ��ΪHibernate Tools������Eclipse�����Database Explorer������*.hbn.xml�����а�����catalog=��***����*��ʾ���ݿ����ƣ�����������,��������ɾ���Ϳ�����
����������������������������������������������������������������������������������
����org.hibernate.ObjectDeletedException: deleted object would be re-saved by cascade (remove deleted object from associations)
ԭ��������
����1 ɾ��Set����cascade
����2 ���������ϵ����ɾ��
����3 ��many-to-one������cascade ��ֵ������none
���һ�У�
���һ��hashCode equals�Ƿ�ʹ����id��ΪΨһ��ʾ��ѡ���ˣ�����uuid.hexʱ��û������ģ���������native���Ͳ����ˣ���ô�죿ɾ������
����������������������������������������������������������������������������������-
���⣺������Tomcat 5.5.12������ԭ���ܺ��õ�ϵͳ�������ˣ��������Է���ҳ���в��ܰ��� taglib����������������ʾ��HTTP Status 500 -type Exception reportMessage description The server encountered an internal error () that prevented it from fulfilling this request.exceptionorg.apache.jasper.JasperException: /index.jsp(1,1) Unable to read TLD ��META-INF/tlds/struts-bean.tld�� from JAR file��file:*****/WEB-INF/lib/struts.jar��:ԭ�򣺸����˹����õ�lib�ļ����µ�jar������ʱҲ������servlet.jar��jsp-api.jar���������jsp-api.jarɾ���ͽ����������ˡ������������������������������������������������������������������������������������C
���� java.lang.NullPointerException
ԭ�� ���� dao ʵ���� manage ʵ������Ҫע��Ķ���û�б�ע�루�׳ƿ�ָ���쳣����������ʱ����Ӧ�ò鿴��־�ļ���Ĭ����Ӧ�÷������� log �ļ������� Tomcat ���� [Tomcat ��װĿ¼ ]/logs ����ᷢ����ʾ�㣺�����ǣ�org.springframework.beans.factory.BeanCreationException: Error creating bean with name ��sf�� defined in ServletContextresource [/WEB-INF/applicationContext.xml]: Initialization of bean failed; nested exception isorg.hibernate.HibernateException: could not configure from URL: file:src/hibernate.cfg.xmlorg.hibernate.HibernateException: could not configure from URL: file:src/hibernate.cfg.xml������������������.Caused by: java.io.FileNotFoundException: src\hibernate.cfg.xml�����ǣ�org.springframework.beans.factory.BeanCreationException: Error creating bean with name ��sessionFactory�� defined inServletContext resource [/WEB-INF/applicationContext.xml]: Initialization of bean failed; nested exception isorg.hibernate.MappingException: Resource: com/mcc/coupon/model/UserRole.hbm.xml not foundorg.hibernate.MappingException: Resource: com/mcc/coupon/model/UserRole.hbm.xml not foundȻ�����֪��ԭ������Ϊ�����ļ��Ľ������˴������ͨ�� Web ҳ���ǿ��������ġ�������ǳ־û�Ӱ���ļ����Ĵ��󣻵�����û�б���������Ȼ����Ҫ�Ĺ��ܾ��޷�ʹ���ˡ�
����������������������������������������������������������������������������������-
����StandardWrapperValve[action]: Servlet.service() for servlet action threw exception
javax.servlet.jsp.JspException: Cannot retrieve mapping for action /settlementTypeManage
���ߣ�      type Status report      message Servlet action is not available      description The requested resource (Servlet action is not available) is not available.
ԭ�� ͬ ��
����������������������������������������������������������������������������������-
����StandardWrapperValve[jsp]: Servlet.service() for servlet jsp threw exceptionjava.lang.ClassNotFoundException: org.apache.struts.taglib.bean.CookieTei����������������
org.apache.jasper.JasperException: Failed to load or instantiate TagExtraInfo class: org.apache.struts.taglib.bean.CookieTei